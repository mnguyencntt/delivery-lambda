package com.anz.platform.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import com.amazonaws.services.lambda.runtime.Context;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.DeliveryRequest;
import com.anz.platform.exception.DeliveryException;
import com.anz.platform.model.Delivery;
import com.anz.platform.util.Constants;
import com.anz.platform.util.ObjectUtils;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Setter
public class DeliveryFunction {
  private DbInfo dbInfo;

  public DeliveryFunction(final DbInfo dbInfo) {
    this.dbInfo = dbInfo;
  }

  /*
   * createDelivery
   */
  public ApiResponse createDelivery(final DeliveryRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final DeliveryService deliveryService = new DeliveryService(dbInfo);

      final Delivery delivery = request.buildDelivery();

      final Integer updatedCount = deliveryService.persist(delivery);
      if (updatedCount > 0) {
        log.info("Response Data: {}", delivery);
        return ApiResponse.build(Constants.STATUS_000, delivery, Constants.DELIVERY_SEND_SUCCESS);
      } else {
        throw new DeliveryException(Constants.DELIVERY_PERSIST_FAILED);
      }
    } catch (Exception e) {
      log.error(e.getMessage());
      throw new DeliveryException(e.getMessage());
    }
  }

  /*
   * updateDelivery
   */
  public ApiResponse updateDelivery(final DeliveryRequest request, final Context context) {
    try {
      log.info("Request Data: {}", request);
      final DeliveryService deliveryService = new DeliveryService(dbInfo);

      final Delivery delivery = request.buildDelivery();

      final Integer updatedCount = deliveryService.updateById(delivery);
      if (updatedCount > 0) {
        log.info("Response Data: {}", delivery);
        return ApiResponse.build(Constants.STATUS_000, delivery, Constants.DELIVERY_SEND_SUCCESS);
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
      if (ObjectUtils.isEmpty(delivery)) {
        return ApiResponse.build(Constants.STATUS_404, null, Constants.DELIVERY_NOT_FOUND);
      }
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
      if (ObjectUtils.isEmpty(deliveries)) {
        return ApiResponse.build(Constants.STATUS_404, Collections.emptyList(), Constants.DELIVERY_NOT_FOUND);
      }
      return ApiResponse.build(Constants.STATUS_000, deliveries, Constants.DELIVERY_FOUND);
    } catch (Exception e) {
      log.error(e.getMessage());
      return ApiResponse.build(Constants.STATUS_999, Collections.emptyList(), Constants.DELIVERY_NOT_FOUND);
    }
  }
}
