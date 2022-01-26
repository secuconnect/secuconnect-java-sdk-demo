package com.secuconnect.demo.api_integration_of_smart_checkout;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.client.api.SmartTransactionsApi;
import com.secuconnect.client.model.*;
import com.secuconnect.demo.Globals;

/**
 * API Integration of Smart Checkout
 *
 * Step 3: Get the details of a completed smart transaction
 *
 * @see <a href="https://developer.secuconnect.com/integration/API_Integration_of_Smart_Checkout.html">API Integration of Smart Checkout</a>
 */
public class Step3 {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // run api call
            SmartTransactionsProductModel response = new SmartTransactionsApi().getOne("STX_NPNF3464P2X4YJ5RABEHH3SGZJXWAH");
            System.out.println(response.toString());
            /*
             * Sample output:
             * ==============
             * class SmartTransactionsProductModel {
             *     class BaseProductModel {
             *         object: smart.transactions
             *         id: STX_NPNF3464P2X4YJ5RABEHH3SGZJXWAH
             *     }
             *     created: 2021-04-28T12:00:00+02:00
             *     updated: 2021-04-28T12:05:00+02:00
             *     status: received
             *     customer: class PaymentCustomersProductModel {
             *         class BaseProductModel {
             *             object: payment.customers
             *             id: PCU_WMZDQQSRF2X4YJ67G997YE8Y26XSAW
             *         }
             *         contact: class Contact {
             *             forename: Max
             *             surname: Muster
             *             email: sd@example.com
             *             phone: +4912342134123
             *             address: class Address {
             *                 street: Kolumbastr.
             *                 streetNumber: 3TEST
             *                 city: Köln
             *                 postalCode: 50667
             *                 country: DE
             *             }
             *         }
             *     }
             *     container: class SmartTransactionsContainer {
             *         class ProductInstanceUID {
             *             object: payment.containers
             *             id: PCT_WCB4H23TW2X4YJ6Y8B7FD5N5MS8NA2
             *         }
             *         type: bank_account
             *     }
             *     transId: 40015106
             *     paymentMethod: debit
             *     transactions: [class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_WP7AEW23T2SMTJ0MCJTTXQ5002K8N6
             *         }
             *         transId: 40015106
             *         transactionHash: qkglowlxxbyz4972791
             *     }]
             *     ...
             * }
             */
        } catch (ApiException e) {
            e.printStackTrace();

            // show the error message from the api
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
