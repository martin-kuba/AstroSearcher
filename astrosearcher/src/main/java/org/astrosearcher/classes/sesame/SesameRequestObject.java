package org.astrosearcher.classes.sesame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.cds.SesameConstats;
import org.astrosearcher.utilities.ConnectionUtils;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Class represents request object which is used in URL request sent to Sesame (CDS).
 *
 * Class provides basic properties for sending request to MAST server as well as main functionality for sending
 * given request by our web application (implementation of abstract methods from abstract class RequestObject).
 *
 * @author Ľuboslav Halama
 */
@Getter
@AllArgsConstructor
public class SesameRequestObject extends RequestObject {

    private String id;

    @Override
    public String send() {
        if ( Limits.DEBUG ) {
            System.out.println("\n    >>> Starting to query Sesame...");
        }

        return ConnectionUtils.sendRequest(this);
    }

    @Override
    public URL getConnectionURL() throws MalformedURLException {
        return new URL(SesameConstats.CONNECTION_URL);
    }

    @Override
    public byte[] getParamsAsBytes() {
        return id.getBytes();
    }
}
