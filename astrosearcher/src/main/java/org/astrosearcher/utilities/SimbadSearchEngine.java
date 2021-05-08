package org.astrosearcher.utilities;

import cds.savot.model.*;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;

import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.classes.constants.cds.SimbadConstants;
import org.astrosearcher.classes.simbad.SimbadMeasurementsTable;
import org.astrosearcher.classes.simbad.SimbadRequestObject;
import org.astrosearcher.classes.simbad.SimbadResponse;
import org.astrosearcher.classes.xmatch.CDSCrossmatchRequestObject;
import org.astrosearcher.enums.cds.simbad.SimbadServices;
import org.astrosearcher.models.SearchFormInput;

import java.io.ByteArrayInputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Class serves as inter-level between general SearchEngine class and ConnectionUtils class.
 *
 * Class provides usage of RequestObject needed for constructing and sending of request as well as parsing of response
 * acquired from Simbad (cds) server.
 *
 * @author Ľuboslav Halama
 */
public class SimbadSearchEngine {

    private static boolean timeQuantumUsed = false;

    public synchronized static boolean isTimeQuantumUsed() {
        return timeQuantumUsed;
    }

    public synchronized static void setTimeQuantum(boolean flag) {
        timeQuantumUsed = flag;
        if (AppConfig.DEBUG_SCHEDULE) {
            if (flag) {
                System.out.println("    " + LocalTime.now() + " ::: [ SIMBAD ]           : Time Quantum used");
            } else {
                System.out.println("    " + LocalTime.now() + " ::: [ SIMBAD ]           : Time Quantum freed");
            }
        }
    }

    // TODO: change return type, implement functionality
    public static SimbadResponse findAllById(SearchFormInput input) {
        String response = new SimbadRequestObject(SimbadServices.SIMBAD_ID, input).send();

        if (response == null) {
            return new SimbadResponse();
        }

        // TODO: rework for proper exception catching
        try {
            SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                    SavotPullEngine.FULL,
                    "UTF-8");
            SavotVOTable vot = parser.getVOTable();

            if (isEmptyResponse(vot)) {
                return new SimbadResponse();
            }

            return new SimbadResponse(
                    SimbadServices.SIMBAD_ID,
                    ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
                    ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
            );
        } catch (Exception e) {
            if (AppConfig.DEBUG) {
                System.out.println("..::!!!   Exception caught (Simbad - ID)  !!!::..");
                System.out.println("Message: " + e.getMessage());
                System.out.println();
                System.out.println("Exception" + e);
                System.out.println();
            }
            return new SimbadResponse();
        }

    }

    public static SimbadResponse findAllByPosition(SearchFormInput input) {
        // TODO: implement whole functionality - get response from Simbad

        String response = new SimbadRequestObject(SimbadServices.SIMBAD_COORDINATES, input).send();

        if (response == null) {
            return new SimbadResponse();
        }

        // TODO: rework for proper exception catching
        try {
            SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                    SavotPullEngine.FULL,
                    "UTF-8");
            SavotVOTable vot = parser.getVOTable();

            if (isEmptyResponse(vot)) {
                return new SimbadResponse();
            }

            return new SimbadResponse(
                    SimbadServices.SIMBAD_COORDINATES,
                    ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
                    ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
            );
        } catch (Exception e) {
            if (AppConfig.DEBUG) {
                System.out.println("..::!!!   Exception caught   !!!::..");
                System.out.println("Message: " + e.getMessage());
                System.out.println();
                System.out.println("Exception" + e);
                System.out.println();
            }
            return new SimbadResponse();
        }
    }

    public static SimbadResponse findAllByCrossmatch(SearchFormInput input) {
        String response = new CDSCrossmatchRequestObject(input, "simbad").send();

        if (response == null) {
            return new SimbadResponse();
        }

        try {
            SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                    SavotPullEngine.FULL,
                    "UTF-8");
            SavotVOTable vot = parser.getVOTable();

            if (isEmptyResponse(vot)) {
                return new SimbadResponse();
            }

            return new SimbadResponse(
                    SimbadServices.SIMBAD_CROSSMATCH,
                    ((SavotResource) vot.getResources().getItemAt(0)).getFieldSet(0).getItems(),
                    ((SavotResource) vot.getResources().getItemAt(0)).getTRSet(0).getItems()
            );
        } catch (Exception e) {
            if (AppConfig.DEBUG) {
                System.out.println("..::!!!   Exception caught (Simbad - ID)  !!!::..");
                System.out.println("Message: " + e.getMessage());
                System.out.println();
                System.out.println("Exception" + e);
                System.out.println();
            }
            return new SimbadResponse();
        }
    }

    public static List<SimbadMeasurementsTable> findAllMeasurementsById(SearchFormInput input) {
        List<SimbadMeasurementsTable> measurements = new ArrayList<>();
        input.setSimbadFormat(SimbadConstants.ASCII_FORMAT);

        String response = new SimbadRequestObject(SimbadServices.SIMBAD_ID, input, input.getSimbadFormat()).send();

        if (response == null) {
            return measurements;
        }

        return new SimbadASCIIParser().parseForMeasurements(response);
    }

    private static boolean isEmptyResponse(SavotVOTable vot) {
        if (vot.getResources().getItemCount() == 0 ) {
            return true;
        }

        if (((SavotResource)vot.getResources().getItemAt(0)).getTableCount() == 0 ) {
            return true;
        }

        return ((SavotResource) vot.getResources().getItemAt(0)).getData(0) == null;
    }

}
