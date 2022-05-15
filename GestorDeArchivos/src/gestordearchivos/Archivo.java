/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestordearchivos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;

/**
 *
 * @author ciber
 */
public class Archivo {

    File archivo;

    public Archivo() {

    }

    public Archivo(String archivo) {
        this.archivo = new File(archivo);
    }

    public File getArchivo() {
        return archivo;
    }

    public void setArchivo(String archivo) {
        this.archivo = new File(archivo);
    }

    public boolean cambiarNombre(String nombre) {
        String name, barra = "\\";
        File rename;

        name = archivo.getParent();
        name += barra;
        name += nombre;
        rename = new File(name);
        return archivo.renameTo(rename);
    }

    public void cambiarNombresSecuencial(String nombre) {
        File[] lista = archivo.listFiles();
        int cont = 1, indexOf;
        File rename;
        String name, barra = "\\", ext;

        Arrays.sort(lista);

        mostrarContenidoCarpeta();

        System.out.println();

        
            for (File arch : lista) {
                if (arch.isFile()) {
                    indexOf = arch.getName().lastIndexOf(".");
                    ext = arch.getName().substring(indexOf);
                    if (cont < 10) {
                        name = archivo.getAbsolutePath();
                        name += barra;
                        name += nombre;
                        name += 0;
                        name += cont;
                        name += ext;
                        rename = new File(name);
                        arch.renameTo(rename);
                    } else {
                        name = archivo.getAbsolutePath();
                        name += barra;
                        name += nombre;
                        name += cont;
                        name += ext;
                        rename = new File(name);
                        arch.renameTo(rename);
                    }
                } else if (arch.isDirectory()) {
                    if (cont < 10) {
                        name = archivo.getAbsolutePath();
                        name += barra;
                        name += nombre;
                        name += 0;
                        name += cont;
                        rename = new File(name);
                        arch.renameTo(rename);
                    } else {
                        name = archivo.getAbsolutePath();
                        name += barra;
                        name += nombre;
                        name += cont;
                        rename = new File(name);
                        arch.renameTo(rename);
                    }
                }
                cont++;
            }
        

        mostrarContenidoCarpeta();
    }

    public void mostrarContenidoCarpeta() {
        File[] vector = archivo.listFiles();

        Arrays.sort(vector);
        for (File arch : vector) {
            if (arch.isFile()) {
                System.out.println("[-] " + arch.getName());
            } else {
                System.out.println("[D] " + arch.getName());
            }
        }
    }

    public boolean cambiarNombreEnCarpeta(String aCambiar, String cambiado) {
        String nameACambiar, nameCambiado, barra = "\\";
        File rename, original;
        boolean ok;

        nameACambiar = archivo.getAbsolutePath();
        nameACambiar += barra;
        nameACambiar += aCambiar;
        nameCambiado = archivo.getAbsolutePath();
        nameCambiado += barra;
        nameCambiado += cambiado;
        original = new File(nameACambiar);
        rename = new File(nameCambiado);
        ok = original.renameTo(rename);

        return ok;
    }

    public boolean moverArchivo(String nombre) {
        boolean ok;
        File mv;

        mv = new File(nombre);

        return archivo.renameTo(mv);
    }

    public void moverArchivoSubcarpetaMp4() throws FileNotFoundException {
        File[] vector = archivo.listFiles(), vector2;

        mostrarContenidoCarpeta();

        for (File arch : vector) {
            if (arch.isDirectory()) {
                vector2 = arch.listFiles();
                for (File f : vector2) {
                    if (f.getName().matches(".*\\.mp4") || f.getName().matches(".*\\.mkv")) {
                        f.renameTo(new File(archivo.getAbsolutePath() + "\\" + f.getName()));
                    }
                }
            }
        }

        for (File arch : vector) {
            borraTodo(arch);
        }
    }

    public boolean borrarArchivo() {
        return archivo.delete();
    }

    public boolean borraTodo() throws FileNotFoundException {
        boolean ok = false;
        File[] vector;

        if (archivo.isFile()) {
            ok = archivo.delete();
        } else if (archivo.isDirectory()) {
            vector = archivo.listFiles();

            for (File arch : vector) {
                if (arch.isFile()) {
                    arch.delete();
                } else {
                    borraTodo(arch);
                }
            }

            ok = archivo.delete();
        } else {
            throw new FileNotFoundException("");
        }

        return ok;
    }

    public boolean borraTodo(File f) throws FileNotFoundException {
        boolean ok = false;
        File[] vector;

        if (f.isFile()) {
            ok = f.delete();
        } else if (f.isDirectory()) {
            vector = f.listFiles();

            for (File arch : vector) {
                if (arch.isFile()) {
                    arch.delete();
                } else {
                    borraTodo(arch);
                }
            }

            ok = f.delete();
        } else {
            throw new FileNotFoundException("");
        }

        return ok;
    }

    public static boolean rutaExsiste(String nombre) {
        File existe = new File(nombre);

        return existe.exists();
    }
    
    public boolean areFiles(){
        boolean ok = false;
        File[] vector = archivo.listFiles();
        
        for (File arch : vector){
            if (arch.isFile()){
                ok = true;
            }
        }
        
        return ok;
    }
}
