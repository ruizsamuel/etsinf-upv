package practica1;
/**
 * class FiguresGroup.
 * 
 * @author LTP 
 * @version 2020-21
 */

public class FiguresGroup {
    private static final int NUM_FIGURES = 10;
    private Figure[] figuresList = new Figure[NUM_FIGURES];
    private int numF = 0;
    
    public void add(Figure f) { figuresList[numF++] = f; }
    
    public String toString() {
        String s = "";
        for (int i = 0; i < numF; i++) {
            s += "\n" + figuresList[i];
        }
        return s;
    }
    
    public boolean equals (Object o) {
    	if (!(o instanceof FiguresGroup)) { return false; }
    	FiguresGroup f = (FiguresGroup) o;
    	if (f.numF != numF) { return false; }
    	return included(f);
    }
    
    private boolean found(Figure f) {
        for (int i = 0; i < numF; i++) {
        	if (figuresList[i].equals(f)) return true;
        }
        return false;
    }
    
    private boolean included(FiguresGroup g) {
		for (int i = 0; i < g.numF; i++) {
        	if (!found(g.figuresList[i])) return false;
        }
		return true;
    }
    
    public double area() {
    	double area = 0;
    	for (int i = 0; i < numF; i++) {
    		area += figuresList[i].area();
    	}
    	return area;
    }
    
    public Figure greatestFigure() {
    	Figure g = null;
    	for (int i = 0; i < numF; i++) {
    		if (g == null || figuresList[i].area() > g.area()) {
    			g = figuresList[i];
    		}
    	}
    	return g;
    }
}