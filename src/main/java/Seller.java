import java.util.ArrayList;
import java.util.List;

public class Seller extends User {

    private List<Product> products = new ArrayList<Product>();
    private List<Product> productListings = new ArrayList<Product>();
    private List<Product> sellingHistory = new ArrayList<Product>();

    public List<Product> getSellerProductListing() {
        return products;
    }
    public Seller(String email, String password, String dateOfBirth) {
        super(email, password, dateOfBirth);
    }

    public boolean listProduct(Product product) {
        // Code to add a product to the seller's product listings
        this.productListings.add(product);
        return true;
    }
    public void addToSellingHistory(Product product){
        this.sellingHistory.add(product);
    }
    public List<Product> getSellingHistory(){
        return this.sellingHistory;
    }
    public List<Product> getProductListings(){
        return this.productListings;
    }
}