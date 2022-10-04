package pruebatecnica.test1.repository;

import org.springframework.stereotype.Repository;
import pruebatecnica.test1.models.Account;
import pruebatecnica.test1.models.User;

import java.math.BigDecimal;

@Repository
public class BankRepository {

    public User getUser(Long userId) {
        User user = new User();
        user.setUserId(userId);

        return user;
    }

    public Account getAccount(String accountId) {
        Account account = new Account();
        account.setAccountId(accountId);

        return account;
    }

    public void cashIn(Long userId, String accountId, BigDecimal amount) {

    }
    public String cashOut(Long userId, String accountSourceId, BigDecimal amount) {
        // Extraemos en la cuenta de origen

        // Depositamos dinero en la cuenta destino

        return "Dinero extraido";
    }
    public String transfer(Long userId, String accountSourceId, String accountTargetId, BigDecimal amount) {

        return "Dinero transferido";
    }


}
