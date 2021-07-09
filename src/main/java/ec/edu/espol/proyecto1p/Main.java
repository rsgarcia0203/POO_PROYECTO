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
        ArrayList<Vendedor> vendedores = Vendedor.readFile("vendedor.txt");
        ArrayList<Comprador> compradores = Comprador.readFile("comprador.txt");
        ArrayList<Automovil> automoviles = Automovil.readFile("automovil.txt");
        ArrayList<Camioneta> camionetas = Camioneta.readFile("camioneta.txt");
        ArrayList<Motocicleta> motocicletas = Motocicleta.readFile("motocicleta.txt");


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
                        System.out.println("==OPCIONES DEL VENDEDOR==");
                        System.out.println("1. Registrar un nuevo vendedor");
                        System.out.println("2. Ingresar un nuevo vehiculo");
                        System.out.println("3. Aceptar oferta");
                        System.out.println("4. Regresar\n");
                    
                        System.out.println("Escribe una de las opciones: ");
                        subopcion = sn.nextInt();
                        switch(subopcion){
                            case 1:
                                System.out.println("\n=REGISTRAR=");
                                Vendedor v1 = Vendedor.registrarNuevoVendedor(sn,vendedores,"vendedor.txt");
                                break;    
                                
                                    
                            case 2:
                                System.out.println("\n=INGRESAR VEHICULO=");
                                System.out.println("Ingrese correo: ");
                                String correo = sn.nextLine();
                                System.out.println("Ingrese clave: ");
                                String clave = sn.nextLine();
                                for (int i=0;i<vendedores.size();i++){
                                    String clave_i = vendedores.get(i).getClave();//clave del vendedor que estamos tomando
                                    String correo_i = vendedores.get(i).getCorreo();//correo del vendedor que estamos tomando
                                    if (correo_i.equals(correo) && clave_i.equals(Util.toHexString(Util.getSHA(clave)))){
                                        System.out.println("Bienvenido " + vendedores.get(i).getNombres() + " " + vendedores.get(i).getApellidos() + " de " + vendedores.get(i).getOrganizacion());
                                        System.out.println("Ingrese tipo de vechiculo(auto/motocicleta/camioneta): ");  
                                        String tipo = sn.nextLine();
                                        id = vendedores.get(i).getID(); //obtenemos el ID del vendedor
                                        if (tipo.equals("auto")){
                                            Automovil au = Automovil.nextAutomovil(sn, id, "automovil.txt");
                                            for (int j=0; j<automoviles.size();j++){
                                                if (au.getPlaca().equals(automoviles.get(j).getPlaca())){
                                                    System.out.println("Eror, placa existente en el sistema");
                                                }
                                                
                                                au.saveFile("automovil.txt");
                                            }
                                       
                                        }
                                        
                                        if (tipo.equals("motocicleta")){
                                            Motocicleta mo = Motocicleta.nextMotocicleta(sn, id, "motocicleta.txt");
                                            for (int j=0; j<motocicletas.size();j++){
                                                if (mo.getPlaca().equals(motocicletas.get(j).getPlaca())){
                                                    System.out.println("Error, placa existente en el sistema");
                                                }
                                                
                                                mo.saveFile("motocicleta.txt");
                                            }
                                            
                                        }
                                        if (tipo.equals("camioneta")){
                                            Camioneta ca = Camioneta.nextCamioneta(sn, id, "camioneta.txt");
                                            for (int j=0; j<camionetas.size();j++){
                                                if (ca.getPlaca().equals(camionetas.get(j).getPlaca())){
                                                    System.out.println("Error, placa existente en el sistema");
                                                }
                                                
                                                ca.saveFile("camioneta.txt");
                                            }
                                            
                                            
                                        }
                                    
                                    }
                                    else 
                                        System.out.println("Clave o correo incorrecto");
                                      
                                }
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
                    while(!sub_salir){
                        System.out.println("==OPCIONES DEL COMPRADOR==");
                        System.out.println("1. Registrar un nuevo comprador");
                        System.out.println("2. Ofertar por un vehículo");
                        System.out.println("3. Regresar");
                    
                        System.out.println("Escribe una de las opciones: ");
                        subopcion = sn.nextInt();
                        switch(subopcion){
                            case 1:
                                System.out.println("\n=REGISTRAR=");
                                Comprador c1 = Comprador.registrarNuevoComprador(sn,"comprador.txt");
                                for (int i=0;i<compradores.size();i++){
                                   if (!(compradores.get(i).getCorreo().equals(c1.getCorreo()))){
                                        c1.saveFile("comprador.txt");
                                        System.out.println("Comprador registrado!");
                                   }
                                   else{
                                        System.out.println("Correo repetido, no se puede registrar!");                                       
                                   }
                                }
                                break;
                            case 2:
                                break;
                            case 3:
                                sub_salir = true;
                                break;
                            default:
                                System.out.println("El submenu solo tiene 3 opciones");
                        }
                    }

                    
                case 3:
                    System.out.println("Gracias");;
                    
                default:
                    System.out.println("El programa solo tiene 3 opciones");
           }
          
       }while(opcion != 3);
    
    }
}   
