import java.util.*;

class Customer {
    String id, name;
    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String toString() { return id + ": " + name; }
}

class Product {
    String id, name;
    double price;
    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    public String toString() { return id + ": " + name + " ($" + price + ")"; }
}

class Order {
    String customerId, productId;
    public Order(String customerId, String productId) {
        this.customerId = customerId;
        this.productId = productId;
    }
    public String toString() { return "Customer: " + customerId + " ordered Product: " + productId; }
}

class ProductComparator implements Comparator<Product> {
    public int compare(Product p1, Product p2) {
        return p1.name.compareTo(p2.name);
    }
}

public class OrderManagementApp {
    static ArrayList<Customer> customers = new ArrayList<>();
    static HashMap<String, Product> products = new HashMap<>();
    static HashSet<Order> orders = new HashSet<>();
    static TreeSet<Product> sortedProducts = new TreeSet<>(new ProductComparator());

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Customer\n2. Add Product\n3. Place Order\n4. View Orders\n5. View Sorted Products\n6. Exit\nEnter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Enter Customer ID: ");
                    String cid = sc.nextLine();
                    if (customers.stream().anyMatch(c -> c.id.equals(cid))) {
                        System.out.println("Error: Customer ID already exists!");
                    } else {
                        System.out.print("Enter Customer Name: ");
                        String cname = sc.nextLine();
                        customers.add(new Customer(cid, cname));
                        System.out.println("Customer added successfully!");

                        System.out.println("Current Customers:");
                        for (Customer customer : customers) {
                            System.out.println(customer);
                        }
                    }
                    break;

                case 2: 
                    System.out.print("Enter Product ID: ");
                    String pid = sc.nextLine();
                    if (products.containsKey(pid)) {
                        System.out.println("Error: Product ID already exists!");
                    } else {
                        System.out.print("Enter Product Name: ");
                        String pname = sc.nextLine();
                        System.out.print("Enter Product Price: ");
                        double pprice = sc.nextDouble();
                        sc.nextLine();
                        Product p = new Product(pid, pname, pprice);
                        products.put(pid, p);
                        sortedProducts.add(p);
                        System.out.println("Product added successfully!");

                        
                        System.out.println("Current Products:");
                        for (Product prod : products.values()) {
                            System.out.println(prod);
                        }
                    }
                    break;

                case 3: 
                    System.out.print("Enter Customer ID: ");
                    String ocid = sc.nextLine();
                    System.out.print("Enter Product ID: ");
                    String opid = sc.nextLine();
                    if (!products.containsKey(opid)) {
                        System.out.println("Error: Product not found!");
                    } else if (orders.stream().anyMatch(o -> o.customerId.equals(ocid) && o.productId.equals(opid))) {
                        System.out.println("Error: Order with this Customer ID and Product ID already exists!");
                    } else {
                        orders.add(new Order(ocid, opid));
                        System.out.println("Order placed successfully!");
                    }
                    break;

                case 4:
                    System.out.println("Order History:");
                    for (Order o : orders) {
                        System.out.println(o);
                    }
                    break;
                case 5:
                    System.out.println("Sorted Products:");
                    for (Product sp : sortedProducts) {
                        System.out.println(sp);
                    }
                    break;
                case 6:
                    System.out.println("Exiting...");
                    sc.close();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
