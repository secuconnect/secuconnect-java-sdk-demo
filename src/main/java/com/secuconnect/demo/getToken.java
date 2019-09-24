package com.secuconnect.demo;

import io.secuconnect.client.ApiException;
import io.secuconnect.client.auth.Authenticator;
import io.secuconnect.client.auth.credentials.OAuthClientCredentials;
import io.secuconnect.client.auth.tokens.AccessToken;
import io.secuconnect.client.auth.tokens.OAuthClientToken;

public class getToken {
    public static String accessToken;

    public static void main(String[] args) throws ApiException {
        try {
            // Set config
            Authenticator authenticator = new Authenticator(
                    new OAuthClientCredentials(
                            "...",
                            "..."
                    )
            );

            // Get oauth access token
            AccessToken token = authenticator.getToken();
            if (token instanceof OAuthClientToken) {
                accessToken = ((OAuthClientToken) token).getAccessToken();
                System.out.println("AccessToken: " + accessToken);

                /*
                 * Sample output:
                 * ==============
                 * AccessToken: fuun4c0n49u49ir9e57t5pabc1
                 */

            }
        } catch (ApiException e) {
            e.printStackTrace();
            System.out.println("ERROR: " + e.getResponseBody());

            /*
             * Sample output:
             * ==============
             * io.secuconnect.client.ApiException: Bad Request
             *      at io.secuconnect.client.ApiClient.handleResponse(ApiClient.java:1006)
             *      at io.secuconnect.client.ApiClient.execute(ApiClient.java:923)
             *      at io.secuconnect.client.auth.Authenticator.getTokenFromApi(Authenticator.java:61)
             *      at io.secuconnect.client.auth.Authenticator.getToken(Authenticator.java:105)
             *      at com.secuconnect.demo.getToken.main(getToken.java:24)
             *
             * ERROR: {"error":"invalid_client","error_description":"The client credentials are invalid"}
             */

            throw e;
        }
    }
}
