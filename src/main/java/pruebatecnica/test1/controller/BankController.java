package pruebatecnica.test1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pruebatecnica.test1.service.BankService;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping(path = "/bank")
public class BankController {
    @Autowired
    private BankService bankService;

    @PostMapping(path = "/cashin")
    public String cashIn(@RequestParam Map<String, String> params) {
        return this.bankService.cashIn(
                Long.valueOf(params.get("user_id")),
                params.get("account_id"),
                new BigDecimal(params.get("amount")));

    }

    @PostMapping("/cashout")
    public String cashOut(@RequestParam Map<String, String> params) {
        return this.bankService.cashOut(
                Long.valueOf(params.get("user_id")),
                params.get("account_id"),
                new BigDecimal(params.get("amount")));

    }

    @PostMapping("/transfer")
    public String transfer(@RequestParam Map<String, String> params) {
        return this.bankService.transfer(
                new BigDecimal(params.get("amount")),
                params.get("account_source_id"),
                params.get("account_target_id"),
                Long.valueOf(params.get("user_id")));

    }

}
