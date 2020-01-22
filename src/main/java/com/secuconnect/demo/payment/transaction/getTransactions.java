package com.secuconnect.demo.payment.transaction;

import com.secuconnect.demo.getToken;
import io.secuconnect.client.ApiException;
import io.secuconnect.client.api.PaymentTransactionsApi;
import io.secuconnect.client.model.PaymentTransactionsList;

public class getTransactions {
    public static void main(String[] args) {
        try {
            getToken.main(null);

            PaymentTransactionsApi apiInstance = new PaymentTransactionsApi();

            String query = "incoming_payment_date:*";
            String sort = "incoming_payment_date:desc";

            apiInstance.getApiClient().setAccessToken(getToken.accessToken);
            PaymentTransactionsList response = apiInstance.getAll(
                    10,
                    null,
                    null,
                    query,
                    sort
            );

            System.out.print(response);

            /*
             * Sample output:
             * ==============
             *
             * class PaymentTransactionsList {
             *     count: 8696
             *     data: [class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_49DVW2JRBRQXYJAM62EM7ZG9YH2BOJ
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3588973
             *         parents: null
             *         productId: 21
             *         product: Kreditkarte Demo
             *         productRaw: Kreditkarte Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 20600
             *         currency: EUR
             *         created: 2014-09-09T09:38:36+02:00
             *         updated: 2014-09-09T09:38:57+02:00
             *         status: 11
             *         statusText: abgeschlossen(Demo)
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 20600
             *             cleared: open
             *             status: 11
             *             statusText: abgeschlossen(Demo)
             *             statusSimple: 1
             *             statusTextSimple: null
             *             description:
             *             descriptionRaw:
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: null
             *             title: null
             *             forename: Helene
             *             surname: Fischer
             *         }
             *         tid: null
             *         paymentData: 411111XXXXXX1111
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: akqnapylltny220227
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_6KB8VRZCYWXRQ7YFWH5W8PG9YH2COK
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3588994
             *         parents: null
             *         productId: 21
             *         product: Kreditkarte Demo
             *         productRaw: Kreditkarte Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 10500
             *         currency: EUR
             *         created: 2014-09-09T09:54:22+02:00
             *         updated: 2014-09-09T09:54:53+02:00
             *         status: 11
             *         statusText: abgeschlossen(Demo)
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 10500
             *             cleared: open
             *             status: 11
             *             statusText: abgeschlossen(Demo)
             *             statusSimple: 1
             *             statusTextSimple: null
             *             description:
             *             descriptionRaw:
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: null
             *             title: null
             *             forename: Helene
             *             surname: Fischer
             *         }
             *         tid: null
             *         paymentData: 411111XXXXXX1111
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: qorqwuwkxkcv220248
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_DYN2ZZTWRUZWCQV8Z7B267R9YH2CON
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3588997
             *         parents: null
             *         productId: 21
             *         product: Kreditkarte Demo
             *         productRaw: Kreditkarte Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 10500
             *         currency: EUR
             *         created: 2014-09-09T09:56:43+02:00
             *         updated: 2014-09-09T09:57:08+02:00
             *         status: 59
             *         statusText: Zahlung abgebrochen
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 10500
             *             cleared: open
             *             status: 59
             *             statusText: Zahlung abgebrochen
             *             statusSimple: 3
             *             statusTextSimple: null
             *             description:
             *             descriptionRaw:
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: null
             *             title: null
             *             forename: Test
             *             surname: Tester
             *         }
             *         tid: null
             *         paymentData: null
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: hdupfqtpehda220251
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_4HT9KWPHU5YEY7B0TDCRM4H9H95BO9
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3149569
             *         parents: null
             *         productId: 21
             *         product: Kreditkarte Demo
             *         productRaw: Kreditkarte Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 199
             *         currency: EUR
             *         created: 2014-05-05T11:37:24+02:00
             *         updated: 2014-05-05T11:37:40+02:00
             *         status: 59
             *         statusText: Zahlung abgebrochen
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 199
             *             cleared: open
             *             status: 59
             *             statusText: Zahlung abgebrochen
             *             statusSimple: 3
             *             statusTextSimple: null
             *             description: Test Order #1
             *             descriptionRaw: Test Order #1
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: Frau
             *             title: null
             *             forename: Joe
             *             surname: Black
             *         }
             *         tid: null
             *         paymentData: null
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: ibnptdkwljsq126070
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_WBBAQKGEQCT4FW2UDA5Y9TC9HC4AOK
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3161154
             *         parents: null
             *         productId: 21
             *         product: Kreditkarte Demo
             *         productRaw: Kreditkarte Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 199
             *         currency: EUR
             *         created: 2014-05-08T13:03:37+02:00
             *         updated: 2014-05-08T13:04:21+02:00
             *         status: 59
             *         statusText: Zahlung abgebrochen
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 199
             *             cleared: open
             *             status: 59
             *             statusText: Zahlung abgebrochen
             *             statusSimple: 3
             *             statusTextSimple: null
             *             description: Test Order #1
             *             descriptionRaw: Test Order #1
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: Frau
             *             title: null
             *             forename: Joe
             *             surname: Black
             *         }
             *         tid: null
             *         paymentData: null
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: pucozsegdzqt128334
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_3GZ90R38UDE4D88D5K74RMS9HC5XO7
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3161707
             *         parents: null
             *         productId: 329
             *         product: Kauf auf Rechnung (Demo)
             *         productRaw: Kauf auf Rechnung (Demo)
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 199
             *         currency: EUR
             *         created: 2014-05-08T16:43:27+02:00
             *         updated: 2014-05-08T16:43:27+02:00
             *         status: 1
             *         statusText: erstellt
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 199
             *             cleared: open
             *             status: 1
             *             statusText: erstellt
             *             statusSimple: 99
             *             statusTextSimple: null
             *             description: Test Order #1
             *             descriptionRaw: Test Order #1
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: null
             *             title: null
             *             forename: Joe
             *             surname: Black
             *         }
             *         tid: null
             *         paymentData: null
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: wzsatgchtpzo128480
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_BMXA9DMKM3UU5MBXDMUVNBR9HE9ZO7
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3172647
             *         parents: null
             *         productId: 21
             *         product: Kreditkarte Demo
             *         productRaw: Kreditkarte Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 1160
             *         currency: EUR
             *         created: 2014-05-13T18:54:42+02:00
             *         updated: 2014-05-13T18:55:30+02:00
             *         status: 59
             *         statusText: Zahlung abgebrochen
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 1160
             *             cleared: open
             *             status: 59
             *             statusText: Zahlung abgebrochen
             *             statusSimple: 3
             *             statusTextSimple: null
             *             description: Bestellung vom 13.05.14|bei secupay Demo Shop xtc3|Bei Fragen TEL 035955755055
             *             descriptionRaw: Bestellung vom 13.05.14|bei secupay Demo Shop xtc3|Bei Fragen TEL 035955755055
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: null
             *             title: null
             *             forename: Joe
             *             surname: Black
             *         }
             *         tid: null
             *         paymentData: null
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: ctsymkitttao131724
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_26UE8T6SF7C3WQG6R2ZJ07R9HE9ZOJ
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3172653
             *         parents: null
             *         productId: 20
             *         product: Lastschrift Demo
             *         productRaw: Lastschrift Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 1160
             *         currency: EUR
             *         created: 2014-05-13T18:56:55+02:00
             *         updated: 2014-05-13T19:00:56+02:00
             *         status: 59
             *         statusText: Zahlung abgebrochen
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 1160
             *             cleared: open
             *             status: 59
             *             statusText: Zahlung abgebrochen
             *             statusSimple: 3
             *             statusTextSimple: null
             *             description: Bestellung vom 13.05.14|bei secupay Demo Shop xtc3|Bei Fragen TEL 035955755055
             *             descriptionRaw: Bestellung vom 13.05.14|bei secupay Demo Shop xtc3|Bei Fragen TEL 035955755055
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: null
             *             title: null
             *             forename: Joe
             *             surname: Black
             *         }
             *         tid: null
             *         paymentData: null
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: tqqihywzxaww131728
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_4CQ04R4SN7C7YW5ZB4KQ5DU9HG0BOY
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3180175
             *         parents: null
             *         productId: 21
             *         product: Kreditkarte Demo
             *         productRaw: Kreditkarte Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 199
             *         currency: EUR
             *         created: 2014-05-16T14:35:28+02:00
             *         updated: 2014-05-16T14:35:28+02:00
             *         status: 1
             *         statusText: erstellt
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 199
             *             cleared: open
             *             status: 1
             *             statusText: erstellt
             *             statusSimple: 99
             *             statusTextSimple: null
             *             description: Test Order #1
             *             descriptionRaw: Test Order #1
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: null
             *             title: null
             *             forename: Joe
             *             surname: Black
             *         }
             *         tid: null
             *         paymentData: null
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: qmddokhdkdiq133526
             *         referenceId:
             *         accrual: false
             *     }, class PaymentTransactionsProductModel {
             *         class BaseProductModel {
             *             object: payment.transactions
             *             id: PCI_8RA4YFERXBY6GXNZX57D2X09HG0CO0
             *         }
             *         platform: null
             *         merchant: class GeneralMerchantsProductModel {
             *             class BaseProductModel {
             *                 object: general.merchants
             *                 id: MRC_WVHJQFQ4JNVYNG5B55TYK748ZCHQP8
             *             }
             *             idOld: null
             *             type: 11
             *             user: class GeneralMerchantsUser {
             *                 object: null
             *                 id: null
             *                 name: Hans Test
             *                 companyname: Secupay Test-Shop
             *                 address: null
             *             }
             *             parent: null
             *             assignedBy: null
             *             invitedBy: null
             *             legalDetails: null
             *             checkoutOptions: null
             *             urls: null
             *             storeName: null
             *         }
             *         store: null
             *         transId: 3180180
             *         parents: null
             *         productId: 21
             *         product: Kreditkarte Demo
             *         productRaw: Kreditkarte Demo
             *         zahlungsmittelId: 0
             *         contractId: 49760
             *         amount: 199
             *         currency: EUR
             *         created: 2014-05-16T14:38:27+02:00
             *         updated: 2014-05-16T14:38:27+02:00
             *         status: 1
             *         statusText: erstellt
             *         incomingPaymentDate: null
             *         details: class PaymentTransactionsProductModelDetails {
             *             amount: 199
             *             cleared: open
             *             status: 1
             *             statusText: erstellt
             *             statusSimple: 99
             *             statusTextSimple: null
             *             description: Test Order #1
             *             descriptionRaw: Test Order #1
             *         }
             *         customer: class PaymentTransactionsProductModelCustomer {
             *             companyname: null
             *             salutation: null
             *             title: null
             *             forename: Joe
             *             surname: Black
             *         }
             *         tid: null
             *         paymentData: null
             *         storeName: null
             *         payoutDate: null
             *         invoiceNumber: null
             *         transactionHash: evzpjrogjjii133529
             *         referenceId:
             *         accrual: false
             *     }]
             * }
             */
        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());
        }
    }
}
