package main;

import entities.Cashier;
import entities.Product;
import entities.Receipt;
import services.StoreManager;
import utils.ReceiptUtils;
import exceptions.InsufficientStockException;
import exceptions.ExpiredProductException;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        // Създаване на обект StoreManager
        StoreManager storeManager = new StoreManager(10); // 10 дни преди да важи отстъпката

        // Създаване и добавяне на продукти
        Product bread = new Product(1, "Хляб", 1.00, "Храна", LocalDate.now().plusDays(5), 0.20, 0.10, 50);
        Product tv = new Product(2, "Телевизор", 300, "Нехранителни стоки", LocalDate.now().plusYears(2), 0.30, 0.0, 30);
        storeManager.addProduct(bread, 50);
        storeManager.addProduct(tv, 30);

        // Създаване и добавяне на касиер
        Cashier john = new Cashier("Иван Иванов", 1, 1500);
        storeManager.addCashier(john);

        try {
            // Продажба на продукт
            Receipt receipt = storeManager.sellProduct(1, 2, 1);
            System.out.println("Касова бележка генерирана успешно:");
            System.out.println(receipt);

            // Записване на касовата бележка във файл
            ReceiptUtils.writeReceiptToFile(receipt, "receipt_" + receipt.getReceiptNumber() + ".txt");
            
            // Четене на касовата бележка от файл
            String readReceipt = ReceiptUtils.readReceiptFromFile("receipt_" + receipt.getReceiptNumber() + ".txt");
            System.out.println("\nКасова бележка прочетена от файл:");
            System.out.println(readReceipt);
        } catch (InsufficientStockException e) {
            System.out.println("Грешка: Недостатъчна наличност. " + e.getMessage());
        } catch (ExpiredProductException e) {
            System.out.println("Грешка: Продуктът е с изтекъл срок на годност. " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Възникна грешка: " + e.getMessage());
        }

        // Извеждане на финансов отчет
        System.out.println("\nФинансов отчет:");
        System.out.println("Общи приходи: $" + storeManager.getTotalRevenue());
        System.out.println("Общи разходи: $" + storeManager.getTotalExpenses());
        System.out.println("Печалба: $" + storeManager.calculateProfit());

        // Изплащане на заплати
        storeManager.paySalaries();
        System.out.println("\nСлед изплащане на заплати:");
        System.out.println("Общи разходи: $" + storeManager.getTotalExpenses());
        System.out.println("Печалба: $" + storeManager.calculateProfit());
    }
}