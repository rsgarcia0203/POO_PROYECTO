/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;


import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rsgar
 */
public class Vehiculo {
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipo_motor;
    protected int anio;
    protected double recorrido;
    protected String color;
    protected String tipo_combustible;
    protected String vidrios;
    protected String transmision;
    protected String traccion;
    protected double precio;

    //constructor para autos
    public Vehiculo(String placa, String marca, String modelo, String tipo_motor, int anio, double recorrido, String color, String tipo_combustible, String vidrios, String transmision, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.precio = precio;
    }
    //constructor para Motocicletas
    public Vehiculo(String placa, String marca, String modelo, String tipo_motor, int anio, double recorrido, String color, String tipo_combustible, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
    }
    //constructor para Camionetas
    public Vehiculo(String placa, String marca, String modelo, String tipo_motor, int anio, double recorrido, String color, String tipo_combustible, String vidrios, String transmision, String Traccion, double precio) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.anio = anio;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.vidrios = vidrios;
        this.transmision = transmision;
        this.Traccion = Traccion;
        this.precio = precio;
    }
    

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo_motor() {
        return tipo_motor;
    }

    public void setTipo_motor(String tipo_motor) {
        this.tipo_motor = tipo_motor;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_combustible() {
        return tipo_combustible;
    }

    public void setTipo_combustible(String tipo_combustible) {
        this.tipo_combustible = tipo_combustible;
    }

    public String getVidrios() {
        return vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getTraccion() {
        return Traccion;
    }

    public void setTraccion(String Traccion) {
        this.Traccion = Traccion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
  
        
        
        
        }
    
  
}
