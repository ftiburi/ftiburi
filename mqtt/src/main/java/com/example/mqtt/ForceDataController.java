package com.example.mqtt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForceDataController {

    private MqttClientService mqttClientService;
    
        ForceDataController(MqttClientService mqttClientService) {
            this.mqttClientService = mqttClientService;
        }
    
        @GetMapping("/force_data")
        public String getForceData() {
            return mqttClientService.getForceData();
        }
    
        public MqttClientService getMqttClientService() {
            return mqttClientService;
        }
    
        public void setMqttClientService(MqttClientService mqttClientService) {
            this.mqttClientService = mqttClientService;
    }
}
