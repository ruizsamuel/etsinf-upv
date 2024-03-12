package practica1;

public class Instructor extends Swimmer {
    public Instructor(int id, Pool p) {super(id,p);}
    void swims() throws InterruptedException {pool.instructorSwims();}
    void rests() throws InterruptedException {pool.instructorRests();}
}