package com.example.mqtt;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class MqttClientService implements MqttCallback {

    @Value("${mqtt.broker.url}")
    private String brokerUrl;

    @Value("${mqtt.broker.username}")
    private String username;

    @Value("${mqtt.broker.password}")
    private String password;

    @Value("${mqtt.topic}")
    private String topic;

    private final SensorDataRepository sensorDataRepository;

    public MqttClientService(SensorDataRepository sensorDataRepository) {
        this.sensorDataRepository = sensorDataRepository;
    }

    @PostConstruct
    public void init() {
        try {
            MqttClient client = new MqttClient(brokerUrl, MqttClient.generateClientId());
            MqttConnectOptions connOpts = new MqttConnectOptions();
            connOpts.setUserName(username);
            connOpts.setPassword(password.toCharArray());
            client.setCallback(this);
            client.connect(connOpts);
            client.subscribe(topic);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connectionLost(Throwable cause) {
        System.out.println("Connection lost: " + cause.getMessage());
    }

    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        System.out.println("Message arrived: " + message.toString());
        SensorData sensorData = new SensorData();
        sensorData.setTopic(topic);
        sensorData.setMessage(new String(message.getPayload()));
        sensorDataRepository.save(sensorData);
    }

    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {
        // Not used in this example
    }

    public String getForceData() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getForceData'");
    }
}


