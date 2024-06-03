
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

public class Main {
        public static void main(String[] args) throws FileLoadException {

                List<Dish> dishes = new ArrayList<>();
                List<Order> orders = new ArrayList<>();
// ÚKOL 1

                try {
                        FilesManager.loadData(dishes, orders);
                        System.out.println("Data byla úspěšně načtena ze souborů.");
                } catch (FileLoadException e) {
                        System.err.println("Chyba při načítání dat ze souborů: " + e.getMessage());
                        return;
                }


// ÚKOL 2
                dishes.add(new Dish(1, "Kuřecí řízek obalovaný", 290));
                dishes.add(new Dish(2, "Hranolky", 90));
                dishes.add(new Dish(3, "Pstruh na víně", 350));
                dishes.add(new Dish(4, "Kofola", 60));
                dishes.add(new Dish(5, "Whiskey", 180));
                dishes.add(new Dish(6, "Panenka", 260));
                dishes.add(new Dish(7, "Losos se špenátem", 315));
                dishes.add(new Dish(8, "Smažený hermelín", 195));
                dishes.add(new Dish(9, "Sachr", 150));


                LocalDateTime orderedTime15 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 30));
                LocalDateTime orderedTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(15, 55));
                LocalDateTime orderedTime9 = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 3));

                LocalDateTime fulfilmentTime15 = LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 0));
                LocalDateTime fulfilmentTime2 = LocalDateTime.of(LocalDate.now(), LocalTime.of(16, 30));
                LocalDateTime fulfilmentTime9 = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 40));



                Order order1 = Order.createOrder(1, 15, 1, 2, orderedTime15, fulfilmentTime15, false, false, dishes);
                Order order2 = Order.createOrder(2, 15, 2, 2, orderedTime15, fulfilmentTime15, false, false, dishes);
                Order order3 = Order.createOrder(3, 15, 4, 2, orderedTime15, fulfilmentTime15, true, false, dishes);
                Order order4 = Order.createOrder(4, 2, 3, 2, orderedTime2, fulfilmentTime2, false, false, dishes);
                Order order5 = Order.createOrder(5, 9, 1, 1, orderedTime9, fulfilmentTime9, true, false, dishes);
                Order order6 = Order.createOrder(6, 9, 2, 1, orderedTime9, fulfilmentTime9, true, false, dishes);
                Order order7 = Order.createOrder(7, 9, 3, 2, orderedTime9, fulfilmentTime9, false, false, dishes);



                RestaurantManager restaurantManager = new RestaurantManager();

                restaurantManager.addOrder(order1);
                restaurantManager.addOrder(order2);
                restaurantManager.addOrder(order3);
                restaurantManager.addOrder(order4);
                restaurantManager.addOrder(order5);
                restaurantManager.addOrder(order6);
                restaurantManager.addOrder(order7);

                orders.add(order1);
                orders.add(order2);
                orders.add(order3);
                orders.add(order4);
                orders.add(order5);
                orders.add(order6);
                orders.add(order7);

                FilesManager.saveData(dishes, orders);
                System.out.println("Data byla úspěšně uložena do souborů.");


// ÚKOL 3

                int tableNumber = 15;
                double totalBillForTable15 = restaurantManager.calculateTotalBillForTable(tableNumber);
                System.out.println("Celková cena konzumace pro stůl číslo " + tableNumber + ": " + totalBillForTable15 + " Kč");


                System.out.println(" ");
                System.out.println(" ");


 // ÚKOL 4


                int openOrdersCount = restaurantManager.getOpenOrdersCount();
                System.out.println("Počet otevřených objednávek: " + openOrdersCount);

                restaurantManager.sortOrdersByTime();


                double averageProcessingTime = restaurantManager.calculateAverageProcessingTime();
                System.out.println("Průměrná doba zpracování objednávek: " + averageProcessingTime + " minut");


                ArrayList<Dish> orderedDishesToday = restaurantManager.getOrderedDishesToday();

                System.out.println("Seznam jídel objednaných dnes:");
                for (Dish dish : orderedDishesToday) {
                        System.out.println("- " + dish.getName());
                }


                System.out.println(restaurantManager.exportOrdersForTable(15));
                    }

        }
