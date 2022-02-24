package org.example.customer;

import com.example.amqp.RabbitMQMessageProducer;
import com.example.clients.fraud.FraudCheckResponse;
import com.example.clients.fraud.FraudClient;
import com.example.clients.notifications.NotificationRequest;
import org.springframework.stereotype.Service;

@Service
public record CustomerService(CustomerRepository customerRepository, FraudClient fraudClient, RabbitMQMessageProducer producer) {

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


        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to this app.", customer.getFirstName())
        );

        producer.publish(notificationRequest, "internal.exchange", "internal.notification.routing-key");
    }
}
