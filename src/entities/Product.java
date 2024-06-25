package entities;

import java.time.LocalDate;
import exceptions.ExpiredProductException;
import exceptions.InsufficientStockException;

public class Product {
    // Полета на класа Product
    private int id;
    private String name;
    private double deliveryPrice;
    private String category;
    private LocalDate expiryDate;
    private double markupPercentage;
    private double discountPercentage;
    private int stock;

    // Конструктор на класа Product
    public Product(int id, String name, double deliveryPrice, String category, LocalDate expiryDate, double markupPercentage, double discountPercentage, int initialStock) {
        this.id = id;
        this.name = name;
        this.deliveryPrice = deliveryPrice;
        this.category = category;
        this.expiryDate = expiryDate;
        this.markupPercentage = markupPercentage;
        this.discountPercentage = discountPercentage;
        this.stock = initialStock;
    }

    // Методи за достъп (getters) до полетата на класа

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getDeliveryPrice() {
        return deliveryPrice;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public double getMarkupPercentage() {
        return markupPercentage;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public int getStock() {
        return stock;
    }

    // Метод за задаване на наличност
    public void setStock(int stock) {
        this.stock = stock;
    }

    // Метод за изчисляване на продажната цена
    // Хвърля изключение, ако продуктът е с изтекъл срок на годност
    public double calculateSalePrice(int daysBeforeDiscountApplies) throws ExpiredProductException {
        if (LocalDate.now().isAfter(expiryDate)) {
            throw new ExpiredProductException("Product " + name + " has expired.");
        }

        double salePrice = this.deliveryPrice * (1 + this.markupPercentage);
        if (this.expiryDate.isBefore(LocalDate.now().plusDays(daysBeforeDiscountApplies))) {
            salePrice = salePrice * (1 - this.discountPercentage);
        }
        return salePrice;
    }

    // Метод за актуализиране на наличността
    // Хвърля изключение, ако няма достатъчно наличност
    public void updateStock(int quantity) throws InsufficientStockException {
        if (this.stock < quantity) {
            throw new InsufficientStockException(this.name, quantity, this.stock);
        }
        this.stock = this.stock - quantity;
    }

    // Предефиниран метод toString за представяне на обекта като низ
    @Override
    public String toString() {
        return "Product{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", deliveryPrice=" + deliveryPrice +
               ", category='" + category + '\'' +
               ", expiryDate=" + expiryDate +
               ", markupPercentage=" + markupPercentage +
               ", discountPercentage=" + discountPercentage +
               ", stock=" + stock +
               '}';
    }
}