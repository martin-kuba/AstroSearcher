package org.astrosearcher.utilities;

import cds.savot.model.SavotResource;
import cds.savot.model.SavotVOTable;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.vizier.VizierRequestObject;
import org.astrosearcher.classes.vizier.VizierResponse;
import org.astrosearcher.enums.vizier.VizierServices;
import org.astrosearcher.models.SearchFormInput;

import java.io.ByteArrayInputStream;

/**
 * Class serves as inter-level between general SearchEngine class and ConnectionUtils class.
 *
 * Class provides usage of RequestObject needed for constructing and sending of request as well as parsing of response
 * acquired from Vizier (cds) server.
 *
 * @author Ľuboslav Halama
 */
public class VizierSearchEngine {

    public static VizierResponse findAllById(SearchFormInput input) {

        String response = new VizierRequestObject(VizierServices.VIZIER_ID, input).send();

        if (Limits.DEBUG) {
            System.out.println("        Initializing SavotPullParser...");
        }

        SavotPullParser parser;
        try {
            parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                    SavotPullEngine.FULL,
                    "UTF-8");
        } catch (Exception e) {
            System.out.println("    Exception caught while initializing vot parser");
            return new VizierResponse();
        }

        SavotVOTable vot = parser.getVOTable();

        if (isEmptyResponse(vot)) {
            return new VizierResponse();
        }

        return new VizierResponse(VizierServices.VIZIER_ID, vot.getResources());

//        return new VizierResponse(VizierServices.VIZIER_ID,
//                ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
//                ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
//        );
    }

    public static VizierResponse findAllByPosition(SearchFormInput input) {

        String response = new VizierRequestObject(VizierServices.VIZIER_COORDINATES, input).send();

        if (Limits.DEBUG) {
            System.out.println("        Initializing SavotPullParser...");
        }

        SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                SavotPullEngine.FULL,
                "UTF-8");
//        System.out.println("done");

        SavotVOTable vot = parser.getVOTable();

        if (isEmptyResponse(vot)) {
            return new VizierResponse();
        }

        return new VizierResponse(VizierServices.VIZIER_COORDINATES, vot.getResources());

//        return new VizierResponse(VizierServices.VIZIER_COORDINATES,
//                ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
//                ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
//        );
    }

    private static boolean isEmptyResponse(SavotVOTable vot) {

        if (Limits.DEBUG) {
            System.out.print("        Checking resources count... ");
            System.out.println(vot.getResources().getItemCount() + " resources found");
        }

        return vot.getResources().getItemCount() == 0;
//        if (vot.getResources().getItemCount() == 0 ) {
//            return true;
//        }

        // TODO: rework so these two IFs can be removed (moved to VizierResponse class)

//        if (((SavotResource)vot.getResources().getItemAt(0)).getTableCount() == 0 ) {
//            return true;
//        }
//
//        return ((SavotResource) vot.getResources().getItemAt(0)).getData(0) == null;
    }
}
