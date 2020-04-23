package com.anz.platform.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DeliveryTest {
  private static final int expectedLength = 18;

  @Test
  public void testUser() {
    Delivery delivery = new Delivery();
    delivery.setId("12345");
    delivery.setOrderId("OderId12345");
    delivery.setDeliveryType("SHIPPING");

    //
    String fieldJoining = delivery.findFieldsJoining();
    System.out.println(fieldJoining);
    assertTrue(fieldJoining.startsWith("id,orderId,deliveryType,"));
    assertEquals(expectedLength, fieldJoining.split(",").length);

    //
    String marks = delivery.findMarksJoining();
    System.out.println(marks);
    assertEquals(expectedLength, marks.chars().filter(ch -> ch == '?').count());
    assertEquals(expectedLength, marks.split(",").length);

    //
    String[] findValues = delivery.findValues();
    assertEquals("12345", findValues[0]);
    assertEquals("OderId12345", findValues[1]);
    assertEquals("SHIPPING", findValues[2]);

    //
    List<String> findFieldValues1 = delivery.findFieldValues();
    assertEquals("id='12345'", findFieldValues1.get(0));
    assertEquals("orderId='OderId12345'", findFieldValues1.get(1));
    assertEquals("deliveryType='SHIPPING'", findFieldValues1.get(2));
    List<String> findFieldValues2 = delivery.findFieldValues("id");
    assertEquals("orderId='OderId12345'", findFieldValues2.get(0));
    assertEquals("deliveryType='SHIPPING'", findFieldValues2.get(1));

    //
    String findFieldValuesJoining1 = delivery.findFieldValuesJoining();
    System.out.println(findFieldValuesJoining1);
    assertTrue(findFieldValuesJoining1.startsWith("id='12345',orderId='OderId12345',deliveryType='SHIPPING',"));
    String findFieldValuesJoining2 = delivery.findFieldValuesJoining("id");
    System.out.println(findFieldValuesJoining2);
    assertTrue(findFieldValuesJoining2.startsWith("orderId='OderId12345',deliveryType='SHIPPING',"));
  }
}
