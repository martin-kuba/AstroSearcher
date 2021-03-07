package org.astrosearcher.utilities;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.mast.*;
import org.astrosearcher.classes.mast.MastRequestObject;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByName;
import org.astrosearcher.classes.mast.services.caom.cone.ResponseForReqByPos;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MASTSearchEngine {

    private static final String NO_PARAMS_URL = "https://mast.stsci.edu/api/v0/invoke?";
    private static final String REQUEST_STR   = "request=";

    public static String sendRequest(MastRequestObject obj) {
        HttpURLConnection connection;
        StringBuilder response = new StringBuilder();

        try {
            connection = (HttpURLConnection) (new URL(NO_PARAMS_URL)).openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            OutputStream os = connection.getOutputStream();
            os.write((REQUEST_STR + obj.toJson()).getBytes());
            os.flush();
            os.close();

            int responseCode = connection.getResponseCode();

            // TODO: check whether exception should be thrown here
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            connection.disconnect();
        } catch (Exception e) {
        }
        return response.toString();
    }

    public static List<PositionInput> resolvePositionByNameOrID(String input) {

        List<PositionInput> resolved = new ArrayList<>();
        String response = sendRequest(new MastRequestObject(Services.MAST_NAME_LOOKUP, input));

        if (response == null) {
            return null;
        }

        double radius;
        // in case parsing would not be valid
        try {
            ResponseForReqByName resp = new Gson().fromJson(response, ResponseForReqByName.class);

            for (JsonObject obj : resp.getResolvedCoordinate()) {

                // TODO: create new general class for constants like this radius
                radius = 0.2;  // default radius;
                if ( obj.has("radius") && !obj.get("radius").isJsonNull() ) {
                    radius = obj.get("radius").getAsDouble();
                }

                resolved.add(new PositionInput(
                        obj.get("ra").getAsDouble(),
                        obj.get("decl").getAsDouble(),
                        radius
                ));
            }
        } catch (Exception e) {
//            System.out.println("Exception caught:\n" + e);
        }

        return resolved;
    }


    public static ResponseForReqByPos findAllByPosition(double ra, double dec, double radius) {

        String response = sendRequest(new MastRequestObject(Services.MAST_CAOM_CONE, ra, dec, radius));

        if (response == null) {
            return null;
        }

        // TODO: determine exception for catching precisely, not general Exception...

        ResponseForReqByPos resp;
        TableFromReqByPos ret;
        // in case parsing would not be valid
        try {
            resp = new Gson().fromJson(response, ResponseForReqByPos.class);
        } catch (Exception e) {
            return null;
        }

        return resp;
    }

}