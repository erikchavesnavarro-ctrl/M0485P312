/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import model.Camion;
import model.Paquete;
import java.util.ArrayList;

/**
 *
 * @author erikc
 */
public class FlotaController {
    private ArrayList<Camion> flota;

    public FlotaController() {
        flota = new ArrayList<>();
    }

    public ArrayList<Camion> getFlota() {
        return flota;
    }

    public Camion buscarCamion(String matricula) {
        Camion encontrado = null;

        for (int i = 0; i < flota.size(); i++) {
            if (flota.get(i).getMatricula().equalsIgnoreCase(matricula)) {
                encontrado = flota.get(i);
            }
        }

        return encontrado;
    }

    public boolean existePaquete(String codigo) {
        boolean existe = false;

        for (int i = 0; i < flota.size(); i++) {
            ArrayList<Paquete> paquetes = flota.get(i).getListaPaquetes();
            for (int j = 0; j < paquetes.size(); j++) {
                if (paquetes.get(j).getCodigo().equalsIgnoreCase(codigo)) {
                    existe = true;
                }
            }
        }

        return existe;
    }

    public boolean altaCamion(String matricula, String modelo, double pma) {
        boolean añadido = false;

        if (buscarCamion(matricula) == null) {
            if (pma >= 1500 && pma <= 40000) {
                flota.add(new Camion(matricula, modelo, pma));
                añadido = true;
            }
        }

        return añadido;
    }
    
    public String cargarPaquete(String matricula, String codigo, String ciudad, double peso) {

        Camion c = buscarCamion(matricula);

        if (c == null) {
            return "El camion no existe";
        }

        if (!c.getEstado().equals("ALMACEN")) {
            return "El camion está en ruta";
        }

        if (existePaquete(codigo)) {
            return "El codigo de paquete ya existe";
        }

        Paquete p = new Paquete(codigo, ciudad, peso);

        if (c.cargarPaquete(p)) {
            return "Paquete cargado correctamente";
        } else {
            return "Se supera el PMA del camion";
        }
    }
    
    public String salidaRuta(String matricula) {

        Camion c = buscarCamion(matricula);

        if (c == null) {
            return "El camion no existe";
        }

        if (c.salirRuta()) {
            return "El camion ha salido a ruta";
        } else {
            return "No tiene minimo 2 paquetes o no esta en almacen";
        }
    }
    
    public String finalizarRuta(String matricula) {

        Camion c = buscarCamion(matricula);

        if (c == null) {
            return "El camion no existe";
        }

        if (c.getEstado().equals("ALMACEN")) {
            return "El camion ya está en almacén";
        }

        c.finalizarRuta();
        return "El camion ha vuelto y se ha vaciado la carga";
    }
    
    public String listarFlota() {

        if (flota.isEmpty()) {
            return "No hay camiones registrados";
        }

        String texto = "";

        for (int i = 0; i < flota.size(); i++) {
            Camion c = flota.get(i);

            texto = texto + c.getMatricula() + " - " +
                    c.getModelo() + " - " +
                    c.getEstado() + " - ";

            if (c.getEstado().equals("ALMACEN")) {
                texto = texto + "Carga actual: " + c.calcularPesoActual() + "/" + c.getPma();
            } else {
                texto = texto + "Paquetes: " + c.getListaPaquetes().size();
            }

            texto = texto + "\n";
        }

        return texto;
    }
    
    public String infoCamion(String matricula) {

        Camion c = buscarCamion(matricula);

        if (c == null) {
            return "El camion no existe";
        }

        String texto = c.getMatricula() + " - " + c.getModelo() + " - " + c.getEstado() + "\n";

        if (c.getListaPaquetes().isEmpty()) {
            texto = texto + "No tiene paquetes\n";
        } else {

            for (int i = 0; i < c.getListaPaquetes().size(); i++) {
                Paquete p = c.getListaPaquetes().get(i);

                texto = texto + p.getCodigo() + " - " + p.getCiudadDestino() + " - " + p.getPeso() + "\n";
            }
        }

        return texto;
    }
    
    public void crearCarpetaDatos() {

        File carpeta = new File("business_data");

        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
    }
    
    public String guardarDatos() {

        try {

            FileWriter fw = new FileWriter("business_data/flota.txt");

            for (int i = 0; i < flota.size(); i++) {
                Camion c = flota.get(i);

                fw.write(c.toString() + "\n");

                FileWriter paquetes = new FileWriter("business_data/" + c.getMatricula() + ".txt");

                for (int j = 0; j < c.getListaPaquetes().size(); j++) {
                    paquetes.write(c.getListaPaquetes().get(j).toString() + "\n");
                }

                paquetes.close();
            }

            fw.close();
            return "Datos guardados correctamente";

        } catch (IOException e) {
            return "Error al guardar datos";
        }
    }
    
    public void cargarDatos() {

        try {

            File f = new File("business_data/flota.txt");

            if (!f.exists()) return;

            Scanner sc = new Scanner(f);

            while (sc.hasNextLine()) {

                String linea = sc.nextLine();
                String[] partes = linea.split(";");

                Camion c = new Camion(partes[0], partes[1], Double.parseDouble(partes[2]));
                flota.add(c);

                File paquetes = new File("business_data/" + partes[0] + ".txt");

                if (paquetes.exists()) {

                    Scanner scp = new Scanner(paquetes);

                    while (scp.hasNextLine()) {

                        String l = scp.nextLine();
                        String[] p = l.split(";");

                        c.cargarPaquete(new Paquete(p[0], p[1], Double.parseDouble(p[2])));
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error cargando datos");
        }
    }
}
