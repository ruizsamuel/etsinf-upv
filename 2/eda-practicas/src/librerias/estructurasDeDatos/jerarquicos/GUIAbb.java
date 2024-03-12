package librerias.estructurasDeDatos.jerarquicos;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class GUIAbb extends JFrame implements ActionListener {
    private ABB<Integer> tree = new ABB();
    private HashMap<NodoABB<Integer>, Rectangle> nodeLocations = new HashMap();
    private HashMap<NodoABB<Integer>, Dimension> subtreeSizes = new HashMap();
    private boolean dirty = true;
    private int parent2child = 15;
    private int child2child = 24;
    private Dimension empty = new Dimension(0, 0);
    private FontMetrics fm = null;
    private JMenuBar barra;
    private JButton btnEquilibrado;
    private JButton btnDegenerado;
    private JButton btnAleatorio;
    private JButton btnInsertar;
    private JButton btnElim;
    private JButton btnEMin;
    private JButton btnReconstruir;
    private JButton btnIn;
    private JButton btnPre;
    private JButton btnPost;
    private JButton btnNiv;

    private GUIAbb() {
        super("Test de Arbol Binario de Busqueda");
        this.setLayout(new BorderLayout());
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());
        this.barra = new JMenuBar();
        this.barra.setBorder(BorderFactory.createBevelBorder(0));
        this.crearMenuSuperior();
        bottomPanel.add(this.barra, "North");
        this.barra = new JMenuBar();
        this.barra.setBorder(BorderFactory.createBevelBorder(0));
        this.crearMenuInferior();
        bottomPanel.add(this.barra, "South");
        this.add(bottomPanel, "South");
        this.setSize(840, 600);
        this.setLocationRelativeTo((Component)null);
        this.setVisible(true);
        this.repaint();
    }

    private void crearMenuSuperior() {
        this.barra.add(new JSeparator(0));
        this.btnEquilibrado = this.crearBoton("Generar ABB equilibrado", Color.black);
        this.btnDegenerado = this.crearBoton("Generar ABB degenerado", Color.black);
        this.btnAleatorio = this.crearBoton("Generar ABB aleatorio", Color.black);
        this.barra.add(new JSeparator(0));
        this.btnElim = this.crearBoton("Eliminar", Color.red);
        this.btnEMin = this.crearBoton("EliminarMin", Color.red);
        this.btnInsertar = this.crearBoton("Insertar", Color.darkGray);
        this.barra.add(new JSeparator(0));
    }

    private void crearMenuInferior() {
        this.barra.add(new JSeparator(0));
        this.btnReconstruir = this.crearBoton("Reconstruir equilibrado", Color.blue);
        this.barra.add(new JSeparator(0));
        this.btnIn = this.crearBoton("In-Orden", Color.GREEN.darker());
        this.btnPre = this.crearBoton("Pre-Orden", Color.GREEN.darker());
        this.btnPost = this.crearBoton("Post-Orden", Color.GREEN.darker());
        this.btnNiv = this.crearBoton("Por niveles", Color.GREEN.darker());
        this.barra.add(new JSeparator(0));
    }

    private JButton crearBoton(String texto, Color c) {
        JButton aux = new JButton(texto);
        aux.setForeground(c);
        aux.addActionListener(this);
        this.barra.add(aux);
        return aux;
    }

    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getSource() == this.btnEquilibrado) {
                this.generarABBEquilibrado();
            } else if (e.getSource() == this.btnDegenerado) {
                this.generarABBDegenerado();
            } else if (e.getSource() == this.btnAleatorio) {
                this.generarABBAleatorio();
            } else {
                Integer s;
                if (e.getSource() == this.btnElim) {
                    s = this.leerNodo("Escribe el dato a eliminar");
                    if (s != null) {
                        this.tree.eliminar(s);
                    }
                } else if (e.getSource() == this.btnEMin) {
                    s = (Integer)this.tree.eliminarMin();
                    JOptionPane.showMessageDialog(this, "Mínimo = " + s.toString(), "Resultado", 1);
                } else if (e.getSource() == this.btnInsertar) {
                    s = this.leerNodo("Escribe el dato a insertar");
                    if (s != null) {
                        this.tree.insertar(s);
                    }
                } else if (e.getSource() == this.btnReconstruir) {
                    this.tree.reconstruirEquilibrado();
                } else if (e.getSource() == this.btnIn) {
                    JOptionPane.showMessageDialog(this, this.tree.toStringInOrden(), "Recorrido In-Orden", -1);
                } else if (e.getSource() == this.btnPre) {
                    JOptionPane.showMessageDialog(this, this.tree.toStringPreOrden(), "Recorrido Pre-Orden", -1);
                } else if (e.getSource() == this.btnPost) {
                    JOptionPane.showMessageDialog(this, this.tree.toStringPostOrden(), "Recorrido Post-Orden", -1);
                } else if (e.getSource() == this.btnNiv) {
                    JOptionPane.showMessageDialog(this, this.tree.toStringPorNiveles(), "Recorrido por niveles", -1);
                }
            }
        } catch (Exception var4) {
            JOptionPane.showMessageDialog(this, var4, "Excepción detectada:", 0);
        }

        this.dirty = true;
        this.repaint();
    }

    private void generarABBEquilibrado() {
        this.tree = new ABB();
        int[] v = this.generarVectorAleatorio(15);
        Arrays.sort(v);
        this.insertarEquilibrado(v, 0, v.length - 1);
    }

    private void generarABBDegenerado() {
        this.tree = new ABB();
        int[] v = this.generarVectorAleatorio(10);
        Arrays.sort(v);

        for(int i = 0; i < v.length; ++i) {
            this.tree.insertar(v[i]);
        }

    }

    private void generarABBAleatorio() {
        Random rnd = new Random();

        do {
            this.tree = new ABB();
            int talla = rnd.nextInt(26) + 5;
            int[] v = this.generarVectorAleatorio(talla);

            for(int i = 0; i < v.length; ++i) {
                this.tree.insertar(v[i]);
            }
        } while(altura(this.tree.raiz) > 10);

    }

    private static <E> int altura(NodoABB<E> actual) {
        if (actual == null) {
            return 0;
        } else {
            return actual.izq == null && actual.der == null ? 0 : 1 + Math.max(altura(actual.izq), altura(actual.der));
        }
    }

    private int[] generarVectorAleatorio(int talla) {
        Random rnd = new Random();
        boolean[] exists = new boolean[100];
        int[] v = new int[talla];
        int i = 0;

        while(i < v.length) {
            v[i] = rnd.nextInt(100);
            if (!exists[v[i]]) {
                exists[v[i]] = true;
                ++i;
            }
        }

        return v;
    }

    private void insertarEquilibrado(int[] v, int izq, int der) {
        if (izq <= der) {
            int m = (izq + der) / 2;
            this.tree.insertar(v[m]);
            this.insertarEquilibrado(v, izq, m - 1);
            this.insertarEquilibrado(v, m + 1, der);
        }

    }

    private Integer leerNodo(String texto) {
        Integer n = null;

        String c;
        do {
            c = JOptionPane.showInputDialog(texto);
            if (c != null) {
                try {
                    n = Integer.parseInt(c);
                } catch (NumberFormatException var5) {
                    JOptionPane.showMessageDialog(this, "No has escrito un n úmero entero válido", "Error", 0);
                }
            }
        } while(n == null && c != null);

        return n;
    }

    private void calculateLocations() {
        this.nodeLocations.clear();
        this.subtreeSizes.clear();
        NodoABB<Integer> root = this.tree.raiz;
        if (root != null) {
            this.calculateSubtreeSize(root);
            this.calculateLocation(root, Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
        }

    }

    private Dimension calculateSubtreeSize(NodoABB<Integer> n) {
        if (n == null) {
            return new Dimension(0, 0);
        } else {
            Dimension ld = this.calculateSubtreeSize(n.izq);
            Dimension rd = this.calculateSubtreeSize(n.der);
            int h = 2 * this.fm.getHeight() + this.parent2child + Math.max(ld.height, rd.height);
            int w = ld.width + this.child2child + rd.width;
            Dimension d = new Dimension(w, h);
            this.subtreeSizes.put(n, d);
            return d;
        }
    }

    private void calculateLocation(NodoABB<Integer> n, int left, int right, int top) {
        if (n != null) {
            Dimension ld = (Dimension)this.subtreeSizes.get(n.izq);
            if (ld == null) {
                ld = this.empty;
            }

            Dimension rd = (Dimension)this.subtreeSizes.get(n.der);
            if (rd == null) {
                rd = this.empty;
            }

            int center = 0;
            if (right != Integer.MAX_VALUE) {
                center = right - rd.width - this.child2child / 2;
            } else if (left != Integer.MAX_VALUE) {
                center = left + ld.width + this.child2child / 2;
            }

            int width = this.fm.stringWidth("" + n.dato);
            Rectangle r = new Rectangle(center - width / 2 - 3, top + 25, width + 6, this.fm.getHeight());
            this.nodeLocations.put(n, r);
            this.calculateLocation(n.izq, Integer.MAX_VALUE, center - this.child2child / 2, top + 2 * this.fm.getHeight() + this.parent2child);
            this.calculateLocation(n.der, center + this.child2child / 2, Integer.MAX_VALUE, top + 2 * this.fm.getHeight() + this.parent2child);
        }
    }

    private void drawTree(Graphics2D g, NodoABB<Integer> n, int px, int py, int yoffs) {
        if (n != null) {
            Rectangle r = (Rectangle)this.nodeLocations.get(n);
            g.draw(r);
            Font ant = g.getFont();
            g.setFont(new Font("Serif", 0, 10));
            g.drawString("" + n.dato, r.x + 3, r.y + yoffs);
            if (px != Integer.MAX_VALUE) {
                g.drawLine(px, py, r.x + r.width / 2, r.y);
            }

            g.setFont(ant);
            this.drawTree(g, n.izq, r.x + r.width / 2, r.y + r.height, yoffs);
            this.drawTree(g, n.der, r.x + r.width / 2, r.y + r.height, yoffs);
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        this.fm = g.getFontMetrics();
        Font ant = g.getFont();
        g.setFont(new Font("Monospaced", 1, 12));
        g.drawString(String.format("Tamaño        = %d", this.tree.talla()), 10, 44);
        int aO = this.alturaOptima(this.tree.talla());
        int aOb = aO > 0 ? aO - 1 : 0;
        int aR = altura(this.tree.raiz);
        g.drawString(String.format("Altura optima = %d", aOb), 10, 60);
        if (aOb != aR) {
            g.setColor(Color.RED);
        } else {
            g.setColor(Color.GREEN.darker());
        }

        g.drawString(String.format("Altura real   = %d", aR), 10, 76);
        g.setColor(Color.BLACK);
        g.setFont(ant);
        if (this.dirty) {
            this.calculateLocations();
            this.dirty = false;
        }

        Graphics2D g2d = (Graphics2D)g;
        RenderingHints rh = g2d.getRenderingHints();
        rh.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g2d.translate(this.getWidth() / 2, this.parent2child);
        this.drawTree(g2d, this.tree.raiz, Integer.MAX_VALUE, Integer.MAX_VALUE, this.fm.getLeading() + this.fm.getAscent());
        this.fm = null;
    }

    public static void main(String[] args) {
        GUIAbb app = new GUIAbb();
        app.setDefaultCloseOperation(3);
    }

    private int alturaOptima(int t) {
        return t == 0 ? 0 : (int)Math.ceil(Math.log((double)(t + 1)) / Math.log(2.0));
    }
}