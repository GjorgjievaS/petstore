package javatask.petstore.webapi;

import jakarta.validation.Valid;
import javatask.petstore.model.Transaction;
import javatask.petstore.service.TransactionService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping("/api/purchase")
@RestController
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public void addTransaction(@Valid @NonNull @RequestBody Transaction transaction) {
        transactionService.addTransaction(transaction);
    }

    @PostMapping(path = "/buy")
    public void purchase() {
        transactionService.purchasePets();
    }

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return  transactionService.getAllTransactions();
    }

    @GetMapping(path = "{id}")
    public Transaction getTransactionById(@PathVariable("id") UUID id) {
        return transactionService.getTransactionById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteTransactionById(@PathVariable("id") UUID id) {
        transactionService.deleteTransaction(id);
    }

    @PutMapping(path = "{id}")
    public void updateTransaction(@PathVariable("id") UUID id,@Valid @NonNull @RequestBody Transaction transaction) {
        transactionService.updateTransaction(id,transaction);
    }
}
