package org.astrosearcher.classes.simbad.id;

import org.astrosearcher.classes.simbad.SimbadFields;

/**
 *
 *
 * @author Ľuboslav Halama
 */
public enum SimbadIDResponseFields {
    TYPED_ID("Typed ID"),
    ANG_DIST("distance (\")"),
    MAIN_ID("Main ID"),
    OTYPE_S("Object type"),

    RA_d("RA [deg]"),
    DEC_d("Dec [deg]"),

    COO_ERR_MAJA_d("Coord. error (major) [mas]"),
    COO_ERR_MINA_d("Coord. error (minor) [mas]"),
    COO_ERR_ANGLE_d("Coord. error (angle) [deg]"),

    PMRA("Prop. motion (RA) [mas yr^(-1)]"),
    PMDEC("Prop. motion (Dec) [mas yr^(-1)]"),

    PM_ERR_MAJA("Prop. motion error (major) [mas yr^(-1)]"),
    PM_ERR_MINA("Prop. motion error (minor) [mas yr^(-1)]"),
    PM_ERR_ANGLE("Prop. motion error (angle) [deg]"),

    PLX_VALUE("Parallax"),
    RV_VALUE("Radial velocity"),
    Z_VALUE("Redshift"),

    GALDIM_MAJAXIS("Angular size (major)"),
    GALDIM_MINAXIS("Angular size (minor)"),
    GALDIM_ANGLE("Galaxy ellipse angle"),

    SP_TYPE("MK Spectral type"),
    MORPH_TYPE("Morphological type"),

    NB_REF("References"),

    FILTER_NAME_U("Flux filter (U)"),
    FLUX_U("Magnitude (U)"),
    FLUX_ERROR_U("Flux error (U)"),
    FLUX_SYSTEM_U("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_U("Flux reference (U)"),
    FLUX_VAR_U("Flux variability flag (U)"),
    FLUX_MULT_U("Flux multiplicity flag (U)"),
    FLUX_QUAL_U("Flux multiplicity flag (U)"),
    FLUX_UNIT_U("Flux unit (U)"),

    FILTER_NAME_B("Flux filter (B)"),
    FLUX_B("Magnitude (B)"),
    FLUX_ERROR_B("Flux error (B)"),
    FLUX_SYSTEM_B("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_B("Flux reference (B)"),
    FLUX_VAR_B("Flux variability flag (B)"),
    FLUX_MULT_B("Flux multiplicity flag (B)"),
    FLUX_QUAL_B("Flux multiplicity flag (B)"),
    FLUX_UNIT_B("Flux unit (B)"),

    FILTER_NAME_V("Flux filter (V)"),
    FLUX_V("Magnitude (V)"),
    FLUX_ERROR_V("Flux error (V)"),
    FLUX_SYSTEM_V("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_V("Flux reference (V)"),
    FLUX_VAR_V("Flux variability flag (V)"),
    FLUX_MULT_V("Flux multiplicity flag (V)"),
    FLUX_QUAL_V("Flux multiplicity flag (V)"),
    FLUX_UNIT_V("Flux unit (V)"),

    FILTER_NAME_R("Flux filter (R)"),
    FLUX_R("Magnitude (R)"),
    FLUX_ERROR_R("Flux error (R)"),
    FLUX_SYSTEM_R("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_R("Flux reference (R)"),
    FLUX_VAR_R("Flux variability flag (R)"),
    FLUX_MULT_R("Flux multiplicity flag (R)"),
    FLUX_QUAL_R("Flux multiplicity flag (R)"),
    FLUX_UNIT_R("Flux unit (R)"),

    FILTER_NAME_I("Flux filter (I)"),
    FLUX_I("Magnitude (I)"),
    FLUX_ERROR_I("Flux error (I)"),
    FLUX_SYSTEM_I("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_I("Flux reference (I)"),
    FLUX_VAR_I("Flux variability flag (I)"),
    FLUX_MULT_I("Flux multiplicity flag (I)"),
    FLUX_QUAL_I("Flux multiplicity flag (I)"),
    FLUX_UNIT_I("Flux unit (I)"),

    FILTER_NAME_J("Flux filter (J)"),
    FLUX_J("Magnitude (J)"),
    FLUX_ERROR_J("Flux error (J)"),
    FLUX_SYSTEM_J("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_J("Flux reference (J)"),
    FLUX_VAR_J("Flux variability flag (J)"),
    FLUX_MULT_J("Flux multiplicity flag (J)"),
    FLUX_QUAL_J("Flux multiplicity flag (J)"),
    FLUX_UNIT_J("Flux unit (J)"),

    FILTER_NAME_H("Flux filter (H)"),
    FLUX_H("Magnitude (H)"),
    FLUX_ERROR_H("Flux error (H)"),
    FLUX_SYSTEM_H("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_H("Flux reference (H)"),
    FLUX_VAR_H("Flux variability flag (H)"),
    FLUX_MULT_H("Flux multiplicity flag (H)"),
    FLUX_QUAL_H("Flux multiplicity flag (H)"),
    FLUX_UNIT_H("Flux unit (H)"),

    FILTER_NAME_K("Flux filter (K)"),
    FLUX_K("Magnitude (K)"),
    FLUX_ERROR_K("Flux error (K)"),
    FLUX_SYSTEM_K("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_K("Flux reference (K)"),
    FLUX_VAR_K("Flux variability flag (K)"),
    FLUX_MULT_K("Flux multiplicity flag (K)"),
    FLUX_QUAL_K("Flux multiplicity flag (K)"),
    FLUX_UNIT_K("Flux unit (K)"),


    FILTER_NAME_u("Flux filter (u)"),
    FLUX_u("Magnitude SDSS (u)"),
    FLUX_ERROR_u("Flux error (u)"),
    FLUX_SYSTEM_u("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_u("Flux reference (u)"),
    FLUX_VAR_u("Flux variability flag (u)"),
    FLUX_MULT_u("Flux multiplicity flag (u)"),
    FLUX_QUAL_u("Flux multiplicity flag (u)"),
    FLUX_UNIT_u("Flux unit (u)"),

//    FILTER_NAME_b("Flux filter (b)"),
//    FLUX_b("Magnitude SDSS (b)"),
//    FLUX_ERROR_b("Flux error (b)"),
//    FLUX_SYSTEM_b("Flux System (A=AB, V=Vega)"),
//    FLUX_BIBCODE_b("Flux reference (b)"),
//    FLUX_VAR_b("Flux variability flag (b)"),
//    FLUX_MULT_b("Flux multiplicity flag (b)"),
//    FLUX_QUAL_b("Flux multiplicity flag (b)"),
//    FLUX_UNIT_b("Flux unit (b)"),
//
//    FILTER_NAME_v("Flux filter (v)"),
//    FLUX_v("Magnitude SDSS (v)"),
//    FLUX_ERROR_v("Flux error (v)"),
//    FLUX_SYSTEM_v("Flux System (A=AB, V=Vega)"),
//    FLUX_BIBCODE_v("Flux reference (v)"),
//    FLUX_VAR_v("Flux variability flag (v)"),
//    FLUX_MULT_v("Flux multiplicity flag (v)"),
//    FLUX_QUAL_v("Flux multiplicity flag (v)"),
//    FLUX_UNIT_v("Flux unit (v)"),

    FILTER_NAME_g("Flux filter (g)"),
    FLUX_g("Magnitude (g)"),
    FLUX_ERROR_g("Flux error (g)"),
    FLUX_SYSTEM_g("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_g("Flux reference (g)"),
    FLUX_VAR_g("Flux variability flag (g)"),
    FLUX_MULT_g("Flux multiplicity flag (g)"),
    FLUX_QUAL_g("Flux multiplicity flag (g)"),
    FLUX_UNIT_g("Flux unit (g)"),

    FILTER_NAME_r("Flux filter (r)"),
    FLUX_r("Magnitude (r)"),
    FLUX_ERROR_r("Flux error (r)"),
    FLUX_SYSTEM_r("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_r("Flux reference (r)"),
    FLUX_VAR_r("Flux variability flag (r)"),
    FLUX_MULT_r("Flux multiplicity flag (r)"),
    FLUX_QUAL_r("Flux multiplicity flag (r)"),
    FLUX_UNIT_r("Flux unit (r)"),

    FILTER_NAME_i("Flux filter (i)"),
    FLUX_i("Magnitude (i)"),
    FLUX_ERROR_i("Flux error (i)"),
    FLUX_SYSTEM_i("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_i("Flux reference (i)"),
    FLUX_VAR_i("Flux variability flag (i)"),
    FLUX_MULT_i("Flux multiplicity flag (i)"),
    FLUX_QUAL_i("Flux multiplicity flag (i)"),
    FLUX_UNIT_i("Flux unit (i)"),

//    FILTER_NAME_J("Flux filter (J)"),
//    FLUX_J("Magnitude (J)"),
//    FLUX_ERROR_J("Flux error (J)"),
//    FLUX_SYSTEM_J("Flux System (A=AB, V=Vega)"),
//    FLUX_BIBCODE_J("Flux reference (J)"),
//    FLUX_VAR_J("Flux variability flag (J)"),
//    FLUX_MULT_J("Flux multiplicity flag (J)"),
//    FLUX_QUAL_J("Flux multiplicity flag (J)"),
//    FLUX_UNIT_J("Flux unit (J)"),
//
//    FILTER_NAME_H("Flux filter (H)"),
//    FLUX_H("Magnitude (H)"),
//    FLUX_ERROR_H("Flux error (H)"),
//    FLUX_SYSTEM_H("Flux System (A=AB, V=Vega)"),
//    FLUX_BIBCODE_H("Flux reference (H)"),
//    FLUX_VAR_H("Flux variability flag (H)"),
//    FLUX_MULT_H("Flux multiplicity flag (H)"),
//    FLUX_QUAL_H("Flux multiplicity flag (H)"),
//    FLUX_UNIT_H("Flux unit (H)"),
//
//    FILTER_NAME_K("Flux filter (K)"),
//    FLUX_K("Magnitude (K)"),
//    FLUX_ERROR_K("Flux error (K)"),
//    FLUX_SYSTEM_K("Flux System (A=AB, V=Vega)"),
//    FLUX_BIBCODE_K("Flux reference (K)"),
//    FLUX_VAR_K("Flux variability flag (K)"),
//    FLUX_MULT_K("Flux multiplicity flag (K)"),
//    FLUX_QUAL_K("Flux multiplicity flag (K)"),
//    FLUX_UNIT_K("Flux unit (K)"),

    FILTER_NAME_z("Flux filter (z)"),
    FLUX_z("Magnitude (z)"),
    FLUX_ERROR_z("Flux error (z)"),
    FLUX_SYSTEM_z("Flux System (A=AB, V=Vega)"),
    FLUX_BIBCODE_z("Flux reference (z)"),
    FLUX_VAR_z("Flux variability flag (z)"),
    FLUX_MULT_z("Flux multiplicity flag (z)"),
    FLUX_QUAL_z("Flux multiplicity flag (z)"),
    FLUX_UNIT_z("Flux unit (z)"),

    Diameter_diameter("Diameter"),
    Diameter_Q("Diameter quality"),
    Diameter_unit("Diameter unit (mas/km)"),
    Diameter_error("Error"),
    Diameter_filter("Filter/Wavelength"),
    Diameter_method("Diameter calc. method"),
    Diameter_bibcode("Diameter bibcode"),

    Distance_distance("Distance"),
    Distance_Q("Distance quality"),
    Distance_unit("Distance unit (pc/kpc/Mpc)"),
    Distance_merr("minus err."),
    Distance_perr("plus err."),
    Distance_method("Distance calc. method"),
    Distance_bibcode("Distance bibcode"),

    Fe_H_Teff("Effective Temperature"),
    Fe_H_log_g("Decimal logarithm of the surface gravity"),
    Fe_H_Fe_H("Metallicity index relative to the Sun"),
    Fe_H_flag("Flag on the [Fe/H] value"),
    Fe_H_CompStar("Designates the comparison star from which the [Fe/H] value was obtained"),
    Fe_H_CatNo("Star in the Cayrel et al. (1997A&AS..124..299C) compilation"),
    Fe_H_bibcode("Bibcode"),

    Herschel_ObsId("Observation identifier"),
    Herschel_alpha("Right ascension"),
    Herschel_delta("Declination"),

    PLX_plx("Parallaxe"),
    PLX_me("sigma{plx}"),
    PLX_R("Observatory code"),
    PLX_bibcode("Bibcode"),

    PM_pmra("Proper motion R.A."),
    PM_me_pmra("sigma{pm-ra}"),
    PM_pmde("Proper motion DEC."),
    PM_me_pmde("sigma{pm-de}"),
    PM_system("coordinates system designation"),
    PM_bibcode("Bibcode"),

    ROT_upVsini("Upper value of Vsini"),
    ROT_Vsini("V sini"),
    ROT_err("error"),
    ROT_mes("Number of measurements"),
    ROT_qual("Quality"),
    ROT_bibcode("Bibcode"),

    V__vartyp("Type of variability"),
    V__LoVmax("Upper limit flag for Vmax"),
    V__Vmax("Maximum of brightness"),
    V__R_Vmax("Uncertainty flag"),
    V__magtyp("Magnitude type"),
    V__UpVmin("Lower limit flag for Vmin"),
    V__Vmin("Minimum of brightness"),
    V__R_Vmin("Uncertainty flag for Vmin"),
    V__UpPeriod("Lower limit flag for the period"),
    V__period("Period"),
    V__R_period("Uncertainty flag on period (:+)"),
    V__epoch("Epoch of maximum or minimum"),
    V__R_epoch("Uncertainty on epoch (:)"),
    V__D_rt("Raising time for all other variable types"),
    V__R_D_rt("Uncertainty flag on raising time"),
    V__bibcode("Bibcode"),

    velocities_type("velocity type (v, z or cz)"),
    velocities_Value("Velocity"),
    velocities_R("colon is uncertain question mark is questionable"),
    velocities_me("sigma(Value)"),
    velocities_Acc("Quality"),
    velocities_Nmes("Number of measurements"),
    velocities_nat("nature of the measurement"),
    velocities_Q("Quality"),
    velocities_dom("Wavelength domain (Rad,mm,IR,Opt,UV,XRay,Gam)"),
    velocities_res("Resolution"),
    velocities_d("D"),
    velocities_Date("Observation date"),
    velocities_Rem("Remarks"),
    velocities_Origin("Origin of the radial velocity"),
    velocities_bibcode("Bibcode"),

    ISO_TDT("Observation identifier"),
    ISO_alpha("Right ascension"),
    ISO_delta("Declination"),

    IUE_Homogenized_Name("Homogenized Name"),
    IUE_ComplID("Complementary Identifier"),
    IUE_PROG("Observing Program Identification"),
    IUE_CL("IUE class code"),
    IUE_D("Dispersion code"),
    IUE_CAM("Camera id"),
    IUE_IMAGE("Image number"),
    IUE_A("Aperture designation code"),
    IUE_FES("FES count"),
    IUE_MD("FES mode"),
    IUE_ObsDate("Observation date"),
    IUE_Time("Observation time"),
    IUE_ExpTim("Effective exposure time"),
    IUE_m("Abnormality code"),
    IUE_CEB("Exposure quality code"),
    IUE_S("Station (V=Vilspa/G=Goddard)"),
    IUE_Comments("Comments"),
    IUE_F("Flag"),
    IUE_bibcode("Bibcode"),

    XMM_Obsno("Observation identifier"),
    OID4("Object internal identifier"),


    DEFAULT("default");

    private String label;

    private SimbadIDResponseFields(String label) {
        this.label = label;
    }
}
