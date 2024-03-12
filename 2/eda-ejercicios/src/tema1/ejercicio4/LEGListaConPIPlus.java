package tema1.ejercicio4;

import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.lineales.NodoLEG;
import librerias.estructurasDeDatos.modelos.ListaConPI;

public class LEGListaConPIPlus<E> extends LEGListaConPI<E> implements ListaConPIPlus<E> {
    @Override
    public boolean contiene(E e) {
        inicio();
        return buscarSiguiente(e);
    }

    @Override
    public boolean eliminarPrimero(E e) {
        boolean res = contiene(e);
        if (res) eliminar();
        return res;
    }

    @Override
    public boolean eliminarUltimo(E e) {
        NodoLEG<E> ultimo;
        for (ultimo = null; buscarSiguiente(e); ultimo = ant) siguiente();
        if (ultimo == null) return false;
        else {
            ant = ultimo;
            eliminar();
            return true;
        }
    }

    @Override
    public boolean eliminarTodos(E e) {
        boolean res;
        for(res = false; buscarSiguiente(e); res = true) eliminar();
        return res;
    }

    @Override
    public void concatenar(ListaConPI<E> l) {
        fin();
        for (l.inicio(); !l.esFin(); l.siguiente()) insertar(l.recuperar());
    }

    @Override
    public void vaciar() {
        while (!esVacia()) eliminar();
    }

    @Override
    public void buscar(E e) {
        inicio();
        while (!esFin() && !recuperar().equals(e)) siguiente();
        // contiene(e);
    }

    @Override
    public void invertir() {
        if (esVacia()) return;
        inicio();
        E dato = recuperar();
        eliminar();
        invertir();
        insertar(dato);
    }

    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[ ");
        for (inicio(); !esFin(); siguiente()) res.append(recuperar()).append(" ");
        res.append("]");
        return res.toString();
    }

    public void moverAIzquierda() {
        if (esVacia()) return;
        ult.siguiente = pri.siguiente;
        pri.siguiente = pri.siguiente.siguiente;
        ult = ult.siguiente;
        ult.siguiente = null;
    }

    protected boolean buscarSiguiente(E e) {
        while (!esFin() && !recuperar().equals(e)) siguiente();
        return !esFin() || recuperar().equals(e);
    }
}