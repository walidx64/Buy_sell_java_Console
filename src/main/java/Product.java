
import java.time.LocalDateTime;

public class Product{
    private String name;
    private double price;
    private String description;
    private LocalDateTime purchaseDate;

    public Product(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.purchaseDate = LocalDateTime.now();

    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}