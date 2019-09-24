package com.secuconnect.demo.payment.transaction;

import com.secuconnect.demo.getToken;
import io.secuconnect.client.ApiException;
import io.secuconnect.client.api.PaymentSecupayPrepaysApi;
import io.secuconnect.client.model.SecupayTransactionProductModel;
import io.secuconnect.client.model.SecupayTransactionSetShippingInformationDTO;

public class addInvoiceNumber {
    public static void main(String[] args) {
        try {
            getToken.main(null);

            SecupayTransactionSetShippingInformationDTO info = new SecupayTransactionSetShippingInformationDTO();
            info.setInvoiceNumber("test1234");

            PaymentSecupayPrepaysApi apiInstance = new PaymentSecupayPrepaysApi();
            apiInstance.getApiClient().setAccessToken(getToken.accessToken);
            apiInstance.setShippingInformationByPaymentId(
                    "secupayprepays",
                    "vfpeiqugpdug3478433_14251592",
                    info
            );
        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
