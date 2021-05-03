package com.secuconnect.demo.payment.transaction;

import com.secuconnect.client.Environment;
import com.secuconnect.demo.Globals;
import com.secuconnect.client.ApiException;
import com.secuconnect.client.api.PaymentSecupayPrepaysApi;
import com.secuconnect.client.model.SecupayTransactionSetShippingInformationDTO;

public class addInvoiceNumber {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // init request
            SecupayTransactionSetShippingInformationDTO info = new SecupayTransactionSetShippingInformationDTO();
            info.setInvoiceNumber("test1234");

            PaymentSecupayPrepaysApi apiInstance = new PaymentSecupayPrepaysApi();
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
