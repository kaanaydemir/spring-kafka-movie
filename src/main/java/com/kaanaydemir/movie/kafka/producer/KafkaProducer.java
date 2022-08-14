package com.kaanaydemir.movie.kafka.producer;

import com.kaanaydemir.movie.mapper.MovieMapper;
import com.kaanaydemir.movie.schemas.Movie;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, Movie> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Movie> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String topicName, Movie movie) {

        ListenableFuture<SendResult<String, Movie>> future = kafkaTemplate.send (topicName, movie);

        future.addCallback (new ListenableFutureCallback<SendResult<String, Movie>> () {

            @Override
            public void onSuccess(SendResult<String, Movie> result) {
                System.out.println (
                        "Sent message=[" + result.getProducerRecord ().value () + "] with offset=[" + result.getRecordMetadata ().offset () + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.out.println ("Unable to send message=[" + movie.toString () + "] due to : " + ex.getMessage ());
            }
        });
    }
}
