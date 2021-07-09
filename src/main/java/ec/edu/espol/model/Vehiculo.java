/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
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
    protected int ID;
    protected int IDvendedor;
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipo_motor;
    protected int año;
    protected double recorrido;
    protected String color;
    protected String tipo_combustible;
    protected double precio;
    protected Vendedor vendedor;
    
    public Vehiculo(int ID, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio){
        this.IDvendedor = IDvendedor;
        this.ID = ID;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
        this.vendedor = null;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDvendedor() {
        return this.IDvendedor;
    }

    public void setIDvendedor(int IDvendedor) {
        this.IDvendedor = IDvendedor;
    }
       
    public String getPlaca() {
        return this.placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo_motor() {
        return this.tipo_motor;
    }

    public void setTipo_motor(String tipo_motor) {
        this.tipo_motor = tipo_motor;
    }

    public int getAño() {
        return this.año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getRecorrido() {
        return this.recorrido;
    }

    public void setRecorrido(float recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipo_combustible() {
        return this.tipo_combustible;
    }

    public void setTipo_combustible(String tipo_combustible) {
        this.tipo_combustible = tipo_combustible;
    }

    public double getPrecio() {
        return this.precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public Vendedor getVendedor() {
        return this.vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }
    
    public static Vehiculo searchByID(ArrayList<Vehiculo> vehiculos, int id)
    {
        for(Vehiculo v : vehiculos)
        {
            if(v.ID == id)
                return v;
        }
        return null;
    }
    
    public static void saveFile(String nomFile, int idtipo, int idclase){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            int ID = Util.nextID(nomFile);
            pw.println(ID+"|"+idtipo+"|"+idclase);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    } 
      
}
