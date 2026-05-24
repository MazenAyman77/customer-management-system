package com.mazenscode.controller;

import com.mazenscode.model.Customer;
import com.mazenscode.services.CustomerService;

import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class CustomerController {

    @FXML
    private TableView<Customer> customerTable;

    @FXML
    private TableColumn<Customer, Integer> idColumn;

    @FXML
    private TableColumn<Customer, String> NameColumn;

    @FXML
    private TableColumn<Customer, String> emailColumn;

    @FXML
    private TableColumn<Customer, String> phoneColumn;

    @FXML
    private TextField NameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;

    @FXML
    private Label statusLabel;

    private final CustomerService service =
            new CustomerService();

    @FXML
    public void initialize() {

        idColumn.setCellValueFactory(
                data ->
                        new SimpleIntegerProperty(
                                data.getValue().getId()
                        ).asObject()
        );

        NameColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getName()
                        )
        );


        emailColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getEmail()
                        )
        );

        phoneColumn.setCellValueFactory(
                data ->
                        new SimpleStringProperty(
                                data.getValue().getPhone()
                        )
        );

        customerTable.getSelectionModel()
                .selectedItemProperty()
                .addListener(
                        (obs, oldVal, customer) -> {

                            if (customer != null) {

                                NameField.setText(
                                        customer.getName()
                                );

                                emailField.setText(
                                        customer.getEmail()
                                );

                                phoneField.setText(
                                        customer.getPhone()
                                );
                            }
                        }
                );

        loadCustomers();
    }

    // LOAD CUSTOMERS
    private void loadCustomers() {

        CompletableFuture.runAsync(() -> {

            try {

                List<Customer> customers =
                        service.getCustomers();

                Platform.runLater(() -> {

                    customerTable.setItems(
                            FXCollections.observableArrayList(
                                    customers
                            )
                    );

                    statusLabel.setText(
                            "Customers loaded"
                    );
                });

            } catch (Exception ex) {

                Platform.runLater(() ->
                        statusLabel.setText(
                                ex.getMessage()
                        )
                );
            }
        });
    }

    // ADD CUSTOMER
    @FXML
    public void handleAdd() {

        Customer customer =
                new Customer();

        customer.setName(
                NameField.getText()
        );

        customer.setEmail(
                emailField.getText()
        );

        customer.setPhone(
                phoneField.getText()
        );

        CompletableFuture.runAsync(() -> {

            try {

                service.addCustomer(customer);

                Platform.runLater(() -> {

                    clearFields();

                    loadCustomers();

                    statusLabel.setText(
                            "Customer added"
                    );
                });

            } catch (Exception ex) {

                Platform.runLater(() ->
                        statusLabel.setText(
                                ex.getMessage()
                        )
                );
            }
        });
    }

    // UPDATE CUSTOMER
    @FXML
    public void handleUpdate() {

        Customer customer =
                customerTable.getSelectionModel()
                        .getSelectedItem();

        if (customer == null) {

            statusLabel.setText(
                    "Select customer first"
            );

            return;
        }

        customer.setName(
                NameField.getText()
        );

        customer.setEmail(
                emailField.getText()
        );

        customer.setPhone(
                phoneField.getText()
        );

        CompletableFuture.runAsync(() -> {

            try {

                service.updateCustomer(customer);

                Platform.runLater(() -> {

                    loadCustomers();

                    clearFields();

                    statusLabel.setText(
                            "Customer updated successfully"
                    );
                });

            } catch (Exception ex) {

                Platform.runLater(() -> {

                    statusLabel.setText(
                            ex.getMessage()
                    );
                });
            }
        });
    }

    // DELETE CUSTOMER
    @FXML
    public void handleDelete() {

        Customer customer =
                customerTable.getSelectionModel()
                        .getSelectedItem();

        if (customer == null) {

            statusLabel.setText(
                    "Select customer first"
            );

            return;
        }

        CompletableFuture.runAsync(() -> {

            try {

                service.deleteCustomer(
                        customer.getId()
                );

                Platform.runLater(() -> {

                    clearFields();

                    loadCustomers();

                    statusLabel.setText(
                            "Customer deleted"
                    );
                });

            } catch (Exception ex) {

                Platform.runLater(() ->
                        statusLabel.setText(
                                ex.getMessage()
                        )
                );
            }
        });
    }

    // REFRESH
    @FXML
    public void handleRefresh() {

        loadCustomers();
    }

    private void clearFields() {

        NameField.clear();
        emailField.clear();
        phoneField.clear();
    }

}
