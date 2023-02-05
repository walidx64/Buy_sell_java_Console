import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class
Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User currentUser;
    private static List<Product> products = new ArrayList<Product>();

    private static List<User> users = new ArrayList<User>();


    public static List<Product> getAllProductListing() {
        List<Product> allProductListing = new ArrayList<Product>();
        for (User user : users) {
            if (user instanceof Seller) {
                allProductListing.addAll(((Seller) user).getSellerProductListing());
            }
        }
        allProductListing.addAll(products);
        return allProductListing;
    }

    public static void main(String[] args) {

        products.add(new Product("Apple", 0.5, "A delicious red fruit."));
        products.add(new Product("Banana", 0.3, "A yellow fruit with a curved shape."));
        products.add(new Product("Carrot", 0.4, "A long, thin vegetable with a orange color."));
        products.add(new Product("Desktop Computer", 600.0, "A computer that is designed to be used on a desk."));
        products.add(new Product("Laptop Computer", 800.0, "A computer that is portable and can be used on a lap."));

//        List<User> users = new ArrayList<User>();
        users.add(new Buyer("buyer1@email.com", "password1", "01/01/1990"));
        users.add(new Buyer("buyer2@email.com", "password2", "01/01/1991"));
        users.add(new Seller("seller1@email.com", "password3", "01/01/1992"));
        users.add(new Seller("seller2@email.com", "password4", "01/01/1993"));


        while (true) {
            if (currentUser == null) {
                showLoginOptions();
            } else if (currentUser instanceof Buyer) {
                showBuyerOptions();
            } else if (currentUser instanceof Seller) {
                showSellerOptions();
            }
        }
    }

    private static void showLoginOptions() {
        System.out.println("1. Register");
        System.out.println("2. Login");

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            register();
        } else if (option == 2) {
            login();
        }
    }

    private static void register() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Enter date of birth (dd-mm-yyyy): ");
        String dob = scanner.nextLine();
        System.out.print("Type of User (Buyer/Seller): ");
        String type = scanner.nextLine();

        if (type.equals("Buyer")) {
            currentUser = new Buyer(email, password, dob);
        } else if (type.equals("Seller")) {
            currentUser = new Seller(email, password, dob);
        }
    }

    private static void login() {
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        for(User user: users) {
            if (user.login(email, password)) {
                currentUser = user;
                System.out.println("Login Successful!");
                return;
            }
        }
        System.out.println("Invalid email or password. Please try again.");
    }

    private static void showBuyerOptions() {
        System.out.println("1. View Profile");
        System.out.println("2. Search Products");
        System.out.println("3. View All Product Listing");
        System.out.println("4. View Purchase History");
        System.out.println("5. Logout");

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            viewProfile();
        } else if (option == 2) {
            searchProducts();
        } else if (option == 3) {
            viewAllProductListing();
        } else if (option == 4) {
            viewPurchaseHistory();
        } else if (option == 5) {
            logout();
        }
    }

    private static void viewProfile() {
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Date of Birth: " + currentUser.getDateOfBirth());
    }

    private static void searchProducts() {
        System.out.print("Search Term: ");
        String searchTerm = scanner.nextLine();
        List<Product> matchingProducts = ((Buyer) currentUser).searchProducts(products, searchTerm);
        System.out.println("Matching Products:");
        for (Product product : matchingProducts) {
            System.out.println(product.getName());
        }
    }

    private static void viewAllProductListing() {
        System.out.println("All Product Listing:");
        int i = 1;
        List<Product> allProductListing = Main.getAllProductListing();
        for (Product product : allProductListing) {
            System.out.println(i + ") " + "Name: " + product.getName() + ", Price: " + product.getPrice() + ", Description: " + product.getDescription());
            i++;
        }

        System.out.println("Enter product number to purchase or 0 to go back:");
        int option = scanner.nextInt();
        scanner.nextLine();
        if (option > 0 && option <= allProductListing.size()) {
            ((Buyer) currentUser).buyProduct(allProductListing.get(option-1));
            System.out.println("Purchase Successful!");
        } else if(option == 0) {
            // you can add your code here
        } else {
            System.out.println("Invalid option selected");
        }

    }

    private static void viewPurchaseHistory() {
        List<Product> purchaseHistory = ((Buyer) currentUser).getPurchaseHistory();
        System.out.println("Purchase History:");
        for (Product product : purchaseHistory) {
            System.out.println("Product: " + product.getName() + ", Price: " + product.getPrice() + ", Purchased at: " + product.getPurchaseDate());
        }
    }

    private static void showSellerOptions() {
        System.out.println("1. View Profile");
        System.out.println("2. List Product");
        System.out.println("3. View Listings");
        System.out.println("4. View Selling History");
        System.out.println("5. Logout");

        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 1) {
            viewProfile();
        } else if (option == 2) {
            listProduct();
        } else if (option == 3) {
            viewListings();
        } else if (option == 4) {
            viewSellingHistory();
        } else if (option == 5) {
            logout();
        }
    }

        private static void listProduct () {
            System.out.print("Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Product Description: ");
            String description = scanner.nextLine();
            System.out.print("Product Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine();
            Product newProduct = new Product(name, price, description);
            ((Seller) currentUser).listProduct(newProduct);
        }

        private static void viewListings () {
            List<Product> listings = ((Seller) currentUser).getProductListings();
            System.out.println("Product Listings:");
            for (Product product : listings) {
                System.out.println(product.getName());
            }
        }

        private static void viewSellingHistory () {
            List<Product> sellingHistory = ((Seller) currentUser).getSellingHistory();
            System.out.println("Selling History:");
            for (Product product : sellingHistory) {
                System.out.println(product.getName());
            }
        }

        private static void logout () {
            currentUser = null;
        }
    }
                