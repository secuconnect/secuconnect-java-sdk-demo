package com.secuconnect.demo.payment.transaction;

import com.secuconnect.demo.getToken;
import io.secuconnect.client.ApiException;
import io.secuconnect.client.api.PaymentSecupayPrepaysApi;
import io.secuconnect.client.model.SecupayTransactionProductModel;
import io.secuconnect.client.model.SecupayTransactionReverseAccrualDTO;

public class revokeAccrual {
    public static void main(String[] args) {
        try {
            getToken.main(null);

            PaymentSecupayPrepaysApi apiInstance = new PaymentSecupayPrepaysApi();
            apiInstance.getApiClient().setAccessToken(getToken.accessToken);
            SecupayTransactionProductModel response = apiInstance.reverseAccrualByPaymentId(
                    "secupayprepays",
                    "vfpeiqugpdug3478433_14251592",
                    new SecupayTransactionReverseAccrualDTO()
            );

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             * class SecupayTransactionProductModel {
             *     object: payment.secupayprepays
             *     id: vfpeiqugpdug3478433_14251592
             *     transId: 14251592
             *     status: authorized
             *     amount: 3324
             *     currency: EUR
             *     purpose: Order 123
             *     basket: [class SecupayBasketItem {
             *         itemType: shipping
             *         name: standard delivery
             *         tax: 19
             *         total: 1324
             *     }, class SecupayBasketItem {
             *         itemType: article
             *         articleNumber: 3211
             *         quantity: 2
             *         name: Fancy Item XYZ
             *         ean: 4123412341243
             *         tax: 19
             *         total: 2000
             *         price: 1000
             *     }, class SecupayBasketItem {
             *         itemType: stakeholder_payment
             *         name: Platform Provision
             *         total: 300
             *     }]
             *     transactionStatus: 25
             *     accrual: false
             *     paymentAction: sale
             *     transferPurpose: TA 14251592
             *     transferAccount: class PaymentInformation {
             *         iban: DE88300500000001747013
             *         bic: WELADEDDXXX
             *         owner: secupay AG
             *         bankname: Landesbank Hessen-Thüringen Girozentrale NL. Düsseldorf
             *     }
             *     customer: class PaymentCustomersProductModel {
             *         object: payment.customers
             *         id: PCU_3BBSV6C702NCA4HR70ZAVCAX3R6CAW
             *         contract: class ProductInstanceUID {
             *             object: payment.contracts
             *             id: PCR_M32SCZ98Q2N3U4GW70ZAVWWE47XPAH
             *         }
             *         contact: class Contact {
             *             forename: John
             *             surname: Doe
             *             companyname: Example Inc.
             *             salutation: Mr.
             *             gender: m
             *             title: Dr.
             *             email: example123@example.com
             *             phone: 0049-123-456789
             *             dob: 1901-02-03T00:00:00+01:00
             *             urlWebsite: example.com
             *             birthplace: AnotherExampleCity
             *             nationality: german
             *             address: class Address {
             *                 street: example street
             *                 streetNumber: 6a
             *                 city: Testcity
             *                 postalCode: 01234
             *                 country: DE
             *             }
             *         }
             *         created: 2019-09-24T16:13:10+02:00
             *     }
             * }
             */

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
