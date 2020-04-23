package com.anz.platform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse {
  private String deliveryId;
  private String orderId;
  private String deliveryType;
  private String deliveryMethod;
  private String priceDelivery;
  private String courierName;
}
