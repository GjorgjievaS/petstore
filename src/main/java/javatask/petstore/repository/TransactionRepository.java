package javatask.petstore.repository;

import javatask.petstore.model.Transaction;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository {

    int insertTransaction(UUID id, Transaction transaction);

    default int insertTransaction(Transaction transaction){
        UUID id=UUID.randomUUID();
        return insertTransaction(id,transaction);
    }

    List<Transaction> selectAllTransactions();

    Optional<Transaction> selectTransactionById(UUID id);

    int deleteTransactionById(UUID id);

    int updateTransactionById(UUID id,Transaction transaction);
}
