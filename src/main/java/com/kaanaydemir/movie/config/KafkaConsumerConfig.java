package com.kaanaydemir.movie.config;


import com.kaanaydemir.movie.schemas.Movie;
import io.confluent.kafka.serializers.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConsumerConfig {

    public Map<String, Object> consumerConfig() {
        Map<String, Object> props = new HashMap<> ();
        props.put (ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put (ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put (ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put (ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put (KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put (ConsumerConfig.GROUP_ID_CONFIG,"movies-group-id");
        props.put (KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        return props;
    }

    @Bean
    public ConsumerFactory<String, Movie> consumerFactory() {
        return new DefaultKafkaConsumerFactory<> (consumerConfig ());
    }

    @Bean
    public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, Movie>> kafkaListenerContainerFactory(
            ConsumerFactory<String, Movie> consumerFactory) {
        ConcurrentKafkaListenerContainerFactory<String, Movie> factory = new ConcurrentKafkaListenerContainerFactory<> ();

        factory.setConsumerFactory (consumerFactory);
        return factory;
    }
}
