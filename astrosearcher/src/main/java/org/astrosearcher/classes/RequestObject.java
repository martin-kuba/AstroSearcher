package org.astrosearcher.classes;

import java.net.MalformedURLException;
import java.net.URL;

public abstract class RequestObject {

    public abstract String send();

    public abstract URL getConnectionURL() throws MalformedURLException;

    public abstract byte[] getParamsAsBytes();
}
