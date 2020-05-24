/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.services;

import model.entities.CarRental;
import model.entities.Invoice;

/**
 *
 * @author Dell
 */
public class RentalServices {

    private Double pricePerDay;
    private Double pricePerHour;

    private BrazilTaxServices taxServices;

    public RentalServices(Double pricePerDay, Double pricePerHour, BrazilTaxServices taxServices) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxServices = taxServices;
    }

    public void processInvoice(CarRental carRental) {

        long t1 = carRental.getStart().getTime();
        long t2 = carRental.getFinish().getTime();
        double hours = (double) (t2 - t1) / 1000 / 60 / 60;
        double basicPayment;
        if (hours <= 12.0) {
            basicPayment = Math.ceil(hours) * pricePerHour;

        }else{
            basicPayment  = Math.ceil(hours /24 )* pricePerDay;
        }
        
double tax = taxServices.text(basicPayment);
carRental.setInvoice(new Invoice(basicPayment, tax));
    }

}
