package com.example.consumer.controller;

import com.example.consumer.service.ConsumerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;
}
