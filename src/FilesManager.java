import java.io.*;
import java.util.List;

public class FilesManager {


    private static final String DISHES_FILE = "dishes.txt";
    private static final String ORDERS_FILE = "orders";

    public static void saveDate (List<Dish> dishes, List<Order> orders) {
        saveDishes(dishes);
        saveOrders(orders);

    }


    public static void loadData (List<Dish> dishes, List<Order> orders) {
        loadDishes(dishes);
        loadOrders(orders, dishes);
    }

    private static void saveDishes(List<Dish> dishes) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DISHES_FILE))) {
            for (Dish dish : dishes) {
                writer.println(dish.getName() + "," + dish.getPrice());
            }
        } catch (IOException e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
    }

    private static void loadDishes(List<Dish> dishes) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DISHES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                dishes.add(new Dish(id, name, price));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void loadOrders(List<Order> orders, List<Dish> dishes) {
        try (BufferedReader reader = new BufferedReader(new FileReader(ORDERS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int dishId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                long orderedTime = Long.parseLong(parts[2]);
                long fulfilmentTime = Long.parseLong(parts[3]);
                boolean isPaid = Boolean.parseBoolean(parts[4]);
                Dish dish = findDishById(dishId, dishes);
                orders.add(new Order(dish, quantity, orderedTime, fulfilmentTime, isPaid));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static Dish findDishById(int id, List<Dish> dishes) {
        for (Dish dish : dishes) {
            if (dish.getId() == id) {
                return dish;
            }
        }
        return null;


}



}

