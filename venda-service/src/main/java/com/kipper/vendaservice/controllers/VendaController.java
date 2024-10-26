package com.kipper.vendaservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("venda")
public class VendaController {
    private KafkaTemplate<String, String> kafkaTemplate;
    public VendaController(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping
    public ResponseEntity<String> criarVenda(@RequestBody String venda){
        kafkaTemplate.send("product-update-topic", venda);
        return ResponseEntity.ok().build();
    }
}
