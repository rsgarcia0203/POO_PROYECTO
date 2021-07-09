/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyecto1p;


import ec.edu.espol.model.Automovil;
import ec.edu.espol.model.Camioneta;
import ec.edu.espol.model.Comprador;
import java.io.IOException;
import java.util.Scanner;
import ec.edu.espol.model.Motocicleta;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.util.Util;
import java.security.InvalidParameterException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import javax.mail.MessagingException;

/**
 *
 * @author rsgar
 */
public class Main {

 
    /**
     * @param args the command line arguments
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.IOException
     */
    
    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        // TODO code application logic here                
        Scanner sn = new Scanner(System.in);
        int id;
        int opcion; //Guardaremos la opcion del usuario
        int subopcion;

        do{
            boolean sub_salir = false;
            System.out.println("===MENU DE OPCIONES===");
            System.out.println("1. Vendedor");
            System.out.println("2. Comprador");
            System.out.println("3. Salir\n");
            
            System.out.println("Escribe una de las opciones: ");
            opcion = sn.nextInt();
            
            switch(opcion){
                case 1:
                {
                    do{
                        ArrayList<Vendedor> vendedores = Vendedor.readFile("vendedor.txt");
                        ArrayList<Automovil> automoviles = Automovil.readFile("automovil.txt");
                        ArrayList<Camioneta> camionetas = Camioneta.readFile("camioneta.txt");
                        ArrayList<Motocicleta> motocicletas = Motocicleta.readFile("motocicleta.txt");
                        System.out.println("\n==OPCIONES DEL VENDEDOR==");
                        System.out.println("1. Registrar un nuevo vendedor");
                        System.out.println("2. Ingresar un nuevo vehiculo");
                        System.out.println("3. Aceptar oferta");
                        System.out.println("4. Regresar\n");
                    
                        System.out.println("Escribe una de las opciones: ");
                        subopcion = sn.nextInt();
                        switch(subopcion){
                            case 1:
                                System.out.println("\n=REGISTRAR=");
                                Vendedor.registrarNuevoVendedor(sn,vendedores,"vendedor.txt");
                                break;    
                                
                                    
                            case 2:
                                System.out.println("\n=INGRESAR VEHICULO=");
                                System.out.println("Ingrese correo: ");
                                String correo = sn.next();
                                System.out.println("Ingrese clave: ");
                                String clave = sn.next();
                                for (int i=0;i<vendedores.size();i++){
                                    String clave_i = vendedores.get(i).getClave();//clave del vendedor que estamos tomando
                                    String correo_i = vendedores.get(i).getCorreo();//correo del vendedor que estamos tomando
                                    if (correo_i.equals(correo)){
                                        if(clave_i.equals(Util.toHexString(Util.getSHA(clave)))){
                                            System.out.println("\nBienvenido " + vendedores.get(i).getNombres() + " " + vendedores.get(i).getApellidos() + " de la organización " + vendedores.get(i).getOrganizacion());
                                            System.out.println("Ingrese el tipo de vechiculo(auto/motocicleta/camioneta): ");  
                                            String tipo = sn.next();
                                            id = vendedores.get(i).getID(); //obtenemos el ID del vendedor
                                            switch (tipo) {
                                                case "auto":
                                                    Automovil.nextAutomovil(sn, id, automoviles, "automovil.txt");
                                                    break;
                                                case "motocicleta":
                                                    Motocicleta.nextMotocicleta(sn, id, motocicletas, "motocicleta.txt");
                                                    break;
                                                case "camioneta":
                                                    Camioneta.nextCamioneta(sn, id, camionetas, "camioneta.txt");
                                                    break;
                                                default:
                                                    System.out.println("Ingrese un tipo de vehiculo correcto.");
                                                    break;
                                            }
                                        }
                                        else 
                                            System.out.println("Clave incorrecta");
                                    }    
                                }   
                                System.out.println("Correo incorrecto");
                                break;
                            
                            case 3:
                                System.out.println("\n=ACEPTAR OFERTA=");
                                System.out.println("Ingrese correo: ");
                                correo = sn.next();
                                System.out.println("Ingrese clave: ");
                                clave = sn.next();
                                for (int i=0;i<vendedores.size();i++){
                                    String clave_i = vendedores.get(i).getClave();//clave del vendedor que estamos tomando
                                    String correo_i = vendedores.get(i).getCorreo();//correo del vendedor que estamos tomando
                                    if (correo_i.equals(correo) && clave_i.equals(Util.toHexString(Util.getSHA(clave)))){
                                        System.out.println("Ingrese la placa del vehiculo: ");
                                        String placa = sn.nextLine();                                     
                                        Util.enviarEmail("rsgarcia@espol.edu.ec", "prueba");
                                    }
                                    
                                    else
                                        System.out.println("Usuario o contraseña incorrectos");
                                    
                                }
                                break;
                                
                            case 4:
                                sub_salir = true;
                                break;
                            
                            default:
                                System.out.println("El submenu solo tiene 4 opciones");
                        }
                    }
                    while(subopcion != 4);
                }
                    
                case 2:
                    do{
                        ArrayList<Comprador> compradores = Comprador.readFile("comprador.txt");
                        ArrayList<Automovil> automoviles = Automovil.readFile("automovil.txt");
                        ArrayList<Camioneta> camionetas = Camioneta.readFile("camioneta.txt");
                        ArrayList<Motocicleta> motocicletas = Motocicleta.readFile("motocicleta.txt");
                        System.out.println("\n==OPCIONES DEL COMPRADOR==");
                        System.out.println("1. Registrar un nuevo comprador");
                        System.out.println("2. Ofertar por un vehículo");
                        System.out.println("3. Regresar");
                    
                        System.out.println("Escribe una de las opciones: ");
                        subopcion = sn.nextInt();
                        switch(subopcion){
                            case 1:
                                System.out.println("\n=REGISTRAR=");
                                Comprador.registrarNuevoComprador(sn, compradores, "comprador.txt");
                                break;
                            
                            case 2:
                                System.out.println("\n=OFERTAR=");
                                System.out.println("Ingrese el tipo de vehiculo: ");
                                String tipo = sn.next();
                                switch (tipo) {
                                    case "automovil":
                                        break;
                                    case "camioneta":
                                        break;
                                    case "motocicleta":
                                        break;
                                    case "":
                                        break;
                                    default:
                                        System.out.println("Tipo de vehículo no valido");
                                        break;
                                }  
                                break;
                                
                            case 3:
                                sub_salir = true;
                                break;
                            default:
                                System.out.println("El submenu solo tiene 3 opciones");
                        }
                    }while(!sub_salir);

                    
                case 3:
                    System.out.println("Gracias");;
                    
                default:
                    System.out.println("El programa solo tiene 3 opciones");
           }
          
       }while(opcion != 3);
    
    }
}   
