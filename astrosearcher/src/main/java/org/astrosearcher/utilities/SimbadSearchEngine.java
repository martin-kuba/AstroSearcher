package org.astrosearcher.utilities;

import cds.savot.model.*;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;
import org.astrosearcher.classes.PositionInput;
import org.astrosearcher.classes.mast.MastRequestObject;
import org.astrosearcher.classes.mast.MastServices;
import org.astrosearcher.classes.simbad.SimbadRequestObject;
import org.astrosearcher.classes.simbad.SimbadServices;
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
    public static void findAllById(SearchFormInput input) {
        String response = new SimbadRequestObject(SimbadServices.SIMBAD_ID, input).send();


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

    public static void findAllByPosition(SearchFormInput input) {
        // TODO: implement whole functionality - get response from Simbad

        String response = new SimbadRequestObject(SimbadServices.SIMBAD_COORDINATES, input).send();
    }

}
