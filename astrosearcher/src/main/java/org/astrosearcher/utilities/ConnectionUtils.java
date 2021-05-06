package org.astrosearcher.utilities;

import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.classes.constants.Limits;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

/**
 * Class provides general functionality for sending requests and retrieving responses from servers.
 *
 * @author Ľuboslav Halama
 */
public class ConnectionUtils {

    public static String sendRequest(RequestObject obj) {

        StringBuilder responseData = new StringBuilder();

        try {
            if ( Limits.DEBUG ) {
                System.out.println("            Opening connection ( " + obj.getConnectionURL().toString() + " )...");
            }

            HttpURLConnection connection = (HttpURLConnection) obj.getConnectionURL().openConnection();
            connection.setRequestMethod("POST");

            if ( Limits.DEBUG ) {
                System.out.println("            Sending parameters...");
            }

            // set request parameters
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(obj.getParamsAsBytes());
            os.flush();
            os.close();

            // Check response code
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                if (Limits.DEBUG) {
                    System.err.println("            RESPONSE CODE:  " + connection.getResponseCode());
                    System.err.println("            RESPONSE MESSAGE:  " + connection.getResponseMessage());
                }
                return null;
            }

            // read response data and store them into 'responseData'
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            if ( Limits.DEBUG ) {
                System.out.println("            Reading response...");
            }

            while ((inputLine = in.readLine()) != null) {
                responseData.append(inputLine);
                responseData.append('\n');
            }
            in.close();

            // after all the work is done, we have to close connection
            connection.disconnect();

        } catch (Exception e) {
            return null;
        }

        return responseData.toString();
    }
}
