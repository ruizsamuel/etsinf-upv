#include <limits.h>
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <omp.h>

#define MAX_LEN 2500
#define MUTP    0.1      // Mutation probability

int *thread_calculated_diffs;

char **generate_data(int ns, int codlen) {
    char actg[]="ACTG";
    char *ref;
    int size, i, j, k, mut;
    char **samples, *tdata;
    double x;

    size = ns*(codlen*(1+MUTP)+1.5);
    samples = malloc(ns*sizeof(char *));
    tdata = malloc(size*sizeof(char));
    ref = malloc((codlen+1)*sizeof(char));
    if (samples==NULL || tdata==NULL || ref==NULL) {
        free(samples);
        free(tdata);
        free(ref);
        return NULL;
    }

    for (j=0; j<codlen; j++)
        ref[j] = actg[rand()%4];
    ref[codlen] = '\0';

    k = 0;
    for(i=0; i<ns; i++){
        samples[i] = &tdata[k];
        for (j=0; j<codlen; j++) {
            x = (double)rand()/RAND_MAX;
            if (x>MUTP) mut = 0;
            else mut = rand()%3+1;
            switch (mut) {
                case 1:  // delete
                    break;
                case 2:  // change
                    tdata[k++] = actg[rand()%4];
                    break;
                case 3:  // insert
                    tdata[k++] = actg[rand()%4];
                default:
                    tdata[k++] = ref[j];
            }
        }
        tdata[k++] = '\0';
    }
    free(ref);

    return samples;
}

void free_samples(char *samples[])
{
    free(samples[0]);
    free(samples);
}

void save_results(int ns, int codlen, int delta, int mindiff[], int maxdiff[], int nclose[]) {
    int i;
    FILE *fp; 
    fp = fopen( "results.txt", "w");
    fprintf(fp, "Samples:              %d\n", ns);
    fprintf(fp, "Code base length:     %d\n", codlen);
    fprintf(fp, "Closeness difference: %d\n\n", delta);
    fprintf(fp,"Sample  Min_diff  Max_diff  Num_close\n");
    fprintf(fp,"-------------------------------------\n");
    for (i=0; i<ns; i++)
        fprintf(fp,"%6d  %8d  %8d  %9d\n", i, mindiff[i], maxdiff[i], nclose[i]);
    fclose(fp);
}

void print_data(int ns, char *samples[]) {
    int i;
    for (i=0; i<ns; i++) 
        printf("%s\n", samples[i]);
}

// Compute the difference between two strings (Levenshtein distance)
#define MIN3(a,b,c) ( (a)<=(b) && (a)<=(c) ? (a) : (b)<=(c) ? (b) : (c) )
int difference(char s[],char t[]) { 
    int i,j,a,b,d[MAX_LEN];
    if ( t[0] == '\0' ) return strlen(s);
    for ( j = 1 ; t[j] != '\0' ; j++ ) d[j] = j + 1;
    a = 1; d[0] = s[0] == t[0] ? 0 : 1;
    i = 0;
    while ( s[i] != '\0' ) {
        for ( j = 1 ; t[j] != '\0' ; j++ ) {
            b = d[j];
            d[j] = ( s[i] == t[j] ? a : MIN3(d[j-1],a,b) + 1 );
            a = b;
        }
        i++;
        a = d[0]; d[0] = s[i] == t[0] ? i : a + 1;
    }
    return d[j-1];
}

/* Process samples
Input:
- ns: number of samples
- samples: array of samples
- delta: difference for closeness
Output:
- mindiff: the minimun difference from each sample to any other sample
- maxdiff: the maximim difference from each sample to any other sample
- nclose: the numer of samples that are close to each sample (difference < delta). 
*/
void process(int ns, char *samples[], int delta, int mindiff[], int maxdiff[], int nclose[]) {
    int i,j,d,
        nclose_i, // number of samples with index j>i that are close to sample i
        mind_i,   // min difference between sample i and any other sample j>i
        maxd_i;   // max difference between sample i and any other sample j>i

    for (i=0; i<ns; i++) {
        mindiff[i] = MAX_LEN+1;
        maxdiff[i] = 0;
        nclose[i] = 0;
    }
    #pragma omp parallel for private(j, d, nclose_i, mind_i, maxd_i) schedule(runtime)
    for (i=0; i<ns; i++) {
        nclose_i = 0;
        mind_i = MAX_LEN+1;
        maxd_i = 0;
        for (j=i+1; j<ns; j++) {
            d = difference(samples[i],samples[j]);
            thread_calculated_diffs[omp_get_thread_num()]++;
            // Update min and max differences for sample i
            if ( d < mind_i ) mind_i = d;
            if ( d > maxd_i ) maxd_i = d;
            // Update min and max differences for sample j
            if ( d < mindiff[j] )
                #pragma omp critical
                if (d < mindiff[j]) mindiff[j] = d;

            if ( d > maxdiff[j] )
                #pragma omp critical
                if (d > maxdiff[j]) maxdiff[j] = d;

            // Update close counts for samples i and j
            if ( d < delta ) {
                nclose_i++;
                #pragma omp atomic
                nclose[j]++;
            }
        }
        // Update close counts for sample i
        nclose[i] += nclose_i;
        // Update min and max differences for sample i
        if ( mind_i < mindiff[i] ) mindiff[i] = mind_i;
        if ( maxd_i > maxdiff[i] ) maxdiff[i] = maxd_i;
    }
}

int main(int argc, char *argv[]) { 
    int iarg,
        ns=200,       // Number of samples 
        codlen=900,   // Reference code length 
        delta,        // difference for closeness
        *mindiff,
        *maxdiff,
        *nclose;
    char **samples;

    double t1, t2;

    thread_calculated_diffs = (int *)malloc(omp_get_max_threads() * sizeof(int));

    for (int i = 0; i < omp_get_max_threads(); i++) {
        thread_calculated_diffs[i] = 0;
    }

    iarg = 1;
    if (iarg<argc) {
        ns = atoi(argv[iarg]);
        iarg++;
    }
    if (iarg<argc) {
        codlen = atoi(argv[iarg]);
        iarg++;
    }
    delta = codlen*2*(MUTP-7.0/6*MUTP*MUTP);

    samples = generate_data(ns,codlen);

    mindiff = malloc(ns*sizeof(int));
    maxdiff = malloc(ns*sizeof(int));
    nclose = malloc(ns*sizeof(int));
    if (samples==NULL || mindiff==NULL || maxdiff==NULL || nclose==NULL) {
        fprintf(stderr, "Could no allocate memory\n");
        free(samples);
        free(mindiff);
        free(maxdiff);
        free(nclose);
        return -1;
    }

    t1 = omp_get_wtime();
    process(ns, samples, delta, mindiff, maxdiff, nclose);
    t2 = omp_get_wtime();

    int max_thread = 0;
    int min_thread = INT_MAX;
    #pragma omp parallel reduction(max:max_thread) reduction(min:min_thread)
    {
        int n = thread_calculated_diffs[omp_get_thread_num()];
        max_thread = n;
        min_thread = n;

        printf("Thread %d: %d differences between samples\n", omp_get_thread_num(), n);
    }

    printf("Maximum number of differences computed by a single thread: %d\n", max_thread);
    printf("Minimum number of differences computed by a single thread: %d\n\n", min_thread);

    printf("El tiempo total de ejecución con %d hilos ha sido de %f segundos \n",omp_get_max_threads(), t2-t1);

    save_results(ns, codlen, delta, mindiff, maxdiff, nclose);

    free_samples(samples);
    free(mindiff);
    free(nclose);

    return 0;
}
