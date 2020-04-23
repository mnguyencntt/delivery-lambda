package com.anz.platform.service;

import java.util.List;
import java.util.UUID;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.model.Delivery;

public class DeliveryServiceTest {
  @Ignore
  @Test
  public void testDelivery() {
    final DeliveryService deliveryService = new DeliveryService(new AppConfigMock().getDbInfo());

    final Delivery delivery = Delivery.builder().id(UUID.randomUUID().toString()).receiverUserId("UIB12345").subject("Delivery OrderId OID12345").contentBody("Minh Nguyen Testing Content Body")
        .deliveryType("EMAIL").amount("1").build();
    delivery.setStatus("status");
    delivery.setMessage("message");
    delivery.setRequest("request");
    delivery.persist();

    System.out.println(deliveryService.persist(delivery));
    List<Delivery> deliveries = deliveryService.findAll(Delivery.class);
    System.out.println(deliveries.size());
    System.out.println(deliveries);
  }
}
