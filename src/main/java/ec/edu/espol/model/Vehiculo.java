/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author rsgar
 */
public class Vehiculo {
    protected int IDvehiculo;
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
    protected ArrayList<Oferta> ofertas;
    
    public Vehiculo(int IDvehiculo, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio){
        this.IDvendedor = IDvendedor;
        this.IDvehiculo = IDvehiculo;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo_motor = tipo_motor;
        this.año = año;
        this.recorrido = recorrido;
        this.color = color;
        this.tipo_combustible = tipo_combustible;
        this.precio = precio;
        this.ofertas = new ArrayList<>();
    }

    public int getIDvehiculo() {
        return this.IDvehiculo;
    }

    public void setIDvehiculo(int IDvehiculo) {
        this.IDvehiculo = IDvehiculo;
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

    public ArrayList<Oferta> getOfertas() {
        return this.ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        return Objects.equals(this.placa, other.placa);
    }
        
    public static ArrayList<Vehiculo> vehiculosxRecorrido(ArrayList<Vehiculo> vehiculos, double recorridoInicio, double recorridoFinal)
    {
        ArrayList<Vehiculo> xRecorrido = new ArrayList <>();
        for(Vehiculo v: vehiculos)
        {
            if(v.getRecorrido() >= recorridoInicio && v.getRecorrido() <= recorridoFinal)
            {
                xRecorrido.add(v);
            }
        }
        return xRecorrido;
    }
    
    public static ArrayList<Vehiculo> vehiculosxAño(ArrayList<Vehiculo> vehiculos, int añoInicio, int añoFinal)
    {
        ArrayList<Vehiculo> xAño = new ArrayList <>();
        for(Vehiculo v: vehiculos)
        {
            if(v.getAño() >= añoInicio && v.getAño() <= añoFinal)
            {
                xAño.add(v);
            }
        }
        return xAño;
    }
    
    public static ArrayList<Vehiculo> vehiculosxPrecio(ArrayList<Vehiculo> vehiculos, double precioInicio, double precioFinal)
    {
        ArrayList<Vehiculo> xPrecio = new ArrayList <>();
        for(Vehiculo v: vehiculos)
        {
            if(v.getPrecio() >= precioInicio && v.getPrecio() <= precioFinal)
            {
                xPrecio.add(v);
            }
        }
        return xPrecio;
    }
    
    public static void eliminarVehiculo(ArrayList<Vehiculo> vehiculos, Vehiculo vehiculo) throws IOException
    {
        for(int i = 0; i < vehiculos.size(); i++)
        {
            if (vehiculos.get(i).equals(vehiculo))
            {
                vehiculos.remove(i);
            }
        }
        Util.limpiarArchivo("automovil.txt");
        Util.limpiarArchivo("motocicleta.txt");
        Util.limpiarArchivo("camioneta.txt");
        for(Vehiculo v: vehiculos)
        {
            if(v instanceof Automovil)
                ((Automovil) v).saveFile("automovil.txt");
            else if(v instanceof Motocicleta)
                ((Motocicleta) v).saveFile("motocicleta.txt");
            else if(v instanceof Camioneta)
                ((Camioneta) v).saveFile("camioneta.txt");
        }
    }
}
