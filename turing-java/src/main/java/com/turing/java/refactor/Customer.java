package com.turing.java.refactor;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 顾客
 *
 * @author xuweizhi
 * @since 2020/09/25 11:01
 */
@Data
public class Customer {
    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental arg) {
        rentals.add(arg);
    }

    public String statement() {

        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
        for (Rental rental : rentals) {
            // 计算积分值进行优化
            result.append("\t").append(rental.getMovie().getTitle()).append("\t").append(rental.getCharge()).append("\n");
        }
        result.append("Amount owed is ").append(getTotalCharge()).append("\n");
        result.append("You earned ").append(getTotalFrequentRenterPoints()).append(" frequent renter points");
        return result.toString();
    }

    public static void main(String[] args) {
        Customer customer = new Customer("杨林");
        customer.addRental(new Rental(new Movie("泰坦尼克号", 1), 15));
        customer.addRental(new Rental(new Movie("Halloween", 2), 25));
        customer.addRental(new Rental(new Movie("ketty", 1), 35));
        System.out.println(customer.statement());
    }

    private double getTotalCharge() {
        return rentals.stream().mapToDouble(Rental::getCharge).sum();
    }

    private int getTotalFrequentRenterPoints() {
        return rentals.stream().map(Rental::getFrequentRenterPoint).mapToInt(value -> value).sum();
    }

    //public String statement() {
    //    double totalAmount = 0;
    //
    //    int frequentRenterPoints = 0;
    //
    //    StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");
    //    ListIterator<Rental> rental = this.rentals.listIterator();
    //    while (rental.hasNext()) {
    //        double thisAmount = 0;
    //        Rental each = rental.next();
    //        switch (each.getMovie().getPriceCode()) {
    //            case Movie.REGULAR:
    //                thisAmount += 2;
    //                if (each.getDaysRented() > 2) {
    //                    thisAmount += (each.getDaysRented() - 2) * 1.5;
    //                }
    //                break;
    //            case Movie.NEW_RELEASE:
    //                thisAmount += each.getDaysRented() * 3;
    //                break;
    //            case Movie.CHILDRENS:
    //                thisAmount += 1.5;
    //                if (each.getDaysRented() > 3) {
    //                    thisAmount += (each.getDaysRented() - 3) * 1.5;
    //                }
    //                break;
    //            default:
    //                throw new RuntimeException("未找到对应影片信息");
    //        }
    //        frequentRenterPoints++;
    //        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) && each.getDaysRented() > 1) {
    //            frequentRenterPoints++;
    //        }
    //        result.append("\t").append(each.getMovie().getTitle()).append("\t").append(thisAmount).append("\n");
    //        totalAmount += thisAmount;
    //    }
    //    result.append("Amount owed is ").append(totalAmount).append("\n");
    //    result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");
    //    return result.toString();
    //}
}
