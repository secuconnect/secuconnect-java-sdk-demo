package com.secuconnect.demo.payment.contract;

import com.secuconnect.client.Environment;
import com.secuconnect.demo.Globals;
import com.secuconnect.client.ApiException;
import com.secuconnect.client.api.PaymentContractsApi;
import com.secuconnect.client.model.*;
import org.joda.time.DateTime;

public class createMerchantWithIdentification {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // init request
            Contact contact = new Contact();
            /*
             * mandatory contact fields
             */
            contact.setForename("John");
            contact.setSurname("Doe");

            Address address = new Address();
            address.setType("invoice");
            address.setStreet("example street");
            address.setStreetNumber("6a");
            address.setPostalCode("01234");
            address.setCity("Testcity");
            address.setCountry("DE");

            contact.setAddress(address);

            /*
             * recommended contact fields
             */
            contact.setCompanyname("Example Inc."); // recommended
            contact.setDob("1901-02-03"); // recommended
            contact.setEmail("example123@example.com"); // recommended
            contact.setPhone("0049-123-456789"); // recommended

            /*
             * optional contact fields
             */
            contact.setSalutation("Mr."); // Mr or Ms
            contact.setTitle("Dr.");
            contact.setGender("m");
            contact.setUrlWebsite("example.com");
            contact.setBirthplace("AnotherExampleCity");
            contact.setNationality("DE");

            /*
             * bank account of the merchant to get the money
             */
            PaymentInformation payoutAccount = new PaymentInformation();
            payoutAccount.setIban("DE89370400440532013000");
            payoutAccount.setBic(""); // recommended
            payoutAccount.setOwner("Test #1");

            /*
             * Submitting the data to the API
             */
            PaymentContractsDTORequestId requestData = new PaymentContractsDTORequestId();
            requestData.setContact(contact);
            requestData.setPayinAccount(false);
            requestData.setPayoutAccount(payoutAccount);
            requestData.setProject("project_name " + new DateTime().toString()); // must be unique for each request

            /*
             * Submitting the data to the API
             */
            PaymentContractsRequestIdResult response = new PaymentContractsApi().requestId("me", requestData);

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             * class PaymentContractsRequestIdResult {
             *     contract: class PaymentContractsProductModel {
             *         object: general.contracts
             *         id: PCR_W496J83E42NCRAFTN0ZAV8UA28GPA3
             *         parent: null
             *         demo: null
             *         created: null
             *         updated: null
             *     }
             *     apikey: ade82a3f1221e326ebd8f5cfefdd837e904d9c58
             *     payinAccount: null
             * }
             */

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
