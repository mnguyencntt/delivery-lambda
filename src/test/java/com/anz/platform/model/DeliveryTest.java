package com.anz.platform.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import com.anz.platform.model.Delivery;

public class DeliveryTest {
  @Test
  public void testDelivery() {
    int expectedLength = 13;

    Delivery delivery = new Delivery();
    delivery.setId("12345");

    String fieldJoining = delivery.findFieldsJoining();
    System.out.println(fieldJoining);
    assertTrue(fieldJoining.startsWith("id,receiverUserId,subject,contentBody"));
    assertEquals(expectedLength, fieldJoining.split(",").length);

    String marks = delivery.findMarksJoining();
    System.out.println(marks);
    assertEquals(expectedLength, marks.chars().filter(ch -> ch == '?').count());
    assertEquals(expectedLength, marks.split(",").length);

    try {
      assertEquals("12345", delivery.findValues()[0]);
      assertEquals("", delivery.findValues()[1]);
      assertEquals("", delivery.findValues()[2]);
    } catch (IllegalArgumentException | IllegalAccessException e) {
      fail();
    }
  }
}
