package com.anz.platform;

import static com.anz.platform.util.Constants.DELIVERY_FUNCTION_NOT_SUPPORT;
import static com.anz.platform.util.Constants.STATUS_101;
import static com.anz.platform.util.Constants.STATUS_999;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.anz.platform.config.AppConfig;
import com.anz.platform.domain.ApiResponse;
import com.anz.platform.domain.DbInfo;
import com.anz.platform.domain.DeliveryRequest;
import com.anz.platform.enumeration.DeliveryFunctionType;
import com.anz.platform.exception.InvalidRequestException;
import com.anz.platform.service.DeliveryFunction;
import com.anz.platform.util.Constants;
import com.anz.platform.util.ObjectUtils;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@NoArgsConstructor
public class DeliveryHandler implements RequestHandler<DeliveryRequest, ApiResponse> {
  private DbInfo dbInfo = new AppConfig().getDbInfo();

  public DeliveryHandler(final DbInfo dbInfo) {
    this.dbInfo = dbInfo;
  }

  public ApiResponse handleRequest(final DeliveryRequest request, final Context context) {
    try {
      if (ObjectUtils.isEmpty(request.getFunctionType())) {
        throw new InvalidRequestException(String.format(Constants.NOT_EMPTY, "functionType"));
      }

      final DeliveryFunction function = new DeliveryFunction(dbInfo);
      if (DeliveryFunctionType.CREATE.equals(request.getFunctionType())) {
        return function.createDelivery(request, context);
      } else if (DeliveryFunctionType.UPDATE.equals(request.getFunctionType())) {
        return function.updateDelivery(request, context);
      } else if (DeliveryFunctionType.FINDID.equals(request.getFunctionType())) {
        return function.findDelivery(request, context);
      } else if (DeliveryFunctionType.FINDALL.equals(request.getFunctionType())) {
        return function.findDeliveries(request, context);
      } else {
        throw new InvalidRequestException(String.format(DELIVERY_FUNCTION_NOT_SUPPORT, request.getFunctionType()));
      }
    } catch (InvalidRequestException e) {
      return ApiResponse.build(STATUS_101, null, e.getMessage());
    } catch (Exception e) {
      return ApiResponse.build(STATUS_999, null, e.getMessage());
    }
  }
}
