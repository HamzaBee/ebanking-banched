package ma.enset.ebankingbanched.exceptions;

public class BalanceNotSufficentException extends RuntimeException {
    public BalanceNotSufficentException(String message) {
        super(message);
    }
}
