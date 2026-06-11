package giannibussoletti.entities;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

public class Order {
    private final Long id;
    private final String status;
    private final LocalDate orderDate;
    private final LocalDate deliveryDate;
    private final List<Product> products;
    private final Costumer costumer;

    Random random = new Random();
    DecimalFormat df = new DecimalFormat("#.##");

    public Order(String status, LocalDate orderDate, LocalDate deliveryDate, List<Product> products, Costumer costumer) {
        this.id = random.nextLong(1000000000000000000L, 9223372036854775807L);
        this.status = status;
        this.orderDate = orderDate;
        this.deliveryDate = deliveryDate;
        this.products = products;
        this.costumer = costumer;
    }

    public double calculateTotal() {
        double total = products.stream().mapToDouble(Product::getPrice).sum();
        return Math.round(total * 100.0) / 100.0;
    }
//        return Double.parseDouble(df.format(this.products.stream().mapToDouble(Product::getPrice).sum()));    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public List<Product> getProductsList() {
        return products;
    }

    @Override
    public String toString() {
        return "\nORDINE:" +
                "id: " + id + "\n" +
                "status: " + status + "\n" +
                "orderDate: " + orderDate + "\n" +
                "deliveryDate: " + deliveryDate + "\n" +
                "\nCart:" + products + "\n"
//                + "costumer: " + costumer
                ;
    }
}
