package org.astrosearcher.utilities;

import cds.savot.model.*;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;
import org.astrosearcher.classes.simbad.SimbadRequestObject;
import org.astrosearcher.classes.simbad.SimbadResponse;
import org.astrosearcher.classes.simbad.SimbadResponseForId;
import org.astrosearcher.enums.simbad.SimbadServices;
import org.astrosearcher.models.SearchFormInput;

import java.io.ByteArrayInputStream;

/**
 * Class serves as inter-level between general SearchEngine class and ConnectionUtils class.
 *
 * Class provides usage of RequestObject needed for constructing and sending of request as well as parsing of response
 * acquired from Simbad (cds) server.
 *
 * @author Ľuboslav Halama
 */
public class SimbadSearchEngine {

    // TODO: change return type, implement functionality
    public static SimbadResponse findAllById(SearchFormInput input) {
        String response = new SimbadRequestObject(SimbadServices.SIMBAD_ID, input).send();

        if (response == null) {
            System.out.println("no response acquired");
            return new SimbadResponse();
        }

        // TODO: rework for proper exception catching
        try {
            SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                    SavotPullEngine.FULL,
                    "UTF-8");
            System.out.println("    -> Parser successfuly initialized!");
            SavotVOTable vot = parser.getVOTable();
            System.out.println("    -> VOT obtained from parser!");

            System.out.println("response: " + response);
            TRSet ts = ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0);
            System.out.println("    -> TRSET obtained!");
//            for (Object row : ts.getItems())


            System.out.println("Returning response...");
//            System.out.println("    Fieldset size: " + ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItemCount());
//            List<SavotField> test = ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems();
//            System.out.println("    List of fields size: " + test.size());

            return new SimbadResponse(
                    SimbadServices.SIMBAD_ID,
                    ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
                    ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
            );
        } catch (Exception e) {
            System.out.println("..::!!!   Exception caught (Simbad - ID)  !!!::..");
            System.out.println("Message: " + e.getMessage());
            System.out.println();
            System.out.println("Exception" + e);
            System.out.println();
            return new SimbadResponse();
        }

    }

    public static SimbadResponse findAllByPosition(SearchFormInput input) {
        // TODO: implement whole functionality - get response from Simbad

        String response = new SimbadRequestObject(SimbadServices.SIMBAD_COORDINATES, input).send();

        if (response == null) {
            System.out.println("no response acquired");
            return new SimbadResponse();
        }

        // TODO: rework for proper exception catching
        try {
            SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                    SavotPullEngine.FULL,
                    "UTF-8");
            SavotVOTable vot = parser.getVOTable();

            TRSet ts = ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0);
//            for (Object row : ts.getItems())


            System.out.println("Returning response...");
//            System.out.println("    Fieldset size: " + ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItemCount());
//            List<SavotField> test = ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems();
//            System.out.println("    List of fields size: " + test.size());

            return new SimbadResponse(
                    SimbadServices.SIMBAD_COORDINATES,
                    ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
                    ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
            );
        } catch (Exception e) {
            System.out.println("..::!!!   Exception caught   !!!::..");
            System.out.println("Message: " + e.getMessage());
            System.out.println();
            System.out.println("Exception" + e);
            System.out.println();
            return new SimbadResponse();
        }
    }

}
