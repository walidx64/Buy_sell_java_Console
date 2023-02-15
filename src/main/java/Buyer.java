import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Buyer extends User {

    private List<Product> purchaseHistory = new ArrayList<Product>();
    private List<Product> buyingHistory = new ArrayList<Product>();

    public void buyProduct(Product product, int quantity) {
        product.setQuantity(product.getQuantity() - quantity);
        purchaseHistory.add(product);
    }

    public Buyer(String email, String password, String dateOfBirth) {
        super(email, password, dateOfBirth);
    }

    public List<Product> searchProducts(List<Product> products, String searchTerm) {
        // Code to search for products in the application based on the provided search term
        List<Product> matchingProducts = new ArrayList<Product>();
        for (Product product : products) {
            if (product.getName().contains(searchTerm)) {
                matchingProducts.add(product);
            }
        }
        return matchingProducts;
    }

    public void addToBuyingHistory(Product product) {
        this.buyingHistory.add(product);
    }

    public List<Product> getBuyingHistory() {
        return this.buyingHistory;
    }

    public List<Product> getPurchaseHistory() {
        return purchaseHistory;
    }

    public void purchaseProduct(Product product) {
        Scanner scanner = new Scanner(System.in);
        int quantity;
        do {
            System.out.print("Enter quantity to purchase: ");
            quantity = scanner.nextInt();
            if (quantity <= 0) {
                System.out.println("Invalid quantity. Please enter a positive number.");
            } else if (quantity > product.getQuantity()) {
                System.out.println("Not enough available quantity. Please enter a lower number.");
            }
        } while (quantity <= 0 || quantity > product.getQuantity());

        buyProduct(product, quantity);
        addToBuyingHistory(product);
        System.out.println(quantity + " " + product.getName() + " purchased successfully.");
    }
}
