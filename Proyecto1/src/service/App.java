package service;

import javax.swing.JOptionPane;

import model.Atleta;
import model.ListaCircular;

public class App {
    public static void main(String[] args) throws Exception {

        String menu = "Menú\n1. Agregar Atleta\n2. Buscar Atleta\n3. Mostrar Posiciones\n4. Modificar Posiciones\n5. Eliminar Atleta\n6. Correr\n7. Salir";
        int op = 0;
        ListaCircular<Atleta> lista = new ListaCircular<>();
        String nombre;
        int num;

        do {
            try {
                op = Integer.parseInt(JOptionPane.showInputDialog(null, menu));
                switch (op) {
                    case 1:
                        try {
                            num = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número del atleta: "));
                            do {
                                nombre = JOptionPane.showInputDialog("Ingrese el nombre del atleta: ");
                                if (nombre == null || nombre.trim().isEmpty()) {
                                    JOptionPane.showMessageDialog(null,
                                            "El nombre no puede estar vacío. Inténtelo de nuevo.", "Error",
                                            JOptionPane.INFORMATION_MESSAGE);
                                }
                            } while (nombre == null || nombre.trim().isEmpty());

                            lista.agregarAtleta(nombre, num);
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null,
                                    "Error: Debe ingresar un número válido para el número del atleta", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 2:
                        try {
                            num = Integer
                                    .parseInt(JOptionPane.showInputDialog("Digite el número de atleta a buscar: "));
                            if (lista.buscarNodo(num)) {
                                JOptionPane.showMessageDialog(null, "Se ha encontrado: " + num + ", en la lista");
                            } else {
                                JOptionPane.showMessageDialog(null, "No se ha encontrado: " + num);
                            }

                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para buscar", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, lista.mostrarLista());
                        break;

                    case 4:
					try {
						num = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número del atleta: "));
						do {
							nombre = JOptionPane.showInputDialog("Ingrese el nombre por el que quiere modificar al  atleta " + num +":");
							if (nombre == null || nombre.trim().isEmpty()) {
								JOptionPane.showMessageDialog(null,
										"El nombre no puede estar vacío. Inténtelo de nuevo.", "Error",
										JOptionPane.INFORMATION_MESSAGE);
							}
						} while (nombre == null || nombre.trim().isEmpty());

						lista.modificarNombre(num, nombre);
					} catch (NumberFormatException nfe) {
						JOptionPane.showMessageDialog(null,
								"Error: Debe ingresar un número válido para el número del atleta", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
                    
                    case 5:
                        try {
                            if (lista.esVacia()) {
                                JOptionPane.showMessageDialog(null, "La lista está vacía");
                            } else {
                                num = Integer.parseInt(
                                        JOptionPane.showInputDialog("Ingrese el número del atleta a eliminar: "));
                                boolean eliminado = lista.eliminarNodo(num);
                                if (eliminado) {
                                    JOptionPane.showMessageDialog(null, "Nodo eliminado con éxito");
                                } else {
                                    JOptionPane.showMessageDialog(null,
                                            "No se encontró el atleta con el número: " + num);
                                }
                            }
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para eliminar",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        break;

                    case 6:

                        break;

                    case 7:
                        JOptionPane.showMessageDialog(null, "Saliendo del sistema, hasta pronto");
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Error: Opción no válida. Ingrese un número del 1 al 7",
                                "Error", JOptionPane.ERROR_MESSAGE);
                        break;
                }

            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(null, "Error: Ingrese un número válido para seleccionar una opción",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        } while (op != 7);

    }
} // LLave de toda la clase
