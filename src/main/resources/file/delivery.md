# Delivery - Use Cases



| API | Method | Description |
| ------ | ------ | ------ |
| /deliver | POST | create delivery info |
| /deliver | PATH | create delivery info |
| /deliver/{deliveryId} | GET | query/view delivery info |



## Buyer delivery
Given a user is a Seller,\
When the user receives Notification OrderCreated from platform
And the user chooses Delivery method from platform
AND the user submits Delivery infos,\
Then the platform saves these infos 
AND send notification to Buyer

```
POST /deliver

Parameters
* orderId (required): unique-id of Order.
* buyerId (required): unique-id of Buyer.
* deliveryMethod (required): delivery method (standard/expedited/frozen).
* courierId (required): unique-id of Courier (uParcel/Grab/GoGoVan).
* pickupAddress (optional): address if user wants to pickup -> point to Address service.
* deliveryAddress (optional): address if user wants to deliver -> point to Address service.
* createdTime (optional): datatime of created item.
* shippedTime (optional): datatime of shipped item.
* deliveriedTime (optional): datatime of deliveried item.
* referenceId (optional): can be timestamp which is for reference info.
* deliveryPrice (optional): price of this delivery method.
* estimationTime (optional): estimation time delivery. Example: from 2020/01/04 to 2020/15/04 (15 days) (discuss to confirm with team)
```
#### Sample Request
```
{
    "orderId": "OI12345",
    "buyerId": "UIB12345",
    "deliveryMethod": "standard",
    "courierId": "GoGoVan",
    "pickupAddress": null,
    "deliveryAddress": "ADI12345",
    "createdTime": "2020/04/04 12:12:12 +0800",
    "shippedTime": null,
    "deliveriedTime": null,
    "referenceId": "44353456345776",
    "deliveryPrice": "0",
	"_estimationTime": :"from 2020/01/04 to 2020/15/04 (15 days)"
}
```
#### Sample Success Response
```
{
    "status": 200,
    "message": "Create Delivery Success",
    "data": [
        {
			"deliveryId": "DLI12345"
        }
    ]
}
```
#### Sample Fail Response
```
{
    "status": 400,
    "message": "Create Delivery Fail",
    "data": null
}
```



## Buyer delivery
Given a user is a Buyer,\
When the user wants to see the deliver info,\
Then the platform shows this info by deliveryId.

```
GET /deliver/{deliveryId}

Parameters
* deliveryId (required): unique-id of Delivery.
```
#### Sample Request
```
{
    "deliveryId": "DLI12345",
}
```
#### Sample Success Response
```
{
    "status": 200,
    "message": null,
    "data": [
        {
			"deliveryId": "DLI12345",
			"orderId": "OI12345",
			"deliveryMethod": "standard",
			"courierId": "GoGoVan",
			"pickupAddress": null,
			"deliveryAddress": "ADI12345",
			"createdTime": "2020/04/04 12:12:12 +0800",
			"shippedTime": null,
			"deliveriedTime": null,
			"referenceId": "44353456345776",
			"deliveryPrice": "0",
			"_estimationTime": :"from 2020/01/04 to 2020/15/04 (15 days)"
		}
    ]
}
```
#### Sample Fail Response
```
{
    "status": 400,
    "message": "Not found any data by deliveryId: DLI12345",
    "data": null
}
```


