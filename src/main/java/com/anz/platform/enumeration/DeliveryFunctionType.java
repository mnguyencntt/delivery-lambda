package com.anz.platform.enumeration;

import java.util.Arrays;
import java.util.List;

public enum DeliveryFunctionType {
  //
  SEND("", "Send new delivery"),
  //
  UPDATE("", "Update delivery"),
  //
  FIND("", "Find delivery by Id"),
  //
  FINDALL("", "Find all deliveries"),
  //
  DELETE("", "Delete delivery by Id");

  private String value;
  private String desc;

  DeliveryFunctionType(String value, String desc) {
    this.value = value;
    this.desc = desc;
  }

  public static List<DeliveryFunctionType> getAll() {
    return Arrays.asList(DeliveryFunctionType.values());
  }

  public String getValue() {
    return value;
  }

  public String getDesc() {
    return desc;
  }
}
