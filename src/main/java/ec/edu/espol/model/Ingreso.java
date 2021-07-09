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
public class Ingreso {
    private int ID;
    private int idVendedor;
    private int idVehiculo;
    private Vendedor vendedor;
    private Vehiculo vehiculo;

    public Ingreso(int ID, int idVendedor, int idVehiculo) {
        this.ID = ID;
        this.idVendedor = idVendedor;
        this.idVehiculo = idVehiculo;
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
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
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
            pw.println(this.ID+"|"+this.idVendedor+"|"+this.idVehiculo);
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
                Ingreso i = new Ingreso(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[1]),Integer.parseInt(tokens[2]));
                ingresos.add(i);
            }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return ingresos;
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
    
}
