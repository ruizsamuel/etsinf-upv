package practica2;

public class BothOrNoneTable extends RegularTable {
    public BothOrNoneTable(StateManager state) {super(state);}

    public synchronized void takeLR(int id) throws InterruptedException{
        while (state.rightUsed(id) || state.leftUsed(id)) { state.wtakeLR(id); wait(); }
        state.takeLR(id);
    }
}
