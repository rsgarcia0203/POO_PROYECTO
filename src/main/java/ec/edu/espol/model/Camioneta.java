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
import java.util.Locale;
import java.util.Scanner;

/**
 *
 * @author rsgar
 */
public class Camioneta extends Vehiculo{
    private int ID;
    private String traccion;
    private String transmision;
    private String vidrios;
    
    public Camioneta(int ID, int IDvehiculo, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, String vidrios, String transmision, String traccion, double precio){
        super(IDvehiculo, IDvendedor, placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
        this.ID = ID;
        this.traccion = traccion;
        this.vidrios = vidrios;
        this.transmision = transmision;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
    
    public static Camioneta searchByID(ArrayList<Camioneta> camionetas, int id)
    {
        for(Camioneta ca : camionetas)
        {
            if(ca.IDvehiculo == id)
                return ca;
        }
        return null;
    }

    public static void nextCamioneta(Scanner sc, int IDvendedor, ArrayList<Camioneta> camionetas, String nomfile){
        sc.useLocale(Locale.US);
        int IDvehiculo = Util.nextID("ingreso.txt");
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
        Camioneta ca = new Camioneta(id, IDvehiculo, IDvendedor, placa, marca, modelo, tipodeMotor, anio, recorrido,color,TipoCombustible,vidrio,transmision,traccion,precio);
        if (camionetas.isEmpty()){
            ca.saveFile("camioneta.txt");
            System.out.println("Camioneta registrada."); 
            Ingreso.nextIngreso(ca,"ingreso.txt");
        }
        for (int j=0; j<camionetas.size();j++){
            if (!(ca.getPlaca().equals(camionetas.get(j).getPlaca()))){
                ca.saveFile("camioneta.txt");
                Ingreso.nextIngreso(ca,"ingreso.txt");
            }
            else
                System.out.println("Error, placa existente en el sistema");                                      
        }
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.ID+"|"+this.IDvehiculo+"|"+this.IDvendedor+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipo_motor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipo_combustible+"|"+this.vidrios+"|"+this.transmision+"|"+this.traccion+"|"+this.precio);
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
                c = new Camioneta(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]),tokens[3],tokens[4],tokens[5],tokens[6],Integer.parseInt(tokens[7]),Double.parseDouble(tokens[8]),tokens[9],tokens[10],tokens[11],tokens[12],tokens[13],Double.parseDouble(tokens[14]));
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
        return "Camioneta<" + this.ID +">{placa=" + super.placa + ", marca=" + super.marca + ", modelo=" + super.modelo + ", tipo_motor=" + super.tipo_motor + ", anio=" + super.año + ", recorrido=" + super.recorrido + ", color=" + super.color + ", tipo_combustible=" + 
                super.tipo_combustible +", vidrio="+ this.vidrios + ", Transmision="+this.transmision +", traccion="+ this.traccion+ ", precio=" + super.precio + "}";
    }
}
