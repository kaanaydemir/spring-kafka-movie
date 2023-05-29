# Spring Kafka Avro Consumer Producer
This project is a Spring Boot application that serves as a Kafka consumer and producer using Avro serialization. It has been implemented in Java.

## Getting Started
### Prerequisites
You need to have the following installed:

Java Development Kit (JDK) <br />
Apache Kafka <br />
Spring Boot <br />

### Running the Application
To run the application:

Clone this repository. <br />
Navigate to the project directory. <br />
Run the command ***./mvnw spring-boot:run***  <br />
### Features
This application includes:  <br />

A Kafka consumer that deserializes Avro messages and processes them.  <br />
A Kafka producer that serializes messages in Avro format and sends them to Kafka.  <br />
### Configuration 
The Kafka consumer is configured in the KafkaConsumerConfig class with the following properties:  <br />

Bootstrap servers: ***"localhost:9092"***  <br />
Key deserializer: StringDeserializer.class  <br />
Value deserializer: KafkaAvroDeserializer.class  <br />
Auto offset reset: "earliest"  <br />
Schema registry URL: "http://localhost:8081"  <br />
Group ID: "movies-group-id"  <br />
Specific Avro reader config: true  <br />
The ConsumerFactory and KafkaListenerContainerFactory beans are also set up in the KafkaConsumerConfig class.  <br />
