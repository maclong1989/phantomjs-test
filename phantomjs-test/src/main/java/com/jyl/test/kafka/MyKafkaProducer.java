package com.jyl.test.kafka;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class MyKafkaProducer {

	public static void main(String[] args) {

		Properties props = new Properties();
		props.put("bootstrap.servers",
				"host-10-19-12-47.example.com:6667,host-10-19-12-51.example.com:6667,host-10-19-12-50.example.com:6667");
		props.put("acks", "all");
		props.put("retries", 0);
		props.put("batch.size", 16384);
		props.put("linger.ms", 1);
		props.put("buffer.memory", 33554432);
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

		Producer<String, String> producer = new KafkaProducer<>(props);
		for (int i = 0; i < 100; i++)
			producer.send(new ProducerRecord<String, String>("jyl-topic", Integer.toString(i), Integer.toString(i)));

		producer.close();

	}

}