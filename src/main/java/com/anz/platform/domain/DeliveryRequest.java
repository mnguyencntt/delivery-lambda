package com.anz.platform.domain;

import static com.anz.platform.util.Constants.DDMMYYYY_HHMMSS;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import org.apache.commons.beanutils.BeanUtils;
import com.anz.platform.enumeration.DeliveryFunctionType;
import com.anz.platform.model.Delivery;
import com.anz.platform.util.DatetimeUtils;
import com.anz.platform.util.JsonUtils;
import com.anz.platform.util.ObjectUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryRequest {
  // Query
  private String deliveryId;

  // Delivery info
  private String orderId;
  private String deliveryType;
  private String deliveryMethod; // PICKUP - SHIPPING (standard/expedited/frozen)
  private String priceDelivery; // Can be 0$
  private String courierName; // uParcel - Grab - GoGoVan

  // Address info
  private AddressInfo pickupAddress;
  private AddressInfo deliveryAddress;

  // Time of delivery
  private String createdTime; // System created Delivery Info
  private String acceptedTime; // SELLER accepted Delivery Info after check Notification
  private String shippedTime; // SHIPPER took Parcel from SELLER & start Delivery
  private String deliveredTime; // BUYER received Parcel from SHIPPER
  private String pickupTime; // For self-collection

  // Function-Type
  private String username;
  private DeliveryFunctionType functionType; // SEND - UPDATE - FINDID - FINDALL - DELETE
  private Map<String, String> additionalFields;

  public Delivery buildDelivery() throws IllegalAccessException, InvocationTargetException {
    final Delivery delivery = new Delivery();
    BeanUtils.copyProperties(delivery, this);
    delivery.setId(ObjectUtils.isEmpty(this.getDeliveryId()) ? UUID.randomUUID().toString() : this.getDeliveryId());
    delivery.setPickupAddress(JsonUtils.toJson(pickupAddress));
    delivery.setDeliveryAddress(JsonUtils.toJson(deliveryAddress));
    delivery.setRequest(JsonUtils.toJson(this));
    delivery.setCreatedTime(DatetimeUtils.formatDateTime(LocalDateTime.now(), DDMMYYYY_HHMMSS));
    return delivery;
  }

}
