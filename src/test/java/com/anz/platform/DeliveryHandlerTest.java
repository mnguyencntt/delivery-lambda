package com.anz.platform;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.DeliveryRequest;
import com.anz.platform.enumeration.DeliveryFunctionType;
import com.anz.platform.util.JsonUtils;

public class DeliveryHandlerTest extends BaseTest {
  private final DbInfo dbInfo = new AppConfigMock().getDbInfo();
  private final DeliveryHandler deliveryHandler = new DeliveryHandler(dbInfo);

  private final String jsonRequesst = "{ \"orderId\": \"OrderId12345\", \"deliveryType\": \"SHIPPING\", \"deliveryMethod\": \"SHIPPING\", \"priceDelivery\": \"10$\", \"courierName\": \"GoGoVan\" }";

  @Test
  public void testCreateDelivery_SUCCESS() {
    final DeliveryRequest request = JsonUtils.toObject(jsonRequesst, DeliveryRequest.class);
    request.setUsername("testuser1");
    request.setFunctionType(DeliveryFunctionType.CREATE);

    ApiResponse handleRequest = deliveryHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }

  @Test
  public void testCreateDelivery_FAILED_Existing() {
    final DeliveryRequest request = JsonUtils.toObject(jsonRequesst, DeliveryRequest.class);
    request.setDeliveryId("12345");
    request.setUsername("testuser1");
    request.setFunctionType(DeliveryFunctionType.CREATE);

    ApiResponse handleRequest = deliveryHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("999", handleRequest.getStatus());
  }

  @Test
  public void testUpdateDelivery_SUCCESS() {
    final DeliveryRequest request = JsonUtils.toObject(jsonRequesst, DeliveryRequest.class);
    request.setDeliveryId("12345");
    request.setUsername("testuser1");
    request.setFunctionType(DeliveryFunctionType.UPDATE);

    ApiResponse handleRequest = deliveryHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }

  @Test
  public void testUpdateDelivery_FAILED_NotExisting() {
    final DeliveryRequest request = JsonUtils.toObject(jsonRequesst, DeliveryRequest.class);
    request.setDeliveryId("123456789");
    request.setUsername("testuser1");
    request.setFunctionType(DeliveryFunctionType.UPDATE);

    ApiResponse handleRequest = deliveryHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("999", handleRequest.getStatus());
  }

  @Test
  public void testFindDeliveryById_SUCCESS_Found() {
    final DeliveryRequest request = JsonUtils.toObject(jsonRequesst, DeliveryRequest.class);
    request.setDeliveryId("12345");
    request.setUsername("testuser1");
    request.setFunctionType(DeliveryFunctionType.FINDID);

    ApiResponse handleRequest = deliveryHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }

  @Test
  public void testFindDeliveryById_FAILED_NotFound() {
    final DeliveryRequest request = JsonUtils.toObject(jsonRequesst, DeliveryRequest.class);
    request.setDeliveryId("123456789");
    request.setUsername("testuser1");
    request.setFunctionType(DeliveryFunctionType.FINDID);

    ApiResponse handleRequest = deliveryHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("404", handleRequest.getStatus());
  }

  @Test
  public void testFindAllDeliveries() {
    final DeliveryRequest request = JsonUtils.toObject(jsonRequesst, DeliveryRequest.class);
    request.setUsername("testuser1");
    request.setFunctionType(DeliveryFunctionType.FINDALL);

    ApiResponse handleRequest = deliveryHandler.handleRequest(request, createContext());
    System.out.println(handleRequest.toString());
    assertEquals("000", handleRequest.getStatus());
  }
}
