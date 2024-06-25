package services;

import entities.Cashier;
import entities.Product;
import entities.Receipt;
import exceptions.InsufficientStockException;
import exceptions.ExpiredProductException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StoreManager {
    // Полета на класа StoreManager
    private List<Product> products;
    private HashMap<Integer, Product> productStock;
    private List<Cashier> cashiers;
    private List<Receipt> receipts;
    private double totalRevenue;
    private double totalExpenses;
    private int daysBeforeDiscountApplies;

    // Конструктор на класа StoreManager
    public StoreManager(int daysBeforeDiscountApplies) {
        this.products = new ArrayList<>();
        this.productStock = new HashMap<>();
        this.cashiers = new ArrayList<>();
        this.receipts = new ArrayList<>();
        this.totalRevenue = 0.0;
        this.totalExpenses = 0.0;
        this.daysBeforeDiscountApplies = daysBeforeDiscountApplies;
    }

    // Метод за добавяне на нов продукт
    public void addProduct(Product product, int initialStock) {
        products.add(product);
        product.setStock(initialStock);
        productStock.put(product.getId(), product);
        totalExpenses = totalExpenses + (product.getDeliveryPrice() * initialStock);
    }

    // Метод за добавяне на нов касиер
    public void addCashier(Cashier cashier) {
        cashiers.add(cashier);
    }

    // Метод за продажба на продукт
    // Хвърля изключения при недостатъчна наличност или изтекъл срок на годност
    public Receipt sellProduct(int productId, int quantity, int cashierId) throws InsufficientStockException, ExpiredProductException {
        Product product = productStock.get(productId);
        if (product == null) {
            throw new IllegalArgumentException("Product not found with ID: " + productId);
        }

        Cashier cashier = null;
        for (Cashier c : cashiers) {
            if (c.getId() == cashierId) {
                cashier = c;
                break;
            }
        }
        if (cashier == null) {
            throw new IllegalArgumentException("Cashier not found with ID: " + cashierId);
        }

        double salePrice = product.calculateSalePrice(daysBeforeDiscountApplies);
        product.updateStock(quantity);

        Receipt receipt = new Receipt(receipts.size() + 1, cashier, LocalDateTime.now());
        receipt.addProduct(product, quantity, salePrice);
        receipts.add(receipt);

        double saleAmount = salePrice * quantity;
        totalRevenue = totalRevenue + saleAmount;
        receipt.setTotalPrice(saleAmount);

        return receipt;
    }

    // Метод за получаване на общите приходи
    public double getTotalRevenue() {
        return totalRevenue;
    }

    // Метод за получаване на общите разходи
    public double getTotalExpenses() {
        return totalExpenses;
    }

    // Метод за изчисляване на печалбата
    public double calculateProfit() {
        return totalRevenue - totalExpenses;
    }

    // Метод за изплащане на заплати
    public void paySalaries() {
        double totalSalaries = 0;
        for (Cashier cashier : cashiers) {
            totalSalaries = totalSalaries + cashier.getMonthlySalary();
        }
        totalExpenses = totalExpenses + totalSalaries;
    }
}