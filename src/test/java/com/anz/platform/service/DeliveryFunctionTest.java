package com.anz.platform.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfigMock;

public class DeliveryFunctionTest extends BaseTest {
  private AppConfigMock appConfigMock = new AppConfigMock();

  @Disabled
  @Test
  public void testDelivery() {
    DeliveryFunction deliveryFunction = new DeliveryFunction(appConfigMock.getDbInfo());
  }
}
