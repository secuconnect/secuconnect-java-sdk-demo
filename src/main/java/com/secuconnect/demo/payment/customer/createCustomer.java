package com.secuconnect.demo.payment.customer;

import com.secuconnect.client.Environment;
import com.secuconnect.demo.Globals;
import com.secuconnect.client.ApiException;
import com.secuconnect.client.api.PaymentCustomersApi;
import com.secuconnect.client.model.Address;
import com.secuconnect.client.model.Contact;
import com.secuconnect.client.model.PaymentCustomersDTO;
import com.secuconnect.client.model.PaymentCustomersProductModel;

public class createCustomer {
    public static void main(String[] args) {
        try {
            // init env
            Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // init request
            Contact customerContact = new Contact();
            customerContact.setSalutation("Mr.");
            customerContact.setTitle("Dr.");
            customerContact.setForename("John");
            customerContact.setSurname("Doe");
            customerContact.setCompanyname("Example Inc.");
            customerContact.setGender("m");
            customerContact.setDob("1901-02-03");
            customerContact.setUrlWebsite("example.com");
            customerContact.setBirthplace("AnotherExampleCity");
            customerContact.setNationality("DE");
            customerContact.setEmail("example123@example.com");
            customerContact.setPhone("0049-123-456789");

            Address customerContactAddress = new Address();
            customerContactAddress.setType("invoice");
            customerContactAddress.setStreet("example street");
            customerContactAddress.setStreetNumber("6a");
            customerContactAddress.setPostalCode("01234");
            customerContactAddress.setCity("Testcity");
            customerContactAddress.setCountry("DE");
            customerContact.setAddress(customerContactAddress);

            PaymentCustomersDTO customer = new PaymentCustomersDTO();
            customer.setContact(customerContact);

            PaymentCustomersApi apiInstance = new PaymentCustomersApi();
            PaymentCustomersProductModel response = apiInstance.paymentCustomersPost(customer);

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             * class PaymentCustomersProductModel {
             *     object: payment.customers
             *     id: PCU_3BBSV6C702NCA4HR70ZAVCAX3R6CAW
             *     contract: class ProductInstanceUID {
             *         object: payment.contracts
             *         id: PCR_M32SCZ98Q2N3U4GW70ZAVWWE47XPAH
             *     }
             *     contact: class Contact {
             *         forename: John
             *         surname: Doe
             *         companyname: Example Inc.
             *         salutation: Mr.
             *         gender: m
             *         title: Dr.
             *         email: example123@example.com
             *         phone: 0049-123-456789
             *         dob: 1901-02-03T00:00:00+01:00
             *         urlWebsite: example.com
             *         birthplace: AnotherExampleCity
             *         nationality: german
             *         address: class Address {
             *             street: example street
             *             streetNumber: 6a
             *             city: Testcity
             *             postalCode: 01234
             *             country: DE
             *         }
             *     }
             *     created: 2019-09-24T16:13:10+02:00
             * }
             */
        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
