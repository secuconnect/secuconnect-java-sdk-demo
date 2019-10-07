package com.secuconnect.demo.payment.contract;

import com.secuconnect.demo.getToken;
import io.secuconnect.client.ApiException;
import io.secuconnect.client.api.PaymentContractsApi;
import io.secuconnect.client.model.PaymentContractsDTOClone;
import io.secuconnect.client.model.PaymentContractsProductModel;
import io.secuconnect.client.model.PaymentInformation;
import org.joda.time.DateTime;

public class createMerchantWithoutIdentification {
    public static void main(String[] args) {
        try {
            getToken.main(null);

            /*
             * bank account of the merchant to get the money
             */
            PaymentInformation payoutAccount = new PaymentInformation();
            payoutAccount.setIban("DE89370400440532013000");
            payoutAccount.setBic(""); // recommended
            payoutAccount.setOwner("Test #1");

            /*
             * Submitting the data to the API
             */
            PaymentContractsDTOClone requestData = new PaymentContractsDTOClone();
            requestData.setPaymentData(payoutAccount);
            requestData.setPayinAccount(false);
            requestData.setProject("project_name " + new DateTime().toString()); // must be unique for each request

            PaymentContractsApi apiInstance = new PaymentContractsApi();
            apiInstance.getApiClient().setAccessToken(getToken.accessToken);
            PaymentContractsProductModel response = apiInstance.clone("me", requestData); //"me" is a shortcut for the current contract of the API-user

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             * class PaymentContractsProductModel {
             *     object: payment.contracts
             *     id: PCR_WEK5WC8442NCRA9DN0ZAV8M5ZHRKA2
             *     parent: class ProductInstanceUID {
             *         object: payment.contracts
             *         id: PCR_M32SCZ98Q2N3U4GW70ZAVWWE47XPAH
             *     }
             *     demo: true
             *     created: 2019-10-07T16:57:31+02:00
             *     updated: null
             * }
             */

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
