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
public class Automovil extends Vehiculo{
    private String vidrios;
    private String transmision;
    
    public Automovil(String placa, String marca, String modelo, String tipo_motor, int año, double recorrido, String color, String tipo_combustible, String vidrios, String transmision, String traccion, double precio){
        super(placa, marca, modelo, tipo_motor, año, recorrido, color, tipo_combustible, precio);
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
    public void saveFile(String nomfile){
        try(PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomfile),true)))
        {
            pw.println(this.placa+"|"+this.marca+"|"+this.modelo+"|"+this.tipo_motor+"|"+this.anio+"|"+this.recorrido+"|"+this.color+"|"+this.tipo_combustible+"|"+this.vidrios+"|"+this.transmision+"|"+this.precio);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        }
}

