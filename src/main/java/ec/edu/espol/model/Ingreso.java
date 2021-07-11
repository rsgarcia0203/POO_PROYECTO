/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

import ec.edu.espol.util.Util;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author rsgar
 */
public class Ingreso {
    private int ID;
    private int idVendedor;
    private int idVehiculo;
    private int idTipo;
    private Vendedor vendedor;
    private Vehiculo vehiculo;

    public Ingreso(int ID, int idVendedor, int idVehiculo, int idTipo) {
        this.ID = ID;
        this.idVendedor = idVendedor;
        this.idVehiculo = idVehiculo;
        this.idTipo = idTipo;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public int getIdVehiculo() {
        return this.idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }
    
    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
    
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.ID+"|"+this.idVendedor+"|"+this.idVehiculo+"|"+this.idTipo);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    public static ArrayList<Ingreso> readFile(String nomfile){
        ArrayList<Ingreso> ingresos = new ArrayList<>();
        try(Scanner sc = new Scanner(new File(nomfile))){
            while(sc.hasNextLine())
            {
                String linea = sc.nextLine();
                String[] tokens = linea.split("\\|");
                Ingreso i = new Ingreso(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                ingresos.add(i);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ingresos;
    }
    
    public static void nextIngreso(Vehiculo vehiculo, String nomfile){
        int ID = Util.nextID(nomfile);
        int idvehiculo = vehiculo.getIDvehiculo();
        int idvendedor = vehiculo.getIDvendedor();
        if(vehiculo instanceof Automovil)
        {
            Ingreso i = new Ingreso(ID,idvendedor,idvehiculo,1);
            i.saveFile(nomfile);
        }
        else if(vehiculo instanceof Motocicleta)
        {
            Ingreso i = new Ingreso(ID,idvendedor,idvehiculo,2);
            i.saveFile(nomfile);
        }
        else if(vehiculo instanceof Camioneta)
        {
            Ingreso i = new Ingreso(ID,idvendedor,idvehiculo,3);
            i.saveFile(nomfile);
        }    
        
    }
    
    public static void link(ArrayList<Vendedor> vendedores, ArrayList<Vehiculo> vehiculos, ArrayList<Automovil> automoviles, ArrayList<Camioneta> camionetas, ArrayList<Motocicleta> motocicletas, ArrayList<Ingreso> ingresos){
        for(Ingreso i: ingresos){
            Vendedor v = Vendedor.searchByID(vendedores, i.getIdVendedor());
            switch(i.getIdTipo())
            {
                case 1:
                    Automovil au = Automovil.searchByID(automoviles, i.getIdVehiculo());
                    v.getVehiculos().add(au);
                    au.setVendedor(v);
                    i.setVendedor(v);
                    i.setVehiculo(au);
                    vehiculos.add(au);
                    break; 
                   
                case 2:
                    Motocicleta mo = Motocicleta.searchByID(motocicletas, i.getIdVehiculo());
                    v.getVehiculos().add(mo);
                    mo.setVendedor(v);
                    i.setVendedor(v);
                    i.setVehiculo(mo);
                    vehiculos.add(mo);
                    break; 
     
                case 3:
                    Camioneta ca = Camioneta.searchByID(camionetas, i.getIdVehiculo());
                    v.getVehiculos().add(ca);
                    ca.setVendedor(v);
                    i.setVendedor(v);
                    i.setVehiculo(ca);
                    vehiculos.add(ca);
                    break; 
            }                      
        }
    }
    
    public static void eliminarIngreso(ArrayList<Ingreso> ingresos, Vehiculo vehiculo) throws IOException
    {
        for(int i = 0; i < ingresos.size(); i++)
        {
            if (ingresos.get(i).getVehiculo().equals(vehiculo))
            {
                ingresos.remove(i);
            }
        }    
        Util.limpiarArchivo("ingreso.txt");
        for(Ingreso i: ingresos)
        {
            i.saveFile("ingreso.txt");
        }
    }

    @Override
    public String toString() {
        return "Ingreso<" + this.ID + ">{idVendedor=" + this.idVendedor + ", idVehiculo=" + this.idVehiculo + ", idTipo=" + this.idTipo + ", vendedor=" + this.vendedor + ", vehiculo=" + this.vehiculo + '}';
    }
    
    
}
