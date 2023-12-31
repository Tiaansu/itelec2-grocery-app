import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.PreparedStatement;

class Product {
    public int id;
    public String name;
    public String category;
    public int stocks;
    public int price;

    public Product(int id, String name, String category, int stocks, int price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.stocks = stocks;
        this.price = price;
    }
}

class ShoppingCartItem {
    public int id;
    public int quantity;
    public String productName;
    public int price;

    public ShoppingCartItem(int id, int quantity, String productName, int price) {
        this.id = id;
        this.quantity = quantity;
        this.productName = productName;
        this.price = price;
    }
}

class GroceryItem {
    public String productName;
    public int price;
    public int category;

    public GroceryItem(String productName, int price, int category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }
}

public class GroceryApp {
    public static List<ShoppingCartItem> shoppingCartItems = new ArrayList<>();

    private void sleepThread(int i) {
        try {
            Thread.sleep(i);
        } catch (Exception e) {}
    }

    private Connection connect() {
        String url = "jdbc:sqlite:grocery-app.db";
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return connection;
    }

    public void clrscr() { 
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void beep() {
        System.out.println("\007");
    }

    public void LoadGroceryItems() {
        this.clrscr();

        System.out.println("Loading grocery items...");

        Connection connection = this.connect();

        try {
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);

            statement.executeUpdate("DROP TABLE IF EXISTS grocery_items");

            String sql = "CREATE TABLE grocery_items (\n"
                + " id INTEGER PRIMARY KEY,\n"
                + " name TEXT NOT NULL,\n"
                + " category INTEGER NOT NULL,\n"
                + " stocks INTEGER NOT NULL,\n"
                + " price INTEGER\n"
                + ");";
            statement.executeUpdate(sql);

            List<GroceryItem> groceryItems = new ArrayList<>();

            groceryItems.add(new GroceryItem("Mangoes", 35, 0));
            groceryItems.add(new GroceryItem("Bananas", 30, 0));
            groceryItems.add(new GroceryItem("Pineapples", 30, 0));
            groceryItems.add(new GroceryItem("Papayas", 25, 0));
            groceryItems.add(new GroceryItem("Oranges", 25, 0));
            groceryItems.add(new GroceryItem("Apples", 20, 0));
            groceryItems.add(new GroceryItem("Grapes", 35, 0));
            groceryItems.add(new GroceryItem("Watermelons", 50, 0));
            groceryItems.add(new GroceryItem("Cantaloupe", 40, 0));
            groceryItems.add(new GroceryItem("Broccoli", 15, 1));
            groceryItems.add(new GroceryItem("Carrots", 20, 1));
            groceryItems.add(new GroceryItem("Cauliflower", 15, 1));
            groceryItems.add(new GroceryItem("Celery", 5, 1));
            groceryItems.add(new GroceryItem("Onions", 10, 1));
            groceryItems.add(new GroceryItem("Potatoes", 15, 1));
            groceryItems.add(new GroceryItem("Tomatoes", 15, 1));
            groceryItems.add(new GroceryItem("Chicken", 120, 2));
            groceryItems.add(new GroceryItem("Pork", 150, 2));
            groceryItems.add(new GroceryItem("Beef", 200, 2));
            groceryItems.add(new GroceryItem("Hotdogs", 30, 2));
            groceryItems.add(new GroceryItem("Bacon", 60, 2));
            groceryItems.add(new GroceryItem("Sausage", 40, 2));
            groceryItems.add(new GroceryItem("Fish", 120, 3));
            groceryItems.add(new GroceryItem("Shrimp", 150, 3));
            groceryItems.add(new GroceryItem("Squid", 150, 3));
            groceryItems.add(new GroceryItem("Crab", 135, 3));
            groceryItems.add(new GroceryItem("Tuna", 200, 3));
            groceryItems.add(new GroceryItem("Sardines", 135, 3));
            groceryItems.add(new GroceryItem("Milk", 25, 4));
            groceryItems.add(new GroceryItem("Cheese", 40, 4));
            groceryItems.add(new GroceryItem("Eggs (per tray)", 30, 4));
            groceryItems.add(new GroceryItem("Yogurt", 25, 4));
            groceryItems.add(new GroceryItem("Butter", 25, 4));
            groceryItems.add(new GroceryItem("Ice cream", 20, 4));
            groceryItems.add(new GroceryItem("Rice (sack)", 1250, 5));
            groceryItems.add(new GroceryItem("Bread", 50, 5));
            groceryItems.add(new GroceryItem("Flour", 50, 5));
            groceryItems.add(new GroceryItem("Sugar", 25, 5));
            groceryItems.add(new GroceryItem("Salt", 25, 5));
            groceryItems.add(new GroceryItem("Pepper", 10, 5));
            groceryItems.add(new GroceryItem("Garlic", 5, 5));
            groceryItems.add(new GroceryItem("Onions", 5, 5));
            groceryItems.add(new GroceryItem("Cooking oil", 10, 5));
            groceryItems.add(new GroceryItem("Soy sauce", 10, 5));
            groceryItems.add(new GroceryItem("Vinegar", 10, 5));
            groceryItems.add(new GroceryItem("Bagoong isda", 35, 5));
            groceryItems.add(new GroceryItem("Bagoong alamang", 35, 5));
            groceryItems.add(new GroceryItem("Patis", 15, 5));
            groceryItems.add(new GroceryItem("Sardines (canned)", 20, 5));
            groceryItems.add(new GroceryItem("Tuna (canned)", 20, 5));
            groceryItems.add(new GroceryItem("Corned beef", 20, 5));
            groceryItems.add(new GroceryItem("Fruits (canned)", 20, 5));
            groceryItems.add(new GroceryItem("Vegetables (canned)", 20, 5));
            groceryItems.add(new GroceryItem("Instant noodles", 15, 5));
            groceryItems.add(new GroceryItem("Pasta", 20, 5));
            groceryItems.add(new GroceryItem("Cereal", 20, 5));
            groceryItems.add(new GroceryItem("Chips", 15, 5));
            groceryItems.add(new GroceryItem("Cookies", 10, 5));
            groceryItems.add(new GroceryItem("Candy (pack)", 25, 5));
            groceryItems.add(new GroceryItem("Water", 15, 6));
            groceryItems.add(new GroceryItem("Juice", 15, 6));
            groceryItems.add(new GroceryItem("Soda", 15, 6));
            groceryItems.add(new GroceryItem("Coffee", 15, 6));
            groceryItems.add(new GroceryItem("Tea", 15, 6));
            groceryItems.add(new GroceryItem("Shampoo", 10, 7));
            groceryItems.add(new GroceryItem("Soap", 10, 7));
            groceryItems.add(new GroceryItem("Toothpaste", 10, 7));
            groceryItems.add(new GroceryItem("Deodorant", 10, 7));
            groceryItems.add(new GroceryItem("Pet food", 100, 7));
            groceryItems.add(new GroceryItem("Baby supplies", 50, 7));
            groceryItems.add(new GroceryItem("Puto", 15, 8));
            groceryItems.add(new GroceryItem("Bibingka", 25, 8));
            groceryItems.add(new GroceryItem("Kutsinta", 5, 8));
            groceryItems.add(new GroceryItem("Kalamay", 15, 8));
            groceryItems.add(new GroceryItem("Halo-halo", 15, 9));
            groceryItems.add(new GroceryItem("Leche flan", 25, 9));
            groceryItems.add(new GroceryItem("Turon", 15, 9));
            groceryItems.add(new GroceryItem("Taho", 10, 9));
            
            String pstr = "INSERT INTO grocery_items(name, category, stocks, price) VALUES (?,?,?,?)";

            for (int i = 0; i < groceryItems.size(); i ++) {
                PreparedStatement pstmt = connection.prepareStatement(pstr);
                pstmt.setString(1, groceryItems.get(i).productName);
                pstmt.setInt(2, groceryItems.get(i).category);
                pstmt.setInt(3, new Random().nextInt(200 - 10 + 1) + 10);
                pstmt.setInt(4, groceryItems.get(i).price);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        System.out.println("Done loading grocery items.");
        this.sleepThread(250);
        this.EnterGroceryStore();
    }

    public String getCategory(int categoryId) {
        String category = "unknown";

        switch (categoryId) {
            case 0:
                category = "Fruits";
                break;

            case 1:
                category = "Vegetables";
                break;

            case 2:
                category = "Meats";
                break;

            case 3:
                category = "Seafoods";
                break;

            case 4:
                category = "Dairy and eggs";
                break;

            case 5:
                category = "Pantry items";
                break;

            case 6:
                category = "Drinks";
                break;

            case 7:
                category = "Others";
                break;

            case 8:
                category = "Rice cakes";
                break;

            case 9:
                category = "Sweets";
                break;
        }

        return category;
    }

    public static void PrintTableLines(int[] arr) {
        for (int i = 0; i < arr.length; i ++) {
            System.out.print("+");
            System.out.print(String.join("", Collections.nCopies(arr[i] + 2, "-")));
        }

        System.out.print("+");
        System.out.println();
    }

    public static void GroceryTable(List<Product> products) {
        String[] columns = {"ID", "Name", "Category", "Stocks", "Price"};
        int[] columnLengths = new int[columns.length];
        for (int i = 0; i < columns.length; i ++) {
            columnLengths[i] = columns[i].length();
        }

        for (int i = 0; i < products.size(); i ++) {
            if (columnLengths[0] < Integer.toString(products.get(i).id).length()) {
                columnLengths[0] = Integer.toString(products.get(i).id).length();
            }
            if (columnLengths[1] < products.get(i).name.length()) {
                columnLengths[1] = products.get(i).name.length();
            }
            if (columnLengths[2] < products.get(i).category.length()) {
                columnLengths[2] = products.get(i).category.length();
            }
            if (columnLengths[3] < Integer.toString(products.get(i).stocks).length()) {
                columnLengths[3] = Integer.toString(products.get(i).stocks).length();
            }
            if (columnLengths[4] < Integer.toString(products.get(i).price).length()) {
                columnLengths[4] = Integer.toString(products.get(i).price).length();
            }
        }

        String columnStrFormat = "| %-" + columnLengths[0] + "s | %-" + columnLengths[1] + "s | %-" + columnLengths[2] + "s | %-" + columnLengths[3] + "s | %-" + columnLengths[4] + "s |%n";
        String strFormat = "| %-" + columnLengths[0] + "d | %-" + columnLengths[1] + "s | %-" + columnLengths[2] + "s | %-" + columnLengths[3] + "d | %-" + columnLengths[4] + "d |%n";

        PrintTableLines(columnLengths);
        System.out.printf(columnStrFormat, columns[0], columns[1], columns[2], columns[3], columns[4]);
        PrintTableLines(columnLengths);

        for (Product product : products) {
            System.out.printf(strFormat, product.id, product.name, product.category, product.stocks, product.price);
            PrintTableLines(columnLengths);
        }
    }

    private int PrintEnterIDMessage(Scanner input) {
        System.out.print("Enter the ID of the product: (Type -1 to choose on other categories) ");
        int productID = input.nextInt();

        return productID;
    }

    private int PrintEnterQuantityMessage(Scanner input) {
        System.out.print("Enter the quantity: ");
        int quantity = input.nextInt();

        return quantity;
    }

    private boolean PrintWantToContinueMessage(Scanner input) {
        System.out.print("Want to checkout? [Y/N]: ");
        String option = input.nextLine();

        if (option.isEmpty()) {
            this.PrintWantToContinueMessage(input);
            return false;
        }

        if (option.equalsIgnoreCase("Y")) {
            return true;
        } else if (option.equalsIgnoreCase("N")) {
            return false;
        } else {
            return this.PrintWantToContinueMessage(input);
        }
    }

    private boolean PrintWantToBuyItem(Scanner input, int totalAmount, String productName) {
        System.out.print("Want to buy " + productName + "? Its total amount is $" + totalAmount + ". [Y/N]: ");
        String option = input.nextLine();

        if (option.isEmpty()) {
            this.PrintWantToBuyItem(input, totalAmount, productName);
            return false;
        }

        if (option.equalsIgnoreCase("Y")) {
            return true;
        } else if (option.equalsIgnoreCase("N")) {
            return false;
        } else {
            return this.PrintWantToBuyItem(input, totalAmount, productName);
        }
    }

    private boolean PrintWantToCheckoutItems(Scanner input) {
        int totalAmount = 0;
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            totalAmount += shoppingCartItem.price * shoppingCartItem.quantity;
        }

        System.out.print("Want to checkout " + shoppingCartItems.size() + " item(s) with overall price of $" + totalAmount + " in your cart? [Y/N]: ");
        String option = input.nextLine();

        if (option.isEmpty()) {
            this.PrintWantToCheckoutItems(input);
            return false;
        }

        if (option.equalsIgnoreCase("Y")) {
            return true;
        } else if (option.equalsIgnoreCase("N")) {
            return false;
        } else {
            return this.PrintWantToCheckoutItems(input);
        }
    }

    private boolean PrintWantToReduceItems(Scanner input) {
        System.out.print("Want to reduce item(s) in your cart? [Y/N]: ");
        String option = input.nextLine();

        if (option.isEmpty()) {
            this.PrintWantToReduceItems(input);
            return false;
        }

        if (option.equalsIgnoreCase("Y")) {
            return true;
        } else if (option.equalsIgnoreCase("N")) {
            return false;
        } else {
            return this.PrintWantToReduceItems(input);
        }
    }

    private void PrintEnterArrayIndexOfShoppingCartItems(Scanner input) {
        System.out.print("Enter the ID you want to remove on your shopping cart: ");
        int index = input.nextInt();

        try {
            shoppingCartItems.remove(index);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Done deleting the item on your cart.");
            boolean wantToReduceItem = this.PrintWantToReduceItems(scanner);

            if (wantToReduceItem) {
                this.clrscr();
                System.out.println(" --- Item to be removed by you --- ");
                this.PrintShoppingCartItemsToBeRemoved(input);
            } else {
                this.PrintCheckout();
            }

            scanner.close();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Sorry but the ID " + index + " is not in your shopping cart.");
            this.PrintEnterArrayIndexOfShoppingCartItems(input);
        }
    }

    private void PrintShoppingCartItemsToBeRemoved(Scanner input) {
        String[] columns = {"ID", "Name", "Quantity", "Price", "Total Price"};
        int[] columnLengths = new int[columns.length];
        for (int i = 0; i < columns.length; i ++) {
            columnLengths[i] = columns[i].length();
        }

        for (int i = 0; i < shoppingCartItems.size(); i ++) {
            if (columnLengths[0] < Integer.toString(shoppingCartItems.get(i).id).length()) {
                columnLengths[0] = Integer.toString(shoppingCartItems.get(i).id).length();
            }
            if (columnLengths[1] < shoppingCartItems.get(i).productName.length()) {
                columnLengths[1] = shoppingCartItems.get(i).productName.length();
            }
            if (columnLengths[2] < Integer.toString(shoppingCartItems.get(i).quantity).length()) {
                columnLengths[2] = Integer.toString(shoppingCartItems.get(i).quantity).length();
            }
            if (columnLengths[3] < Integer.toString(shoppingCartItems.get(i).price).length()) {
                columnLengths[3] = Integer.toString(shoppingCartItems.get(i).price).length();
            }
            if (columnLengths[4] < Integer.toString(shoppingCartItems.get(i).price * shoppingCartItems.get(i).quantity).length()) {
                columnLengths[4] = Integer.toString(shoppingCartItems.get(i).price * shoppingCartItems.get(i).quantity).length();
            }
        }

        String columnStrFormat = "| %-" + columnLengths[0] + "s | %-" + columnLengths[1] + "s | %-" + columnLengths[2] + "s | %-" + columnLengths[3] + "s | %-" + columnLengths[4] + "s |%n";
        String strFormat = "| %-" + columnLengths[0] + "d | %-" + columnLengths[1] + "s | %-" + columnLengths[2] + "d | %-" + columnLengths[3] + "d | %-" + columnLengths[4] + "d |%n";

        PrintTableLines(columnLengths);
        System.out.printf(columnStrFormat, columns[0], columns[1], columns[2], columns[3], columns[4]);
        PrintTableLines(columnLengths);

        for (int i = 0; i < shoppingCartItems.size(); i ++) {
            System.out.printf(strFormat, i, shoppingCartItems.get(i).productName, shoppingCartItems.get(i).quantity, shoppingCartItems.get(i).price, shoppingCartItems.get(i).price * shoppingCartItems.get(i).quantity);
            PrintTableLines(columnLengths);
        }

        this.PrintEnterArrayIndexOfShoppingCartItems(input);
    }

    private void PrintGroceryTicket(int change) {
        System.out.println("Thank you for purchasing on our store! Your change is $"+ change);
        System.out.println("You can see the item(s) you bought below.");

        this.PrintShoppingCartItems(shoppingCartItems);

        Connection connection = null;
        try {
            connection = this.connect();
            Statement stmt = connection.createStatement();

            for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
                stmt.executeUpdate("UPDATE grocery_items SET stocks = stocks - " + shoppingCartItem.quantity + " WHERE id = " + shoppingCartItem.id);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.err.println(e.getMessage());
            }
        }

        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, MMMM dd yyyy - hh:mm:ss a");
        String formattedDate = now.format(formatter);
        System.out.println("You completed your purchase on our store on " + formattedDate + ".");

        System.out.println("Preparing the start menu...");
        this.sleepThread(2500);
        this.EnterGroceryStore();
    }

    private void PrintEnterUserMoney(Scanner input) {
        int totalAmount = 0;
        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            totalAmount += shoppingCartItem.price * shoppingCartItem.quantity;
        }

        System.out.print("Enter the amount of your money: (Please make sure it's higher than $" + totalAmount + ") ");
        int money = input.nextInt();

        if (money < totalAmount) {
            System.out.println("You still need $" + (totalAmount - money) + " to proceed.");

            Scanner scanner = new Scanner(System.in);
            boolean wantToReduceItem = this.PrintWantToReduceItems(scanner);
            if (wantToReduceItem) {
                this.clrscr();
                scanner = new Scanner(System.in);
                System.out.println(" --- Item to be removed by you --- ");
                this.PrintShoppingCartItemsToBeRemoved(scanner);
            } else {
                this.PrintEnterUserMoney(input);
            }
            scanner.close();
            return;
        } else {
            this.clrscr();
            this.PrintGroceryTicket(money - totalAmount);
        }
    }

    private void PrintShoppingCartItems(List<ShoppingCartItem> shoppingCartItems) {
        String[] columns = {"ID", "Name", "Quantity", "Price", "Total Price"};
        int[] columnLengths = new int[columns.length];
        for (int i = 0; i < columns.length; i ++) {
            columnLengths[i] = columns[i].length();
        }

        for (int i = 0; i < shoppingCartItems.size(); i ++) {
            if (columnLengths[0] < Integer.toString(shoppingCartItems.get(i).id).length()) {
                columnLengths[0] = Integer.toString(shoppingCartItems.get(i).id).length();
            }
            if (columnLengths[1] < shoppingCartItems.get(i).productName.length()) {
                columnLengths[1] = shoppingCartItems.get(i).productName.length();
            }
            if (columnLengths[2] < Integer.toString(shoppingCartItems.get(i).quantity).length()) {
                columnLengths[2] = Integer.toString(shoppingCartItems.get(i).quantity).length();
            }
            if (columnLengths[3] < Integer.toString(shoppingCartItems.get(i).price).length()) {
                columnLengths[3] = Integer.toString(shoppingCartItems.get(i).price).length();
            }
            if (columnLengths[4] < Integer.toString(shoppingCartItems.get(i).price * shoppingCartItems.get(i).quantity).length()) {
                columnLengths[4] = Integer.toString(shoppingCartItems.get(i).price * shoppingCartItems.get(i).quantity).length();
            }
        }

        String columnStrFormat = "| %-" + columnLengths[0] + "s | %-" + columnLengths[1] + "s | %-" + columnLengths[2] + "s | %-" + columnLengths[3] + "s | %-" + columnLengths[4] + "s |%n";
        String strFormat = "| %-" + columnLengths[0] + "d | %-" + columnLengths[1] + "s | %-" + columnLengths[2] + "d | %-" + columnLengths[3] + "d | %-" + columnLengths[4] + "d |%n";

        PrintTableLines(columnLengths);
        System.out.printf(columnStrFormat, columns[0], columns[1], columns[2], columns[3], columns[4]);
        PrintTableLines(columnLengths);

        for (ShoppingCartItem shoppingCartItem : shoppingCartItems) {
            System.out.printf(strFormat, shoppingCartItem.id, shoppingCartItem.productName, shoppingCartItem.quantity, shoppingCartItem.price, shoppingCartItem.price * shoppingCartItem.quantity);
            PrintTableLines(columnLengths);
        }
    }

    private void PrintCheckout() {
        this.clrscr();

        System.out.println(" --- Shopping cart item(s) --- ");
        this.PrintShoppingCartItems(shoppingCartItems);

        Scanner scanner = new Scanner(System.in);
        boolean wantToCheckoutItems = this.PrintWantToCheckoutItems(scanner);

        if (wantToCheckoutItems) {
            scanner = new Scanner(System.in);
            this.PrintEnterUserMoney(scanner);
        }

        scanner.close();
    }

    private void BrowseGroceryItemOnCategory(int categoryId) {
        this.clrscr();

        try {
            Connection connection = connect();
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM grocery_items WHERE category = " + categoryId);
        
            List<Product> products = new ArrayList<>();

            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"), getCategory(rs.getInt("category")), rs.getInt("stocks"), rs.getInt("price")));
            }

            System.out.println(" --- Buy --- ");
            GroceryTable(products);

            Scanner scanner = new Scanner(System.in);
            int id = this.PrintEnterIDMessage(scanner);

            if (id <= -1) {
                this.BrowseAndBuyGroceryItems();
                return;
            }

            int quantity = this.PrintEnterQuantityMessage(scanner);

            int totalAmount = 0;
            int price = 0;
            String productName = "unknown";

            rs = stmt.executeQuery("SELECT * FROM grocery_items WHERE id = " + id);

            while (rs.next()) {
                if (rs.getInt("stocks") <= 0) {
                    System.out.println("No stocks, choose another product.");
                    this.sleepThread(500);
                    this.BrowseGroceryItemOnCategory(categoryId);
                    break;
                } else {
                    price = rs.getInt("price");
                    totalAmount = rs.getInt("price") * quantity;
                    productName = rs.getString("name");
                }
            }

            scanner = new Scanner(System.in);
            boolean wantToBuyItem = this.PrintWantToBuyItem(scanner, totalAmount, productName);
            
            if (wantToBuyItem) {
                shoppingCartItems.add(new ShoppingCartItem(id, quantity, productName, price));
            } else {
                this.BrowseGroceryItemOnCategory(categoryId);
                return;
            }

            scanner = new Scanner(System.in);
            boolean wantToCheckout = this.PrintWantToContinueMessage(scanner);

            if (!wantToCheckout) {
                System.out.println("Loading...");
                this.sleepThread(500);
                this.BrowseGroceryItemOnCategory(categoryId);
            } else {
                this.PrintCheckout();
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    private void BrowseAndBuyGroceryItems() {
        this.clrscr();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Options:");
        System.out.println("[0] - Fruits");
        System.out.println("[1] - Vegetables");
        System.out.println("[2] - Meats");
        System.out.println("[3] - Seafoods");
        System.out.println("[4] - Dairy and eggs");
        System.out.println("[5] - Pantry items");
        System.out.println("[6] - Drinks");
        System.out.println("[7] - Others");
        System.out.println("[8] - Rice cakes");
        System.out.println("[9] - Sweets");
        System.out.print("Choose: ");
        int category = scanner.nextInt();

        if (category > 9 || category < 0) {
            this.BrowseAndBuyGroceryItems();
            scanner.close();
            return;
        }

        this.BrowseGroceryItemOnCategory(category);
        scanner.close();
    }

    public void EnterGroceryStore() {
        this.clrscr();

        System.out.println("Welcome to Grocery App version 0.0.1!\n");

        System.out.println("Options: ");
        System.out.println("[1] - Load grocery items (startup)");
        System.out.println("[2] - Browse & buy grocery items");
        System.out.println("[3] - Exit the store");

        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose: ");
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                this.LoadGroceryItems();
                break;

            case 2:
                this.BrowseAndBuyGroceryItems();
                break;

            case 3:
                this.clrscr();
                System.out.println("Good bye! :)");
                System.exit(1);
                break;

            default:
                this.beep();
                this.EnterGroceryStore();
                break;
        }

        scanner.close();
    }

    public static void main(String[] args) {
        GroceryApp app = new GroceryApp();
        app.EnterGroceryStore();
    }
}