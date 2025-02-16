package model;

import java.util.Random;

import javax.swing.JOptionPane;

public class ListaCircular<T extends Atleta> {

    Nodo<Atleta> ultimo;

    public ListaCircular() {
        this.ultimo = null;
    }

    public boolean esVacia() {
        return (this.ultimo == null);
    }

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
            JOptionPane.showMessageDialog(null, "Atleta: " + atletaNuevo + "Ingresado correctamente");
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
                    + nodoAnterior.atleta.getNumeroAtleta() + ", delante del: " + nodoActual.atleta.getNumeroAtleta()
                    + " va el: " + nodoSiguiente.atleta.getNumeroAtleta() + ".\n";

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

    private Nodo<Atleta> obtenerPenultimo(Nodo<Atleta> ultimo) {
        Nodo<Atleta> actual = ultimo.siguiente;
        while (actual.siguiente != ultimo) {
            actual = actual.siguiente;
        }
        return actual;
    }

    public boolean correr() {
        if (esVacia() || ultimo.siguiente == ultimo) {
            JOptionPane.showMessageDialog(null, "Atletas insuficientes");
            return false;
        }

        StringBuilder mostrar = new StringBuilder();
        mostrar.append("Antes de correr:\n").append(mostrarLista()).append("\n");

        if (ultimo.siguiente != ultimo) {
            Nodo<Atleta> primero = ultimo.siguiente;  // Primer nodo actual
            Nodo<Atleta> nuevoUltimo = obtenerPenultimo(ultimo); // El que será el nuevo último

            nuevoUltimo.siguiente = ultimo; // Penúltimo ahora apunta al último
            ultimo.siguiente = primero; // Último (nuevo primero) apunta al viejo primero
            ultimo = nuevoUltimo; // El nuevo último ahora es el penúltimo original
        }

        mostrar.append("Después de correr: \n").append(mostrarLista());
        JOptionPane.showMessageDialog(null, mostrar.toString());

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

    public boolean pasarCompetidor(int numAtletaUno, int numAtletaDos) {
        if (esVacia()) {
            JOptionPane.showMessageDialog(null, "La lista está vacía");
            return false;
        }

        Nodo<Atleta> nodoActual = ultimo.siguiente;
        Nodo<Atleta> nodoAtletaUno = null;
        Nodo<Atleta> nodoAtletaDos = null;

        do {

            if (nodoActual.atleta.getNumeroAtleta() == numAtletaUno) {
                nodoAtletaUno = nodoActual;

            }

            if (nodoActual.atleta.getNumeroAtleta() == numAtletaDos) {
                nodoAtletaDos = nodoActual;
            }

            nodoActual = nodoActual.siguiente;

        } while (nodoActual != ultimo.siguiente);

        if (nodoAtletaUno == null || nodoAtletaDos == null) {
            JOptionPane.showMessageDialog(null, "Uno o ambos atletas no fueron encontrados");
            return false;
        }

        // Variable temp almacena el objeto Atleta que esta en el nodoAtletaUno
        Atleta temp = nodoAtletaUno.atleta;
        // El atleta 2 pasa a ser nodoAtletaUno
        nodoAtletaUno.atleta = nodoAtletaDos.atleta;
        // El atleta 1 que estaba en la variable temp se le asigna al nodoAtletaDos
        nodoAtletaDos.atleta = temp;

        JOptionPane.showMessageDialog(null,
                "Se intercambiaron las posiciones de los atletas " + numAtletaUno + " y " + numAtletaDos);
        return true;
    }

    public int contarAtletas() {
        if (esVacia()) {
            return 0;
        }

        int contador = 0;
        Nodo<Atleta> temp = ultimo.siguiente;

        do {
            contador++;
            temp = temp.siguiente;
        } while (temp != ultimo.siguiente);

        return contador;
    }

    public void simularCarrera() {

        if (esVacia() || ultimo.siguiente == ultimo) {
            JOptionPane.showMessageDialog(null, "No hay suficientes atletas para la carrera");
            return;
        }

        Random random = new Random();
        int vueltas = 3;
        Nodo<Atleta> temp = ultimo.siguiente;

        for (int i = 0; i < vueltas; i++) {
            JOptionPane.showMessageDialog(null, "Vuelta: " + (i + 1));
            int movimiento = random.nextInt(3) + 1;
            for (int j = 0; j < movimiento; j++) {
                temp = temp.siguiente;
            }

        }

        JOptionPane.showMessageDialog(null,
                "Carrera finalizada\nEl ganador de la carrera es el jugador: " + temp.atleta);
    }

}// Llave de todo el cod
