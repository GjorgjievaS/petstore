package javatask.petstore.service;

import javatask.petstore.enumerations.Type;
import javatask.petstore.model.Transaction;
import javatask.petstore.repository.PetRepository;
import javatask.petstore.repository.TransactionRepository;
import javatask.petstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final PetRepository petRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, PetRepository petRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.petRepository = petRepository;
        this.userRepository = userRepository;
    }
    public int addTransaction(Transaction transaction){
        return transactionRepository.insertTransaction(transaction);
    }

    public void purchasePets(){

        List<Integer> buyersIndices = new ArrayList<>();

        int purchaseCount=0;
        int notPuchasedCount;

        for (int i=0; i<userRepository.selectAllUsers().size();i++){
            for (int j=0;j<petRepository.selectAllPets().size();j++){

                if (petRepository.selectAllPets().get(j).getOwner() != null) continue;

                if (petRepository.selectAllPets().get(j).getPrice() > userRepository.selectAllUsers().get(i).getBudget()){

                    continue;
                }

                petRepository.selectAllPets().get(j).setOwner(userRepository.selectAllUsers().get(i));
                userRepository.selectAllUsers().get(i).setBudget(userRepository.selectAllUsers().get(i).getBudget() - petRepository.selectAllPets().get(j).getPrice());
                if(!buyersIndices.contains(i)) {
                    purchaseCount++;
                    buyersIndices.add(i);
                }


                if (petRepository.selectAllPets().get(j).getType()== Type.CAT){
                    System.out.printf("Meow, cat %s has owner %s",this.petRepository.selectAllPets().get(i).getName(), this.petRepository.selectAllPets().get(i).getOwner());
                } else {
                    System.out.printf("Woof, dog %s has owner %s",this.petRepository.selectAllPets().get(i).getName(), this.petRepository.selectAllPets().get(i).getOwner());

                }
            }
        }

        notPuchasedCount=userRepository.selectAllUsers().size()-purchaseCount;
        Transaction transaction=new Transaction(UUID.randomUUID(),new Date(),purchaseCount,notPuchasedCount);
        this.addTransaction(transaction);
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.selectAllTransactions();
    }

    public Optional<Transaction> getTransactionById(UUID id){
        return transactionRepository.selectTransactionById(id);
    }

    public int deleteTransaction(UUID id){
        return transactionRepository.deleteTransactionById(id);
    }

    public int updateTransaction(UUID id,Transaction transaction){
        return  transactionRepository.updateTransactionById(id,transaction);
    }
}

