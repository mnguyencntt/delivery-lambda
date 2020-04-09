package com.anz.platform.service;

import org.junit.Ignore;
import org.junit.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfig;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DeliveryRequest;
import com.anz.platform.util.JsonUtils;

public class DeliveryFunctionTest extends BaseTest {
  private AppConfigMock appConfigMock = new AppConfigMock();

  @Ignore
  @Test
  public void testDelivery() {
    DeliveryFunction deliveryFunction = new DeliveryFunction();
    AppConfig appConfig = new AppConfig();
    deliveryFunction.setAppConfig(appConfig);
    deliveryFunction.setDbInfo(appConfigMock.getDbInfo());

    String deliveryRequestJson = "{\n" + "  \"senderId\": \"UIB12345\",\n" + "  \"orderId\": \"OI12345\",\n" + "  \"deliveryId\": \"DI12345\",\n" + "  \"eventStatus\": \"ORDER_CREATED\",\n"
        + "  \"recieverId\": \"UIS12345\",\n" + "  \"smsNumber\": \"\",\n" + // +6593767011
        "  \"sesEmail\": \"m.nguyencntt7891@gmail.com\",\n" + "  \"functionType\": \"SEND\"\n" + "}";
    final DeliveryRequest deliveryRequest = JsonUtils.toObject(deliveryRequestJson, DeliveryRequest.class);
    ApiResponse submitDelivery = deliveryFunction.submitDelivery(deliveryRequest, createContext());
    System.out.println(submitDelivery);
    ApiResponse findDelivery = deliveryFunction.findDelivery(DeliveryRequest.builder().deliveryId("2c8e98fa-7c3a-4ec1-8730-1bc8d22797a2").build(), createContext());
    System.out.println(findDelivery);
    ApiResponse findDeliveries = deliveryFunction.findDeliveries(new DeliveryRequest(), createContext());
    System.out.println(findDeliveries);
  }
}
