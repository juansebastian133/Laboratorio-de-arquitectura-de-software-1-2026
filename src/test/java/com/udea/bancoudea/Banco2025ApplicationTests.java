package com.udea.bancoudea;

import com.udea.bancoudea.controller.CustomerController;
import com.udea.bancoudea.controller.HealthController;
import com.udea.bancoudea.controller.TransactionController;
import com.udea.bancoudea.dto.CustomerDTO;
import com.udea.bancoudea.dto.TransactionDTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class Banco2025ApplicationTests {

    @Autowired
    private HealthController healthController;

    @Autowired
    private CustomerController customerController;

    @Autowired
    private TransactionController transactionController;

    @Test
    void contextLoads() {
        assertNotNull(customerController);
    }

    @Test
    void health() {
        assertEquals("HEALTH CHECK OK!", healthController.healthCheck());
    }

    @Test
    void version() {
        assertEquals("The actual version is 1.0.0", healthController.version());
    }

    @Test
    void seededCustomersExist() {
        List<CustomerDTO> customers = customerController.getAllCustomers().getBody();
        assertNotNull(customers);
        assertTrue(customers.size() >= 2);
    }

    @Test
    void createAndGetCustomer() {
        CustomerDTO created = createCustomer("2001", 300.0);
        assertNotNull(created.getId());

        CustomerDTO found = customerController.getCustomerById(created.getId()).getBody();
        assertNotNull(found);
        assertEquals("2001", found.getAccountNumber());
        assertEquals(300.0, found.getBalance());
    }

    @Test
    void createCustomerWithoutBalanceFails() {
        CustomerDTO dto = new CustomerDTO(null, "Sin", "Saldo", "2002", null);
        assertThrows(IllegalArgumentException.class, () -> customerController.createCustomer(dto));
    }

    @Test
    void getUnknownCustomerFails() {
        assertThrows(RuntimeException.class, () -> customerController.getCustomerById(999999L));
    }

    @Test
    void transferMoneyUpdatesBalancesAndHistory() {
        createCustomer("3001", 1000.0);
        createCustomer("3002", 50.0);

        ResponseEntity<?> response = transactionController.transferMoney(transaction("3001", "3002", 400.0));
        assertEquals(HttpStatus.OK, response.getStatusCode());

        List<TransactionDTO> history = transactionController.getTransactionsByAccount("3001");
        assertEquals(1, history.size());
        assertEquals(400.0, history.get(0).getAmount());
        assertEquals("3002", history.get(0).getReceiverAccountNumber());
    }

    @Test
    void transferWithInsufficientBalanceReturnsBadRequest() {
        createCustomer("4001", 10.0);
        createCustomer("4002", 0.0);

        ResponseEntity<?> response = transactionController.transferMoney(transaction("4001", "4002", 500.0));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Sender Balance not enough", response.getBody());
    }

    @Test
    void transferWithUnknownSenderReturnsBadRequest() {
        createCustomer("5001", 10.0);

        ResponseEntity<?> response = transactionController.transferMoney(transaction("9999", "5001", 1.0));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Sender Account Number not found", response.getBody());
    }

    @Test
    void transferWithUnknownReceiverReturnsBadRequest() {
        createCustomer("6001", 10.0);

        ResponseEntity<?> response = transactionController.transferMoney(transaction("6001", "9999", 1.0));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Receiver Account Number not found", response.getBody());
    }

    @Test
    void transferWithNullAccountsReturnsBadRequest() {
        ResponseEntity<?> response = transactionController.transferMoney(transaction(null, null, 1.0));
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    private CustomerDTO createCustomer(String accountNumber, Double balance) {
        CustomerDTO dto = new CustomerDTO(null, "Test", "User", accountNumber, balance);
        return customerController.createCustomer(dto).getBody();
    }

    private TransactionDTO transaction(String sender, String receiver, Double amount) {
        TransactionDTO dto = new TransactionDTO();
        dto.setSenderAccountNumber(sender);
        dto.setReceiverAccountNumber(receiver);
        dto.setAmount(amount);
        return dto;
    }
}
