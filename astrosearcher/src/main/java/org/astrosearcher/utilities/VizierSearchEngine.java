package org.astrosearcher.utilities;

import cds.savot.model.SavotResource;
import cds.savot.model.SavotVOTable;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;
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

    public static VizierResponse findAllByPosition(SearchFormInput input) {

//        System.out.print("\n\n    Sending Vizier request ... ");
        String response = new VizierRequestObject(VizierServices.VIZIER_COORDINATES, input).send();
//        System.out.println("received");

//        System.out.println("    Response processing");
//        System.out.print("        parser initialization with response ... ");
        SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                SavotPullEngine.FULL,
                "UTF-8");
//        System.out.println("done");

        SavotVOTable vot = parser.getVOTable();

        return new VizierResponse(VizierServices.VIZIER_COORDINATES,
                ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
                ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
        );
    }
}
