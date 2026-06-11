package giannibussoletti;

import giannibussoletti.entities.Costumer;
import giannibussoletti.entities.Order;
import giannibussoletti.entities.Product;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {

        DecimalFormat df = new DecimalFormat("###.##");

        Costumer gianni = new Costumer("Gianni", 2);
        Costumer francesco = new Costumer("Francesco", 1);
        Costumer giorgio = new Costumer("Giorgio", 1);
        Costumer lucrezia = new Costumer("Lucrezia", 3);
        Costumer martina = new Costumer("Martina", 2);

        Product signoreAnelli = new Product("Il Signore Degli Anelli", "Books", 103.50);
        Product harryPotter = new Product("Harry Potter", "Boys", 27.90);
        Product deBello = new Product("De Bello Gallico - Illustrato", "Books", 120.30);
        Product PercyJackson = new Product("Percy Jackson E Gli Dei Dell'Olimpo", "Boys", 23.60);
        Product primaveraPerduta = new Product("Primavera Perduta", "Boys", 109.74);
        Product tinTin = new Product("Le avventure di Tin Tin", "Boys", 113.50);
        Product favoleEsopo = new Product("Le Favole di Esopo", "Baby", 10.90);
        Product gattoStivali = new Product("Il Gatto Con Gli Stivali", "Books", 23.50);
        Product cenerentola = new Product("Cenerentola", "Baby", 10.50);

        List<Product> cart1 = new ArrayList<>(List.of(signoreAnelli, harryPotter, deBello, PercyJackson, primaveraPerduta, tinTin));
        List<Product> cart2 = new ArrayList<>(List.of(deBello, primaveraPerduta, favoleEsopo, gattoStivali, cenerentola));
        List<Product> cart3 = new ArrayList<>(List.of(signoreAnelli, deBello, primaveraPerduta, favoleEsopo, cenerentola));
        List<Product> cart4 = new ArrayList<>(List.of(signoreAnelli, harryPotter, deBello, PercyJackson, primaveraPerduta, tinTin, favoleEsopo, gattoStivali, cenerentola));
        List<Product> cart5 = new ArrayList<>(List.of(signoreAnelli));


        Order order1 = new Order("delivered", LocalDate.of(2021, 2, 1), LocalDate.of(2021, 2, 3), cart1, gianni);
        Order order6 = new Order("delivered", LocalDate.of(2022, 2, 1), LocalDate.of(2023, 2, 3), cart3, gianni);
        Order order2 = new Order("delivered", LocalDate.of(2021, 3, 4), LocalDate.of(2021, 3, 10), cart2, martina);
        Order order3 = new Order("shipping", LocalDate.of(2021, 10, 4), LocalDate.of(2021, 11, 10), cart3, lucrezia);
        Order order4 = new Order("delivered", LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 10), cart4, francesco);
        Order order5 = new Order("shipping", LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 10), cart5, giorgio);
        Order order7 = new Order("shipping", LocalDate.of(2021, 4, 1), LocalDate.of(2021, 5, 10), cart5, giorgio);


        List<Order> orders = new ArrayList<>(List.of(order1, order2, order3, order4, order5, order6, order7));


        Map<Costumer, List<Order>> groupingByOrders = orders.stream().collect(Collectors.groupingBy(Order::getCostumer));
        groupingByOrders.forEach((costumer, ordersList) -> System.out.println("Cliente: " + costumer + "\n" + "Ordine: " + ordersList));

        Map<Costumer, Double> calculateTotalAll = orders.stream().collect(Collectors.groupingBy(Order::getCostumer, Collectors.summingDouble(Order::calculateTotal)));
        calculateTotalAll.forEach((costumer, aDouble) -> System.out.println(costumer + "Totale: " + aDouble));

        List<Product> maxCostProducts = cart4.stream().filter(product -> product.getPrice() > 99).sorted(Comparator.comparing(Product::getPrice).reversed()).toList();
        maxCostProducts.forEach(System.out::println);

        OptionalDouble averageTotal = orders.stream().mapToDouble(Order::calculateTotal).average();
        if (averageTotal.isPresent())
            System.out.println("La media di tutti gli ordini è: " + (Math.round(averageTotal.getAsDouble() * 100) / 100));
        else System.out.println("Non è stato possibile fare la media");

        Map<String, Double> totalCategoryPrice = cart4.stream().collect(Collectors.groupingBy(Product::getCategory, Collectors.summingDouble(Product::getPrice)));
        totalCategoryPrice.forEach((product, totalPriceCat) -> System.out.println(product + totalPriceCat));

    }
}
