package com.anz.platform.domain;

import com.anz.platform.model.Delivery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryResponse {
  private String buyerId;
  private String sellerId;
  private String receiverId;
  private String deliveryId;
  private String deliveryType;
  private String amount;

  public static DeliveryResponse buildResponse(final Delivery delivery, final String amount) {
    return DeliveryResponse.builder().receiverId(delivery.getReceiverUserId()).deliveryId(delivery.getId()).deliveryType(delivery.getDeliveryType()).amount(amount).build();
  }
}
