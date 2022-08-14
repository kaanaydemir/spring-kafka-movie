package com.kaanaydemir.movie.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value ("${movie.topic.name}")
    private String movieTopic;

    @Bean
    public NewTopic createTopic() {
        return TopicBuilder.name (movieTopic).build ();

    }
}
