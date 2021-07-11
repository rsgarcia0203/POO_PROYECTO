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
    private int IDvendedor;
    private int IDvehiculo;
    private int IDtipo; //guardaremos el ID del tipo dependiendo del tipo
    private String correo;
    
    public Oferta(int ID, int IDvendedor, int IDvehiculo, int IDtipo, String correo){
        this.ID = ID;
        this.IDvendedor = IDvendedor;
        this.IDvehiculo = IDvehiculo;
        this.IDtipo = IDtipo;
        this.correo = correo;
    }

    public int getID() {
        return this.ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDvendedor() {
        return this.IDvendedor;
    }

    public void setIDvendedor(int IDvendedor) {
        this.IDvendedor = IDvendedor;
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
    
    public String getCorreo() {
        return this.correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public void saveFile(String nomfile, String correo)
    {
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.ID+"|"+this.IDvendedor+"|"+this.IDvehiculo+"|"+correo);
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
                Oferta o = new Oferta(Integer.parseInt(arreglo[0]), Integer.parseInt(arreglo[1]), Integer.parseInt(arreglo[2]), Integer.parseInt(arreglo[3]), arreglo[4]);
                oferta.add(o);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return oferta;
    }
    
    @Override
    public String toString() {
        return  this.ID + ", " + this.IDvendedor + ", " + this.IDvehiculo + ", " + this.IDtipo + ", " + this.correo;
    }
    
    public static Oferta registrarNuevaOferta(Vehiculo vehiculo, Comprador comprador, String nomfile)
     {
        int id = Util.nextID(nomfile);
        int IDvendedor = vehiculo.getIDvendedor();
        int IDvehiculo = vehiculo.getIDvehiculo();
        int IDtipo = 0;
        if (vehiculo instanceof Automovil)
            IDtipo = 1; 
        else if (vehiculo instanceof Motocicleta)
            IDtipo = 2; 
        else if (vehiculo instanceof Camioneta)
            IDtipo = 3; 
        String correo = comprador.getCorreo();
        Oferta nuevo = new Oferta(id,IDvendedor,IDvehiculo, IDtipo,correo);
        return nuevo;
    }
    
    public static void link(ArrayList<Vendedor> vendedores, ArrayList<Vehiculo> vehiculos, ArrayList<Ingreso> ingresos){
        for(Ingreso i: ingresos){
            //Vehiculo vh = Vehiculo.searchByID(vehiculos, i.getIdVehiculo());
            //Vendedor v = Vendedor.searchByID(vendedores, i.getIdVendedor());
            //v.getVehiculos().add(vh);
            //.setVendedor(v);
            //i.setVendedor(v);
            //i.setVehiculo(vh);
        }
    }
}
