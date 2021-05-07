package org.astrosearcher.utilities;

import cds.savot.model.SavotVOTable;
import cds.savot.pull.SavotPullEngine;
import cds.savot.pull.SavotPullParser;
import org.astrosearcher.classes.constants.AppConfig;
import org.astrosearcher.classes.vizier.VizierRequestObject;
import org.astrosearcher.classes.vizier.VizierResponse;
import org.astrosearcher.classes.xmatch.CDSCrossmatchRequestObject;
import org.astrosearcher.enums.cds.vizier.VizierServices;
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

        if (AppConfig.DEBUG) {
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
    }

    public static VizierResponse findAllByPosition(SearchFormInput input) {

        String response = new VizierRequestObject(VizierServices.VIZIER_COORDINATES, input).send();

        if (AppConfig.DEBUG) {
            System.out.println("        Initializing SavotPullParser...");
        }

        SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                SavotPullEngine.FULL,
                "UTF-8");

        SavotVOTable vot = parser.getVOTable();

        if (isEmptyResponse(vot)) {
            return new VizierResponse();
        }

        return new VizierResponse(VizierServices.VIZIER_COORDINATES, vot.getResources());
    }

    public static VizierResponse findAllByCrossmatch(SearchFormInput input) {
        String response = new CDSCrossmatchRequestObject(input, "vizier:" + input.getVizierCat()).send();

        if (AppConfig.DEBUG) {
            System.out.println("        Initializing SavotPullParser...");
        }

        SavotPullParser parser = new SavotPullParser(new ByteArrayInputStream(response.getBytes()),
                SavotPullEngine.FULL,
                "UTF-8");

        SavotVOTable vot = parser.getVOTable();

        if (isEmptyResponse(vot)) {
            return new VizierResponse();
        }

        return new VizierResponse(VizierServices.VIZIER_CROSSMATCH, vot.getResources());
    }

    private static boolean isEmptyResponse(SavotVOTable vot) {

        if (AppConfig.DEBUG) {
            System.out.print("        Checking resources count... ");
            System.out.println(vot.getResources().getItemCount() + " resources found");
        }

        return vot.getResources().getItemCount() == 0;
    }
}
