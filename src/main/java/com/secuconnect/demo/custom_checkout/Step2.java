package com.secuconnect.demo.custom_checkout;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.client.api.SmartTransactionsApi;
import com.secuconnect.client.model.*;
import com.secuconnect.demo.Globals;

/**
 * Custom Checkout
 *
 * Step 2: Create the Smart Transaction
 *
 * @see <a href="https://developer.secuconnect.com/integration/Custom_Checkout.html">Custom Checkout</a>
 */
public class Step2 {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // init request
            SmartTransactionsDTO transaction = new SmartTransactionsDTO()
                .isDemo(true)
                .intent("sale")
                .contract(
                    new ProductInstanceID()
                        .id("GCR_2H69XY35227V2VKP9WRA3SJ0W95RP0")
                )
                .customer(new SmartTransactionPaymentCustomerDTO().contact(
                    new Contact()
                        .salutation("Mr.")
                        .forename("Max")
                        .surname("Mustermann")
                        .address(
                            new Address()
                                .street("Max-Muster-Str.")
                                .streetNumber("25a")
                                .postalCode("09555")
                                .city("Musterstadt")
                                .country("DE")
                                .additionalAddressData("Whg. 202")
                        )
                        .email("mmustermann@example.net")
                        .mobile("+49 177 5555555")
                        .phone("+49 555 5555555")
                ))
                .transactionRef("Hotelbuchung 23.10.2020 für Hrn. Muster, Musterfirma GmbH Musterstadt")
                .basketInfo(
                    new SmartTransactionsBasketInfo()
                        .currency("EUR")
                        .sum(11970) // €119.70 (smallest currency unit)
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
             *         id: STX_3JWNJ0Q9W2X4YJF535ZHR29T6SJ5AJ
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
             *             id: PCU_3XDX9HQM62X4YJF535ZHR29T6SJ5AH
             *         }
             *         contact: class Contact {
             *             forename: Max
             *             surname: Mustermann
             *             salutation: Mr.
             *             email: mmustermann@example.net
             *             phone: +495555555555
             *             mobile: +491775555555
             *             address: class Address {
             *                 street: Max-Muster-Str.
             *                 streetNumber: 25aa
             *                 city: Musterstadt
             *                 postalCode: 09555
             *                 country: DE
             *                 additionalAddressData: Whg. 202
             *             }
             *         }
             *     }
             *     transactionRef: Hotelbuchung 23.10.2020 für Hrn. Muster, Musterfirma GmbH Musterstadt
             *     basketInfo: class SmartTransactionsBasketInfo {
             *         sum: 11970
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
             *     paymentContext: class PaymentContext {
             *         autoCapture: true
             *     }
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
