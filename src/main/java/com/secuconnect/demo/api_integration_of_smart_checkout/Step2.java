package com.secuconnect.demo.api_integration_of_smart_checkout;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.client.api.SmartTransactionsApi;
import com.secuconnect.client.model.*;
import com.secuconnect.demo.Globals;

import java.util.*;

/**
 * API Integration of Smart Checkout
 *
 * Step 2: Create the Smart Transaction
 *
 * @see <a href="https://developer.secuconnect.com/integration/API_Integration_of_Smart_Checkout.html">API Integration of Smart Checkout</a>
 */
public class Step2 {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // fill basket
            int id = 0;
            List<SmartTransactionsBasketProduct> basketItems = new ArrayList<>();
            basketItems.add(
                new SmartTransactionsBasketProduct()
                    .id(++id)
                    .itemType("article")
                    .desc("ACME ball pen Modern Line 8050")
                    .priceOne(1595) // in euro-cent
                    .tax(19)
                    .quantity(2)
            );
            basketItems.add(
                new SmartTransactionsBasketProduct()
                    .id(++id)
                    .itemType("article")
                    .desc("ACME pen case Modern Line")
                    .priceOne(1795) // in euro-cent
                    .tax(19)
                    .quantity(1)
            );

            // calculate the sum
            int sum = 0;
            for (SmartTransactionsBasketProduct item : basketItems) {
                if ("article".equals(item.getItemType()) || "shipping".equals(item.getItemType())) {
                    sum += item.getPriceOne() * item.getQuantity();
                }
                if ("coupon".equals(item.getItemType())) {
                    sum -= item.getPriceOne() * item.getQuantity();
                }
            }

            // init request
            SmartTransactionsDTO transaction = new SmartTransactionsDTO()
                .isDemo(true)
                .intent("order")
                .contract(
                    new ProductInstanceID()
                        .id("GCR_2H69XY35227V2VKP9WRA3SJ0W95RP0")
                )
                .basket(
                    new SmartTransactionsBasket()
                        .products(basketItems)
                )
                .basketInfo(
                    new SmartTransactionsBasketInfo()
                        .sum(sum)
                        .currency("EUR")
                )
                .applicationContext(
                    new SmartTransactionsApplicationContext()
                        .returnUrls(
                            new SmartTransactionsApplicationContextReturnUrls()
                                .urlSuccess("https://shop.example.com/PAYMENT-SUCCEEDED")
                                .urlError("https://shop.example.com/PAYMENT-FAILED")
                                .urlAbort("https://shop.example.com/PAYMENT-ABORTED")
                        )
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
             *         id: STX_NPNF3464P2X4YJ5RABEHH3SGZJXWAH
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
             *     basketInfo: class SmartTransactionsBasketInfo {
             *         sum: 4985
             *         currency: EUR
             *     }
             *     basket: class SmartTransactionsBasket {
             *         products: [class SmartTransactionsBasketProduct {
             *             id: 1
             *             itemType: article
             *             desc: ACME ball pen Modern Line 8050
             *             quantity: 2
             *             priceOne: 1595
             *             tax: 19
             *         }, class SmartTransactionsBasketProduct {
             *             id: 2
             *             itemType: article
             *             desc: ACME pen case Modern Line
             *             quantity: 1
             *             priceOne: 1795
             *             tax: 19
             *         }]
             *         type: default
             *     }
             *     isDemo: true
             *     checkoutLinks: class SmartTransactionsCheckoutLinks {
             *         urlCheckout: https://checkout-dev.secuconnect.com?...
             *     }
             *     intent: order
             *     applicationContext: class SmartTransactionsApplicationContext {
             *         returnUrls: class SmartTransactionsApplicationContextReturnUrls {
             *             urlSuccess: https://shop.example.com/PAYMENT-SUCCEEDED
             *             urlAbort: https://shop.example.com/PAYMENT-ABORTED
             *             urlError: https://shop.example.com/PAYMENT-FAILED
             *         }
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
