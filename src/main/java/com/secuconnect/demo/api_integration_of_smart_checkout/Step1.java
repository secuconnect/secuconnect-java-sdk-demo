package com.secuconnect.demo.api_integration_of_smart_checkout;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.demo.Globals;

/**
 * API Integration of Smart Checkout
 *
 * Step 1: Authenticate with OAuth 2.0
 *
 * @see <a href="https://developer.secuconnect.com/integration/API_Integration_of_Smart_Checkout.html">API Integration of Smart Checkout</a>
 */
public class Step1 {
    public static String accessToken;

    public static void main(String[] args) {
        try {
            // init env
            Environment env = Environment.getGlobalEnv().setCredentials(Globals.O_AUTH_CLIENT_CREDENTIALS);

            // enable for using the live environment
//            env.useLivePaths();

            // The authenticate() method will be called automatically on the first API call, so this optional:
            accessToken = env.authenticate();
            System.out.println(accessToken);
            /*
             * Sample output:
             * ==============
             * fdjt28qq8qr7b0s9epcvesnq33
             */
        } catch (ApiException e) {
            e.printStackTrace();

            // show the error message from the api
            System.out.println("ERROR: " + e.getResponseBody());
            /*
             * Sample output:
             * ==============
             * ERROR: {"error":"invalid_client","error_description":"The client credentials are invalid"}
             */
        }
    }
}
