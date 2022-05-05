/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gestordearchivos;
/**
 *
 * @author Aitor Rodriguez Gallardo
 */
public class Utilidades {

    public static boolean validarDNI(String dni) {
        char[] letraDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        boolean ok = true;
        String num;
        char letra;
        int indice, resto;

        ok = true;
        indice = 0;
        if (dni.length() == 9) {
            num = dni.substring(0, 8);
            while (indice < num.length() && ok) {
                if (Character.isDigit(num.charAt(indice))) {
                    indice++;
                } else {
                    ok = false;
                }
            }
            //Verificacion de la letra
            if (ok) {
                letra = dni.charAt(8);
                if (Character.isLetter(letra)) {
                    letra = Character.toUpperCase(letra);
                    resto = Integer.parseInt(num) % 23;
                    if (letra != letraDni[resto]) {
                        ok = false;
                    }
                } else {
                    ok = false;
                }
            } else {
                ok = false;
            }
        } else {
            ok = false;
        }
        return ok;
    }

    public static boolean validarPositivo(int i) {
        return i > 0;
    }

    public static boolean validarPositivo(double i) {
        return i > 0;
    }

    public static boolean validarPositivo(float i) {
        return i > 0;
    }

    public static int generarNumeroAleatorio(int min, int max) {
        int aleatorio;
        aleatorio = (int) (Math.random() * (max - min + 1) + (min));
        System.out.println(aleatorio);
        return aleatorio;
    }

    public static boolean validarFecha(String fecha) {
        boolean ok = true;
        String Sdia, Smes, Sanyo;
        String separador = "-";
        String s, s1;
        s = fecha.substring(2,3);
        s1 = fecha.substring(5,6);
        int dia, mes, anyo, diasFeb;
        Sdia = fecha.substring(0, 2);
        Smes = fecha.substring(3, 5);
        Sanyo = fecha.substring(6);
        dia = Integer.parseInt(Sdia);
        mes = Integer.parseInt(Smes);
        anyo = Integer.parseInt(Sanyo);

        if (fecha.length() != 10) {
            System.err.println("La fecha debe tener 10 caracteres de longitud");
        } else {
            if (s.equals(separador) && s1.equals(separador)) {
                if (anyo < 1900 || anyo > 2022) {
                    ok = false;
                    System.err.println("El año no puede ser menor a 1900 ni mayor a 2022.");
                }
                if (mes <= 0 || mes > 12) {
                    ok = false;
                    System.err.println("Mes incorrecto.");
                } else {
                    switch (mes) {
                        case 1:
                        case 3:
                        case 5:
                        case 7:
                        case 8:
                        case 10:
                        case 12:
                            if (dia <= 0 || dia > 31) {
                                System.err.println("Dia no existente en este mes");
                                ok = false;
                            }
                            break;
                        case 4:
                        case 6:
                        case 9:
                        case 11:
                            if (dia <= 0 || dia > 30) {
                                System.err.println("Dia no existente en este mes");
                                ok = false;
                            }
                            break;
                        case 2:
                            if (anyo % 4 == 0 && anyo % 100 != 0 || anyo % 400 == 0) {
                                diasFeb = 29;
                                if (dia <= 0 || dia > diasFeb) {
                                    ok = false;
                                    System.err.println("Dia no existente en este mes");
                                }
                            } else {
                                diasFeb = 28;
                                if (dia <= 0 || dia > diasFeb) {
                                    ok = false;
                                    System.err.println("Dia no existente en este mes");
                                }
                                break;
                            }
                    }
                }
            } else {
                System.out.println("Formato Incorrecto");
                ok = false;
            }
        }
        return ok;
    }

    public static boolean validarFecha1(String fecha) {
        boolean ok = true;

        if (!fecha.matches("\\d{1,2}-\\d{1,2}-\\d{4}")) {
            ok = false;
            System.err.println("Formato de Fecha no valido");
        }
        return ok;
    }

    public static boolean validarPlataforma(String plataforma) {
        boolean ok = false;
        String[] plataformas = {"PS4", "PS5", "XBOX ONE", "XBOX SERIES X", "PC", "SWITCH"};
        int i = 0;
        String p;

        while (ok == false && i < plataformas.length) {
            p = plataformas[i];
            if (p.equalsIgnoreCase(plataforma)) {
                ok = true;
            }

            i++;
        }

        return ok;
    }

    public static boolean validarTipoMusica(String tipo) {
        boolean ok = false;
        String[] tipos = {"CD", "Vinilo"};
        int i = 0;
        String t;

        while (ok == false && i < tipos.length) {
            t = tipos[i];
            if (t.equalsIgnoreCase(tipo)) {
                ok = true;
            }

            i++;
        }

        return ok;
    }

    public static boolean validarTapa(String tapa) {
        boolean ok = false;
        String tipo1 = "Dura";
        String tipo2 = "Blanda";

        if (tapa.equalsIgnoreCase(tipo1)) {
            ok = true;
        } else if (tapa.equalsIgnoreCase(tipo2)) {
            ok = true;
        }

        return ok;
    }

    public static boolean validarMinutos(int min) {
        boolean ok = false;

        if (min >= 0 && min < 60) {
            ok = true;
        }

        return ok;
    }

    public static boolean validarSN(String resp) {
        boolean ok = true;

        if (!(resp.equalsIgnoreCase("si")) && !(resp.equalsIgnoreCase("no"))) {
            ok = false;
        }
        return ok;
    }

    public static boolean campoVacio(String texto) {
        boolean ok = true;
        if (texto.isEmpty()) {
            ok = false;
        }
        return ok;
    }

    public static boolean validarCOD(String cod) {
        boolean ok = false;

        if (cod.matches("\\d{3}")) {
            ok = true;
        }

        return true;
    }

    public static boolean validarFormatoLibro(String formato) {
        boolean ok = false;

        if (formato.equalsIgnoreCase("papel") || formato.equalsIgnoreCase("ebook")) {
            ok = true;
        }

        return ok;
    }

    public static boolean validarPEGI(int edad) {
        boolean ok = true;

        if (edad < 3) {
            ok = false;
        }

        return ok;
    }
}
