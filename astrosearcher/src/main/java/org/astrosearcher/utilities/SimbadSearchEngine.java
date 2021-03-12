package org.astrosearcher.utilities;

import cds.savot.model.*;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.simbad.SimbadRequestObject;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;


public class SimbadSearchEngine {

    private static final String SIMBAD_URL  = "http://simbad.u-strasbg.fr/simbad/";

    private static final String ID_REQUEST  = "sim-id?";
    private static final String POS_REQUEST = "sim-coo?";


    // TODO: change return type, implement functionality
    public static void findAllById(String input) {
        String response = new SimbadRequestObject(input).send();


//        new ByteArrayInputStream(response.getBytes());
//        new InputStream(response);
        SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                SavotPullEngine.FULL,
                "UTF-8");
        SavotVOTable vot = parser.getVOTable();

        for (int resourceIndex = 0; resourceIndex < parser.getResourceCount(); resourceIndex++) {
            SavotResource currentResource = (SavotResource) vot.getResources().getItemAt(resourceIndex);

//            for (int fieldIndex = 0; fieldIndex < currentResource.getFieldSet(0).getItemCount(); fieldIndex++) {
//                System
//            }
            FieldSet fields = currentResource.getFieldSet(0);
//            SavotField field = fields.getItemAt(0);
//            System.out

            for (int tableIndex = 0; tableIndex < currentResource.getTableCount(); tableIndex++ ) {
                TRSet rows = currentResource.getTRSet(tableIndex);

                for (int rowIndex = 0; rowIndex < rows.getItemCount(); rowIndex++ ) {
                    TDSet columns = rows.getTDSet(rowIndex);

                    for (int columnIndex = 0; columnIndex < columns.getItemCount(); columnIndex++) {
                        System.out.println(((SavotField)fields.getItemAt(columnIndex)).getId() + ":   " + columns.getContent(columnIndex));
                    }
                }
            }
        }

//        System.out.println("Simbad response [By ID]: " + response);
    }

    public static void findAllByPosition(PositionInput input) {
        // TODO: implement whole functionality - get response from Simbad

        // code for testing of two methods only
        SimbadRequestObject obj = new SimbadRequestObject(input);
        try {
            obj.getConnectionURL();
            obj.getParamsAsBytes();
        } catch (Exception e) {}

        // lets test response
        String response = obj.send();
    }

}
