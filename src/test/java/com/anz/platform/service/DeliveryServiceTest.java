package com.anz.platform.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.junit.jupiter.api.Test;
import com.anz.platform.base.BaseTest;
import com.anz.platform.config.AppConfigMock;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.model.Delivery;
import com.anz.platform.util.ObjectUtils;

public class DeliveryServiceTest extends BaseTest {
  private final DbInfo dbInfo = new AppConfigMock().getDbInfo();
  private final DeliveryService deliveryService = new DeliveryService(dbInfo);

  @Test
  public void loadSqlFile() throws Exception {
    File file = getFileFromResources("file/delivery.sql");
    String printFile = printFile(file);
    // System.out.println(printFile);
    assertTrue(ObjectUtils.isNotEmpty(printFile));
  }

  @Test
  public void testUserDbConnection() throws SQLException, ClassNotFoundException {
    final DbInfo dbInfo = new AppConfigMock().getDbInfo();

    Class.forName(dbInfo.getSqlDriver());
    final QueryRunner run = new QueryRunner();
    final Connection conn = DriverManager.getConnection(dbInfo.getEndpoint(), dbInfo.getUsername(), dbInfo.getPassword());
    try {
      List<Map<String, Object>> maps = run.query(conn, "SELECT * FROM DELIVERY", new MapListHandler());
      // System.out.println(maps);
      assertEquals(1, maps.size());
    } finally {
      DbUtils.close(conn);
    }
  }

  @Test
  public void testFindAllDeliveries() {
    List<Delivery> deliveries = deliveryService.findAll(Delivery.class);
    // System.out.println(deliveries);
    assertEquals(1, deliveries.size());
    assertEquals("12345", deliveries.get(0).getId());
  }

  @Test
  public void testFindByIdDelivery() {
    Delivery delivery = deliveryService.findById("12345", Delivery.class);
    // System.out.println(delivery);
    assertEquals("12345", delivery.getId());
  }

  @Test
  public void testFindByFieldDelivery() {
    Delivery delivery = deliveryService.findByField("orderId", "OrderId12345", Delivery.class);
    // System.out.println(delivery);
    assertEquals("12345", delivery.getId());
  }

  @Test
  public void testPersistUser() {
    final Delivery delivery = Delivery.builder().id("54321").orderId("OrderId12345").deliveryType("SHIPPING").priceDelivery("10$").courierName("GoGoVan").createdTime("24-04-2020 12:12:12").build();
    delivery.setRequest("request-json-format");
    delivery.persist();
    assertEquals(new Integer(1), deliveryService.persist(delivery));
  }

  @Test
  public void testUpdateDelivery() {
    final Delivery delivery = Delivery.builder().id("12345").orderId("OrderId12345").deliveryType("SHIPPING").priceDelivery("10$").courierName("GoGoVan").createdTime("24-04-2020 12:12:12").build();
    delivery.setRequest("request-json-format");
    delivery.persist();
    assertEquals(new Integer(1), deliveryService.updateById(delivery));
  }
}
