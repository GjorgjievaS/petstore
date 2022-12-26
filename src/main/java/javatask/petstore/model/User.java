package javatask.petstore.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;


import java.util.UUID;

public class User {

    private final UUID id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private String email;

    private double budget;

    public User(@JsonProperty("id") UUID id,
                @JsonProperty("firstName") String firstName,
                @JsonProperty("lastName") String lastName,
                @JsonProperty("email") String email,
                @JsonProperty("budget") double budget) {
        //if (firstName.equals("")) throw new HttpClientErrorException(HttpStatusCode.valueOf(400));
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.budget = budget;

    }



    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
}
