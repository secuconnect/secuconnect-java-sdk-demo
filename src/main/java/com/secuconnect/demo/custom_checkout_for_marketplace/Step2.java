package com.secuconnect.demo.custom_checkout_for_marketplace;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.client.api.SmartTransactionsApi;
import com.secuconnect.client.model.*;
import com.secuconnect.demo.Globals;

import java.util.ArrayList;
import java.util.List;

/**
 * Custom Checkout for Marketplace
 *
 * Step 2: Create the Smart Transaction
 *
 * @see <a href="https://developer.secuconnect.com/integration/Custom_Checkout_for_Marketplace.html">Custom Checkout for Marketplace</a>
 */
public class Step2 {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // fill sub-basket
            int id = 0;
            List<SmartTransactionsSubBasketProduct> subItems = new ArrayList<>();
            subItems.add(
                new SmartTransactionsSubBasketProduct()
                    .id(++id)
                    .itemType("article")
                    .desc("Coffee maker with remote control")
                    .quantity(1)
                    .priceOne(5000) // in euro-cent
                    .tax(19)
            );
            subItems.add(
                new SmartTransactionsSubBasketProduct()
                    .id(++id)
                    .itemType("shipping")
                    .desc("Standard delivery 2-3 days")
                    .quantity(1)
                    .priceOne(350) // in euro-cent
                    .tax(19)
            );
            subItems.add(
                new SmartTransactionsSubBasketProduct()
                    .id(++id)
                    .itemType("stakeholder_payment")
                    .desc("Marketplace fee")
                    .sum(161) // in euro-cent
                    .referenceId("fee #12333")
                    .contractId("GCR_2H69XY35227V2VKP9WRA3SJ0W95RP0")
            );

            // fill basket
            List<SmartTransactionsBasketProduct> basketItems = new ArrayList<>();
            basketItems.add(
                new SmartTransactionsBasketProduct()
                    .itemType("sub_transaction")
                    .desc("Orders for Muster-Elektrogeräte GmbH")
                    .referenceId("1002")
                    .contractId("GCR_ZPMJGRH4SU3X0H3Y3WYB69XVXAG8PJ")
                    .subBasket(subItems)
            );

            // calculate the sum
            int total = 0;
            for (SmartTransactionsBasketProduct item : basketItems) {
                int sum = 0;
                for (SmartTransactionsSubBasketProduct subItem : item.getSubBasket()) {
                    if ("article".equals(subItem.getItemType()) || "shipping".equals(subItem.getItemType())) {
                        sum += subItem.getPriceOne() * subItem.getQuantity();
                    }
                    if ("coupon".equals(subItem.getItemType())) {
                        sum -= subItem.getPriceOne() * subItem.getQuantity();
                    }
                }
                total += sum;
                item.sum(sum);
            }

            // init request
            SmartTransactionsDTO transaction = new SmartTransactionsDTO()
                .isDemo(true)
                .contract(
                    new ProductInstanceID()
                        .id("GCR_2H69XY35227V2VKP9WRA3SJ0W95RP0")
                )
                .customer(new PaymentCustomersProductModel().contact(
                    new Contact()
                        .forename("Lesley")
                        .surname("Mustermann")
                        .phone("+49 555 5555555")
                        .mobile("+49 177 5555555")
                        .address(
                            new Address()
                                .street("Musterstr.")
                                .streetNumber("840")
                                .additionalAddressData("App. 506")
                                .postalCode("09999")
                                .city("East Palmaside")
                                .country("DE")
                        )
                        .email("Andrew37@example.org")
                        .dob("1965-12-31")
                ))
                .intent("sale")
                .basket(
                    new SmartTransactionsBasket()
                        .products(basketItems)
                )
                .basketInfo(
                    new SmartTransactionsBasketInfo()
                        .sum(total)
                        .currency("EUR")
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
             *         id: STX_2SQHXHXRS2X4YJVP0NYSU5H73RWXA3
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
             *             id: PCU_H4RNC9W9J2X4YJVP0NYSU5H73RWXA2
             *         }
             *         contact: class Contact {
             *             forename: Lesley
             *             surname: Mustermann
             *             email: Andrew37@example.org
             *             phone: +495555555555
             *             mobile: +491775555555
             *             dob: 1965-12-31T00:00:00+01:00
             *             address: class Address {
             *                 street: Musterstr.
             *                 streetNumber: 840
             *                 city: East Palmaside
             *                 postalCode: 09999
             *                 country: DE
             *                 additionalAddressData: App. 506
             *             }
             *         }
             *     }
             *     basketInfo: class SmartTransactionsBasketInfo {
             *         sum: 5350
             *         currency: EUR
             *     }
             *     basket: class SmartTransactionsBasket {
             *         products: [class SmartTransactionsBasketProduct {
             *             itemType: sub_transaction
             *             desc: Orders for Muster-Elektrogeräte GmbH
             *             sum: 5350
             *             referenceId: 1002
             *             contractId: GCR_ZPMJGRH4SU3X0H3Y3WYB69XVXAG8PJ
             *             subBasket: [class SmartTransactionsSubBasketProduct {
             *                 id: 1
             *                 itemType: article
             *                 desc: Coffee maker with remote control
             *                 quantity: 1
             *                 priceOne: 5000
             *                 tax: 19
             *             }, class SmartTransactionsSubBasketProduct {
             *                 id: 2
             *                 itemType: shipping
             *                 desc: Standard delivery 2-3 days
             *                 quantity: 1
             *                 priceOne: 350
             *                 tax: 19
             *             }, class SmartTransactionsSubBasketProduct {
             *                 itemType: stakeholder_payment
             *                 desc: Marketplace fee
             *                 sum: 161
             *                 referenceId: fee #12333
             *                 contractId: GCR_2H69XY35227V2VKP9WRA3SJ0W95RP0
             *             }]
             *         }]
             *         type: mixed
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
             * }
             */
        } catch (ApiException e) {
            e.printStackTrace();

            // show the error message from the api
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
