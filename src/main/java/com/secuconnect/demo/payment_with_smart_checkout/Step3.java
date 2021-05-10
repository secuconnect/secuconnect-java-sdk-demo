package com.secuconnect.demo.payment_with_smart_checkout;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.client.api.SmartTransactionsApi;
import com.secuconnect.client.model.*;
import com.secuconnect.demo.Globals;

/**
 * Payment with Smart Checkout
 *
 * Step 3: Get the details of a completed smart transaction
 *
 * @see <a href="https://developer.secuconnect.com/integration/Payment_with_Smart_Checkout.html">Payment with Smart Checkout</a>
 */
public class Step3 {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // run api call
            SmartTransactionsProductModel response = new SmartTransactionsApi().getOne("STX_WPATWC3RJ2X4YKCZYU2NJCTMMC6YAJ");
            System.out.println(response.toString());
            /*
             * Sample output:
             * ==============
             * class SmartTransactionsProductModel {
             *     class BaseProductModel {
             *         object: smart.transactions
             *         id: STX_WPATWC3RJ2X4YKCZYU2NJCTMMC6YAJ
             *     }
             *     created: 2021-04-28T12:00:00+02:00
             *     updated: 2021-04-28T12:05:00+02:00
             *     status: ok
             *     ...
             *     container: class SmartTransactionsContainer {
             *         class ProductInstanceUID {
             *             object: payment.containers
             *             id: PCT_2FNN7XCTY2X4YKFPD4WCD6EZ4CFWAZ
             *         }
             *         type: credit_card
             *     }
             *     transId: 40015111
             *     paymentMethod: creditcard
             *     transactions: [class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_WGWAEV0R6GUNST2TCFG6GZ5002K8NH
             *         }
             *         transId: 40015111
             *         transactionHash: tqussdueuatn4972796
             *     }]
             *     paymentLinks: null
             * }
             */
        } catch (ApiException e) {
            e.printStackTrace();

            // show the error message from the api
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
