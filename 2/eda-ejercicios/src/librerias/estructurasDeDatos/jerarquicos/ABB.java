package librerias.estructurasDeDatos.jerarquicos;

import java.io.PrintStream;
import librerias.estructurasDeDatos.lineales.ArrayCola;
import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.Cola;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class ABB<E extends Comparable<E>> {
    protected NodoABB<E> raiz = null;

    public ABB() {
    }

    public boolean esVacio() {
        return this.raiz == null;
    }

    public int altura() {
        return this.altura(this.raiz);
    }

    protected int altura(NodoABB<E> actual) {
        return actual == null ? -1 : Math.max(this.altura(actual.izq), this.altura(actual.der)) + 1;
    }

    public int talla() {
        return this.talla(this.raiz);
    }

    protected int talla(NodoABB<E> actual) {
        return actual == null ? 0 : actual.talla;
    }

    public E recuperar(E e) {
        NodoABB<E> res = this.recuperar(e, this.raiz);
        return res == null ? null : (E) res.dato;
    }

    protected NodoABB<E> recuperar(E e, NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual != null) {
            int resC = ((Comparable)actual.dato).compareTo(e);
            if (resC > 0) {
                res = this.recuperar(e, actual.izq);
            } else if (resC < 0) {
                res = this.recuperar(e, actual.der);
            }
        }

        return res;
    }

    public void insertar(E e) {
        this.raiz = this.insertar(e, this.raiz);
    }

    protected NodoABB<E> insertar(E e, NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual != null) {
            int resC = ((Comparable)actual.dato).compareTo(e);
            if (resC > 0) {
                actual.izq = this.insertar(e, actual.izq);
            } else if (resC < 0) {
                actual.der = this.insertar(e, actual.der);
            } else {
                actual.dato = e;
            }

            actual.talla = 1 + this.talla(actual.izq) + this.talla(actual.der);
        } else {
            res = new NodoABB(e);
        }

        return res;
    }

    public void eliminar(E e) {
        this.raiz = this.eliminar(e, this.raiz);
    }

    protected NodoABB<E> eliminar(E e, NodoABB<E> actual) {
        if (actual != null) {
            int resC = ((Comparable)actual.dato).compareTo(e);
            if (resC > 0) {
                actual.izq = this.eliminar(e, actual.izq);
            } else if (resC < 0) {
                actual.der = this.eliminar(e, actual.der);
            } else {
                if (actual.izq == null) {
                    return actual.der;
                }

                if (actual.der == null) {
                    return actual.izq;
                }

                actual.dato = this.recuperarMin(actual.der).dato;
                actual.der = this.eliminarMin(actual.der);
            }

            actual.talla = 1 + this.talla(actual.izq) + this.talla(actual.der);
        }

        return actual;
    }

    public E recuperarMin() {
        return (E) this.recuperarMin(this.raiz).dato;
    }

    protected NodoABB<E> recuperarMin(NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual.izq != null) {
            res = this.recuperarMin(actual.izq);
        }

        return res;
    }

    public E recuperarMax() {
        return (E) this.recuperarMax(this.raiz).dato;
    }

    protected NodoABB<E> recuperarMax(NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual.der != null) {
            res = this.recuperarMin(actual.der);
        }

        return res;
    }

    public E eliminarMin() {
        E res = this.recuperarMin();
        this.raiz = this.eliminarMin(this.raiz);
        return res;
    }

    protected NodoABB<E> eliminarMin(NodoABB<E> actual) {
        NodoABB<E> res = actual;
        if (actual.izq != null) {
            actual.izq = this.eliminarMin(actual.izq);
            --actual.talla;
        } else {
            res = actual.der;
        }

        return res;
    }

    public E sucesor(E e) {
        NodoABB<E> res = this.sucesor(e, this.raiz);
        return res == null ? null : (E) res.dato;
    }

    protected NodoABB<E> sucesor(E e, NodoABB<E> actual) {
        NodoABB<E> res = null;
        if (actual != null) {
            int resC = ((Comparable)actual.dato).compareTo(e);
            if (resC > 0) {
                res = this.sucesor(e, actual.izq);
                if (res == null) {
                    res = actual;
                }
            } else {
                res = this.sucesor(e, actual.der);
            }
        }

        return res;
    }

    public E predecesor(E e) {
        NodoABB<E> res = this.predecesor(e, this.raiz);
        return res == null ? null : (E) res.dato;
    }

    protected NodoABB<E> predecesor(E e, NodoABB<E> actual) {
        NodoABB<E> res = null;
        if (actual != null) {
            int resC = ((Comparable)actual.dato).compareTo(e);
            if (resC <= 0) {
                res = this.predecesor(e, actual.der);
                if (res == null) {
                    res = actual;
                }
            } else {
                res = this.predecesor(e, actual.izq);
            }
        }

        return res;
    }

    public E seleccionar(int k) {
        return (E) this.seleccionar(k, this.raiz).dato;
    }

    protected NodoABB<E> seleccionar(int k, NodoABB<E> actual) {
        int tallaI = this.talla(actual.izq);
        if (k == tallaI + 1) {
            return actual;
        } else {
            return k <= tallaI ? this.seleccionar(k, actual.izq) : this.seleccionar(k - tallaI - 1, actual.der);
        }
    }

    public E recuperarI(E e) {
        NodoABB<E> aux = this.raiz;

        while(aux != null) {
            int resC = ((Comparable)aux.dato).compareTo(e);
            if (resC == 0) {
                return (E) aux.dato;
            }

            if (resC > 0) {
                aux = aux.izq;
            } else {
                aux = aux.der;
            }
        }

        return null;
    }

    protected NodoABB<E> eliminarMinI(NodoABB<E> actual) {
        NodoABB<E> aux = actual;

        NodoABB padreAux;
        for(padreAux = null; aux.izq != null; aux = aux.izq) {
            --aux.talla;
            padreAux = aux;
        }

        if (padreAux == null) {
            actual = actual.der;
        } else {
            padreAux.izq = aux.der;
        }

        return actual;
    }

    protected NodoABB<E> eliminarMin(NodoABB<E> actual, NodoABB<E> nodoMin) {
        NodoABB<E> aux = actual;

        NodoABB padreAux;
        for(padreAux = null; aux.izq != null; aux = aux.izq) {
            --aux.talla;
            padreAux = aux;
        }

        nodoMin.dato = aux.dato;
        if (padreAux == null) {
            actual = actual.der;
        } else {
            padreAux.izq = aux.der;
        }

        return actual;
    }

    public E eliminarMin2() {
        NodoABB<E> nodoMin = new NodoABB((Object)null);
        this.raiz = this.eliminarMin(this.raiz, nodoMin);
        return (E) nodoMin.dato;
    }

    public String toStringPostOrden() {
        StringBuilder res = (new StringBuilder()).append("[");
        if (this.raiz != null) {
            this.toStringPostOrden(this.raiz, res);
        }

        return res.append("]").toString();
    }

    protected void toStringPostOrden(NodoABB<E> actual, StringBuilder res) {
        if (actual.izq != null) {
            this.toStringPostOrden(actual.izq, res);
            res.append(", ");
        }

        if (actual.der != null) {
            this.toStringPostOrden(actual.der, res);
            res.append(", ");
        }

        res.append(((Comparable)actual.dato).toString());
    }

    public String toStringPreOrden() {
        StringBuilder res = (new StringBuilder()).append("[");
        if (this.raiz != null) {
            this.toStringPreOrden(this.raiz, res);
        }

        return res.append("]").toString();
    }

    protected void toStringPreOrden(NodoABB<E> actual, StringBuilder res) {
        res.append(((Comparable)actual.dato).toString());
        if (actual.izq != null) {
            res.append(", ");
            this.toStringPreOrden(actual.izq, res);
        }

        if (actual.der != null) {
            res.append(", ");
            this.toStringPreOrden(actual.der, res);
        }

    }

    public String toStringInOrden() {
        StringBuilder res = (new StringBuilder()).append("[");
        if (this.raiz != null) {
            this.toStringInOrden(this.raiz, res);
        }

        return res.append("]").toString();
    }

    protected void toStringInOrden(NodoABB<E> actual, StringBuilder res) {
        if (actual.izq != null) {
            this.toStringInOrden(actual.izq, res);
            res.append(", ");
        }

        res.append(((Comparable)actual.dato).toString());
        if (actual.der != null) {
            res.append(", ");
            this.toStringInOrden(actual.der, res);
        }

    }

    public String toStringPorNiveles() {
        if (this.raiz == null) {
            return "[]";
        } else {
            StringBuilder res = (new StringBuilder()).append("[");
            Cola<NodoABB<E>> q = new ArrayCola();
            q.encolar(this.raiz);

            while(!q.esVacia()) {
                NodoABB<E> actual = (NodoABB)q.desencolar();
                res.append(((Comparable)actual.dato).toString());
                res.append(", ");
                if (actual.izq != null) {
                    q.encolar(actual.izq);
                }

                if (actual.der != null) {
                    q.encolar(actual.der);
                }
            }

            res.setLength(res.length() - 2);
            return res.append("]").toString();
        }
    }

    public ListaConPI<E> toListaConPI() {
        ListaConPI<E> res = new LEGListaConPI();
        if (this.raiz != null) {
            this.toListaConPI(this.raiz, res);
        }

        return res;
    }

    protected void toListaConPI(NodoABB<E> actual, ListaConPI<E> res) {
        if (actual.izq != null) {
            this.toListaConPI(actual.izq, res);
        }

        res.insertar((E) actual.dato);
        if (actual.der != null) {
            this.toListaConPI(actual.der, res);
        }

    }

    public boolean sonEquivalentes(ListaConPI<NodoABB<E>> postorden) {
        if (this.raiz == null) {
            return false;
        } else {
            postorden.inicio();
            return this.sonEquivalentes(this.raiz, postorden);
        }
    }

    protected boolean sonEquivalentes(NodoABB<E> actual, ListaConPI<NodoABB<E>> postorden) {
        PrintStream var10000 = System.out;
        String var10001 = actual.toString();
        var10000.println("Actual: " + var10001 + " elemento lista: " + ((NodoABB)postorden.recuperar()).dato);
        if (actual.izq != null && !postorden.esFin() && ((Comparable)actual.dato).compareTo((Comparable)((NodoABB)postorden.recuperar()).dato) > 0) {
            System.out.println(" Entra izq");
            this.sonEquivalentes(actual.izq, postorden);
        }

        if (actual.der != null && !postorden.esFin() && ((Comparable)actual.dato).compareTo((Comparable)((NodoABB)postorden.recuperar()).dato) < 0) {
            System.out.println(" Entra der");
            this.sonEquivalentes(actual.der, postorden);
        }

        if (!postorden.esFin() && ((Comparable)actual.dato).compareTo((Comparable)((NodoABB)postorden.recuperar()).dato) == 0) {
            System.out.println("Son iguales, avanza lista");
            postorden.siguiente();
            return true;
        } else {
            System.out.println("No son iguales.");
            postorden.fin();
            return false;
        }
    }

    public boolean esPostOrden(ListaConPI<E> l) {
        if (this.raiz == null) {
            return false;
        } else {
            l.inicio();
            this.esPostOrdenEx(this.raiz, l);
            return l.esFin();
        }
    }

    protected boolean esPostOrdenMario(NodoABB<E> n, ListaConPI<E> l) {
        if (l.talla() != this.talla()) {
            return false;
        } else if (n == null) {
            return true;
        } else if (this.esPostOrdenMario(n.izq, l) && this.esPostOrdenMario(n.der, l) && !l.esFin()) {
            if (!((Comparable)l.recuperar()).equals(n.dato)) {
                return false;
            } else {
                l.siguiente();
                return true;
            }
        } else {
            return false;
        }
    }

    protected void esPostOrdenExV2(NodoABB<E> n, ListaConPI<E> l) {
        if (n != null) {
            if (!l.esFin() && n != null) {
                this.esPostOrdenExV2(n.izq, l);
            }

            if (!l.esFin() && n != null) {
                this.esPostOrdenExV2(n.der, l);
            }

            if (!l.esFin() && n != null && ((Comparable)l.recuperar()).compareTo((Comparable)n.dato) == 0) {
                l.siguiente();
            }

        }
    }

    protected void esPostOrdenEx(NodoABB<E> raiz, ListaConPI<E> l) {
        if (l.talla() == this.talla()) {
            if (raiz.izq != null && !l.esFin() && ((Comparable)raiz.dato).compareTo((Comparable)l.recuperar()) > 0) {
                this.esPostOrden(raiz.izq, l);
            }

            if (raiz.der != null && !l.esFin() && ((Comparable)raiz.dato).compareTo((Comparable)l.recuperar()) < 0) {
                this.esPostOrden(raiz.der, l);
            }

            if (!l.esFin() && ((Comparable)raiz.dato).compareTo((Comparable)l.recuperar()) == 0) {
                l.siguiente();
            }

        }
    }

    protected void esPostOrden(NodoABB<E> n, ListaConPI<E> l) {
        if (n != null && !l.esFin()) {
            E d = (E) n.dato;
            if (d.compareTo((E) l.recuperar()) > 0) {
                this.esPostOrden(n.izq, l);
            }

            if (d.compareTo((E) l.recuperar()) < 0) {
                this.esPostOrden(n.der, l);
            }

            if (d.compareTo((E) l.recuperar()) == 0) {
                l.siguiente();
            }

            if (l.esFin()) {
                return;
            }
        }

    }

    protected void esPostOrdenDup(NodoABB<E> n, ListaConPI<E> l) {
        if (n != null) {
            if (n.izq == null && n.der == null && ((Comparable)n.dato).compareTo((Comparable)l.recuperar()) == 0) {
                l.siguiente();
            }

            this.esPostOrdenDup(n.izq, l);
            this.esPostOrdenDup(n.der, l);
            if (((Comparable)n.dato).compareTo((Comparable)l.recuperar()) == 0) {
                l.siguiente();
            }
        }

    }

    protected void esPostOrdenV(NodoABB<E> n, ListaConPI<E> l) {
        if (n.izq != null && !l.esFin() && ((Comparable)n.dato).compareTo((Comparable)l.recuperar()) > 0) {
            this.esPostOrdenV(n.izq, l);
        }

        if (n.der != null && !l.esFin() && ((Comparable)n.dato).compareTo((Comparable)l.recuperar()) < 0) {
            this.esPostOrdenV(n.der, l);
        }

        if (!l.esFin() && ((Comparable)n.dato).compareTo((Comparable)l.recuperar()) == 0) {
            l.siguiente();
        }

    }

    public boolean esPostOrdenM(ListaConPI<E> lista) {
        if (this.raiz == null) {
            return false;
        } else {
            lista.inicio();
            this.esPostOrdenM(this.raiz, lista);
            return lista.esFin();
        }
    }

    private boolean esPostOrdenM(NodoABB<E> nodo, ListaConPI<E> lista) {
        if (lista.talla() != this.talla()) {
            return false;
        } else if (nodo.izq == null || !lista.esFin() && this.esPostOrdenM(nodo.izq, lista)) {
            if (nodo.der != null && (lista.esFin() || !this.esPostOrdenM(nodo.der, lista))) {
                return false;
            } else if (!lista.esFin() && ((Comparable)nodo.dato).compareTo((Comparable)lista.recuperar()) == 0) {
                lista.siguiente();
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    protected void esPostOrdenAngel(NodoABB<E> n, ListaConPI<E> l) {
        if (n != null) {
            this.esPostOrdenV(n.izq, l);
            this.esPostOrdenV(n.der, l);
            if (l.esFin()) {
                l.inicio();
                return;
            }

            if (((Comparable)n.dato).compareTo((Comparable)l.recuperar()) == 0) {
                l.siguiente();
            }
        }

    }

    protected void esPostOrdenAlejandro(NodoABB<E> n, ListaConPI<E> l) {
        if (l.talla() == this.talla()) {
            if (n.izq != null) {
                this.esPostOrdenAlejandro(n.izq, l);
            }

            if (n.der != null) {
                this.esPostOrdenAlejandro(n.der, l);
            }

            if (((Comparable)n.dato).equals(l.recuperar())) {
                l.siguiente();
            }

        }
    }
}