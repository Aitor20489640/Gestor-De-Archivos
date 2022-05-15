/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestordearchivos;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;

/**
 *
 * @author ciber
 */
public class Pricipal {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in);
        String nombre, ext, nombreCambiado, siNo;
        int op = -1;
        Archivo ruta = null;
        boolean ok = false, okMovido;

        do {
            if (op == -1) {
                do {
                    System.out.println("Escriba la ruta absoluta de la carpeta o archivo que quiere gestionar.");
                    nombre = sc.nextLine();
                    if (Archivo.rutaExsiste(nombre)) {
                        ruta = new Archivo(nombre);
                        ok = true;
                    } else {
                        System.out.println("Ruta no existe, introduzca una ruta existente.");
                    }
                } while (!ok);
            }
            do {
                try{
                mostrarMenu();
                System.out.println("Seleccione una opción.");
                op = sc.nextInt();
                
                if (op < -1 || op > 9) {
                    System.out.println("Opción no disponible, eliga una opción disponible.");
                }
                }
                catch(InputMismatchException e){
                    System.err.println("Solo se pueden introducir numeros");
                    sc.nextLine();
                    op = -2;
                }

            } while (op < -1 || op > 9);

            switch (op) {
                case 0:
                    do {
                        System.out.println("Escriba la ruta absoluta donde va a gestionar los archivos.");
                        sc.nextLine();
                        nombre = sc.nextLine();
                        if (Archivo.rutaExsiste(nombre)) {
                            ruta.setArchivo(nombre);
                            ok = true;
                        } else {
                            System.err.println("Ruta no existe, introduzca una ruta existente.");
                        }
                    } while (!ok);
                    break;
                case 1:
                    if (ruta.getArchivo().isDirectory()) {
                        ruta.mostrarContenidoCarpeta();
                    } else {
                        System.err.println("Esta opcion solo soporta que la ruta sea una carpeta.");
                    }
                    break;
                case 2:
                    do {
                        System.out.println("Introduzca el nombre que le quiere poner.");
                        System.out.println("RECUERDA: Si es un archivo introduce la extensión.");
                        nombre = sc.nextLine();
                        ok = Utilidades.campoVacio(nombre);
                    } while (!ok);
                    if (ruta.cambiarNombre(nombre)) {
                        System.out.println("Se ha cambiado el nombre.");
                    } else {
                        System.out.println("No se ha cambiado el nombre.");
                    }
                    break;
                case 3:
                    if (ruta.getArchivo().isDirectory()) {
                        do {
                            System.out.println("Introduce el nombre que le quieres poner al archivo.");
                            sc.nextLine();
                            nombre = sc.nextLine();
                            ok = Utilidades.campoVacio(nombre);
                        } while (!ok);
                        ruta.cambiarNombresSecuencial(nombre);
                        System.out.println();
                    } else {
                        System.err.println("Esta opcion solo soporta que la ruta sea una carpeta.");
                    }
                    break;
                case 4:
                    if (ruta.getArchivo().isDirectory()) {

                        ruta.mostrarContenidoCarpeta();

                        do {
                            sc.nextLine();
                            System.out.println("Dime el nombre del archivo o carpeta que quieres cambiar.");
                            nombre = sc.nextLine();
                            Utilidades.campoVacio(nombre);
                        } while (!ok);
                        do {
                            System.out.println("Dime el nombre que le vas a poner.");
                            nombreCambiado = sc.nextLine();
                            sc.nextLine();
                            Utilidades.campoVacio(nombreCambiado);
                        } while (!ok);
                        if (ruta.cambiarNombreEnCarpeta(nombre, nombreCambiado)) {
                            System.out.println("El nombre se a cambiado correctamente.");
                        } else {
                            System.out.println("El nombre no se a podido cambiar.");
                        }
                    } else {
                        System.err.println("Esta opcion solo soporta que la ruta sea una carpeta.");
                    }
                    break;
                case 5:
                    do {
                        System.out.println("Escriba la ruta absoluta donde va a mover el archivo o carpeta.");
                        System.out.println("AVISO: No se puede mover una carpeta con contenidos dentro.");
                        nombre = sc.nextLine();
                        ok = Utilidades.campoVacio(nombre);
                        if (ok) {
                            if (Archivo.rutaExsiste(nombre)) {
                                okMovido = ruta.moverArchivo(nombre);
                                if (okMovido) {
                                    System.out.println("Se ha movido al lugar indicado.");
                                } else {
                                    System.out.println("No se ha podido mover.");
                                }
                            } else {
                                System.err.println("Ruta no existe, introduzca una ruta existente.");
                            }
                        }
                    } while (!ok);
                    op = -1;
                    break;
                case 6:
                    if (ruta.getArchivo().isDirectory()) {
                        ruta.moverArchivoSubcarpetaMp4();
                    } else {
                        System.err.println("Esta opcion solo soporta que la ruta sea una carpeta.");
                    }
                    break;
                case 7:
                    System.out.println("Estas seguro que quieres borrar este archivo? " + ruta.getArchivo().getName());
                    do {
                        System.out.println("Si/No");
                        siNo = sc.nextLine();
                        ok = Utilidades.validarSN(siNo);
                    } while (!ok);
                    if (siNo.equalsIgnoreCase("si")) {
                        ruta.borrarArchivo();
                    } else {
                        System.out.println("Okay, volvemos al menu.");
                    }
                    op = -1;
                    break;
                case 8:
                    if (ruta.getArchivo().isFile()) {
                        System.out.println("Estas seguro que quieres borrar este archivo? " + ruta.getArchivo().getName());
                    } else if (ruta.getArchivo().isDirectory()) {
                        System.out.println("Estas seguro que quieres borrar todo el contenido de esta carpeta y sus subcarpetas?");
                    }
                    do {
                        System.out.println("Si/No");
                        siNo = sc.nextLine();
                        ok = Utilidades.validarSN(siNo);
                    } while (!ok);
                    if (siNo.equalsIgnoreCase("si")) {
                        ok = ruta.borraTodo();
                        if (ok) {
                            System.out.println("Se ha borrado todo");
                        } else {
                            System.out.println("No se ha conseguido borrar");
                        }
                    } else {
                        System.out.println("Okay, volvemos al menu.");
                    }
                    op = -1;
                    break;
                case 9:
                    System.out.println("Gracias por utilizar el programa.");
                    break;
            }

        } while (op != 9);
    }

    public static void mostrarMenu() {
        System.out.println("----------MENU-----------");
        System.out.println("0 - Cambiar el directorio al que esta apuntando");
        System.out.println("1 - Mostrar el contenido de la carpeta en la que estas.");
        System.out.println("2 - Cambiar nombre de un archivo");
        System.out.println("3 - Cambiar nombre de archivos secuencialmente");
        System.out.println("4 - Cambiar nombre de archivos dentro de una carpeta");
        System.out.println("5 - Mover archivos");
        System.out.println("6 - Mover archivos mp4 o mkv fuera de subcarpeta");
        System.out.println("7 - Borrar archivo");
        System.out.println("8 - Borrar todo");
        System.out.println("9 - Salir");
    }
}
