package com.secuconnect.demo.payment.transaction;

import com.secuconnect.demo.getToken;
import io.secuconnect.client.ApiException;
import io.secuconnect.client.Configuration;
import io.secuconnect.client.api.PaymentSecupayCreditcardsApi;
import io.secuconnect.client.api.PaymentSecupayPrepaysApi;
import io.secuconnect.client.model.*;

import java.util.ArrayList;

public class createCreditcardPaymentWithMixedBasketAndGBP {
    public static void main(String[] args) {
        try {
            // Live-Server:
//            Configuration.getDefaultApiClient().setBasePath("https://connect.secucard.com/api/v2");
//            Configuration.getDefaultApiClient().setAuthHost("https://connect.secucard.com/");

            getToken.main(null); // All currencies are using the same credentials!

            SecupayTransactionProductDTO transaction = new SecupayTransactionProductDTO();
            transaction.setOptData(new SecupayTransactionProductDTOOptData());
            transaction.getOptData().setLanguage("de_DE"); // or "en_US" for english

            transaction.setAmount(3324); // in pence
            transaction.setCurrency("GBP");
            transaction.setDemo(true); // transact does not allow GBP in the demo mode right now, so you can just create them but not complete the checkout with demo = true.
            transaction.setAccrual(true);

            transaction.setRedirectUrl(new SecupayRedirectUrl());

            transaction.setCustomer(new PaymentCustomersProductModel());
            transaction.getCustomer().setId("PCU_CMENVMVGS2NEZD67EPGT82MR5VR7AZ"); // from "payment/customer/createCustomer.java"

            SecupayBasketItem subTransactionForSubContract = new SecupayBasketItem();
            subTransactionForSubContract.setItemType("sub_transaction");
            subTransactionForSubContract.setName("Order 123");
            subTransactionForSubContract.setContractId("PCR_Z7WPQ7JM32NEZDB799E3CDC42GKVAW"); // See "payment/contract/create*" for details
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
            basketItem3.setContractId("PCR_2EGUEF7YT2MSZ9WV52TSDEGEK5URAW"); // This id is fixed for the platform
            basketItem3.setName("Platform Provision");
            basketItem3.setTotal(300);

            transaction.setBasket(new ArrayList<>());
            transaction.getBasket().add(subTransactionForSubContract);

            PaymentSecupayCreditcardsApi apiInstance = new PaymentSecupayCreditcardsApi();
            apiInstance.getApiClient().setAccessToken(getToken.accessToken);
            SecupayTransactionProductModel response = apiInstance.paymentSecupaycreditcardsPost(transaction);

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             * class SecupayTransactionProductModel {
             *     object: payment.secupaycreditcards
             *     id: jtpjsbqwhpnc4033079
             *     transId: 19862251
             *     status: internal_server_status
             *     amount: 3324
             *     currency: EUR
             *     purpose: 
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
             *         }]
             *     }]
             *     transactionStatus: 1
             *     accrual: true
             *     paymentAction: sale
             *     customer: class PaymentCustomersProductModel {
             *         object: payment.customers
             *         id: PCU_CMENVMVGS2NEZD67EPGT82MR5VR7AZ
             *         contract: class ProductInstanceUID {
             *             object: payment.contracts
             *             id: PCR_WREDJWU342NEZD479ZBZ79PN2THCAW
             *         }
             *         contact: class Contact {
             *             forename: John
             *             surname: Doe
             *             companyname: Example Inc.
             *             salutation: Mr.
             *             title: Dr.
             *             email: example@example.com
             *             phone: 0049-123-456789
             *             mobile: 0049-987-654321
             *             dob: 1901-02-03T00:00:00+01:00
             *             address: class Address {
             *                 street: Example Street
             *                 streetNumber: 6a
             *                 city: Examplecity
             *                 postalCode: 01234
             *                 country: Germany
             *             }
             *         }
             *         created: 2019-11-18T10:51:10+01:00
             *     }
             *     redirectUrl: class SecupayRedirectUrl {
             *         iframeUrl: https://api-dev6.secupay-ag.de/payment/jtpjsbqwhpnc4033079
             *         urlSuccess: http://example.com
             *         urlFailure: http://example.com
             *         urlPush: http://example.com/push_url
             *     }
             *     iframeUrl: https://api-dev6.secupay-ag.de/payment/jtpjsbqwhpnc4033079
             * }
             */

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
