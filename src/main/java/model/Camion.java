/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author erikc
 */
public class Camion {
    private String matricula;
    private String modelo;
    private double pma;
    private String estado;
    private ArrayList<Paquete> listaPaquetes;

    /**
     * Constructor del camión.
     */
    public Camion(String matricula, String modelo, double pma) {
        this.matricula = matricula.toUpperCase();
        this.modelo = modelo;
        this.pma = pma;
        this.estado = "ALMACEN";
        this.listaPaquetes = new ArrayList<>();
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public double getPma() {
        return pma;
    }

    public String getEstado() {
        return estado;
    }

    public ArrayList<Paquete> getListaPaquetes() {
        return listaPaquetes;
    }

    public double calcularPesoActual() {
        double total = 0;
        for (int i = 0; i < listaPaquetes.size(); i++) {
            total = total + listaPaquetes.get(i).getPeso();
        }
        return total;
    }

    public boolean cargarPaquete(Paquete paquete) {
        boolean añadido = false;

        if (estado.equals("ALMACEN")) {
            if (calcularPesoActual() + paquete.getPeso() <= pma) {
                listaPaquetes.add(paquete);
                añadido = true;
            }
        }

        return añadido;
    }

    public boolean salirRuta() {
        boolean cambiado = false;

        if (estado.equals("ALMACEN") && listaPaquetes.size() >= 2) {
            estado = "RUTA";
            cambiado = true;
        }

        return cambiado;
    }

    public void finalizarRuta() {
        estado = "ALMACEN";
        listaPaquetes.clear();
    }

    @Override
    public String toString() {
        return matricula + ";" + modelo + ";" + pma + ";" + estado;
    }
}
