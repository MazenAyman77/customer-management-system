package com.mazenscode.services;

import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mazenscode.model.Customer;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class CustomerService {

    private static final String BASE_URL =
            "http://localhost:8080/api/v1/customers";

    private final OkHttpClient client =
            new OkHttpClient();

    private final ObjectMapper mapper =
            new ObjectMapper()
                    .registerModule(
                            new JavaTimeModule()
                    );

    // GET ALL CUSTOMERS
    public List<Customer> getCustomers()
            throws IOException {

        Request request = new Request.Builder()
                .url(BASE_URL)
                .get()
                .build();

        try (Response response =
                     client.newCall(request).execute()) {

            handleErrors(response);

            String json =
                    response.body().string();

            return mapper.readValue(
                    json,
                    new TypeReference<>() {}
            );
        }
    }

    // ADD CUSTOMER
    public void addCustomer(Customer customer)
            throws IOException {

        String json =
                mapper.writeValueAsString(customer);

        RequestBody body =
                RequestBody.create(
                        json,
                        MediaType.parse(
                                "application/json"
                        )
                );

        Request request = new Request.Builder()
                .url(BASE_URL)
                .post(body)
                .build();

        try (Response response =
                     client.newCall(request).execute()) {

            handleErrors(response);
        }
    }

    // UPDATE CUSTOMER
    public void updateCustomer(Customer customer)
            throws IOException {

        String json =
                mapper.writeValueAsString(customer);

        RequestBody body =
                RequestBody.create(
                        json,
                        MediaType.parse(
                                "application/json"
                        )
                );

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + customer.getId())
                .put(body)
                .build();

        try (Response response =
                     client.newCall(request).execute()) {

            handleErrors(response);
        }
    }

    // DELETE CUSTOMER
    public void deleteCustomer(int id)
            throws IOException {

        Request request = new Request.Builder()
                .url(BASE_URL + "/" + id)
                .delete()
                .build();

        try (Response response =
                     client.newCall(request).execute()) {

            handleErrors(response);
        }
    }

    // ERROR HANDLING
    private void handleErrors(Response response)
            throws IOException {

        if (!response.isSuccessful()) {

            throw new IOException(
                    "HTTP Error: " + response.code()
            );
        }
    }

}
