package com.secuconnect.demo.payment.payout;

import com.secuconnect.client.Environment;
import com.secuconnect.demo.Globals;
import com.secuconnect.client.ApiException;
import com.secuconnect.client.api.PaymentSecupayPayoutApi;
import com.secuconnect.client.model.*;

import java.util.ArrayList;

public class createPayoutPayment {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // init request
            SecupayPayoutDTO transaction = new SecupayPayoutDTO();
            transaction.setCurrency("EUR");
            transaction.setContract("PCR_M32SCZ98Q2N3U4GW70ZAVWWE47XPAH");

            transaction.setRedirectUrl(new SecupayRedirectUrl());
            transaction.getRedirectUrl().setUrlPush("https://api.example.com/secuconnect/push");

            transaction.setPurpose("Payout Test #1");
            transaction.setOrderId("201900123");

            // See src/payment/createCustomer.java if you want to know how you can create a payment customer id
            transaction.setCustomer(new PaymentCustomersProductModel());
            transaction.getCustomer().setId("PCU_3BBSV6C702NCA4HR70ZAVCAX3R6CAW");

            SecupayTransactionListItem listItem1 = new SecupayTransactionListItem();
            listItem1.setReferenceId("2000.1");
            listItem1.setName("Payout Purpose 1");
            listItem1.setTransactionHash("andbeqalxuik3414051");
            listItem1.setTotal(100); // in euro-cent

            SecupayTransactionListItem listItem2 = new SecupayTransactionListItem();
            listItem2.setReferenceId("2000.2");
            listItem2.setName("Payout Purpose 2");
            listItem2.setContainerId("PCT_3RW7JJ3JC2NCA5RRN0ZAVDYF4DUKA2");
            listItem2.setTotal(200); // in euro-cent

            SecupayTransactionListItem listItem3 = new SecupayTransactionListItem();
            listItem3.setReferenceId("2000.3");
            listItem3.setName("Payout Purpose 3");
            listItem3.setTransactionId("PCI_WQH6C6CM2KQHBCJ5YVCX5D49P4P0NG");
            listItem3.setTotal(50); // in euro-cent

            transaction.setTransactionList(new ArrayList<SecupayTransactionListItem>(3));
            transaction.getTransactionList().add(listItem1);
            transaction.getTransactionList().add(listItem2);
            transaction.getTransactionList().add(listItem3);

            // calculate the amount
            Integer amount = 0;
            for (SecupayTransactionListItem item : transaction.getTransactionList()) {
                amount += item.getTotal();
            }
            transaction.setAmount(amount); // in euro-cent

            PaymentSecupayPayoutApi apiInstance = new PaymentSecupayPayoutApi();
            SecupayPayoutProductModel response = apiInstance.paymentSecupaypayoutPost(transaction);

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             * class SecupayPayoutProductModel {
             *     object: payment.secupaypayout
             *     id: aoaomyekdbjf3479559
             *     transId: 14252853
             *     status: authorized
             *     amount: 350
             *     currency: EUR
             *     purpose: Payout Test #1
             *     orderId: 201900123
             *     transactionStatus: 25
             *     transactionList: [class SecupayTransactionListItem {
             *         itemType: transaction_payout
             *         referenceId: 2000.1
             *         name: Payout Purpose 1
             *         transactionHash: andbeqalxuik3414051
             *         total: 100
             *     }, class SecupayTransactionListItem {
             *         itemType: transaction_payout
             *         referenceId: 2000.2
             *         name: Payout Purpose 2
             *         containerId: PCT_3RW7JJ3JC2NCA5RRN0ZAVDYF4DUKA2
             *         total: 200
             *     }, class SecupayTransactionListItem {
             *         itemType: transaction_payout
             *         referenceId: 2000.3
             *         name: Payout Purpose 3
             *         transactionId: PCI_WQH6C6CM2KQHBCJ5YVCX5D49P4P0NG
             *         total: 50
             *     }]
             *     transferPurpose: TA 14252853
             *     transferAccount: class PaymentInformation {
             *         iban: DE88300500000001747013
             *         bic: WELADEDDXXX
             *     }
             * }
             */

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
