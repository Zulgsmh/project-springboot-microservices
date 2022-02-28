package com.example.kafka;


import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "microcervice_test_1", groupId = "groupId")
    void listener(String data) {
        System.out.println("Listener received data : " + data + " ! ");
    }

}
