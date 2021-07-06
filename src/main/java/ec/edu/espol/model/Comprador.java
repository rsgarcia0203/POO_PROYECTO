/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 *
 * @author rsgar
 */
public class Comprador {
    String nombres;
    String apellidos;
    String organizacion;
    String correo;
    String clave;

    public Comprador(String nombres, String apellidos, String organizacion, String correo, String clave) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
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
    
    //funcion Hash
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    { 
        // Static getInstance method is called with hashing SHA 
        MessageDigest md = MessageDigest.getInstance("SHA-256"); 
  
        // digest() method called 
        // to calculate message digest of an input 
        // and return array of byte
        return md.digest(input.getBytes(StandardCharsets.UTF_8)); 
    }
    
    public static String toHexString(byte[] hash)
    {
        // Convert byte array into signum representation 
        BigInteger number = new BigInteger(1, hash); 
  
        // Convert message digest into hex value 
        StringBuilder hexString = new StringBuilder(number.toString(16)); 
  
        // Pad with leading zeros
        while (hexString.length() < 32) 
        { 
            hexString.insert(0, '0'); 
        } 
  
        return hexString.toString(); 
    }
    //
    
    public void saveFile(String nomFile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))){
            pw.println(nombres+"|"+apellidos+"|"+organizacion+"|"+correo+"|"+toHexString(getSHA(clave)));
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
                Comprador c = new Comprador(arreglo[0], arreglo[1],arreglo[2], arreglo[3], arreglo[4]);
                comprador.add(c);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return comprador;
    }
    @Override
    public String toString() {
        return  nombres + ", " + apellidos + ", "+ organizacion +", "+ correo+ ", " + clave) ;
    }
    
    
     public static Comprador registrarNuevoComprador(Scanner sc)
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
        Comprador nuevo = new Comprador(nombres,apellidos,organizacion,correo,clave);
        return nuevo;
    }
}
