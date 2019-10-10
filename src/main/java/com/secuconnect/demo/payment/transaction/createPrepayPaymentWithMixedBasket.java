package com.secuconnect.demo.payment.transaction;

import com.secuconnect.demo.getToken;
import io.secuconnect.client.ApiException;
import io.secuconnect.client.api.PaymentSecupayPrepaysApi;
import io.secuconnect.client.model.*;

import java.util.ArrayList;

public class createPrepayPaymentWithMixedBasket {
    public static void main(String[] args) {
        try {
            getToken.main(null);

            SecupayTransactionProductDTO transaction = new SecupayTransactionProductDTO();
            transaction.setOptData(new SecupayTransactionProductDTOOptData());
            transaction.getOptData().setLanguage("de_DE"); // or "en_US"

            transaction.setAmount(3324); // in euro-cent
            transaction.setCurrency("EUR");
            transaction.setDemo(true);
            transaction.setAccrual(true);

            transaction.setRedirectUrl(new SecupayRedirectUrl());

            transaction.setCustomer(new PaymentCustomersProductModel());
            transaction.getCustomer().setId("PCU_3BBSV6C702NCA4HR70ZAVCAX3R6CAW"); // from "payment/customer/createCustomer.java"

            SecupayBasketItem subTransactionForSubContract = new SecupayBasketItem();
            subTransactionForSubContract.setItemType("sub_transaction");
            subTransactionForSubContract.setName("Order 123");
            subTransactionForSubContract.setContractId("PCR_2TEAKU97D2NC4JDAN0ZAVA49Z6TJA4"); // See "payment/contract/create*" for details
            subTransactionForSubContract.setTotal(3324);
            subTransactionForSubContract.setSubBasket(new ArrayList<>(3));

            SecupayBasketItem basketItem1 = new SecupayBasketItem();
            basketItem1.setItemType("shipping");
            basketItem1.setName("standard delivery");
            basketItem1.setTax("19");
            basketItem1.setTotal(1324);
            subTransactionForSubContract.getSubBasket().add(basketItem1);

            SecupayBasketItem basketItem2 = new SecupayBasketItem();
            basketItem2.setItemType("article");
            basketItem2.setArticleNumber("3211");
            basketItem2.setQuantity(2);
            basketItem2.setName("Fancy Item XYZ");
            basketItem2.setEan("4123412341243");
            basketItem2.setTax("19");
            basketItem2.setTotal(2000);
            basketItem2.setPrice(1000);
            subTransactionForSubContract.getSubBasket().add(basketItem2);

            SecupayBasketItem basketItem3 = new SecupayBasketItem();
            basketItem3.setItemType("stakeholder_payment");
            basketItem3.setContractId("PCR_M32SCZ98Q2N3U4GW70ZAVWWE47XPAH"); // This id is fixed for the platform
            basketItem3.setName("Platform Provision");
            basketItem3.setTotal(300);
            subTransactionForSubContract.getSubBasket().add(basketItem3);

            transaction.setBasket(new ArrayList<>());
            transaction.getBasket().add(subTransactionForSubContract);

            PaymentSecupayPrepaysApi apiInstance = new PaymentSecupayPrepaysApi();
            apiInstance.getApiClient().setAccessToken(getToken.accessToken);
            SecupayTransactionProductModel response = apiInstance.paymentSecupayprepaysPost(transaction);

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             * class SecupayTransactionProductModel {
             *     object: payment.secupayprepays
             *     id: vfpeiqugpdug3478433
             *     transId: 14251591
             *     status: authorized
             *     amount: 3324
             *     currency: EUR
             *     basket: [class SecupayBasketItem {
             *         itemType: sub_transaction
             *         name: Order 123
             *         total: 3324
             *         subBasket: [class SecupayBasketItem {
             *             itemType: shipping
             *             name: standard delivery
             *             tax: 19
             *             total: 1324
             *         }, class SecupayBasketItem {
             *             itemType: article
             *             articleNumber: 3211
             *             quantity: 2
             *             name: Fancy Item XYZ
             *             ean: 4123412341243
             *             tax: 19
             *             total: 2000
             *             price: 1000
             *         }, class SecupayBasketItem {
             *             itemType: stakeholder_payment
             *             name: Platform Provision
             *             total: 300
             *         }]
             *     }]
             *     transactionStatus: 25
             *     accrual: true
             *     paymentAction: sale
             *     transferPurpose: TA 14251591
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
             *     redirectUrl: class SecupayRedirectUrl {
             *         iframeUrl: https://api-testing.secupay-ag.de/payment/vfpeiqugpdug3478433
             *         urlSuccess: http://example.com
             *         urlFailure: http://example.com
             *         urlPush: https://example.com
             *     }
             *     subTransactions: [class SecupaySubTransactionProductModel {
             *         id: vfpeiqugpdug3478433_14251592
             *         transId: 14251592
             *         amount: 3324
             *         transactionStatus: 25
             *         status: authorized
             *         currency: EUR
             *     }]
             * }
             */

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
