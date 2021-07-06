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
        m.saveFile(nomfile);
        
    }
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipo_motor+"|"+this.anio+"|"+this.recorrido+"|"+this.color+"|"+this.tipo_combustible+"|"+this.precio);
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
                // linea = "1|20201010|eduardo|cruz"
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Motocicleta e;
                e = new Motocicleta(tokens[0],tokens[1],tokens[2],tokens[3],Integer.parseInt(tokens[4]),Double.parseDouble(tokens[5]),tokens[6],tokens[7],Double.parseDouble(tokens[8]));
                motocicleta.add(e);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return motocicleta;
    }

    
}
