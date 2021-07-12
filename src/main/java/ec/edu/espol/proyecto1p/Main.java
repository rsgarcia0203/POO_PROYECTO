package ec.edu.espol.proyecto1p;


import ec.edu.espol.model.Automovil;
import ec.edu.espol.model.Camioneta;
import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.Ingreso;
import java.io.IOException;
import java.util.Scanner;
import ec.edu.espol.model.Motocicleta;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.model.Vendedor;
import ec.edu.espol.util.Util;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

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
        Scanner sn = new Scanner(System.in);
        int id;
        int opcion; //Guardaremos la opcion del usuario
        int subopcion;
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        ArrayList<Vendedor> vendedores = Vendedor.readFile("vendedor.txt");
        ArrayList<Comprador> compradores = Comprador.readFile("comprador.txt");
        ArrayList<Automovil> automoviles = Automovil.readFile("automovil.txt");
        ArrayList<Camioneta> camionetas = Camioneta.readFile("camioneta.txt");
        ArrayList<Motocicleta> motocicletas = Motocicleta.readFile("motocicleta.txt"); 
        ArrayList<Ingreso> ingresos = Ingreso.readFile("ingreso.txt");
        ArrayList<Oferta> ofertas = Oferta.readFile("oferta.txt");
        Oferta.link(compradores, automoviles, camionetas, motocicletas, ofertas);
        Ingreso.link(vendedores, vehiculos, automoviles, camionetas, motocicletas, ingresos); 
        for (Vehiculo v: vehiculos){
            System.out.println(v);
        }
             
        do{
            boolean sub_salir = false;
            opcion = Util.MenuPrincipal(sn);
            
            switch(opcion){
                case 1:
                {
                    do{
                        vendedores = Vendedor.readFile("vendedor.txt");
                        automoviles = Automovil.readFile("automovil.txt");
                        camionetas = Camioneta.readFile("camioneta.txt");
                        motocicletas = Motocicleta.readFile("motocicleta.txt"); 
                        ingresos = Ingreso.readFile("ingreso.txt");
                        Oferta.link(compradores, automoviles, camionetas, motocicletas, ofertas);
                        Ingreso.link(vendedores, vehiculos, automoviles, camionetas, motocicletas, ingresos);
                        subopcion = Util.MenuVendedor(sn);
                        switch(subopcion){
                            case 1:
                                Vendedor.registrarNuevoVendedor(sn,vendedores,"vendedor.txt");
                                break;    
                                             
                            case 2:
                                Vendedor.registrarVehiculo(sn, vendedores, automoviles, camionetas, motocicletas);
                                break;
                            
                            case 3:
                                Vendedor.aceptarOferta(sn, vendedores, ofertas, ingresos, vehiculos);
                                break;
                                
                            case 4:
                                sub_salir = true;
                                break;
                            
                            default:
                                System.out.println("El submenu solo tiene 4 opciones");
                        }
                    }
                    while(subopcion != 4);
                    break;
                }
                    
                case 2:
                {
                    do{
                        compradores = Comprador.readFile("comprador.txt");
                        automoviles = Automovil.readFile("automovil.txt");
                        camionetas = Camioneta.readFile("camioneta.txt");
                        motocicletas = Motocicleta.readFile("motocicleta.txt");
                        ofertas = Oferta.readFile("oferta.txt");
                        Oferta.link(compradores, automoviles, camionetas, motocicletas, ofertas);
                        Ingreso.link(vendedores, vehiculos, automoviles, camionetas, motocicletas, ingresos); 
                        subopcion = Util.MenuComprador(sn);
                        switch(subopcion){
                            case 1:
                                Comprador.registrarNuevoComprador(sn, compradores, "comprador.txt");
                                break;
                            
                            case 2:
                                Comprador.OfertarVehiculo(sn, vehiculos, compradores);                                
                                break;
                                
                            case 3:
                                sub_salir = true;
                                break;
                            default:
                                System.out.println("El submenu solo tiene 3 opciones");
                        }
                    }while(!sub_salir);
                    break;
                }        
                    
                case 3:
                    System.out.println("\nGracias por usar nuestro sistema.");
                    break;
                    
                default:
                    System.out.println("El programa solo tiene 3 opciones");
           }
          
       }while(opcion != 3);  
    }
}   
