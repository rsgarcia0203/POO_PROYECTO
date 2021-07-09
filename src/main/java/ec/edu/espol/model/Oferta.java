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
    private String correo;
    
    public Oferta(int ID, int IDvendedor, int IDvehiculo, String correo){
        this.ID = ID;
        this.IDvendedor = IDvendedor;
        this.IDvehiculo = IDvehiculo;
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
            pw.println(this.ID+"|"+this.IDvendedor+"|"+this.IDvehiculo+"|"+this.correo);
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
                Oferta o = new Oferta(Integer.parseInt(arreglo[0]), Integer.parseInt(arreglo[1]), Integer.parseInt(arreglo[2]), arreglo[3]);
                oferta.add(o);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return oferta;
    }
    
    @Override
    public String toString() {
        return  this.ID + ", " + this.IDvendedor + ", " + this.IDvehiculo + ", " + this.correo;
    }
    
    public static Oferta registrarNuevaOferta(Vendedor vendedor, Vehiculo vehiculo, Comprador comprador, String nomfile)
     {
        int id = Util.nextID(nomfile);
        int IDvendedor = vendedor.getID();
        int IDvehiculo = vehiculo.getID();
        String correo = comprador.getCorreo();
        Oferta nuevo = new Oferta(id,IDvendedor,IDvehiculo,correo);
        return nuevo;
    }
    
    public static void link(ArrayList<Vendedor> vendedores, ArrayList<Vehiculo> vehiculos, ArrayList<Ingreso> ingresos){
        for(Ingreso i: ingresos){
            Vehiculo vh = Vehiculo.searchByID(vehiculos, i.getIdVehiculo());
            Vendedor v = Vendedor.searchByID(vendedores, i.getIdVendedor());
            v.getVehiculos().add(vh);
            vh.setVendedor(v);
            i.setVendedor(v);
            i.setVehiculo(vh);
        }
    }
    
    public void OfertarVehiculo(Comprador comprador, ArrayList<Vehiculo> vehiculos){
        
    }
}
