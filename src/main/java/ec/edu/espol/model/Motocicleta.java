/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.util.Scanner;

/**
 *
 * @author rsgar
 */
public class Motocicleta extends Vehiculo{
    
     public Motocicleta(String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, double precio){
        super(placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
    }
    
     public static Vehiculo registrarNuevoVehiculo(Scanner sc)
     {
        System.out.println("Ingrese los nombres: ");
        String nombres = sc.next();
        System.out.println("Ingrese los apellidos: ");
        String apellidos = sc.next();
        System.out.println("Ingrese el nombre de la organización: ");
        String organizacion = sc.next();
        System.out.println("Ingrese su dirección de correo electrónico: ");
        String correo = sc.next();
        System.out.println("Ingrese su clave: ");
        String clave = sc.next();
        //Vehiculo nuevo = new Vehiculo(nombres,apellidos,organizacion,correo,clave);
        return null;
    }
}
