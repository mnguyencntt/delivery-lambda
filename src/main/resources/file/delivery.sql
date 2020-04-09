
-- Delivery --
DROP TABLE `Delivery`;
CREATE TABLE `Delivery` (
  `id` VARCHAR(255) NOT NULL,

  `receiverUserId` VARCHAR(255) NULL,
  `subject` VARCHAR(255) NULL,
  `contentBody` VARCHAR(1000) NULL,
  `deliveryType` VARCHAR(255) NULL,
  `amount` VARCHAR(255) NULL,
  `status` VARCHAR(255) NULL,
  `message` VARCHAR(255) NULL,
  `request` VARCHAR(1000) NULL,

  `createdAt` VARCHAR(255) NULL,
  `createdBy` VARCHAR(255) NULL,
  `updatedAt` VARCHAR(255) NULL,
  `updatedBy` VARCHAR(255) NULL,
  PRIMARY KEY (`id`));

INSERT INTO `Delivery` (id, receiverUserId, subject, contentBody, deliveryType, amount, status, message, request, createdAt, createdBy, updatedAt, updatedBy)
VALUES  ('1', 'UIB12345', 'Delivery OrderId OID12345', 'Minh Nguyen Testing Content Body', 'EMAIL', '1', '000', 'Sent Success', '', '', '', '', '');
INSERT INTO `Delivery` (id, receiverUserId, subject, contentBody, deliveryType, amount, status, message, request, createdAt, createdBy, updatedAt, updatedBy)
VALUES  ('2', 'UIB12345', 'Delivery OrderId OID12345', 'Minh Nguyen Testing Content Body', 'EMAIL', '1', '000', 'Sent Success', '', '', '', '', '');

SELECT * FROM `Delivery`;
