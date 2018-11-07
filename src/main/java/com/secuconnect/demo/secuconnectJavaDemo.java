package com.secuconnect.demo;

import io.secuconnect.client.ApiException;
import io.secuconnect.client.api.PaymentContractsApi;
import io.secuconnect.client.api.PaymentCustomersApi;
import io.secuconnect.client.api.PaymentSecupayPrepaysApi;
import io.secuconnect.client.auth.Authenticator;
import io.secuconnect.client.auth.credentials.OAuthClientCredentials;
import io.secuconnect.client.auth.tokens.OAuthClientToken;
import io.secuconnect.client.model.Address;
import io.secuconnect.client.model.Contact;
import io.secuconnect.client.model.PaymentContractsDTO;
import io.secuconnect.client.model.PaymentContractsDTORequestId;
import io.secuconnect.client.model.PaymentContractsRequestIdResult;
import io.secuconnect.client.model.PaymentCustomersDTO;
import io.secuconnect.client.model.PaymentCustomersProductModel;
import io.secuconnect.client.model.PaymentInformation;
import io.secuconnect.client.model.SecupayBasketItem;
import io.secuconnect.client.model.SecupayTransactionProductDTO;
import io.secuconnect.client.model.SecupayTransactionProductDTORedirectUrl;
import io.secuconnect.client.model.SecupayTransactionProductModel;
import io.secuconnect.client.model.SecupayTransactionReverseAccrualDTO;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class secuconnectJavaDemo {

  public static void main(String[] args) {
    System.out.println("Starting the Sample...");

    // Set config
    String accessToken;
    OAuthClientCredentials cred = new OAuthClientCredentials("...", "...");
    Authenticator authenticator = new Authenticator(cred);

    // Get oauth access token
    try {
      // Send request
      OAuthClientToken token = (OAuthClientToken) authenticator.getToken();
      accessToken = token.getAccessToken();

      // Analyse response
      System.out.println("AccessToken: " + accessToken);
      /*
       * Sample output:
       * ==============
       * AccessToken: 0laq8bk8p2dd8e4spj5a0ik6q7
       */
    } catch (ApiException e) {
      e.printStackTrace();
      System.out.println("ERROR: " + e.getResponseBody());
      return;
    }

    // Contract management
    try {
      // Init Data
      PaymentContractsDTORequestId contractRequestId = prepareContractRequestId();

      // Init API interface
      PaymentContractsApi contractsApi = new PaymentContractsApi();
      contractsApi.getApiClient().setAccessToken(accessToken);

      // Send request
      PaymentContractsRequestIdResult contractResult = contractsApi.requestId("me", contractRequestId);

      // Analyse response
      System.out.println("Contract: " + contractResult.toString());
      /*
       * Sample output:
       * ==============
       * Contract: class PaymentContractsRequestIdResult {
       *     contract: class PaymentContractsProductModel {
       *         object: payment.contracts
       *         id: PCR_WAUN605YW2MV3265N0ZAV2V2G3A2A3
       *         parent: class ProductInstanceUID {
       *             object: payment.contracts
       *             id: PCR_3YFT66NUT2MRMZSH70ZAV946082CAW
       *         }
       *         demo: true
       *         allowCloning: false
       *         sepaMandateInform: never
       *         created: 2018-11-07T17:23:39.000+01:00
       *     }
       *     apikey: 270bf4b1f4d5bf923cb4b1ef6e8c62b16377fff9
       * }
       */
    } catch (ApiException e) {
      // Fetch errors
      e.printStackTrace();
      System.out.println("ERROR: " + e.getResponseBody());
      return;
    }




    // Submit customer data
    PaymentCustomersProductModel customer;
    try {
      // Init Data
      PaymentCustomersDTO customerInputData = prepareCustomer();

      // Init API interface
      PaymentCustomersApi customerApi = new PaymentCustomersApi();
      customerApi.getApiClient().setAccessToken(accessToken);

      // Send request
      customer = customerApi.paymentCustomersPost(customerInputData);

      // Analyse response
      System.out.println("Customer: " + customer.toString());
      /*
       * Sample output:
       * ==============
       * Customer: class PaymentCustomersProductModel {
       *     object: payment.customers
       *     id: PCU_W0VGRS8KY2MTSXDS70ZAVDH753D0AZ
       *     contract: class ProductInstanceUID {
       *         object: payment.contracts
       *         id: PCR_23ZD9EQGH2MTSXDH70ZAVDGHHN79AH
       *     }
       *     contact: class Contact {
       *         forename: John
       *         surname: Doe
       *         companyname: Example Inc.
       *     }
       *     created: 2018-10-09T17:04:24.000+02:00
       * }
       */
    } catch (ApiException e) {
      // Fetch errors
      e.printStackTrace();
      System.out.println("ERROR: " + e.getResponseBody());
      return;
    }


    // Submit transaction data
    SecupayTransactionProductModel prepayTransactionData;
    try {
      // Init data
      SecupayTransactionProductDTO transactionInputData = prepareTransactionData();
      transactionInputData.setCustomer(customer);
      transactionInputData.setBasket(prepareMixedBasket());

      // Init API interface
      PaymentSecupayPrepaysApi prepayApi = new PaymentSecupayPrepaysApi();
      prepayApi.getApiClient().setAccessToken(accessToken);

      // Send request
      prepayTransactionData = prepayApi.paymentSecupayprepaysPost(transactionInputData);

      // Analyse response
      System.out.println("PrepayTransactionData: " + prepayTransactionData.toString());
      /*
       * Sample output:
       * ==============
       * PrepayTransactionData: class SecupayTransactionProductModel {
       *     object: payment.secupayprepays
       *     id: yzktljurjdct3062689
       *     transId: 12736001
       *     status: authorized
       *     amount: 1800
       *     currency: EUR
       *     purpose: for what text
       *     orderId: ZZZZZZ
       *     basket: [class SecupayBasketItem {
       *         itemType: sub_transaction
       *         name: Position 1 aus Bestellung 000x
       *         total: 1800
       *         apikey: 552f5e3e62388c460e3caf084e9bc60e1892dedb
       *         referenceId: 52534
       *         subBasket: [class SecupayBasketItem {
       *             itemType: article
       *             quantity: 2
       *             name: Position 1 Artikel A
       *             ean: EAN001
       *             tax: 19
       *             total: 1800
       *             price: 900
       *             referenceId: 52534.1
       *         }, class SecupayBasketItem {
       *             itemType: stakeholder_payment
       *             name: Position 1 Plattform Provision
       *             total: 144
       *             apikey: aa2ce72b3d1f771d7c1a19a7ac1830b0e1dd45a2
       *             referenceId: 52534.2
       *         }]
       *     }]
       *     transactionStatus: 25
       *     accrual: true
       *     paymentAction: sale
       *     transferPurpose: TA 12736001
       *     transferAccount: class SecupayTransactionProductModelTransferAccount {
       *         iban: DE88300500000001747013
       *         bic: WELADEDDXXX
       *         accountnumber: 1747013
       *         bankcode: 30050000
       *         accountOwner: secupay AG
       *     }
       *     customer: class PaymentCustomersProductModel {
       *         object: payment.customers
       *         id: PCU_28GPZWS8B2MTSXG070ZAVDZQ4ZCPAH
       *         contract: class ProductInstanceUID {
       *             object: payment.contracts
       *             id: PCR_23ZD9EQGH2MTSXDH70ZAVDGHHN79AH
       *         }
       *         contact: class Contact {
       *             forename: John
       *             surname: Doe
       *             companyname: Example Inc.
       *         }
       *         created: 2018-10-09T17:06:40.000+02:00
       *     }
       *     redirectUrl: class SecupayTransactionProductModelRedirectUrl {
       *         iframeUrl: https://api-testing.secupay-ag.de/payment/yzktljurjdct3062689
       *         urlSuccess: http://shop.example.com?success=true
       *         urlFailure: http://shop.example.com?success=false
       *         urlPush: null
       *     }
       * }
       */

      // This is not mandatory for prepaid, because for this payment method it will be display some summary page only:
      System.out.println("Please open this URL in your browser: " + prepayTransactionData.getRedirectUrl().getIframeUrl());
      /*
       * Sample output:
       * ==============
       * Please open this URL in your browser: https://api-testing.secupay-ag.de/payment/yzktljurjdct3062689
       */

      // reverse the accrual flag
      SecupayTransactionReverseAccrualDTO accrualDTO = new SecupayTransactionReverseAccrualDTO();
      accrualDTO.accrual(false);

      prepayTransactionData = prepayApi.reverseAccrualByPaymentId("secupayprepays", "yzktljurjdct3062689_12736002", accrualDTO);

      // Analyse response
      System.out.println("PrepayTransactionData 2: " + prepayTransactionData.toString());
      /*
       * Sample output:
       * ==============
       * PrepayTransactionData: class SecupayTransactionProductModel {
       *     object: payment.secupayprepays
       *     id: yzktljurjdct3062689
       *     accrual: false
       * ...
       */
    } catch (ApiException e) {
      // Fetch errors
      e.printStackTrace();
      System.out.println("ERROR: " + e.getResponseBody());
      return;
    }

    System.out.println("Samples done.");
  }

  private static PaymentContractsDTORequestId prepareContractRequestId() {
      PaymentContractsDTORequestId dto = new PaymentContractsDTORequestId();

      // Define the "contact person"
      Contact contact = new Contact();
      contact.setSalutation("Mr."); // mandatory
      contact.setTitle("Dr."); // optional
      contact.setForename("John"); // mandatory
      contact.setSurname("Doe"); // mandatory
      contact.setCompanyname("Example Inc."); // mandatory for companies
      contact.setDob("1901-02-03"); // mandatory
      contact.setEmail("example@example.com"); // mandatory
      contact.setPhone("0049-123-456789"); // mandatory

      Address address = new Address();
      address.setStreet("Example Street"); // mandatory
      address.setStreetNumber("6a"); // can be also in the street, if you have only one field one your side for this
      address.setPostalCode("01234"); // mandatory
      address.setCity("Example-City"); // mandatory
      address.setCountry("DE"); // not mandatory but highly recommended (ISO 3166-1 alpha-2)
      contact.setAddress(address);

      dto.setContact(contact);

      // Define the bank account for payout
      PaymentInformation payoutAccount = new PaymentInformation();
      payoutAccount.setOwner("John Doe");
      payoutAccount.setIban("DE37503240001000000524");
      payoutAccount.setBic("FTSBDEFAXXX"); // not mandatory for "DE" but recommended
      dto.setPayoutAccount(payoutAccount);

      // Define the "project name"
      dto.setProject("Project #" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())); // MUST be unique

      return dto;
  }

  private static PaymentCustomersDTO prepareCustomer() {
    Contact contact = new Contact();
    contact.setForename("John");
    contact.setSurname("Doe");
    contact.setCompanyname("Example Inc.");

    PaymentCustomersDTO customerDTO = new PaymentCustomersDTO();
    customerDTO.setContact(contact);

    return customerDTO;
  }

  private static SecupayTransactionProductDTO prepareTransactionData() {
    SecupayTransactionProductDTO transactionData = new SecupayTransactionProductDTO();
    transactionData.setAmount(1800);
    transactionData.setCurrency("EUR");
    transactionData.setPurpose("for what text");
    transactionData.setOrderId("ZZZZZZ");
    transactionData.setAccrual(true);

    SecupayTransactionProductDTORedirectUrl redirectUrl = new SecupayTransactionProductDTORedirectUrl();
    redirectUrl.setUrlSuccess("http://shop.example.com?success=true");
    redirectUrl.setUrlFailure("http://shop.example.com?success=false");
    redirectUrl.setUrlPush("https://example.com");
    transactionData.setRedirectUrl(redirectUrl);

    return transactionData;
  }

  private static List<SecupayBasketItem> prepareBasket() {
    List<SecupayBasketItem> basket = new ArrayList<SecupayBasketItem>();

    SecupayBasketItem basketItem1 = new SecupayBasketItem();
    basketItem1.setItemType("article");
    basketItem1.setArticleNumber("3211");
    basketItem1.setQuantity(2);
    basketItem1.setName("Product 3211");
    basketItem1.setEan("4123412341243");
    basketItem1.setTax("19");
    basketItem1.setTotal(2000);
    basketItem1.setPrice(1000);
    basket.add(basketItem1);

    SecupayBasketItem basketItem2 = new SecupayBasketItem();
    basketItem2.setItemType("article");
    basketItem2.setArticleNumber("48875");
    basketItem2.setQuantity(2);
    basketItem2.setName("Product 48875");
    basketItem2.setEan("4123412341236");
    basketItem2.setTax("19");
    basketItem2.setTotal(4000);
    basketItem2.setPrice(2000);
    basket.add(basketItem2);

    SecupayBasketItem basketItem3 = new SecupayBasketItem();
    basketItem3.setItemType("shipping");
    basketItem3.setName("standard delivery");
    basketItem3.setTax("19");
    basketItem3.setTotal(200);
    basket.add(basketItem3);

    return basket;
  }

  private static List<SecupayBasketItem> prepareMixedBasket() {
    List<SecupayBasketItem> basket = new ArrayList<SecupayBasketItem>();

    SecupayBasketItem basketItem1 = new SecupayBasketItem();
    basketItem1.setItemType("sub_transaction");
    basketItem1.setReferenceId("52534");
    basketItem1.setApikey("552f5e3e62388c460e3caf084e9bc60e1892dedb"); // You can use "0027376e7cc218eb3b0834936e6628d7c057d9de" for a second one.
    basketItem1.setName("Position 1 aus Bestellung 000x");
    basketItem1.setTotal(1800);

    List<SecupayBasketItem> subBasket1 = new ArrayList<SecupayBasketItem>();

    SecupayBasketItem subBasketItem1 = new SecupayBasketItem();
    subBasketItem1.setItemType("article");
    subBasketItem1.setReferenceId("52534.1");
    subBasketItem1.setQuantity(2);
    subBasketItem1.setName("Position 1 Artikel A");
    subBasketItem1.setEan("EAN001");
    subBasketItem1.setTax("19");
    subBasketItem1.setTotal(1800);
    subBasketItem1.setPrice(900);
    subBasket1.add(subBasketItem1);

    SecupayBasketItem subBasketItem2 = new SecupayBasketItem();
    subBasketItem2.setItemType("stakeholder_payment");
    subBasketItem2.setReferenceId("52534.2");
    subBasketItem2.setApikey("aa2ce72b3d1f771d7c1a19a7ac1830b0e1dd45a2");
    subBasketItem2.setName("Position 1 Plattform Provision");
    subBasketItem2.setTotal(144);
    subBasket1.add(subBasketItem2);

    basketItem1.setSubBasket(subBasket1);

    basket.add(basketItem1);

    return basket;
  }

}
