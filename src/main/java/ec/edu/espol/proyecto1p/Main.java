/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyecto1p;


import ec.edu.espol.model.Comprador;
import java.io.IOException;
import java.util.Scanner;
import ec.edu.espol.model.Mail;
import ec.edu.espol.model.Vendedor;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import javax.mail.MessagingException;

/**
 *
 * @author rsgar
 */
public class Main {

 
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // TODO code application logic here                
        Scanner sn = new Scanner(System.in);
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
                                Vendedor v1 = Vendedor.registrarNuevoVendedor(sn);
                                ArrayList<Vendedor> vendedor = Vendedor.readFile("vendedor.txt");
                                for (int i=0;i<vendedor.size();i++){
                                   if (!(vendedor.get(i).getCorreo().equals(v1.getCorreo()))){
                                        v1.saveFile("vendedor.txt");
                                        System.out.println("Vendedor registrado!");
                                   }
                                   else{
                                        System.out.println("Correo repetido, no se puede registrar!");   
                                   }
                                }
                                    
       
                            case 2:
                                
                                 
                                 
                                
                                break;
                            case 3:
                                System.out.println("\n=ACEPTAR OFERTA=");
                                System.out.println("Ingrese correo: ");
                                String correo = sn.nextLine();
                                System.out.println("Ingrese clave: ");
                                String clave1 = sn.nextLine();
                                ArrayList<Vendedor> vendedores = Vendedor.readFile("vendedor.txt");
                                for (int i=0;i<vendedores.size();i++){
                                    if (Vendedor.getSHA(clave1).equals(vendedores.get(i).getClave())){
                                    System.out.println("Ingrese la placa del vechiculo: ");
                                    String placa = sn.nextLine();                                     
                                    
                                    }
                                
                                    
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
                                Comprador c1 = Comprador.registrarNuevoComprador(sn);
                                ArrayList<Comprador> comprador = Comprador.readFile("comprador.txt");
                                for (int i=0;i<comprador.size();i++){
                                   if (!(comprador.get(i).getCorreo().equals(c1.getCorreo()))){
                                        c1.saveFile("comprador.txt");
                                        System.out.println("Comprador registrado!");
                                   }
                                   else{
                                        System.out.println("Correo repetido, no se puede registrar!");                                       
                                   }
                                }
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
