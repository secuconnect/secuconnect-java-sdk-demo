package com.secuconnect.demo.payment_with_smart_checkout;

import com.secuconnect.client.ApiException;
import com.secuconnect.client.Environment;
import com.secuconnect.demo.Globals;

/**
 * Payment with Smart Checkout
 *
 * Step 1: Authenticate with OAuth 2.0
 *
 * @see <a href="https://developer.secuconnect.com/integration/Payment_with_Smart_Checkout.html">Payment with Smart Checkout</a>
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
             * j9o39tmgb4j7gd76upautp1e56
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
