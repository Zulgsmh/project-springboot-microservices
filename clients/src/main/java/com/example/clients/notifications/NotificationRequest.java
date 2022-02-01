package com.example.clients.notifications;

public record NotificationRequest(
        Long toCustomerId,
        String toCustomerName,
        String message
) {
}
