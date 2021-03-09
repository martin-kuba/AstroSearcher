package org.astrosearcher.utilities;

import org.astrosearcher.classes.mast.MastRequestObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SimbadSearchEngine {

    private static final String SIMBAD_URL  = "http://simbad.u-strasbg.fr/simbad/";

    private static final String ID_REQUEST  = "sim-id?";
    private static final String POS_REQUEST = "sim-coo?";


    /*
    http://simbad.u-strasbg.fr/simbad/sim-id?output.format=ASCII&Ident=hd%201
    http://simbad.u-strasbg.fr/simbad/sim-coo?output.format=HTML&Coord=12%2030%20%2b10%2020&Radius=10&Radius.unit=arcmin
     */

}
