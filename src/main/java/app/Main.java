/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package app;

import java.util.Scanner;
import controller.FlotaController;

/**
 *
 * @author erikc
 */
public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        FlotaController servicio = new FlotaController();

        servicio.crearCarpetaDatos();
        servicio.cargarDatos();

        int opcion = -1;

        while (opcion != 8) {

            mostrarMenu();
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {

                case 1:
                    altaCamion(sc, servicio);
                    break;

                case 2:
                    cargarPaquete(sc, servicio);
                    break;

                case 3:
                    salidaRuta(sc, servicio);
                    break;

                case 4:
                    retornoRuta(sc, servicio);
                    break;

                case 5:
                    System.out.println(servicio.listarFlota());
                    break;

                case 6:
                    infoCamion(sc, servicio);
                    break;

                case 7:
                    System.out.println(servicio.guardarDatos());
                    break;

                case 8:
                    System.out.println("Tancant l'aplicacio…");
                    System.out.println("Gracies per utilitzar el sistema de gestio LogiTrans S.L. Fins aviat!");
                    break;

                default:
                    System.out.println("Opcio incorrecta");
            }
        }
    }
    
    private static void mostrarMenu() {

        System.out.println("\n--- LOGITRANS ---");
        System.out.println("1. Alta Camio");
        System.out.println("2. Carregar Paquet");
        System.out.println("3. Sortida Ruta");
        System.out.println("4. Retorn Magatzem");
        System.out.println("5. Llistar Flota");
        System.out.println("6. Info Camio");
        System.out.println("7. Guardar Canvis");
        System.out.println("8. Sortir");
        System.out.print("Opcio: ");
    }
    
    private static void altaCamion(Scanner sc, FlotaController servicio) {

        System.out.print("Matricula: ");
        String mat = sc.nextLine();

        System.out.print("Model: ");
        String model = sc.nextLine();

        System.out.print("PMA: ");
        double pma = sc.nextDouble();
        sc.nextLine();

        if (servicio.altaCamion(mat, model, pma)) {
            System.out.println("Camio registrat correctament");
        } else {
            System.out.println("No s'ha pogut registrar el camio");
        }
    }
    
    private static void cargarPaquete(Scanner sc, FlotaController servicio) {

        System.out.print("Matricula: ");
        String mat = sc.nextLine();

        System.out.print("Codi paquet: ");
        String cod = sc.nextLine();

        System.out.print("Ciutat desti: ");
        String ciutat = sc.nextLine();

        System.out.print("Pes: ");
        double pes = sc.nextDouble();
        sc.nextLine();

        System.out.println(servicio.cargarPaquete(mat, cod, ciutat, pes));
    }
    
    private static void salidaRuta(Scanner sc, FlotaController servicio) {

        System.out.print("Matricula: ");
        String mat = sc.nextLine();

        System.out.println(servicio.salidaRuta(mat));
    }
    
    private static void retornoRuta(Scanner sc, FlotaController servicio) {

        System.out.print("Matricula: ");
        String mat = sc.nextLine();

        System.out.println(servicio.finalizarRuta(mat));
    }
    
    private static void infoCamion(Scanner sc, FlotaController servicio) {

        System.out.print("Matricula: ");
        String mat = sc.nextLine();

        System.out.println(servicio.infoCamion(mat));
    }
}
