package com.anz.platform.model;

import static com.anz.platform.util.Constants.ANONYMOUS;
import org.joda.time.LocalDateTime;
import com.anz.platform.model.base.BaseObject;
import com.anz.platform.util.ObjectUtils;
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

  public void persist(final String username) {
    createdAt = LocalDateTime.now().toString();
    updatedAt = LocalDateTime.now().toString();
    if (ObjectUtils.isEmpty(username)) {
      createdBy = ANONYMOUS;
      updatedBy = ANONYMOUS;
    } else {
      createdBy = username;
      updatedBy = username;
    }
  }

  public void update(final String username) {
    updatedAt = LocalDateTime.now().toString();
    if (ObjectUtils.isEmpty(username)) {
      updatedBy = ANONYMOUS;
    } else {
      updatedBy = username;
    }
  }
}
