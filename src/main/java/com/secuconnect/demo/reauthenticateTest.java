package com.secuconnect.demo;

import io.secuconnect.client.ApiClient;
import io.secuconnect.client.ApiException;
import io.secuconnect.client.api.PaymentTransactionsApi;
import io.secuconnect.client.auth.Authenticator;
import io.secuconnect.client.auth.credentials.OAuthClientCredentials;
import io.secuconnect.client.auth.tokens.AccessToken;
import io.secuconnect.client.auth.tokens.OAuthClientToken;
import io.secuconnect.client.model.PaymentTransactionsList;


public class reauthenticateTest {
    public static String accessToken;

    public static void main(String[] args) {
        try {
            // Set config
            Authenticator authenticator = new Authenticator(
                    new OAuthClientCredentials(
                            "...",
                            "..."
                    )
            );

            PaymentTransactionsApi testAPI = new PaymentTransactionsApi();
            String query = "incoming_payment_date:*";
            String sort = "incoming_payment_date:desc";

            ApiClient authClient3 = new ApiClient();
            authenticator.setApiClient(authClient3, ".../");
            ApiClient apiClient3 = new ApiClient();
            authenticator.setApiClient(apiClient3, ".../api/v2");
            ApiClient authClient6 = new ApiClient();
            authenticator.setApiClient(authClient6, ".../");
            ApiClient apiClient6 = new ApiClient();
            authenticator.setApiClient(apiClient6, ".../api/v2");

            authenticator.setApiClient(authClient3);

            // check that the login works for server 1
            AccessToken token = authenticator.getToken();
            if (token instanceof OAuthClientToken) {
                accessToken = ((OAuthClientToken) token).getAccessToken();
                System.out.println("AccessToken: " + accessToken);
            }

//            // should work
//            System.out.println("should work");
//            apiClient3.setAccessToken(accessToken);
//            testAPI.setApiClient(apiClient3);
//            PaymentTransactionsList response = testAPI.getAll(
//                    10,
//                    null,
//                    null,
//                    query,
//                    sort
//            );
//
//            System.out.println(response);
//
//            // should fail
//            System.out.println("should fail");
//            apiClient3.setAccessToken(accessToken);
//            testAPI.setApiClient(apiClient6);
//
//            response = testAPI.getAll(
//                    10,
//                    null,
//                    null,
//                    query,
//                    sort
//            );


            authenticator.setApiClient(authClient6);
            authenticator.reauthenticate();

            // check that the login works for server 2
            token = authenticator.getToken();
            if (token instanceof OAuthClientToken) {
                accessToken = ((OAuthClientToken) token).getAccessToken();
                System.out.println("AccessToken: " + accessToken);
            }

            // should work
            System.out.println("should work");
            apiClient6.setAccessToken(accessToken);
            testAPI.setApiClient(apiClient6);
            PaymentTransactionsList response = testAPI.getAll(
                    10,
                    null,
                    null,
                    query,
                    sort
            );
            System.out.println(response);

            // should fail
            System.out.println("should fail");
            apiClient3.setAccessToken(accessToken);
            testAPI.setApiClient(apiClient3);

            testAPI.getAll(
                    10,
                    null,
                    null,
                    query,
                    sort
            );

        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
