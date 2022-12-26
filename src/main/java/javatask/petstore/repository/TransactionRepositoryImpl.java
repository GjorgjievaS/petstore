package javatask.petstore.repository;

import javatask.petstore.model.Transaction;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("transactionRepo")
public class TransactionRepositoryImpl implements TransactionRepository{

    private static List<Transaction> DB=new ArrayList<>();

    @Override
    public int insertTransaction(UUID id, Transaction transaction) {
        DB.add(new Transaction(id,transaction.getExecutionDate(),transaction.getUsersThatBoughtCount(),transaction.getUsersThatDidntBuyCount()));
        return 1;
    }

    @Override
    public List<Transaction> selectAllTransactions() {
        return DB;
    }

    @Override
    public Optional<Transaction> selectTransactionById(UUID id) {
        return DB.stream()
                .filter(t->t.getId().equals(id))
                .findFirst();
    }

    @Override
    public int deleteTransactionById(UUID id) {
        Optional<Transaction> transactionMaybe = selectTransactionById(id);
        if(transactionMaybe.isEmpty()) return 0;

        DB.remove(transactionMaybe.get());
        return 1;
    }

    @Override
    public int updateTransactionById(UUID id, Transaction update) {
        return selectTransactionById(id)
                .map(transaction -> {
                    int indexOfTransactionToUpdate = DB.indexOf(transaction);
                    if(indexOfTransactionToUpdate >= 0){
                        DB.set(indexOfTransactionToUpdate, new Transaction(id,update.getExecutionDate(),update.getUsersThatBoughtCount(),update.getUsersThatDidntBuyCount()));
                        return 1;
                    }
                    return 0;
                })
                .orElse(0);
    }
}
