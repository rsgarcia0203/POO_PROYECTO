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
}
