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
public class Automovil extends Vehiculo{
    private String vidrios;
    private String transmision;
    
    public Automovil(int ID, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, String vidrios, String transmision, double precio){
        super(ID, IDvendedor, placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
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
    
    public static void nextAutomovil(Scanner sc, int IDvendedor, ArrayList<Automovil> automoviles, String nomfile){
        int id = Util.nextID(nomfile);
        sc.useLocale(Locale.US);
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
        System.out.println("Ingrese el precio: ");
        double precio =sc.nextDouble();
        Automovil au = new Automovil(id, IDvendedor, placa,marca, modelo, tipodeMotor, anio, recorrido,color,TipoCombustible,vidrio,transmision,precio);
        if (automoviles.isEmpty()){
            au.saveFile("automovil.txt");
            System.out.println("Automovil registrado.");
            Vehiculo.saveFile("vehiculo.txt",1,id);
        }
        for (int j=0; j<automoviles.size();j++){
            if(!(au.getPlaca().equals(automoviles.get(j).getPlaca()))){
                System.out.println("Automovil registrado.");
                au.saveFile("automovil.txt");
                Vehiculo.saveFile("vehiculo.txt",1,id);
            }
            else
                System.out.println("Eror, placa existente en el sistema");
        }
    }
    
    
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.ID+"|"+this.IDvendedor+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipo_motor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipo_combustible+"|"+this.vidrios+"|"+this.transmision+"|"+this.precio);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Automovil> readFile(String nomfile){
        ArrayList<Automovil> automovil = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Automovil e;
                e = new Automovil(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Double.parseDouble(tokens[7]),tokens[8],tokens[9],tokens[10],tokens[11],Double.parseDouble(tokens[12]));
                automovil.add(e);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return automovil;
    }
    @Override
    public String toString() {
        return "Vehiculo{ ID: " + super.ID + ", IDvendedor: " + super.IDvendedor +", placa=" + super.placa + ", marca=" + super.marca + ", modelo=" + super.modelo + ", tipo_motor=" + super.tipo_motor + ", anio=" + super.año + ", recorrido=" + super.recorrido + 
                ", color=" + super.color + ", tipo_combustible=" + super.tipo_combustible +", vidrio="+ this.vidrios + ", Transmision="+this.transmision + ", precio=" + super.precio + '}';
    }

}

