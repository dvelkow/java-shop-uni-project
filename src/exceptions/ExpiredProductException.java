package exceptions;

// В случай на изтекъл срок на годност на продукт
public class ExpiredProductException extends Exception {
    
    public ExpiredProductException(String message) {
        super(message);
    }
}
