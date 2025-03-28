package edu.sdccd.cisc191.template;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CustomerRequest {
    private Integer id;
//consider using int instead of integer if null values arent expected
    //Test //test

    @JsonIgnore
    //converts a customer request object to a json string
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static String toJSON(CustomerRequest customer) throws Exception {
        return objectMapper.writeValueAsString(customer);
    }
    public static CustomerRequest fromJSON(String input) throws Exception{
        return objectMapper.readValue(input, CustomerRequest.class);
    }
    //default constructor required for json serialization/deserilization
    protected CustomerRequest() {}
//constructor to instalize customer request with an ID
    public CustomerRequest(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d]",
                id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}


//JUst added some java docs its good for group work.