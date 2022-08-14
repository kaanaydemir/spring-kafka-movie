package com.kaanaydemir.movie.kafka.consumer;

import com.kaanaydemir.movie.schemas.Movie;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "${movie.topic.name}",
                   containerFactory = "kafkaListenerContainerFactory")
    void listener(ConsumerRecord<String, Movie> record) {
        String key = record.key ();
        Movie movie = record.value ();
        System.out.println ("Avro message received for key : " + key + " value : " + movie.toString ());
    }
}

