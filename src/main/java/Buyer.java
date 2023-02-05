import java.util.ArrayList;
import java.util.List;

public class Buyer extends User {

    private List<Product> purchaseHistory = new ArrayList<Product>();

    private List<Product> buyingHistory = new ArrayList<Product>();

    public void buyProduct(Product product) {
        purchaseHistory.add(product);
    }

    public Buyer(String email, String password, String dateOfBirth) {
        super(email, password, dateOfBirth);
        this.purchaseHistory = new ArrayList<Product>();
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
    public void addToBuyingHistory(Product product){
        this.buyingHistory.add(product);
    }
    public List<Product> getBuyingHistory(){
        return this.buyingHistory;
    }

    public List<Product> getPurchaseHistory() {
        return purchaseHistory;
    }
}