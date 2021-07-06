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
public class Motocicleta extends Vehiculo{
    
     public Motocicleta(String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio){
        super(placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
    }
    
    public static void nextMotocicleta(Scanner sc, String nomfile){
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
        Motocicleta m = new Motocicleta(placa,marca,modelo,tipodeMotor,anio,recorrido,color,TipoCombustible,precio);
        m.saveFile("Motocicleta.txt");
        
    }
     public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.id+"|"+this.codigo+"|"+this.nombre);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
}
