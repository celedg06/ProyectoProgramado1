package model;

import javax.swing.JOptionPane;

public class ListaCircular<T extends Atleta> {

    Nodo<Atleta> ultimo;

    public ListaCircular() {
        this.ultimo = null;
    }

    public boolean esVacia() {
        return (this.ultimo == null);
    }

    // Preguntarle al profe por el <T>
    public ListaCircular<T> agregarAtleta(String nombre, int num) {
        Atleta atletaNuevo = new Atleta(nombre, num);
        Nodo<Atleta> nodoNuevo = new Nodo<Atleta>(atletaNuevo, null);

        if (esVacia()) {
            ultimo = nodoNuevo;
            ultimo.siguiente = ultimo;
            JOptionPane.showMessageDialog(null, "Atleta: " + atletaNuevo + "Ingresado correctamente");
        } else {
            nodoNuevo.siguiente = ultimo.siguiente;
            ultimo.siguiente = nodoNuevo;
            ultimo = nodoNuevo;
            JOptionPane.showMessageDialog(null, "Atleta: " + atletaNuevo + "I-ngresado correctamente");
        }

        return this;
    }

    public String mostrarLista() {
        if (esVacia()) {
            return "Lista vacía";
        }
        Nodo<Atleta> nodoAnterior = ultimo;
        Nodo<Atleta> nodoSiguiente;
        Nodo<Atleta> nodoActual = ultimo.siguiente;

        String infoLista = "";
        do {
            nodoSiguiente = nodoActual.siguiente;
            infoLista += "Detrás del atleta: " + nodoActual.atleta.getNumeroAtleta() + " va el: "
                    + nodoSiguiente.atleta.getNumeroAtleta() + ", delante del: " + nodoActual.atleta.getNumeroAtleta()
                    + " va el: " + nodoAnterior.atleta.getNumeroAtleta() + ".\n";

            nodoAnterior = nodoActual;
            nodoActual = nodoSiguiente;
        } while (nodoActual != ultimo.siguiente);
        return infoLista;
    }

    public boolean buscarNodo(int dato) {
        if (esVacia()) {
            return false;
        }

        Nodo<Atleta> nodoActual = ultimo.siguiente;

        do {
            if (nodoActual.atleta.getNumeroAtleta() == dato) {
                return true;
            }
            nodoActual = nodoActual.siguiente;
        } while (nodoActual != ultimo.siguiente);
        return false;
    }

    public boolean eliminarNodo(int num) {
        Nodo<Atleta> nodoActual = ultimo;
        boolean encontrado = false;

        while (nodoActual.siguiente != ultimo && !encontrado) {
            encontrado = (nodoActual.siguiente.atleta.getNumeroAtleta() == num);
            if (!encontrado) {
                nodoActual = nodoActual.siguiente;
            }
        }
        encontrado = (nodoActual.siguiente.atleta.getNumeroAtleta() == num);
        if (encontrado) {
            Nodo<Atleta> nodoAux = nodoActual.siguiente;
            // Si es el único nodo que existe
            if (ultimo == ultimo.siguiente) {
                ultimo = null;
            } else {
                if (nodoAux == ultimo) {
                    ultimo = nodoActual;
                }
                nodoActual.siguiente = nodoAux.siguiente;
            }
            nodoAux = null;
        }
        return encontrado;
    }

    public boolean correr() {
        // Ahora lo termino :)
        return false;
    }

    
       public boolean modificarNombre(int numAtleta, String nombre) {
        if (esVacia()) {
            JOptionPane.showMessageDialog(null, "La lista está vacía");
            return false;
        }
        Nodo<Atleta> nodoActual = ultimo.siguiente;

        do {
            if (nodoActual.atleta.getNumeroAtleta() == numAtleta) {
                nodoActual.atleta.setNombre(nombre);
                 JOptionPane.showMessageDialog(null, "Modificación exitosa");
                return true;
            }
            nodoActual = nodoActual.siguiente;

        } while (nodoActual != ultimo.siguiente);

        JOptionPane.showMessageDialog(null, "No se encontró al atleta con el número especificado");
        return false;

    }


}// Llave de todo el cod
