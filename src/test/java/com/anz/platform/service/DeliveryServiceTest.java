package com.anz.platform.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.model.Delivery;

public class DeliveryServiceTest extends BaseTest {
  private final DbInfo dbInfo = new AppConfigMock().getDbInfo();
  private final DeliveryService deliveryService = new DeliveryService(dbInfo);

  @Test
  public void testFindAllDeliveries() {
    List<Delivery> deliveries = deliveryService.findAll(Delivery.class);
    // System.out.println(deliveries);
    assertEquals(1, deliveries.size());
    assertEquals("12345", deliveries.get(0).getId());
  }

  @Test
  public void testFindByIdDelivery() {
    Delivery delivery = deliveryService.findById("12345", Delivery.class);
    // System.out.println(delivery);
    assertEquals("12345", delivery.getId());
  }

  @Test
  public void testFindByFieldDelivery() {
    Delivery delivery = deliveryService.findByField("orderId", "OrderId12345", Delivery.class);
    // System.out.println(delivery);
    assertEquals("12345", delivery.getId());
  }

  @Test
  public void testPersistUser() {
    final Delivery delivery = Delivery.builder().id("54321").orderId("OrderId12345").deliveryType("SHIPPING").priceDelivery("10$").courierName("GoGoVan").createdTime("24-04-2020 12:12:12").build();
    delivery.setRequest("request-json-format");
    delivery.persist("MinhNguyen");
    assertEquals(new Integer(1), deliveryService.persist(delivery));
  }

  @Test
  public void testUpdateDelivery() {
    final Delivery delivery = Delivery.builder().id("12345").orderId("OrderId12345").deliveryType("SHIPPING").priceDelivery("10$").courierName("GoGoVan").createdTime("24-04-2020 12:12:12").build();
    delivery.setRequest("request-json-format");
    delivery.persist("MinhNguyen");
    assertEquals(new Integer(1), deliveryService.updateById(delivery));
  }
}
