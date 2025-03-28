package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerResponse {
    private Integer id;
    //again change from integer to int to prevent null values
    private String firstName;
    private String lastName;
//suggest leaving java docs helps with colaberative enviroment

    @JsonIgnore
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(CustomerResponse customer) throws Exception {
        return objectMapper.writeValueAsString(customer);
    }
    public static CustomerResponse fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, CustomerResponse.class);
    }
    protected CustomerResponse() {}

    public CustomerResponse(Integer id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
    // good tha tyou used this. because it clarifies that your referencing to instance variables and helps confusion between parameters and instance variables.

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

//going over .that