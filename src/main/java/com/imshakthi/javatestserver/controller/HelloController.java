package com.imshakthi.javatestserver.controller;

import com.imshakthi.javatestserver.kafka.producer.SampleProducer;
import com.imshakthi.javatestserver.model.request.MessageRequest;
import com.imshakthi.javatestserver.model.response.MessageBody;
import com.imshakthi.javatestserver.repository.ProductBomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

  private final SampleProducer producer;
  private final ProductBomRepository productBomRepository;

  @Autowired
  public HelloController(
      final SampleProducer producer, final ProductBomRepository productBomRepository) {
    this.producer = producer;
    this.productBomRepository = productBomRepository;
  }

  @GetMapping("/hello")
  public ResponseEntity<MessageBody> index() {

    MessageBody messageBody = MessageBody.builder().message("Welcome to HOME page!").build();

    Optional.of(productBomRepository.findByProductName("chair"))
        .ifPresent(
            productBomViews ->
                productBomViews.forEach(
                    i -> System.out.println(i.getName() + "  >> " + i.getRequiredItemUnits())));

    return ResponseEntity.ok(messageBody);
  }

  @PostMapping("/say")
  public ResponseEntity<MessageRequest> say(@RequestBody final MessageRequest request) {

    producer.sendMessage(request.message());

    return ResponseEntity.ok(request);
  }
}
