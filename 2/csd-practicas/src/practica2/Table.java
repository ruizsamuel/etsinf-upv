package practica2;

public interface Table {
    void begin(int id);
    void ponder(int id);
    void takeR(int id) throws InterruptedException;
    void takeL(int id) throws InterruptedException;
    void eat(int id);
    void dropR(int id);
    void dropL(int id);
    void end(int id);
    void takeLR(int id) throws InterruptedException;
    void enter(int id) throws InterruptedException;
    void exit(int id);
}
