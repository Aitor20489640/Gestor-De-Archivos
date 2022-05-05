/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gestordearchivos;

import java.util.Scanner;
import java.io.FileNotFoundException;

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
        String nombre, ext, nombreCambiado;
        int op = -1;
        Archivo ruta = null;
        boolean ok = false;

        do {

            do {
                System.out.println("Escriba la ruta absoluta de la carpeta o archivo que quiere gestionar.");
                nombre = sc.next();
                if (Archivo.rutaExsiste(nombre)) {
                    ruta = new Archivo(nombre);
                    ok = true;
                } else {
                    System.out.println("Ruta no existe, introduzca una ruta existente.");
                }
            } while (ok);

            do {

                mostrarMenu();
                System.out.println("Seleccione una opción.");
                op = sc.nextInt();

                if (op < -1 || op > 8) {
                    System.out.println("Opción no disponible, eliga una opción disponible.");
                }

            } while (op > -1 || op < 8);

            switch (op) {
                case 0:
                    do {
                        System.out.println("Escriba la ruta absoluta donde va a gestionar los archivos.");
                        nombre = sc.next();
                        if (Archivo.rutaExsiste(nombre)) {
                            ruta.setArchivo(nombre);
                            ok = true;
                        } else {
                            System.err.println("Ruta no existe, introduzca una ruta existente.");
                        }
                    } while (!ok);
                    break;
                case 1:
                    do {
                        System.out.println("Introduzca el nombre que le quiere poner.");
                        System.out.println("RECUERDA: Si es un archivo introduce la extensión.");
                        nombre = sc.next();
                        ok = Utilidades.campoVacio(nombre);
                    } while (!ok);
                    ruta.cambiarNombre(nombre);
                    break;
                case 2:
                    if(ruta.getArchivo().isDirectory()){
                        do{
                            System.out.println("Introduce el nombre que le quieres poner al archivo.");
                            nombre = sc.next();
                            ok = Utilidades.campoVacio(nombre);
                        }while(!ok);
                        do{
                            System.out.println("Introduce la extension del archivo.");
                            ext = sc.next();
                            ok = Utilidades.campoVacio(ext);
                        }while(!ok);
                        ruta.cambiarNombresSecuencial(nombre, ext);
                        System.out.println();
                    } else{
                        System.err.println("Esta opcion solo soporta que la ruta sea una carpeta.");
                    }
                    break;
                case 3:
                    ruta.mostrarContenidoCarpeta();
                    
                    do{
                        System.out.println("Dime el nombre del archivo o carpeta que quieres cambiar.");
                        nombre = sc.next();
                        Utilidades.campoVacio(nombre);
                    }while(!ok);
                    do{
                        System.out.println("Dime el nombre que le vas a poner.");
                        nombreCambiado = sc.next();
                        Utilidades.campoVacio(nombreCambiado);
                    }while(!ok);
                    if(ruta.cambiarNombreEnCarpeta(nombre, nombreCambiado))
                        System.out.println("El nombre se a cambiado correctamente.");
                    else
                        System.out.println("El nombre no se a podido cambiar.");
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    System.out.println("Gracias por utilizar el programa.");
                    break;
            }

        } while (op != 8);
    }

    public static void mostrarMenu() {
        System.out.println("----------MENU-----------");
        System.out.println("0- Cambiar el directorio al que esta apuntando");
        System.out.println("1- Cambiar nombre de un archivo");
        System.out.println("2- Cambiar nombre de archivos secuencialmente");
        System.out.println("3- Cambiar nombre de archivos dentro de una carpeta");
        System.out.println("4- Mover archivos");
        System.out.println("5- Mover archivos fuera de subcarpeta");
        System.out.println("6- Borrar archivos");
        System.out.println("7- Borrar todo");
        System.out.println("8- Salir");
    }
}
