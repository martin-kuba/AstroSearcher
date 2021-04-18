package org.astrosearcher.classes.sesame;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.astrosearcher.classes.RequestObject;
import org.astrosearcher.classes.constants.Limits;
import org.astrosearcher.classes.constants.cds.SesameConstats;
import org.astrosearcher.utilities.ConnectionUtils;

import java.net.MalformedURLException;
import java.net.URL;

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
