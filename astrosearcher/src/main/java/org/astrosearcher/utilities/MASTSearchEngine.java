package org.astrosearcher.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import org.astrosearcher.classes.AstroObject;
import org.astrosearcher.classes.mast.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MASTSearchEngine {

    private static final String NO_PARAMS_URL = "https://mast.stsci.edu/api/v0/invoke?";
    private static final String REQUEST_STR   = "request=";

    private static final String SERVICE_LOOKUP      = "Mast.Name.Lookup";

    public static TableFromReqByPos findAllByPosition(double ra, double dec, double radius) {
//        List<AstroObject> results = new ArrayList<>();

        // TODO: extract search parameters from searchInput
//        double ra  = 254.28746;  //default test value
//        double dec = -4.09933;   //default test value

//        double ra  = 6.752569;      // Sirius
//        double dec = -16.71314;     // Sirius

        ResponseForReqByPos resp;

        // TODO: create valid connection + request, send request, receive response
        HttpURLConnection connection;
        try {
            connection = (HttpURLConnection) (new URL(NO_PARAMS_URL)).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write(( REQUEST_STR +
                        (new MastRequestObject(Services.MAST_CAOM_CONE.toString(), ra, dec, radius)).toJson() )
                    .getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();
            System.out.println("POST Response Code :: " + responseCode); // 200 ==> OK

            // TODO: check whether exception should be thrown here
            if (responseCode != HttpURLConnection.HTTP_OK) {
                System.out.println("POST Request failed! Throwing exception...");
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("Server reponse :: " + response);
//            System.out.

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.print("From JSON: ");

            Gson gson = new Gson();

            resp = new Gson().fromJson(response.toString(), ResponseForReqByPos.class);
            System.out.println("parsed!");
            System.out.println("name:" + resp.getName());
            System.out.println("tables:\n" + resp.getTables());

            System.out.println("columns:\n" + gson.toJson(resp.getTables().get(0).getColumns()));
//            System.out.println("rows:\n" + resp.getTables().get(0).getRows());
            //TODO: Extract data from response, store it into 'results'
            return resp.getTables().get(0);

        } catch (MalformedURLException me) {
            //something
        } catch (ProtocolException pe) {
            // protocol exception caught
        } catch (IOException ioe) {
            // IO exception caught
        }

        // TODO: process response

//        return resp.getTables().get(0);
        return null;
    }

}

//        System.out.println("JSON: " + new MastRequestObject(SERVICE_CONE_SEARCH, 254.28746, -4.09933)
//                .toJson()
//        );