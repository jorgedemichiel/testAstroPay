package pruebatecnica.test1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pruebatecnica.test1.models.Account;
import pruebatecnica.test1.models.User;
import pruebatecnica.test1.repository.BankRepository;

import java.math.BigDecimal;

@Service
public class BankService {
    @Autowired
    private BankRepository bankRepository;

    public String cashIn(Long userId, String accountId, BigDecimal amount) {

        // validamos el usuario
        User user = this.bankRepository.getUser(userId);

        // validamos la cuenta
        Account account = this.bankRepository.getAccount(accountId);

        // validar que la cuenta pertenece al user
        if (user.getUserId() == account.getUserId()) {
            // validamos el monto
            if (validateAmountGrantThatZero(amount)) {
                this.bankRepository.cashIn(
                        user.getUserId(),
                        account.getAccountId(),
                        amount);
            }
        }

        return "El dinero se deposito exitosamente";
    }

    public String cashOut(Long userId, String accountId, BigDecimal amount) {

        // validamos el usuario
        User user = this.bankRepository.getUser(userId);

        // validamos la cuenta
        Account account = this.bankRepository.getAccount(accountId);

        // validar que la cuenta pertenece al user
        if (validateUserAccount(user, account)) {
            // validamos el monto
            if (validateAmountGrantThatZero(amount) && validateCashAvailable(amount, account.getCashAvailable())) {
                this.bankRepository.cashIn(
                        user.getUserId(),
                        account.getAccountId(),
                        amount);
            }
        }

        return "El dinero se deposito exitosamente";
    }

    private boolean validateUserAccount(User user, Account account) {
        return user.getUserId() == account.getUserId();
    }

    public boolean validateAmountGrantThatZero(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) == 1;
    }

    public boolean validateCashAvailable(BigDecimal amount, BigDecimal cashAvailable) {
        return amount.compareTo(cashAvailable) < 1;
    }

    public String transfer(BigDecimal amount, String accountSourceId, String accountTargetId, Long userId) {
        User user = this.bankRepository.getUser(userId);
        Account accountSource = this.bankRepository.getAccount(accountSourceId);
        Account accountTarget = this.bankRepository.getAccount(accountTargetId);

        if (validateUserAccount(user, accountSource)
                && validateCashAvailable(amount, accountSource.getCashAvailable())
                && validateAmountGrantThatZero(amount)
                && accountTarget != null) {
            this.bankRepository.cashOut(userId, accountSourceId, amount);
            this.bankRepository.transfer(userId, accountSourceId, accountTargetId, amount);

            return "Transferencia realizada";
        }

        return "Transferencia rechazada";
    }

}
