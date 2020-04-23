package com.anz.platform.service;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfig;
import com.anz.platform.config.AppConfigMock;

public class DeliveryFunctionTest extends BaseTest {
  private AppConfigMock appConfigMock = new AppConfigMock();

  @Ignore
  @Test
  public void testDelivery() {
    DeliveryFunction deliveryFunction = new DeliveryFunction();
    AppConfig appConfig = new AppConfig();
    deliveryFunction.setAppConfig(appConfig);
    deliveryFunction.setDbInfo(appConfigMock.getDbInfo());
  }
}
