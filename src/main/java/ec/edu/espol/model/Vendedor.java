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
import java.util.Scanner;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 *
 * @author rsgar
 */
public class Vendedor {

    int ID;
    String nombres;
    String apellidos;
    String organizacion;
    String correo;
    String clave;
    ArrayList<Vehiculo> vehiculos;

    public Vendedor(int ID, String nombres, String apellidos, String organizacion, String correo, String clave) {
        this.ID = ID;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        this.vehiculos = new ArrayList<>();
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

    public ArrayList<Vehiculo> getVehiculos() {
        return this.vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }

    public void saveFile(String nomFile) {
        try (PrintWriter pw = new PrintWriter(new FileOutputStream(new File(nomFile), true))) {
            pw.println(this.ID + "|" + this.nombres + "|" + this.apellidos + "|" + this.organizacion + "|" + this.correo + "|" + Util.toHexString(Util.getSHA(this.clave)));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static ArrayList<Vendedor> readFile(String nomFile) {

        ArrayList<Vendedor> vendedor = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(nomFile))) {
            while (sc.hasNextLine()) {
                String linea = sc.nextLine();
                String[] arreglo = linea.split("\\|");
                Vendedor v;
                v = new Vendedor(Integer.parseInt(arreglo[0]), arreglo[1], arreglo[2], arreglo[3], arreglo[4], arreglo[5]);
                vendedor.add(v);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return vendedor;
    }

    public static Vendedor searchByID(ArrayList<Vendedor> vendedores, int id) {
        for (Vendedor v : vendedores) {
            if (v.ID == id) {
                return v;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.ID + ", " + this.nombres + ", " + this.apellidos + ", " + this.organizacion + ", " + this.correo + ", " + this.clave;
    }

    public static void registrarNuevoVendedor(Scanner sc, ArrayList<Vendedor> vendedores, String nomfile) {
        System.out.println("\n=REGISTRAR=");
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
        Vendedor nuevo = new Vendedor(id, nombres, apellidos, organizacion, correo, clave);
        if (vendedores.isEmpty()) {
            nuevo.saveFile(nomfile);
            System.out.println("Vendedor registrado!");
        } else {
            for (int i = 0; i < vendedores.size(); i++) {
                if (!(vendedores.get(i).getCorreo().equals(correo))) {
                    nuevo.saveFile(nomfile);
                    System.out.println("Vendedor registrado!");
                } else {
                    System.out.println("Correo repetido, no se puede registrar!");
                }
            }
        }
    }

    public static void registrarVehiculo(Scanner sc, ArrayList<Vendedor> vendedores, ArrayList<Automovil> automoviles, ArrayList<Camioneta> camionetas, ArrayList<Motocicleta> motocicletas) throws NoSuchAlgorithmException {
        System.out.println("\n=INGRESAR VEHICULO=");
        System.out.println("Ingrese correo: ");
        String correo = sc.next();
        System.out.println("Ingrese clave: ");
        String clave = sc.next();
        boolean validarCorreo = false;
        for (int i = 0; i < vendedores.size(); i++) {
            String clave_i = vendedores.get(i).getClave();//clave del vendedor que estamos tomando
            String correo_i = vendedores.get(i).getCorreo();//correo del vendedor que estamos tomando

            if (correo_i.equals(correo)) {
                validarCorreo = true;
                if (clave_i.equals(Util.toHexString(Util.getSHA(clave)))) {
                    System.out.println("\nBienvenido " + vendedores.get(i).getNombres() + " " + vendedores.get(i).getApellidos() + " de la organización " + vendedores.get(i).getOrganizacion());
                    System.out.println("Ingrese el tipo de vechiculo(auto/motocicleta/camioneta): ");
                    String tipo = sc.next();
                    int id = vendedores.get(i).getID(); //obtenemos el ID del vendedor
                    switch (tipo) {
                        case "auto":
                            Automovil.nextAutomovil(sc, id, automoviles, "automovil.txt");
                            break;
                        case "motocicleta":
                            Motocicleta.nextMotocicleta(sc, id, motocicletas, "motocicleta.txt");
                            break;
                        case "camioneta":
                            Camioneta.nextCamioneta(sc, id, camionetas, "camioneta.txt");
                            break;
                        default:
                            System.out.println("Ingrese un tipo de vehiculo correcto.");
                            break;
                    }
                } else {
                    System.out.println("Clave incorrecta");
                }
            }
        }
        if (validarCorreo == false) {
            System.out.println("Correo incorrecto");
        }
    }

    public static void aceptarOferta(Scanner sc, ArrayList<Vendedor> vendedores, ArrayList<Oferta> ofertas) throws NoSuchAlgorithmException, IOException {
        System.out.println("\n=OFERTAS=");
        System.out.println("Ingrese correo: ");
        String correo = sc.next();
        System.out.println("Ingrese clave: ");
        String clave = sc.next();
        boolean validarCorreo = false;
        for (int i = 0; i < vendedores.size(); i++) {
            String clave_i = vendedores.get(i).getClave();//clave del vendedor que estamos tomando
            String correo_i = vendedores.get(i).getCorreo();//correo del vendedor que estamos tomando

            if (correo_i.equals(correo)) {
                validarCorreo = true;
                if (clave_i.equals(Util.toHexString(Util.getSHA(clave)))) {
                    System.out.println("\nBienvenido " + vendedores.get(i).getNombres() + " " + vendedores.get(i).getApellidos() + " de la organización " + vendedores.get(i).getOrganizacion());
                    System.out.println("Ingrese una placa: ");
                    String placa = sc.next();
                    for (Vehiculo v : vendedores.get(i).getVehiculos()) //recorremos los vehiculos que ha registrado ese vendedor
                    {
                        if (v.getPlaca().equals(placa)) {
                            System.out.println(v.getMarca() + " " + v.getModelo() + "Precio: " + v.getPrecio());
                            System.out.println("Se han realizado " + v.getOfertas().size() + " ofertas");
                            ArrayList<Oferta> ofs = v.getOfertas();
                            for (int e = 0; e < ofs.size(); e++) {
                                System.out.println("Oferta" + (e + 1));
                                System.out.println(" Correo: " + ofs.get(e).getComprador().getCorreo());
                                System.out.println("Precio Ofertado" + ofs.get(e).getPrecioOfertado());
                                String op = Util.aceptarOfertas(sc, e, ofs.size()-1);
                                if (op.equals("anterior")) {
                                    e -= 2;
                                } 
                                else if (op.equals("aceptar")) {
                                    System.out.println("Oferta aceptada, se enviara un mensaje al correo del ofertante.");
                                    String mensaje = "<b>Estimado : " + ofs.get(e).getComprador().getNombres().toUpperCase() + ofs.get(e).getComprador().getApellidos().toUpperCase()+"</b>"+
                                            "Le informamos a Ud. que su oferta por el vehiculo de placas "+v.getPlaca()+" ha sido aceptada, por favor ponerse en contacto con el dueño del vehiculo antes singularizado."
                                            + "<em> SYSTEM-POO-G2 </em>" ;
                                    Util.enviarEmail(ofs.get(e).getComprador().getCorreo(), mensaje);
                                    
                                }

                            }
                        }
                    }
                } else {
                    System.out.println("Clave incorrecta");
                }
            }
        }
        if (validarCorreo == false) {
            System.out.println("Correo incorrecto");
        }

    }

}
