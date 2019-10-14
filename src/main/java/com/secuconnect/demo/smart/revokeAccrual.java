package com.secuconnect.demo.smart;

import com.secuconnect.demo.getToken;
import io.secuconnect.client.ApiException;
import io.secuconnect.client.api.PaymentTransactionsApi;
import io.secuconnect.client.model.PaymentTransactionsProductModel;

public class revokeAccrual {
    public static void main(String[] args) {
        try {
            getToken.main(null);

            PaymentTransactionsApi apiInstance = new PaymentTransactionsApi();
            apiInstance.getApiClient().setAccessToken(getToken.accessToken);
            PaymentTransactionsProductModel response = apiInstance.revokeAccrual(
                    "PCI_WJGXA9JV74EKWBRGX5P8CB4A2908N8", null
            );

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             * class PaymentTransactionsProductModel {
             *     object: payment.transactions
             *     id: PCI_WJGXA9JV74EKWBRGX5P8CB4A2908N8
             *     merchant: class GeneralMerchantsProductModel {
             *         object: general.merchants
             *         id: MRC_AU2RGQ0JTTFM5VBG4NM96PQ97D0JO9
             *         type: 11
             *         user: class GeneralMerchantsUser {
             *             name: Hans Test
             *             companyname: Secupay Test-Shop [MZ_Testprodukt]
             *         }
             *     }
             *     transId: 14248108
             *     productId: 20
             *     product: Lastschrift Demo
             *     productRaw: Lastschrift Demo
             *     zahlungsmittelId: 962142
             *     contractId: 686464
             *     amount: 100000
             *     currency: EUR
             *     created: 2019-07-16T16:35:00+02:00
             *     updated: 2019-07-16T16:35:02+02:00
             *     status: 11
             *     statusText: abgeschlossen(Demo)
             *     details: class PaymentTransactionsProductModelDetails {
             *         amount: 100000
             *         cleared: open
             *         status: 11
             *         statusText: abgeschlossen(Demo)
             *         statusSimple: 1
             *         description: XV0000186
             *         descriptionRaw: XV0000186
             *     }
             *     customer: class PaymentTransactionsProductModelCustomer {
             *         forename: Maria
             *         surname: Muster
             *     }
             *     paymentData: DE27 XXXX XXXX 9700
             *     transactionHash: pavfjyldocky3475113
             *     accrual: false
             * }
             */

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
