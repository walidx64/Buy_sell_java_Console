import java.util.ArrayList;
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

        products.add(new Product("apple", 0.5, "A delicious red fruit.", 50));
        products.add(new Product("banana", 0.3, "A yellow fruit with a curved shape.", 100));
        products.add(new Product("carrot", 0.4, "A long, thin vegetable with a orange color.", 75));
        products.add(new Product("desktop Computer", 600.0, "A computer that is designed to be used on a desk.", 10));
        products.add(new Product("laptop Computer", 800.0, "A computer that is portable and can be used on a lap.", 15));


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

    // Into Page
    private static void showLoginOptions() {
        System.out.println("********************************************");
        System.out.println("*       Welcome to our marketplace      *");
        System.out.println("********************************************\n");

        System.out.println("Please choose one of the following options:");
        System.out.println("1. Register");
        System.out.println("2. Login\n");

        int option = 0;
        while (option != 1 && option != 2) {
            System.out.print("Enter your choice: ");
            String input = scanner.nextLine();

            try {
                option = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
                continue;
            }

            if (option == 1) {
                register();
            } else if (option == 2) {
                login();
            } else {
                System.out.println("Invalid input. Please enter a number (1 or 2).");
            }
        }
    }

    //Register as Buyer or Seller
    private static void register() {
        System.out.println("********************************************");
        System.out.println("*              Register for a              *");
        System.out.println("*           New Marketplace Account        *");
        System.out.println("********************************************\n");

        String email = "";
        while (email.isEmpty()) {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.out.println("Invalid email format. Please try again.");
                email = "";
            }
        }

        System.out.print("Password (at least 4 characters): ");
        String password = scanner.nextLine();
        while (password.isEmpty() || password.length() < 4) {
            System.out.println("Error: Password must be at least 4 characters long. Please try again.");
            System.out.print("Password: ");
            password = scanner.nextLine();
        }

        String dob = "";
        while (dob.isEmpty()) {
            System.out.print("Enter date of birth (dd-mm-yyyy): ");
            dob = scanner.nextLine();
            if (!dob.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
                System.out.println("Invalid date format. Please try again.");
                dob = "";
            }
        }

        String type = "";
        while (type.isEmpty()) {
            System.out.print("Type of User (Buyer/Seller): ");
            type = scanner.nextLine().toLowerCase();
            if (type.isEmpty() || (!type.equals("buyer") && !type.equals("seller"))) {
                System.out.println("Error: type of user can't be empty or does not match 'Buyer' or 'Seller'. Please try again.");
                type = "";
            }
        }
        System.out.println();

        if (type.equals("buyer")) {
            currentUser = new Buyer(email, password, dob);
        } else if (type.equals("seller")) {
            currentUser = new Seller(email, password, dob);
        }

            System.out.println("Registration successful! continue to PROFILE or Press 1 to go back to the welcome page:");
            String option = scanner.nextLine();
            if (option.equals("1")) {
                return;
            }
        }


    //Login
    private static void login() {
        System.out.println("********************");
        System.out.println("      LOGIN     ");
        System.out.println("********************");

        String email = "";
        while (email.isEmpty() || !email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
            System.out.print("Email: ");
            email = scanner.nextLine();
            if (email.isEmpty()) {
                System.out.println("Email cannot be empty. Please try again.");
            } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.out.println("Invalid email format. Please try again.");
            }
        }

        String password = "";
        while (password.length() < 4) {
            System.out.print("Password (minimum 4 characters): ");
            password = scanner.nextLine();
            if (password.length() < 4) {
                System.out.println("Password should be at least 4 characters. Please try again.");
            }
        }

        for(User user: users) {
            if (user.login(email, password)) {
                currentUser = user;
                System.out.println("********************");
                System.out.println("  Login Successful! ");
                System.out.println("********************");
                return;
            }
        }

        System.out.println("********************");
        System.out.println("Invalid email or password. Please try again.");
        System.out.println("********************");
    }


    //Buyer Options
    private static void showBuyerOptions() {
        System.out.println("=====================================");
        System.out.println("|         BUYER MAIN MENU           |");
        System.out.println("=====================================");
        System.out.println("| 1. View Profile                   |");
        System.out.println("| 2. Search Products                |");
        System.out.println("| 3. View All Product Listings      |");
        System.out.println("| 4. View Purchase History          |");
        System.out.println("| 5. Logout                         |");
        System.out.println("=====================================");

        int option = 0;
        while (option < 1 || option > 5) {
            System.out.print("Enter your choice (1-5): ");
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        viewProfile();
                        break;
                    case 2:
                        searchProducts();
                        break;
                    case 3:
                        viewAllProductListing();
                        break;
                    case 4:
                        viewPurchaseHistory();
                        break;
                    case 5:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number from 1 to 5.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }


    // View the Profile detail Email and DOB
    private static void viewProfile() {
        System.out.println("---------- PROFILE ----------");
        System.out.println("Email: " + currentUser.getEmail());
        System.out.println("Date of Birth: " + currentUser.getDateOfBirth());
    }

    private static void searchProducts() {
        System.out.print("Search Term: ");
        String searchTerm = scanner.nextLine().toLowerCase();
        List<Product> matchingProducts = ((Buyer) currentUser).searchProducts(products, searchTerm);
        if (matchingProducts.isEmpty()) {
            System.out.println("No matching products found. Please try a different search term.");
        } else {
            System.out.println("Matching Products:");
            for (Product product : matchingProducts) {
                System.out.println(product.getName());
            }
        }
    }


    private static void viewAllProductListing() {
        System.out.println("All Available Products:\n");

        List<Product> allProducts = Main.getAllProductListing();
        for (int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
            System.out.println(i + 1 + ". " + "Name: " + product.getName());
            System.out.println("\tPrice: $" + product.getPrice());
            System.out.println("\tDescription: " + product.getDescription());
            System.out.println("\tQuantity: " + product.getQuantity());
        }

        System.out.println("\nEnter the number of the product to purchase (or 0 to go back):");
        int option = scanner.nextInt();
        scanner.nextLine();

        if (option == 0) {
            System.out.println("\nReturning to main menu...\n");
        } else if (option > 0 && option <= allProducts.size()) {
            Product productToPurchase = allProducts.get(option - 1);
            System.out.println("\nYou have selected: " + productToPurchase.getName());

            int quantity = 0;
            while (quantity <= 0) {
                System.out.println("\nEnter the quantity to purchase (must be greater than 0): ");
                if (scanner.hasNextInt()) {
                    quantity = scanner.nextInt();
                    scanner.nextLine();
                    if (quantity <= 0) {
                        System.out.println("\nError: Quantity must be greater than 0.");
                    } else if (quantity > productToPurchase.getQuantity()) {
                        System.out.println("\nError: Quantity must not exceed the product's available quantity.");
                        quantity = 0;
                    }
                } else {
                    System.out.println("\nError: Invalid input. Please enter a number.");
                    scanner.nextLine();
                }
            }

            ((Buyer) currentUser).buyProduct(productToPurchase, quantity);
            System.out.println("\nPurchase successful! Thank you for shopping with us.\n");
        } else {
            System.out.println("\nInvalid option selected. Please try again.\n");
        }
    }



    private static void viewPurchaseHistory() {
        List<Product> purchaseHistory = ((Buyer) currentUser).getPurchaseHistory();
        System.out.println("----------- Purchase History -----------");
        if (purchaseHistory.size() > 0) {
            int i = 1;
            for (Product product : purchaseHistory) {
                System.out.println(i + ") Product: " + product.getName() + " | Price Per Unit: $" + product.getPrice()+ " | Purchased on: " + product.getPurchaseDate());
                i++;
            }
        } else {
            System.out.println("No items found in purchase history.");
        }
        System.out.println("----------------------------------------");
    }

    //Seller Option
    private static void showSellerOptions() {
        System.out.println("---------- SELLER OPTIONS ----------");
        System.out.println("| 1. View Profile                   |");
        System.out.println("| 2. List Product for Sale          |");
        System.out.println("| 3. View Product Listings          |");
        System.out.println("| 4. View Selling History           |");
        System.out.println("| 5. Logout                         |");
        System.out.println("------------------------------------");

        int option = 0;
        while (option < 1 || option > 5) {
            System.out.print("Enter your choice (1-5): ");
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1:
                        viewProfile();
                        break;
                    case 2:
                        listProduct();
                        break;
                    case 3:
                        viewListings();
                        break;
                    case 4:
                        viewSellingHistory();
                        break;
                    case 5:
                        logout();
                        break;
                    default:
                        System.out.println("Invalid option. Please choose a number from 1 to 5.");
                        break;
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }


    private static void listProduct() {
        while (true) {
            System.out.println("Enter Product Details:");
            System.out.print("Product Name: ");
            String name = scanner.nextLine();
            System.out.print("Product Description: ");
            String description = scanner.nextLine();
            System.out.print("Product Price: ");
            String priceInput = scanner.nextLine();
            System.out.print("Product Quantity: ");
            String quantityInput = scanner.nextLine();
            if (name.isEmpty() || description.isEmpty() || priceInput.isEmpty() || quantityInput.isEmpty()) {
                System.out.println("Error: Name, description, price and quantity cannot be empty. Please try again.");
                continue;
            }
            double price;
            int quantity;
            try {
                price = Double.parseDouble(priceInput);
                quantity = Integer.parseInt(quantityInput);
            } catch (NumberFormatException e) {
                System.out.println("Error: Price and quantity must be numbers. Please try again.");
                continue;
            }
            if (price <= 0 || quantity <= 0) {
                System.out.println("Error: Price and quantity must be positive numbers. Please try again.");
                continue;
            }
            Product newProduct = new Product(name, price, description, quantity);
            ((Seller) currentUser).listProduct(newProduct);
            System.out.println("Product listed successfully!");
            break;
        }
    }


    private static void viewListings() {
        List<Product> listings = ((Seller) currentUser).getProductListings();
        System.out.println("Product Listings:");
        if (listings.isEmpty()) {
            System.out.println("No product listings found.");
        } else {
            int i = 1;
            for (Product product : listings) {
                System.out.println(i + ") Name: " + product.getName() + ", Price: $" + product.getPrice() + ", Description: " + product.getDescription() + ", Quantity: "+ product.getQuantity());
                i++;
            }
        }
    }

    private static void viewSellingHistory() {
        List<Product> sellingHistory = ((Seller) currentUser).getSellingHistory();
        System.out.println("==== Selling History ====");
        if (sellingHistory.size() == 0) {
            System.out.println("No selling history found.");
        } else {
            int i = 1;
            for (Product product : sellingHistory) {
                System.out.println(i + ") " + product.getName() + ", Price: $" + product.getPrice() + ", Sold at: " + product.getPurchaseDate());
                i++;
            }
        }
    }

    private static void logout() {
        System.out.println("Logging out...");
        currentUser = null;
        System.out.println("You have been logged out.");
    }
    }
                