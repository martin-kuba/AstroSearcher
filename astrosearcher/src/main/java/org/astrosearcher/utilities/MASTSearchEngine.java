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

        ResponseForReqByPos resp;
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

            // TODO: check whether exception should be thrown here
            if (responseCode != HttpURLConnection.HTTP_OK) {
                return null;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            resp = new Gson().fromJson(response.toString(), ResponseForReqByPos.class);
            TableFromReqByPos ret = resp.getTables().get(0);
            ret.initMapper();

            connection.disconnect();
            return ret;

        } catch (MalformedURLException me) {
            //something
        } catch (ProtocolException pe) {
            // protocol exception caught
        } catch (IOException ioe) {
            // IO exception caught
        }

        return null;
    }

}