package org.astrosearcher.utilities;

import org.astrosearcher.classes.RequestObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

public class ConnectionUtils {

    public static String sendRequest(RequestObject obj) {

        StringBuilder responseData = new StringBuilder();

        try {
            HttpURLConnection connection = (HttpURLConnection) obj.getConnectionURL().openConnection();
            connection.setRequestMethod("POST");

            // set request parameters
            connection.setDoOutput(true);
            OutputStream os = connection.getOutputStream();
            os.write(obj.getParamsAsBytes());
            os.flush();
            os.close();

            // Check response code
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                return null;
            }

            // read response data and store them into 'responseData'
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                responseData.append(inputLine);
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
