package com.anz.platform.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import com.amazonaws.services.lambda.runtime.Context;
import com.anz.platform.config.AppConfig;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.DeliveryRequest;
import com.anz.platform.domain.DeliveryResponse;
import com.anz.platform.exception.DeliveryException;
import com.anz.platform.model.Delivery;
import com.anz.platform.util.Constants;
import com.anz.platform.util.JsonUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class DeliveryFunction {
  private AppConfig appConfig = new AppConfig();
  private DbInfo dbInfo = appConfig.getDbInfo();

  /*
   * submitDelivery
   */
  public ApiResponse submitDelivery(final DeliveryRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final DeliveryService deliveryService = new DeliveryService(dbInfo);

      final Delivery delivery = Delivery.builder()
          .id(UUID.randomUUID().toString())
          .receiverUserId(request.getRecieverId())
          .status(Constants.STATUS_000)
          .message(Constants.DELIVERY_SEND_SUCCESS)
          .request(JsonUtils.toJson(request))
          .build();
      delivery.persist();

      final Integer updatedCount = deliveryService.persist(delivery);
      if (updatedCount > 0) {
        log.info("Response Data: {}", delivery);
        return ApiResponse.build(Constants.STATUS_000, DeliveryResponse.buildResponse(delivery, String.valueOf(11111)), Constants.DELIVERY_SEND_SUCCESS);
      } else {
        throw new DeliveryException(Constants.DELIVERY_PERSIST_FAILED);
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DeliveryException(e.getMessage());
    }
  }

  /*
   * findDelivery
   */
  public ApiResponse findDelivery(final DeliveryRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final DeliveryService deliveryService = new DeliveryService(dbInfo);
      final Delivery delivery = deliveryService.findById(request.getDeliveryId(), Delivery.class);
      log.info("Response Data: {}", delivery);
      return ApiResponse.build(Constants.STATUS_000, delivery, Constants.DELIVERY_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, null, Constants.DELIVERY_NOT_FOUND);
    }
  }

  /*
   * findDeliveries
   */
  public ApiResponse findDeliveries(final DeliveryRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final DeliveryService deliveryService = new DeliveryService(dbInfo);
      final List<Delivery> deliveries = deliveryService.findAll(Delivery.class);
      deliveries.sort(Comparator.comparing(Delivery::getUpdatedAt));
      log.info("Response Data: {}", deliveries);
      return ApiResponse.build(Constants.STATUS_000, deliveries, Constants.DELIVERY_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, Collections.emptyList(), Constants.DELIVERY_NOT_FOUND);
    }
  }
}
