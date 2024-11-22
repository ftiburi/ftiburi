package com.example.opcua;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class OpcUaController {

    @Autowired
    private OpcUaService opcUaService;

    @PostMapping("/data")
    public ResponseEntity<String> receiveData(@RequestBody DataDTO data) {

        try {
            opcUaService.writeData(data.getNodeId(), data.getValue());
            return ResponseEntity.ok("Dados recebidos com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao processar dados: " + e.getMessage());
        }
    }
}
