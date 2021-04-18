package org.astrosearcher.classes.xmatch;

/*import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.mime.HttpMultipartMode;
import org.apache.hc.client5.http.entity.mime.StringBody;*/

/*
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.*;
*/

import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.mime.*;
import org.apache.http.entity.mime.content.*;



import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.cds.SimbadConstants;
import org.astrosearcher.classes.constants.cds.XMatchConstats;
import org.astrosearcher.classes.simbad.SimbadArg;
        import org.astrosearcher.enums.cds.simbad.SimbadServices;
import org.astrosearcher.enums.cds.xmatch.XMatchArgType;
import org.astrosearcher.models.SearchFormInput;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
        import java.util.ArrayList;
import java.util.List;

public class CDSCrossmatchRequestObject extends RequestObject {

    private SimbadServices service;
    private List<SimbadArg> args = new ArrayList<>();
    private MultipartFile file;

    private MultipartEntity entity = new MultipartEntity( HttpMultipartMode.BROWSER_COMPATIBLE );

    public CDSCrossmatchRequestObject(SearchFormInput input, String catalogue) {
        this.service = SimbadServices.SIMBAD_CROSSMATCH;
        this.file = input.getFile();

        try {
            entity.addPart(
                    XMatchArgType.REQUEST_TYPE.toString(),
                    new StringBody(SimbadServices.SIMBAD_CROSSMATCH.toString())
            );
            entity.addPart(
                    XMatchArgType.MAX_DISTANCE.toString(),
                    new StringBody(String.valueOf(Math.min(input.getRadiusInArcseconds(), XMatchConstats.MAX_RADIUS_ARCSEC)))
            );
            entity.addPart(XMatchArgType.RESPONSE_FORMAT.toString() , new StringBody(XMatchConstats.FORMAT));
            entity.addPart(XMatchArgType.CATALOG1_RA_COL.toString() , new StringBody(XMatchConstats.RA_COLUMN));
            entity.addPart(XMatchArgType.CATALOG1_DEC_COL.toString(), new StringBody(XMatchConstats.DEC_COLUMN));
            entity.addPart(
                    XMatchArgType.MAX_RECORDS.toString(),
                    new StringBody(String.valueOf(input.getPagesize()))
            );

            //entity.addPart( "cat1", new FileBody(tempFile));  // Original
            entity.addPart(
                    XMatchArgType.CATALOG1.toString(),
                    new InputStreamBody(file.getInputStream(), file.getOriginalFilename())
            );
            entity.addPart(XMatchArgType.CATALOG2.toString(), new StringBody(catalogue));
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String send() {

        StringBuilder responseData = new StringBuilder();

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(XMatchConstats.CONNECTION_URL);

        try {
            httpPost.setEntity(entity);
            HttpResponse response = httpClient.execute(httpPost);

            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String inputLine;

            if ( Limits.DEBUG ) {
                System.out.println("            Reading response...");
            }

            while ((inputLine = in.readLine()) != null) {
                responseData.append(inputLine);
            }
            in.close();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return responseData.toString();
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
        return new URL(SimbadConstants.CDS_CROSSMTACH_URL);
    }

    @Override
    public byte[] getParamsAsBytes() {
        StringBuilder params = new StringBuilder();
        for (SimbadArg arg : args) {
            params.append(arg.toString());
        }

        if (Limits.DEBUG) {
            System.out.println("            params: " + params);
        }

        return (params.toString()).getBytes();
    }
}
