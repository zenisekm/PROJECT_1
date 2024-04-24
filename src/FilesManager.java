import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilesManager {

    private static final String DISHES_FILE = "dishes.txt";
    private static final String ORDERS_FILE = "orders.txt";

    private static final Logger logger = Logger.getLogger(FilesManager.class.getName());

    public static void saveData (List<Dish> dishes, List<Order> orders) {
        saveDishes(dishes);
        saveOrders(orders);

    }


    public static void loadData (List<Dish> dishes, List<Order> orders) throws FileLoadException {
        loadDishes(dishes);
        loadOrders(orders, dishes);
    }

    private static void saveDishes(List<Dish> dishes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DISHES_FILE))) {
            for (Dish dish : dishes) {
                writer.println(dish.getId() + "," + dish.getName() + "," + dish.getPrice());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save dishes from file", e);
        }
    }



    private static void saveOrders(List<Order> orders) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ORDERS_FILE))) {
            for (Order order : orders) {
                writer.println(order.getDish().getId()
                        + "," + order.getQuantity()
                        + "," + order.getOrderedTime()
                        + "," + order.getFulfilmentTime()
                        + "," + order.isPaid());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to save orders from file", e);
        }
    }




    private static void loadDishes(List<Dish> dishes) throws FileLoadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(DISHES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    dishes.add(new Dish(id, name, price));
                } else {
                    throw new FileLoadException("Neplatný formát řádku v souboru dishes.txt: " + line);
                }
            }
        } catch (IOException e) {
            throw new FileLoadException("Chyba při načítání seznamu jídel ze souboru: " + e.getMessage());
        }
    }


    private static void loadOrders(List<Order> orders, List<Dish> dishes) throws FileLoadException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (!parts[0].isEmpty()) {
                    int dishId = Integer.parseInt(parts[0].trim());
                    int quantity = Integer.parseInt(parts[1].trim());
                    long orderedTimeEpochSeconds = Long.parseLong(parts[2].trim());
                    long fulfilmentTimeEpochSeconds = Long.parseLong(parts[3].trim());
                    LocalDateTime orderedTime = LocalDateTime.ofEpochSecond(orderedTimeEpochSeconds, 0, ZoneOffset.UTC);
                    LocalDateTime fulfilmentTime = LocalDateTime.ofEpochSecond(fulfilmentTimeEpochSeconds, 0, ZoneOffset.UTC);
                    boolean isPaid = Boolean.parseBoolean(parts[4].trim());
                    Dish dish = findDishById(dishId, dishes);
                    orders.add(Order.createOrder(orders.size() + 1, 15, dish, quantity, isPaid, orderedTime));
                } else {

                }
            }
        } catch (IOException e) {
            throw new FileLoadException("Chyba při načítání seznamu jídel ze souboru: " + e.getMessage());
        }
    }



    public static Dish findDishById(int id, List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getId() == id) {
                return dish;
            }
        }
        return null;


    }

    public static void loadInitialState(List<Dish> dishes, List<Order> orders) {
        try {
            loadData(dishes, orders);
            System.out.println("Data byla úspěšně načtena ze souborů.");
        } catch (FileLoadException e) {
            System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());

        }
    }

    public static double getTotalBillForTable(List<Order> orders, int tableNumber) {
        double totalBill = 0;
        for (Order order : orders) {
            if (order.getTableNumber() == tableNumber) {
                totalBill += order.getDish().getPrice() * order.getQuantity();
            }
        }
        return totalBill;
    }

}