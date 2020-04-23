package com.anz.platform.model;

import org.joda.time.LocalDateTime;
import com.anz.platform.model.base.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Delivery extends BaseObject {
  private String id;

  private String orderId;
  private String deliveryType;
  private String deliveryMethod;
  private String priceDelivery;
  private String courierName;

  private String pickupAddress;
  private String deliveryAddress;

  private String createdTime;
  private String acceptedTime;
  private String shippedTime;
  private String deliveredTime;
  private String pickupTime;

  private String request; // store request of API

  private String createdAt;
  private String createdBy;
  private String updatedAt;
  private String updatedBy;

  public void persist() {
    createdAt = LocalDateTime.now().toString();
    createdBy = "Anonymous";
    updatedAt = LocalDateTime.now().toString();
    updatedBy = "Anonymous";
  }

  public void update() {
    updatedAt = LocalDateTime.now().toString();
    updatedBy = "Anonymous";
  }
}
