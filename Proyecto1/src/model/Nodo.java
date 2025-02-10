package model;

public class Nodo<T extends Atleta> {

    Atleta atleta;
    Nodo<Atleta> siguiente;

    public Nodo(Atleta atleta, Nodo<Atleta> siguiente) {
        this.atleta = atleta;
        this.siguiente = siguiente;
    }

    public Atleta getAtleta() {
        return atleta;
    }

    public void setAtleta(Atleta atleta) {
        this.atleta = atleta;
    }

    public Nodo<Atleta> getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Nodo<Atleta> siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return atleta + "\n";
    }

} // LLave de todo el cod
