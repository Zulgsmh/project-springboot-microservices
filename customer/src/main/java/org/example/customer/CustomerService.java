package org.example.customer;

import com.example.clients.fraud.FraudCheckResponse;
import com.example.clients.fraud.FraudClient;
import com.example.clients.notifications.NotificationClient;
import com.example.clients.notifications.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, NotificationClient notificationClient) {
    public void registerCustomer(CustomerRegistrationRequest request) throws IllegalAccessException {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email()).build();

        customerRepository.saveAndFlush(customer); //flush to have access to the customer ID directly after

        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster()) {
            throw new IllegalAccessException("Fraudster detected");
        }


        notificationClient.sendNotification(
                new NotificationRequest(
                        customer.getId(),
                        customer.getEmail(),
                        String.format("Hi %s, welcome to this app.", customer.getFirstName())
                )
        );

    }
}
