package ru.example.getuserservice.services;

import com.google.gson.Gson;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.example.getuserservice.model.User;

import java.util.List;
import java.util.UUID;

@Service
public class KafkaProducerService {
    @Value(value = "spring.kafka.template.default-topic")
    private String topic;
    private Gson gson;
    private KafkaTemplate<String,String> kafkaTemplate;

    public KafkaProducerService(Gson gson, KafkaTemplate<String, String> kafkaTemplate) {
        this.gson = gson;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendUsersToKafka(List<User> users){
        users.stream()
                .map(u-> gson.toJson(u))
                .map(s ->kafkaTemplate.send(UUID.randomUUID().toString(),s))
                .forEach(System.out::println);
    }
}
