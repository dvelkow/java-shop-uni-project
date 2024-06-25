package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Receipt {
    private int receiptNumber;
    private Cashier cashier;
    private LocalDateTime issueDate;
    private List<ProductEntry> products;
    private double totalPrice;

    // Конструктор на класа Receipt
    public Receipt(int receiptNumber, Cashier cashier, LocalDateTime issueDate) {
        this.receiptNumber = receiptNumber;
        this.cashier = cashier;
        this.issueDate = issueDate;
        this.products = new ArrayList<>();
        this.totalPrice = 0.0;
    }

    // Метод за добавяне на продукт към касовата бележка
    public void addProduct(Product product, int quantity, double salePrice) {
        ProductEntry entry = new ProductEntry(product, quantity, salePrice);
        products.add(entry);
        calculateTotalPrice();
    }

    // Метод за изчисляване на общата сума на касовата бележка
    private void calculateTotalPrice() {
        double sum = 0;
        for (ProductEntry entry : products) {
            sum = sum + (entry.getSalePrice() * entry.getQuantity());
        }
        this.totalPrice = sum;
    }

    // Методи за достъп (getters) до полетата на класа

    public int getReceiptNumber() {
        return receiptNumber;
    }

    public Cashier getCashier() {
        return cashier;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public List<ProductEntry> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    // Метод за задаване на общата сума
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    // Метод който превръща касовата бележка в низ
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Receipt Number: ").append(receiptNumber).append("\n");
        sb.append("Cashier: ").append(cashier.getName()).append("\n");
        sb.append("Date: ").append(issueDate).append("\n");
        sb.append("Products:\n");
        for (ProductEntry entry : products) {
            sb.append("- ").append(entry.getProduct().getName())
              .append(", Quantity: ").append(entry.getQuantity())
              .append(", Price: ").append(String.format("%.2f", entry.getSalePrice()))
              .append(", Total: ").append(String.format("%.2f", entry.getSalePrice() * entry.getQuantity()))
              .append("\n");
        }
        sb.append("Total Price: ").append(String.format("%.2f", totalPrice));
        return sb.toString();
    }

    // Вложен клас за показване на един продукт в касовата бележка
    public static class ProductEntry {
        private Product product;
        private int quantity;
        private double salePrice;

        public ProductEntry(Product product, int quantity, double salePrice) {
            this.product = product;
            this.quantity = quantity;
            this.salePrice = salePrice;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getSalePrice() {
            return salePrice;
        }
    }
}