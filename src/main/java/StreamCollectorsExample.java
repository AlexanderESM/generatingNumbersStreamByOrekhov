import java.util.*;
import java.util.stream.Collectors;

class Order {
    private String product;
    private double cost;

    public Order(String product, double cost) {
        this.product = product;
        this.cost = cost;
    }

    public String getProduct() {
        return product;
    }

    public double getCost() {
        return cost;
    }
}

public class StreamCollectorsExample {
    public static void main(String[] args) {
        List<Order> orders = List.of(
                new Order("Microwave", 8000),
                new Order("Microwave", 5000),
                new Order("Fridge", 100000),
                new Order("Fridge", 50000),
                new Order("Washing machine", 40000),
                new Order("Washing machine", 90000),
                new Order("dishwasher", 40000),
                new Order("dishwasher", 60000),
                new Order("Laptop", 180000),
                new Order("Laptop", 80000),
                new Order("Tablet", 5000),
                new Order("Tablet", 12000),
                new Order("Smartphone", 12000.0),
                new Order("Smartphone", 20000)
        );

        // Группировка заказов по продуктам и вычисление общей стоимости для каждого продукта
        Map<String, Double> productCostMap = orders.stream()
                .collect(Collectors.groupingBy(
                        Order::getProduct,                    // Группировка по продукту
                        Collectors.summingDouble(Order::getCost) // Суммирование стоимости для каждого продукта
                ));

        // Сортировка продуктов по общей стоимости в убывающем порядке и выбор 3 самых дорогих продуктов
        List<Map.Entry<String, Double>> topThreeProducts = productCostMap.entrySet().stream()
                .sorted((entry1, entry2) -> Double.compare(entry2.getValue(), entry1.getValue())) // Сортировка по стоимости
                .limit(3) // Выбираем только три самых дорогих продукта
                .collect(Collectors.toList());

        // Вывод результата
        System.out.println("Три самых дорогих продукта:");
        topThreeProducts.forEach(entry ->
                System.out.println(entry.getKey() + ": " + entry.getValue()));
    }
}
