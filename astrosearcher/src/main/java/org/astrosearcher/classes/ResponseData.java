package org.astrosearcher.classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.astrosearcher.classes.mast.MastResponse;
import org.astrosearcher.classes.simbad.SimbadResponse;
import org.astrosearcher.classes.vizier.VizierResponse;


/**
 * Stores and provides access to responses from three primary services (Mast, Simbad, Vizier)
 *
 * Constructors -> No arguments contructor:
 *                     This class sets non-null empty responses for each of the services by default,
 *                     thus only empty constructor is needed.
 *              -> generated with Lombok
 *
 * Getters      -> Getters provide access to each response
 *              -> generated with Lombok
 *
 * Setters      -> In order to set response for corresponding service, use provided setters
 *              -> generated with Lombok
 *
 * Checking     -> There are 4 methods provided for checking the responses:
 *                     1.) containsMastResponse()   -> signals whether there was anything acquired from **_ Mast _**
 *                     2.) containsSimbadResponse() -> signals whether there was anything acquired from **_ Simbad _**
 *                     3.) containsVizierResponse() -> signals whether there was anything acquired from **_ Vizier _**
 *                     4.) isEmpty()                -> signals whether there was anything acquired **_ at all _**
 *
 * @author Ľuboslav Halama
 */
@Getter
@Setter
@NoArgsConstructor
public class ResponseData {

    private MastResponse mastResponse = new MastResponse();
    private VizierResponse vizierResponse = new VizierResponse();
    private SimbadResponse simbadResponse = new SimbadResponse();

    public boolean containsMastResponse() {
        return mastResponse != null && !mastResponse.isEmpty();
    }

    public boolean containsVizierResponse() {
        return vizierResponse != null && !vizierResponse.isEmpty();
    }

    public boolean containsSimbadResponse() {
        return simbadResponse != null && !simbadResponse.isEmpty();
    }

    public boolean isEmpty() {
        return !containsMastResponse() && !containsVizierResponse() && !containsSimbadResponse();
    }
}
