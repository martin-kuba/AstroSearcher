package org.astrosearcher.classes.xmatch;

import com.google.gson.Gson;

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
import org.astrosearcher.classes.constants.SimbadConstants;
import org.astrosearcher.classes.simbad.CrossmatchFile;
import org.astrosearcher.classes.simbad.SimbadArg;
import org.astrosearcher.enums.simbad.SimbadArgType;
import org.astrosearcher.enums.simbad.SimbadServices;
import org.astrosearcher.models.SearchFormInput;
import org.astrosearcher.utilities.ConnectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.MalformedURLException;
import java.net.MulticastSocket;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class CDSCrossmatchRequestObject extends RequestObject {

    private SimbadServices service;
    private List<SimbadArg> args = new ArrayList<>();
    private MultipartFile file;

    private double radius;
    private int    maxRecords;

    public CDSCrossmatchRequestObject(SearchFormInput input, String catalogue) {
        this.service = SimbadServices.SIMBAD_CROSSMATCH;
        this.file = input.getFile();


        args.add(new SimbadArg(SimbadArgType.REQUEST_TYPE, SimbadServices.SIMBAD_CROSSMATCH));

        radius = input.getRadius() > 0.05 ? 180 : input.getRadius()*3600;
        maxRecords = input.getPagesize();

        args.add(new SimbadArg(SimbadArgType.MAX_DISTANCE, radius ));
        args.add(new SimbadArg(SimbadArgType.MAX_RECORDS, maxRecords));

        args.add(new SimbadArg(SimbadArgType.RESPONSE_FORMAT, "votable"));

        args.add(new SimbadArg(SimbadArgType.CATALOG2, catalogue));


        args.add(new SimbadArg(SimbadArgType.CATALOG1_RA_COL, SimbadConstants.DEFAULT_RA_COLUMN_NAME));
        args.add(new SimbadArg(SimbadArgType.CATALOG1_DEC_COL, SimbadConstants.DEFAULT_DEC_COLUMN_NAME));

        Gson gson = new Gson();
        args.add(new SimbadArg(SimbadArgType.CATALOG1, gson.toJson(new CrossmatchFile(input.getFile()))));
    }

    @Override
    public String send() {
//        if ( Limits.DEBUG ) {
//            System.out.println("\n    >>> Starting to query CDS Xmatch (Simbad)...");
//        }
//
//        return ConnectionUtils.sendRequest(this);

        StringBuilder responseData = new StringBuilder();

        HttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost("http://cdsxmatch.u-strasbg.fr/xmatch/api/v1/sync");
        MultipartEntity entity = new MultipartEntity( HttpMultipartMode.BROWSER_COMPATIBLE );//HttpMultipartMode.BROWSER_COMPATIBLE );

        try {
            entity.addPart( "request", new StringBody("xmatch"));
            entity.addPart( "distMaxArcsec", new StringBody(String.valueOf(radius)));
            entity.addPart( "RESPONSEFORMAT", new StringBody("votable"));
            entity.addPart( "colRA1", new StringBody("ra"));
            entity.addPart( "colDec1", new StringBody("dec"));
            entity.addPart("maxrec", new StringBody(String.valueOf(maxRecords)));

//            entity.addPart( "cat1", new FileBody(new File("posList.csv")));
//            entity.addPart( "cat1", new FileBody(file.getResource().getFile()));

            // create temporary file and write into it content of uploaded file
            File tempFile = Files.createTempFile(file.getOriginalFilename(), ".csv").toFile();
            FileOutputStream fos = new FileOutputStream(tempFile);
            fos.write(file.getBytes());
            fos.close();

            entity.addPart( "cat1", new FileBody(tempFile));
            entity.addPart( "cat2", new StringBody("simbad"));

            httpPost.setEntity(entity);

            HttpResponse response = httpClient.execute(httpPost);

//            BufferedInputStream in = new BufferedInputStream(
//                    response.getEntity().getContent());

            BufferedReader in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String inputLine;

            if ( Limits.DEBUG ) {
                System.out.println("            Reading response...");
            }

            while ((inputLine = in.readLine()) != null) {
                responseData.append(inputLine);
            }
            in.close();
            tempFile.delete();


//            BufferedOutputStream out = new BufferedOutputStream(
//                    new FileOutputStream(new File("result.vot")));
//            // copy result to file
//            byte[] buffer = new byte[1024];
//            int len = in.read(buffer);
//            while (len != -1) {
//                out.write(buffer, 0, len);
//                len = in.read(buffer);
//            }
//            out.close();

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
