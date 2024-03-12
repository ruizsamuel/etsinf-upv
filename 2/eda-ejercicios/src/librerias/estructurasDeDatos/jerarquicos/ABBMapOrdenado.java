package librerias.estructurasDeDatos.jerarquicos;

import librerias.estructurasDeDatos.lineales.LEGListaConPI;
import librerias.estructurasDeDatos.modelos.ListaConPI;
import librerias.estructurasDeDatos.modelos.MapOrdenado;

public class ABBMapOrdenado<C extends Comparable<C>, V> implements MapOrdenado<C, V> {
    protected ABB<EntradaMap<C, V>> abb = new ABB();

    public ABBMapOrdenado() {
    }

    public boolean esVacio() {
        return this.abb.esVacio();
    }

    public int talla() {
        return this.abb.talla();
    }

    public V recuperar(C c) {
        EntradaMap<C, V> e = (EntradaMap)this.abb.recuperar(new EntradaMap(c, (Object)null));
        return e != null ? e.getValor() : null;
    }

    public V insertar(C c, V v) {
        EntradaMap<C, V> eC = new EntradaMap(c, (Object)null);
        EntradaMap<C, V> e = (EntradaMap)this.abb.recuperar(eC);
        this.abb.insertar(new EntradaMap(c, v));
        return e != null ? e.getValor() : null;
    }

    public V eliminar(C c) {
        EntradaMap<C, V> eC = new EntradaMap(c, (Object)null);
        EntradaMap<C, V> e = (EntradaMap)this.abb.recuperar(eC);
        this.abb.eliminar(new EntradaMap(c, (Object)null));
        return e != null ? e.getValor() : null;
    }

    public ListaConPI<C> claves() {
        ListaConPI<EntradaMap<C, V>> lpi = this.abb.toListaConPI();
        ListaConPI<C> res = new LEGListaConPI();
        lpi.inicio();

        while(!lpi.esFin()) {
            res.insertar((C) ((EntradaMap)lpi.recuperar()).getClave());
            lpi.siguiente();
        }

        return res;
    }

    public EntradaMap<C, V> recuperarEntradaMin() {
        return (EntradaMap)this.abb.recuperarMin();
    }

    public C recuperarMin() {
        return (C) ((EntradaMap)this.abb.recuperarMin()).getClave();
    }

    public EntradaMap<C, V> recuperarEntradaMax() {
        return (EntradaMap)this.abb.recuperarMax();
    }

    public C recuperarMax() {
        return (C) ((EntradaMap)this.abb.recuperarMax()).getClave();
    }

    public EntradaMap<C, V> sucesorEntrada(C c) {
        return (EntradaMap)this.abb.sucesor(new EntradaMap(c, (Object)null));
    }

    public C sucesor(C c) {
        EntradaMap<C, V> eSuc = (EntradaMap)this.abb.sucesor(new EntradaMap(c, (Object)null));
        return eSuc.getClave();
    }

    public EntradaMap<C, V> predecesorEntrada(C c) {
        return (EntradaMap)this.abb.predecesor(new EntradaMap(c, (Object)null));
    }

    public C predecesor(C c) {
        EntradaMap<C, V> ePred = (EntradaMap)this.abb.predecesor(new EntradaMap(c, (Object)null));
        return ePred.getClave();
    }

    public EntradaMap<C, V> eliminarEntradaMin() {
        return (EntradaMap)this.abb.eliminarMin();
    }

    public C eliminarMin() {
        return (C) ((EntradaMap)this.abb.eliminarMin()).getClave();
    }

    public String toString() {
        return this.abb.toStringInOrden();
    }
}