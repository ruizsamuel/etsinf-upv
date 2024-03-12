package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.modelos.UFSet;

public class ForestUFSet implements UFSet {
    protected int[] elArray;

    public ForestUFSet(int n) {
        this.elArray = new int[n];

        for(int i = 0; i < this.elArray.length; ++i) {
            this.elArray[i] = -1;
        }

    }

    public int find(int i) {
        return this.elArray[i] < 0 ? i : (this.elArray[i] = this.find(this.elArray[i]));
    }

    public void union(int claseI, int claseJ) {
        if (this.elArray[claseI] == this.elArray[claseJ]) {
            this.elArray[claseI] = claseJ;
            int var10002 = this.elArray[claseJ]--;
        } else if (this.elArray[claseI] < this.elArray[claseJ]) {
            this.elArray[claseJ] = claseI;
        } else {
            this.elArray[claseI] = claseJ;
        }

    }

    public String toString() {
        String res = "";

        for(int i = 0; i < this.elArray.length; ++i) {
            res = res + String.format("%3d : %3d\n", i, this.elArray[i]);
        }

        return res;
    }
}