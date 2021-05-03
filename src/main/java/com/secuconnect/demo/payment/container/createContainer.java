package com.secuconnect.demo.payment.container;

import com.secuconnect.client.Environment;
import com.secuconnect.demo.Globals;
import com.secuconnect.client.ApiException;
import com.secuconnect.client.api.PaymentContainersApi;
import com.secuconnect.client.model.PaymentContainersDTO;
import com.secuconnect.client.model.PaymentContainersDTOCustomer;
import com.secuconnect.client.model.BankAccountDescriptor;
import com.secuconnect.client.model.PaymentContainersProductModel;

public class createContainer {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // init request
            PaymentContainersDTO container =
                new PaymentContainersDTO()
                    .type("bank_account")
                    .customer(
                        new PaymentContainersDTOCustomer()
                            .id("PCU_3BBSV6C702NCA4HR70ZAVCAX3R6CAW") // from "payment/customer/createCustomer.java"
                    )
                    ._private(
                        new BankAccountDescriptor()
                            .owner("John Doe")
                            .iban("DE37503240001000000524")
                            .bic("FTSBDEFAXXX")
                    );

            // run api call
            PaymentContainersProductModel response = new PaymentContainersApi().paymentContainersPost(container);

            System.out.print(response);
            /*
             * Sample output:
             * ==============
             * class PaymentContainersProductModel {
             *     object: payment.containers
             *     id: PCT_3RW7JJ3JC2NCA5RRN0ZAVDYF4DUKA2
             *     contract: class PaymentContractsProductModel {
             *         object: payment.contracts
             *         id: PCR_M32SCZ98Q2N3U4GW70ZAVWWE47XPAH
             *     }
             *     customer: class PaymentCustomersProductModel {
             *         object: payment.customers
             *         id: PCU_3BBSV6C702NCA4HR70ZAVCAX3R6CAW
             *     }
             *     type: bank_account
             *     _public: class BankAccountDescriptor {
             *         iban: DE37503240001000000524
             *         bic: FTSBDEFAXXX
             *         owner: John Doe
             *         bankname: ABN AMRO Bank, Frankfurt Branch
             *     }
             *     _private: class BankAccountDescriptor {
             *         iban: DE37503240001000000524
             *         bic: FTSBDEFAXXX
             *         owner: John Doe
             *         bankname: ABN AMRO Bank, Frankfurt Branch
             *     }
             *     created: 2019-09-24T16:57:59+02:00
             *     mandate: class PaymentContainerMandate {
             *         sepaMandateId: 558430
             *         type: CORE
             *         identification: PAM/A8I8KH21MG9G5B11G
             *     }
             * }
             */

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
