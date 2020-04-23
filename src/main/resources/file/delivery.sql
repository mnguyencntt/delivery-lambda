
-- Delivery --
DROP TABLE IF EXISTS `Delivery`;

CREATE TABLE `Delivery` (
  `id` VARCHAR(255) NOT NULL,

  `orderId` VARCHAR(255) NULL,
  `deliveryType` VARCHAR(255) NULL,
  `deliveryMethod` VARCHAR(255) NULL,
  `priceDelivery` VARCHAR(255) NULL,
  `courierName` VARCHAR(255) NULL,

  `pickupAddress` VARCHAR(1000) NULL,
  `deliveryAddress` VARCHAR(1000) NULL,

  `createdTime` VARCHAR(255) NULL,
  `acceptedTime` VARCHAR(255) NULL,
  `shippedTime` VARCHAR(255) NULL,
  `deliveredTime` VARCHAR(255) NULL,
  `pickupTime` VARCHAR(255) NULL,

  `request` VARCHAR(1000) NULL,

  `createdAt` VARCHAR(255) NULL,
  `createdBy` VARCHAR(255) NULL,
  `updatedAt` VARCHAR(255) NULL,
  `updatedBy` VARCHAR(255) NULL,

  PRIMARY KEY (`id`));

INSERT INTO 
Delivery (id,orderId,deliveryType,deliveryMethod,priceDelivery,courierName,pickupAddress,deliveryAddress,createdTime,acceptedTime,shippedTime,deliveredTime,pickupTime,request,createdAt,createdBy,updatedAt,updatedBy) 
VALUES ('12345', 'OrderId12345', 'SHIPPING', 'SHIPPING', '10$', 'GoGoVan', '', '', '24-04-2020 12:12:12', '', '', '', '', 'request-json-format', '2020-04-24T03:57:16.122', 'Anonymous', '2020-04-24T03:57:16.368', 'Anonymous');

SELECT * FROM `Delivery`;
