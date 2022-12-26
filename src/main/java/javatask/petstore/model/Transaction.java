package javatask.petstore.model;

import java.util.Date;
import java.util.UUID;

public class Transaction {

    private final UUID id;

    private Date executionDate;

    private int usersThatBoughtCount;

    private int usersThatDidntBuyCount;


    public Transaction(UUID id, Date executionDate, int usersThatBoughtCount, int usersThatDidntBuyCount) {
        this.id = id;
        this.executionDate = executionDate;
        this.usersThatBoughtCount = usersThatBoughtCount;
        this.usersThatDidntBuyCount = usersThatDidntBuyCount;
    }

    public UUID getId() {
        return id;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public int getUsersThatBoughtCount() {
        return usersThatBoughtCount;
    }

    public int getUsersThatDidntBuyCount() {
        return usersThatDidntBuyCount;
    }
}
