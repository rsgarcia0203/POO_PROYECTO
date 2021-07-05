/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.edu.espol.proyecto1p;


import java.io.IOException;
import java.util.Scanner;
import ec.edu.espol.model.Mail;
import ec.edu.espol.model.Vendedor;
import java.security.InvalidParameterException;
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
                                if(v1.getCorreo() == null){
                                    
                                }
                                    
                                
                                    
                            case 2:
                                
                                break;
                            case 3:
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
