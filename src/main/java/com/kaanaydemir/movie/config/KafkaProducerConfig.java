package com.kaanaydemir.movie.config;

import com.kaanaydemir.movie.schemas.Movie;
import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    public Map<String, Object> producerConfig() {
        Map<String, Object> properties = new HashMap<> ();
        properties.put (ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put (ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        properties.put (ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        properties.put (ProducerConfig.ACKS_CONFIG, "1");
        properties.put (KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");


        return properties;
    }

    @Bean
    public ProducerFactory<String, Movie> producerFactory() {
        return new DefaultKafkaProducerFactory<> (producerConfig ());
    }

    @Bean
    public KafkaTemplate<String, Movie> kafkaTemplate(ProducerFactory<String, Movie> producerFactory) {
        return new KafkaTemplate<> (producerFactory);
    }

}
