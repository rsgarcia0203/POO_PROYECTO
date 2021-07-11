package ec.edu.espol.proyecto1p;


import ec.edu.espol.model.Automovil;
import ec.edu.espol.model.Camioneta;
import ec.edu.espol.model.Comprador;
import ec.edu.espol.model.Ingreso;
import java.io.IOException;
import java.util.Scanner;
import ec.edu.espol.model.Motocicleta;
import ec.edu.espol.model.Oferta;
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
        Scanner sn = new Scanner(System.in);
        int id;
        int opcion; //Guardaremos la opcion del usuario
        int subopcion;

        do{
            boolean sub_salir = false;
            opcion = Util.MenuPrincipal(sn);
            
            switch(opcion){
                case 1:
                {
                    do{
                        ArrayList<Vendedor> vendedores = Vendedor.readFile("vendedor.txt");
                        ArrayList<Automovil> automoviles = Automovil.readFile("automovil.txt");
                        ArrayList<Camioneta> camionetas = Camioneta.readFile("camioneta.txt");
                        ArrayList<Motocicleta> motocicletas = Motocicleta.readFile("motocicleta.txt");
                        ArrayList<Ingreso> ingresos = Ingreso.readFile("ingreso.txt");
                        Ingreso.link(vendedores, automoviles, camionetas, motocicletas, ingresos);
                        subopcion = Util.MenuVendedor(sn);
                        switch(subopcion){
                            case 1:
                                Vendedor.registrarNuevoVendedor(sn,vendedores,"vendedor.txt");
                                break;    
                                
                                    
                            case 2:
                                Vendedor.registrarVehiculo(sn, vendedores, automoviles, camionetas, motocicletas);
                                break;
                            
                            case 3:
                                System.out.println("\n=ACEPTAR OFERTA=");
                                System.out.println("Ingrese correo: ");
                                String correo = sn.next();
                                System.out.println("Ingrese clave: ");
                                String clave = sn.next();
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
                        ArrayList<Oferta> ofertas = Oferta.readFile("oferta.txt");
                        Oferta.link(compradores, automoviles, camionetas, motocicletas, ofertas);
                        subopcion = Util.MenuComprador(sn);
                        switch(subopcion){
                            case 1:
                                Comprador.registrarNuevoComprador(sn, compradores, "comprador.txt");
                                break;
                            
                            case 2:
                                
                                 
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
