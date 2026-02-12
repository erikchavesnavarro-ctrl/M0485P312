/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author erikc
 */
public class Paquete {
    private String codigo;
    private String ciudadDestino;
    private double peso;

    /**
     * @param codigo
     * @param ciudadDestino
     * @param peso
     */
    public Paquete(String codigo, String ciudadDestino, double peso) {
        this.codigo = codigo;
        this.ciudadDestino = ciudadDestino;
        this.peso = peso;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getCiudadDestino() {
        return ciudadDestino;
    }

    public double getPeso() {
        return peso;
    }

    
    @Override
    public String toString() {
        return codigo + ";" + ciudadDestino + ";" + peso;
    }
}
