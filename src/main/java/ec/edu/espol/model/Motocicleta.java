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
public class Motocicleta extends Vehiculo{
    
     public Motocicleta(int ID, int IDvendedor, String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio){
        super(ID, IDvendedor, placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
    }
     
     public static Motocicleta nextMotocicleta(Scanner sc, int IDvendedor, String nomfile){
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
        System.out.println("Ingrese el precio: ");
        double precio =sc.nextDouble();
        Motocicleta m = new Motocicleta(id, IDvendedor, placa, marca, modelo, tipodeMotor, anio, recorrido, color, TipoCombustible, precio);
        return m;
        
    }
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.ID+"|"+this.IDvendedor+"|"+this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipo_motor+"|"+this.año+"|"+this.recorrido+"|"+this.color+"|"+this.tipo_combustible+"|"+this.precio);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        }
    public static ArrayList<Motocicleta> readFile(String nomfile){
        ArrayList<Motocicleta> motocicleta = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Motocicleta e;
                e = new Motocicleta(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),tokens[2],tokens[3],tokens[4],tokens[5],Integer.parseInt(tokens[6]),Double.parseDouble(tokens[7]),tokens[8],tokens[9],Double.parseDouble(tokens[10]));
                motocicleta.add(e);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return motocicleta;
    }
    @Override
    public String toString() {
        return "Motocicleta{ ID:" + super.ID + ", IDvendedor=" + + super.IDvendedor +  ", placa=" + super.placa + ", marca=" + super.marca + ", modelo=" + super.modelo + ", tipo_motor=" + super.tipo_motor + ", anio=" + super.año + ", recorrido=" + super.recorrido + 
                ", color=" + super.color + ", tipo_combustible=" + super.tipo_combustible + ", precio=" + precio + '}';
    }
}
