package com.secuconnect.demo.payment_with_smart_checkout;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.client.api.SmartTransactionsApi;
import com.secuconnect.client.model.*;
import com.secuconnect.demo.Globals;

/**
 * Payment with Smart Checkout
 *
 * Step 2: Create the Smart Transaction
 *
 * @see <a href="https://developer.secuconnect.com/integration/Payment_with_Smart_Checkout.html">Payment with Smart Checkout</a>
 */
public class Step2 {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // init request
            SmartTransactionsDTO transaction = new SmartTransactionsDTO()
                .isDemo(true)
                .contract(
                    new ProductInstanceID()
                        .id("GCR_2H69XY35227V2VKP9WRA3SJ0W95RP0")
                )
                .customer(new SmartTransactionPaymentCustomerDTO().contact(
                    new Contact()
                        .forename("Max")
                        .surname("Mustermann")
                        .email("max@example.net")
                ))
                .intent("sale")
                .basketInfo(
                    new SmartTransactionsBasketInfo()
                        .sum(500)
                        .currency("EUR")
                )
                .applicationContext(
                    new SmartTransactionsApplicationContext()
                        .returnUrls(
                            new SmartTransactionsApplicationContextReturnUrls()
                                .urlSuccess("https://shop.example.com/payment-success")
                                .urlError("https://shop.example.com/payment-failure")
                                .urlAbort("https://shop.example.com/payment-abort")
                        )
                )
                .paymentContext(
                    new PaymentContext()
                        .autoCapture(true)
                );

            // run api call
            SmartTransactionsProductModel response = new SmartTransactionsApi().addTransaction(transaction);
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
             *     status: created
             *     merchant: class SmartTransactionsMerchant {
             *         object: general.merchants
             *         id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *         companyname: Secupay Test-Shop
             *     }
             *     contract: class ProductInstanceUID {
             *         object: general.contracts
             *         id: GCR_2H69XY35227V2VKP9WRA3SJ0W95RP0
             *     }
             *     customer: class PaymentCustomersProductModel {
             *         class BaseProductModel {
             *             object: payment.customers
             *             id: PCU_TYYMMJW5K2X4YKCZYU2NJCTMMC6YAH
             *         }
             *         contact: class Contact {
             *             forename: Max
             *             surname: Mustermann
             *             email: max@example.net
             *         }
             *     }
             *     basketInfo: class SmartTransactionsBasketInfo {
             *         sum: 500
             *         currency: EUR
             *     }
             *     isDemo: true
             *     intent: sale
             *     paymentLinks: class SmartTransactionsPaymentLinks {
             *         prepaid: https://checkout-dev.secuconnect.com?...
             *         debit: https://checkout-dev.secuconnect.com?...
             *         creditcard: https://checkout-dev.secuconnect.com?...
             *         invoice: https://checkout-dev.secuconnect.com?...
             *         paypal: null
             *         sofort: https://checkout-dev.secuconnect.com?...
             *         general: https://checkout-dev.secuconnect.com?...
             *     }
             *     applicationContext: class SmartTransactionsApplicationContext {
             *         returnUrls: class SmartTransactionsApplicationContextReturnUrls {
             *             urlSuccess: https://shop.example.com/payment-success
             *             urlAbort: https://shop.example.com/payment-abort
             *             urlError: https://shop.example.com/payment-failure
             *         }
             *     }
             *     paymentContext: class PaymentContext {
             *         autoCapture: true
             *     }
             * }
             */
        } catch (ApiException e) {
            e.printStackTrace();

            // show the error message from the api
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
