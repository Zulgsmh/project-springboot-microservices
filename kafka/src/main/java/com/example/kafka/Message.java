package com.example.kafka;

import java.time.LocalDateTime;

public record Message(String message, LocalDateTime createdAt) {
}
