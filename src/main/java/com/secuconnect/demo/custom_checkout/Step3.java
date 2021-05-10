package com.secuconnect.demo.custom_checkout;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.client.api.PaymentContainersApi;
import com.secuconnect.client.api.SmartTransactionsApi;
import com.secuconnect.client.model.*;
import com.secuconnect.demo.Globals;

/**
 * Custom Checkout
 *
 * Step 3: Authorize and Capture for Direct Debit
 *
 * @see <a href="https://developer.secuconnect.com/integration/Custom_Checkout.html">Custom Checkout</a>
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
                "STX_3JWNJ0Q9W2X4YJF535ZHR29T6SJ5AJ",
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
             *         id: STX_3JWNJ0Q9W2X4YJF535ZHR29T6SJ5AJ
             *     }
             *     created: 2021-04-28T12:00:00+02:00
             *     updated: 2021-04-28T12:05:00+02:00
             *     status: ok
             *     container: class SmartTransactionsContainer {
             *         class ProductInstanceUID {
             *             object: payment.containers
             *             id: PCT_NAW3Z3FHS2X4YJFR0NHWB3AM2YY8A2
             *         }
             *         type: bank_account
             *     }
             *     transId: 40015108
             *     paymentMethod: debit
             *     transactions: [class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_WVC27UT5T8UA46Z2YTAGFB5002K8N8
             *         }
             *         transId: 40015108
             *         transactionHash: mwekikpdsswi4972793
             *     }]
             *     paymentLinks: null
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
