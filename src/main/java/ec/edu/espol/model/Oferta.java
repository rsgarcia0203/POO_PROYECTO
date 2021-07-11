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
public class Oferta {
    private int ID;
    private int IDvehiculo;
    private int IDtipo; //guardaremos el ID del tipo dependiendo del tipo
    private int IDcomprador;
    private Vehiculo vehiculo;
    private Comprador comprador;
    
    public Oferta(int ID, int IDvehiculo, int IDtipo, int IDcomprador){
        this.ID = ID;
        this.IDvehiculo = IDvehiculo;
        this.IDtipo = IDtipo;
        this.IDcomprador = IDcomprador;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDvehiculo() {
        return this.IDvehiculo;
    }

    public void setIDvehiculo(int IDvehiculo) {
        this.IDvehiculo = IDvehiculo;
    }

    public int getIDtipo() {
        return IDtipo;
    }

    public void setIDtipo(int IDtipo) {
        this.IDtipo = IDtipo;
    }

    public int getIDcomprador() {
        return IDcomprador;
    }

    public void setIDcomprador(int IDcomprador) {
        this.IDcomprador = IDcomprador;
    }
    
    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Comprador getComprador() {
        return comprador;
    }

    public void setComprador(Comprador comprador) {
        this.comprador = comprador;
    }
    
    public void saveFile(String nomfile, String correo)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.ID+"|"+this.IDvehiculo+"|"+this.IDtipo+"|"+this.IDcomprador);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    public static ArrayList<Oferta> readFile(String nomFile){ 
        
        ArrayList<Oferta> oferta = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomFile))){
            while (sc.hasNextLine()){
                String linea = sc.next();
                String [] arreglo = linea.split("\\|");
                Oferta o = new Oferta(Integer.parseInt(arreglo[0]), Integer.parseInt(arreglo[1]), Integer.parseInt(arreglo[2]), Integer.parseInt(arreglo[3]));
                oferta.add(o);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return oferta;
    }
    
    @Override
    public String toString() {
        return  this.ID + ", " + this.IDvehiculo + ", " + this.IDtipo + ", " + this.IDcomprador;
    }
    
    public static Oferta registrarNuevaOferta(Vehiculo vehiculo, Comprador comprador, String nomfile)
     {
        int id = Util.nextID(nomfile);
        int IDvehiculo = vehiculo.getIDvehiculo();
        int IDtipo = 0;
        int IDcomprador = comprador.getID();
        if (vehiculo instanceof Automovil)
            IDtipo = 1; 
        else if (vehiculo instanceof Motocicleta)
            IDtipo = 2; 
        else if (vehiculo instanceof Camioneta)
            IDtipo = 3; 
        Oferta nuevo = new Oferta(id,IDvehiculo, IDtipo, IDcomprador);
        return nuevo;
    }
    
    public static void link(ArrayList<Comprador> compradores, ArrayList<Automovil> automoviles, ArrayList<Camioneta> camionetas, ArrayList<Motocicleta> motocicletas, ArrayList<Oferta> ofertas){
        for(Oferta o: ofertas){
            Comprador c = Comprador.searchByID(compradores, o.getIDcomprador());
            switch(o.getIDtipo())
            {
                case 1:
                    Automovil au = Automovil.searchByID(automoviles, o.getIDvehiculo());
                    au.getOfertas().add(o);
                    o.setComprador(c);
                    o.setVehiculo(au);
                    break; 
                   
                case 2:
                    Motocicleta mo = Motocicleta.searchByID(motocicletas, o.getIDvehiculo());
                    mo.getOfertas().add(o);
                    o.setComprador(c);
                    o.setVehiculo(mo);
                    break; 
                    
                case 3:
                    Camioneta ca = Camioneta.searchByID(camionetas, o.getIDvehiculo());
                    ca.getOfertas().add(o);
                    o.setComprador(c);
                    o.setVehiculo(ca);
                    break; 
            }                      
        }
    }
}
