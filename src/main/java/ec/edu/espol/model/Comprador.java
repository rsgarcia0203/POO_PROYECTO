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
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rsgar
 */
public class Comprador {
    int ID;
    String nombres;
    String apellidos;
    String organizacion;
    String correo;
    String clave;

    public Comprador(int ID, String nombres, String apellidos, String organizacion, String correo, String clave) {
        this.ID = ID;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return this.organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
        
    public void saveFile(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            pw.println(this.ID+"|"+this.nombres+"|"+this.apellidos+"|"+this.organizacion+"|"+this.correo+"|"+Util.toHexString(Util.getSHA(this.clave)));
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Comprador> readFile(String nomFile){ 
        
        ArrayList<Comprador> comprador = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile))){
            while (sc.hasNextLine()){
                String linea = sc.next();
                String [] arreglo = linea.split("\\|");
                Comprador c = new Comprador(Integer.parseInt(arreglo[0]),arreglo[1], arreglo[2],arreglo[3], arreglo[4], arreglo[5]);
                comprador.add(c);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return comprador;
    }
    
    public static Comprador searchByID(ArrayList<Comprador> compradores, int id)
    {
        for(Comprador c : compradores)
        {
            if(c.ID == id)
                return c;
        }
        return null;
    }
    
    @Override
    public String toString() {
        return  this.ID + ", " + this.nombres + ", " + this.apellidos + ", "+ this.organizacion +", "+ this.correo+ ", " + this.clave;
    }
    
     public static void registrarNuevoComprador(Scanner sc, ArrayList<Comprador> compradores, String nomfile)
     {
        int id = Util.nextID(nomfile);
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
        Comprador c = new Comprador(id,nombres,apellidos,organizacion,correo,clave);
        if(compradores.isEmpty()){
            c.saveFile("comprador.txt");
            System.out.println("Comprador registrado!");

        }
        for (int i=0;i<compradores.size();i++){
            if (!(compradores.get(i).getCorreo().equals(c.getCorreo()))){
                c.saveFile("comprador.txt");
                System.out.println("Comprador registrado!");
            }
            else{
                System.out.println("Correo repetido, no se puede registrar!");                                       
            }
        }
    }
     
     public static void OfertarVehiculo(int año1, int año2, double recorrido1, double recorrido2, double precio1, double precio2){
        
     }
}
