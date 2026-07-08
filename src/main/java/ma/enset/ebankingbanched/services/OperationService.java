package ma.enset.ebankingbanched.services;


public interface OperationService {
    void debit(String accountId, double amount, String description);
    void credit(String accountId, double amount, String description);
    void transfer(String accountIdSource, String accountIdDestination, double amount, String description);
}
