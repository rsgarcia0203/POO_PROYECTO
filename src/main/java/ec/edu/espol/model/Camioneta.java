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
public class Camioneta extends Vehiculo{
    private String traccion;
    private String transmision;
    private String vidrios;
    
    public Camioneta(int ID, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, String vidrios, String transmision, String traccion, double precio){
        super(ID, IDvendedor, placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
        this.traccion = traccion;
        this.vidrios = vidrios;
        this.transmision = transmision;
    }
    
    public String getVidrios() {
        return this.vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return this.transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getTraccion() {
        return this.traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }
    
    public static Camioneta nextCamioneta(Scanner sc, int IDvendedor, String nomfile){
        int id = Util.nextID(nomfile);
        System.out.println("Ingrese la placa: ");
        String placa = sc.next();
        System.out.println("Ingrese la marca: ");
        String marca = sc.next();
        System.out.println("Ingrese el Modelo: ");
        String modelo = sc.next();
        System.out.println("Ingrese el tipo de motor: ");
        String tipodeMotor = sc.next();
        System.out.println("Ingrese el anio: ");
        int anio = sc.nextInt();
        System.out.println("Ingrese el recorrido: ");
        double recorrido =sc.nextDouble();
        System.out.println("Ingrese el color: ");
        String color =sc.next();
        System.out.println("Ingrese el tipo de combustible: ");
        String TipoCombustible =sc.next();
        System.out.println("Ingrese el tipo de vidrio: ");
        String vidrio =sc.next();
        System.out.println("Ingrese el tipo de transmision: ");
        String transmision =sc.next();
        System.out.println("Ingrese el tipo de traccion: ");
        String traccion =sc.next();
        System.out.println("Ingrese el precio: ");
        Double precio =sc.nextDouble();
        Camioneta c = new Camioneta(id, IDvendedor, placa, marca, modelo, tipodeMotor, anio, recorrido,color,TipoCombustible,vidrio,transmision,traccion,precio);
        return c;
    }
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.ID+"|"+this.IDvendedor+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipo_motor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipo_combustible+"|"+this.vidrios+"|"+this.transmision+"|"+this.traccion+"|"+this.precio);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        }
    public static ArrayList<Camioneta> readFile(String nomfile){
        ArrayList<Camioneta> camioneta = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine())
            {
                // linea = "1|20201010|eduardo|cruz"
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Camioneta c;
                c = new Camioneta(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Double.parseDouble(tokens[7]),tokens[8],tokens[9],tokens[10],tokens[11],tokens[12],Double.parseDouble(tokens[13]));
                camioneta.add(c);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return camioneta;
    }
    @Override
    public String toString() {
        return "Camioneta{ ID=" + super.ID + ", IDvendedor=" + super.IDvendedor + ", placa=" + super.placa + ", marca=" + super.marca + ", modelo=" + super.modelo + ", tipo_motor=" + super.tipo_motor + ", anio=" + super.año + ", recorrido=" + super.recorrido + 
                ", color=" + super.color + ", tipo_combustible=" + super.tipo_combustible +", vidrio="+ this.vidrios + ", Transmision="+this.transmision +", traccion="+ this.traccion+ ", precio=" + precio + '}';
    }
}
