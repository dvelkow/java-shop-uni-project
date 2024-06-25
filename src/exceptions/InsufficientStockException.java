package exceptions;

// В случай на недостатъчна наличност на продукт
public class InsufficientStockException extends Exception {
    
    private String productName;
    private int requestedQuantity;
    private int availableQuantity;

    public InsufficientStockException(String productName, int requestedQuantity, int availableQuantity) {
        super("Недостатъчна наличност за продукт " + productName + 
              ": Заявено количество " + requestedQuantity + 
              ", налично количество " + availableQuantity);
        this.productName = productName;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}
