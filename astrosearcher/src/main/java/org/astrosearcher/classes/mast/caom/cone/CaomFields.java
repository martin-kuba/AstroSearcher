package org.astrosearcher.classes.mast.caom.cone;

/**
 Enum class mainly based on fields listed in: https://mast.stsci.edu/api/v0/_c_a_o_mfields.html
 */
public enum CaomFields {
    INTENT_TYPE("intentType", "Observation type", "Whether observation is for science or calibration"),
    OBS_COLLECTION ("obs_collection", "Mission", "Collection"),
    PROVENANCE_NAME("provenance_name", "Provenance Name", "Provenance name, or source of data"),
    INSTRUMENT_NAME("instrument_name", "Instrument", "Instrument Name"),
    PROJECT("project", "Project", "Processing project"),
    FILTERS("filters", "Filters", "Instrument filters"),
    WAVELENGTH_REGION("wavelength_region", "Waveband", "Energy Band"),
    TARGET_NAME("target_name", "Target Name", "Target Name"),
    TARGET_CLASSIFICATION("target_classification", "Target Classification", "Type of target"),
    OBS_ID("obs_id", "Observation ID", "Observation identifier, given by mission"),
    S_RA("s_ra", "RA", "Observation Right Ascension"),
    S_DEC("s_dec", "Dec", "Observation Declination"),
    DATAPRODUCT_TYPE("dataproduct_type", "Product type", "Type of product"),
    PROPOSAL_PI("proposal_pi", "Principal Investigator", "Principal investigator's last name"),
    CALIB_LEVEL("calib_level", "Calibration Level", "Calibration level"),
    T_MIN("t_min","Start Time", "Observation start datetime"),
    T_MAX("t_max","End Time", "Observation end datetime"),
    T_EXPTIME("t_exptime", "Exposure Length", "Exposure time"),
    EM_MIN("em_min", "Min. Wavelength", "Minimum Wavelength"),
    EM_MAX("em_max", "Max. Wavelength", "Maximum Wavelength"),
    OBS_TITLE("obs_title", "Observation Title", "Observation description from proposal"),
    T_OBS_RELEASE("t_obs_release", "Release Date", "Dataset release date"),
    PROPOSAL_ID("proposal_id", "Proposal ID", "Proposal ID"),
    PROPOSAL_TYPE("proposal_type", "Proposal Type", "Type of telescope proposal"),
    SEQUENCE_NUMBER("sequence_number", "Sequence Number",
            "Sequence number, e.g. Kepler quarter or TESS sector");


    private String workName;
    private String label;
    private String description;


    CaomFields(String workName, String label, String description) {
        this.workName    = workName;
        this.label       = label;
        this.description = description;
    }

    @Override
    public String toString() {
        return label;
    }

    public String describe() {
        return description;
    }


}
