package model;


public class Atleta {

    String nombre;
    int numeroAtleta;

    public Atleta() {
    }

    public Atleta(String nombre, int numeroAtleta) {
        this.nombre = nombre;
        this.numeroAtleta = numeroAtleta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNumeroAtleta() {
        return numeroAtleta;
    }

    public void setNumeroAtleta(int numeroAtleta) {
        this.numeroAtleta = numeroAtleta;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Número de atl: " + numeroAtleta + "\n";
    }

}
