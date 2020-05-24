/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;
import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalServices;

/**
 *
 * @author Dell
 */
public class Program {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException  {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
        
        System.out.println("Entrar com os dados");
        System.out.print("Modelo do carro");
        String CarModel = sc.nextLine();
        
        System.out.print("Pickup em dd/MM/yyyy HH:ss");
        Date start = sdf.parse(sc.nextLine());
        System.out.print("Return em dd/MM/yyyy HH:ss");
        Date finish = sdf.parse(sc.nextLine());
        
        CarRental cr = new CarRental(start, finish, new Vehicle(CarModel));
        
        System.out.print("Entre com o preço por hora");
        double pricePerHour = sc.nextDouble();
         System.out.print("Entre com o preço por dia");
        double pricePerDay = sc.nextDouble();
       
        RentalServices rentalServices = new RentalServices(pricePerDay, pricePerHour, new BrazilTaxServices());
        
        rentalServices.processInvoice(cr);
        System.out.println("INVOICE");
        System.out.println("Basic payment: " +String.format(""+ cr.getInvoice().getBasicPayment()));
        
        System.out.println("Tex: " +String.format(""+ cr.getInvoice().getTax()));
         
          System.out.println("Total payment: " +String.format(""+ cr.getInvoice().getTotalPayment()));
        
        sc.close();
    }
    
}
