/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.model;

/**
 *
 * @author rsgar
 */
public class Camioneta extends Vehiculo{
    private String traccion;
    private String transmision;
    private String vidrios;
    
    public Camioneta(String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, String vidrios, String transmision, String traccion, double precio){
        super(placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
        this.traccion = traccion;
        this.vidrios = vidrios;
        this.transmision = transmision;
    }
    
    public String getVidrios() {
        return this.vidrios;
    }

    public void setVidrios(String vidrios) {
        this.vidrios = vidrios;
    }

    public String getTransmision() {
        return this.transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getTraccion() {
        return this.traccion;
    }

    public void setTraccion(String traccion) {
        this.traccion = traccion;
    }
    public static void nextCamioneta(Scanner sc, String nomfile){
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
        System.out.println("Ingrese el tipo de vidrio: ");
        String vidrio =sc.next();
        System.out.println("Ingrese el tipo de transmision: ");
        String transmision =sc.next();
        System.out.println("Ingrese el tipo de traccion: ");
        String traccion =sc.next();
        System.out.println("Ingrese el precio: ");
        String precio =sc.next();
        Camioneta c=new Camioneta(placa,marca, modelo, tipodeMotor, anio, recorrido,color,TipoCombustible,vidrio,transmision,traccion,precio);
        c.saveFile(nomfile);
    }
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipo_motor+"|"+this.anio+"|"+this.recorrido+"|"+this.color+"|"+this.tipo_combustible+"|"+this.vidrios+"|"+this.transmision+"|"+this.traccion+"|"+this.precio);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        }
}
