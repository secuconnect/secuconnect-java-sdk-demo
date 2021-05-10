package com.secuconnect.demo.custom_checkout_for_marketplace;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.client.api.PaymentContainersApi;
import com.secuconnect.client.api.SmartTransactionsApi;
import com.secuconnect.client.model.*;
import com.secuconnect.demo.Globals;

/**
 * Custom Checkout for Marketplace
 *
 * Step 3: Authorise and Capture for Direct Debit
 *
 * @see <a href="https://developer.secuconnect.com/integration/Custom_Checkout_for_Marketplace.html">Custom Checkout for Marketplace</a>
 */
public class Step3 {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // create a payment container
            PaymentContainersProductModel container = new PaymentContainersApi().paymentContainersPost(
                new PaymentContainersDTO()
                    .type("bank_account")
                    ._private(
                        new BankAccountDescriptor()
                            .owner("Max Mustermann")
                            .iban("DE35500105175418493188")
                    )
            );

            // run api call
            SmartTransactionsProductModel response = new SmartTransactionsApi().prepare(
                "STX_2SQHXHXRS2X4YJVP0NYSU5H73RWXA3",
                "debit",
                new SmartTransactionsPrepare()
                    .container(
                        new ProductInstanceUID()
                            .id(container.getId())
                    )
            );
            System.out.println(response.toString());
            /*
             * Sample output:
             * ==============
             * class SmartTransactionsProductModel {
             *     class BaseProductModel {
             *         object: smart.transactions
             *         id: STX_2SQHXHXRS2X4YJVP0NYSU5H73RWXA3
             *     }
             *     created: 2021-04-28T12:00:00+02:00
             *     updated: 2021-04-28T12:05:00+02:00
             *     status: ok
             *     container: class SmartTransactionsContainer {
             *         class ProductInstanceUID {
             *             object: payment.containers
             *             id: PCT_39F8STS792X4YK6XESBEKWUP02PCAZ
             *         }
             *         type: bank_account
             *     }
             *     transId: 40015109
             *     paymentMethod: debit
             *     transactions: [class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_WXNHW7J0SHJ000YDJ0HV5M5002K8N9
             *         }
             *         transId: 40015109
             *         transactionHash: uoprjkwqcodm4972794
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_7U6VCXFMPUPFH9Y8Y3C0AU5002K8NG
             *         }
             *         transId: 40015110
             *         transactionHash: uoprjkwqcodm4972794_40015110
             *         referenceId: 1002
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
