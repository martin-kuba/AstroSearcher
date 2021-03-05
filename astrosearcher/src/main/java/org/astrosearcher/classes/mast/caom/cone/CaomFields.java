package org.astrosearcher.classes.mast.caom.cone;

/**
 Enum class mainly based on fields listed in: https://mast.stsci.edu/api/v0/_c_a_o_mfields.html

 *************************************************************************************************************
 * 'workName' String must not be changed! It is used for matching response from MAST server with this class! *
 *************************************************************************************************************

 Class provides option to redefine following properties of CAOM fields in MAST response:
    - label ......... (column header name)
    - description
    - order ......... (order of column in table)
    - visible ....... (visibility of column)
    - transformed ... (indicates whether response data are transformed into icon)
 */
public enum CaomFields {
    INTENT_TYPE(
            "intentType",
            "Observation type",
            "Whether observation is for science or calibration",
            2,
            true,
            false
    ),
    OBS_COLLECTION (
            "obs_collection",
            "Mission",
            "Collection",
            3,
            true,
            false
    ),
    PROVENANCE_NAME(
            "provenance_name",
            "Provenance Name",
            "Provenance name, or source of data",
            4,
            true,
            false
    ),
    INSTRUMENT_NAME(
            "instrument_name",
            "Instrument",
            "Instrument Name",
            5,
            true,
            false
    ),
    PROJECT(
            "project",
            "Project",
            "Processing project",
            6,
            true,
            false
    ),
    FILTERS(
            "filters",
            "Filters",
            "Instrument filters",
            7,
            true,
            false
    ),
    WAVELENGTH_REGION(
            "wavelength_region",
            "Waveband",
            "Energy Band",
            8,
            true,
            false
    ),
    TARGET_NAME(
            "target_name",
            "Target Name",
            "Target Name",
            9,
            true,
            false
    ),
    TARGET_CLASSIFICATION(
            "target_classification",
            "Target Classification",
            "Type of target",
            10,
            true,
            false
    ),
    OBS_ID(
            "obs_id",
            "Observation ID",
            "Observation identifier, given by mission",
            11,
            true,
            false
    ),
    S_RA(
            "s_ra",
            "RA",
            "Observation Right Ascension",
            12,
            true,
            false
    ),
    S_DEC(
            "s_dec",
            "Dec",
            "Observation Declination",
            13,
            true,
            false
    ),
    DATAPRODUCT_TYPE(
            "dataproduct_type",
            "Product type",
            "Type of product",
            14,
            true,
            false
    ),
    PROPOSAL_PI(
            "proposal_pi",
            "Principal Investigator",
            "Principal investigator's last name",
            15,
            true,
            false
    ),
    CALIB_LEVEL(
            "calib_level",
            "Calibration Level",
            "Calibration level",
            16,
            true,
            false
    ),
    T_MIN(
            "t_min",
            "Start Time",
            "Observation start datetime",
            17,
            true,
            false
    ),
    T_MAX(
            "t_max",
            "End Time",
            "Observation end datetime",
            18,
            true,
            false
    ),
    T_EXPTIME(
            "t_exptime",
            "Exposure Length",
            "Exposure time",
            19,
            true,
            false
    ),
    EM_MIN(
            "em_min",
            "Min. Wavelength",
            "Minimum Wavelength",
            20,
            true,
            false
    ),
    EM_MAX(
            "em_max",
            "Max. Wavelength",
            "Maximum Wavelength",
            21,
            true,
            false
    ),
    OBS_TITLE(
            "obs_title",
            "Observation Title",
            "Observation description from proposal",
            22,
            true,
            false
    ),
    T_OBS_RELEASE(
            "t_obs_release",
            "Release Date",
            "Dataset release date",
            23,
            true,
            false
    ),
    PROPOSAL_ID(
            "proposal_id",
            "Proposal ID",
            "Proposal ID",
            24,
            true,
            false
    ),
    PROPOSAL_TYPE(
            "proposal_type",
            "Proposal Type",
            "Type of telescope proposal",
            25,
            true,
            false
    ),
    SEQUENCE_NUMBER(
            "sequence_number",
            "Sequence Number",
            "Sequence number, e.g. Kepler quarter or TESS sector",
            26,
            true,
            false
    ),
    S_REGION(
            "s_region",
            "s_region",
            "STC/S Footprint",
            27,
            false,
            false
    ),
    JPEG_URL(
            "jpegURL",
            "JPEG URL",
            "Preview Image URL",
            1,
            true,
            true
    ),
    DATA_URL(
            "dataURL",
            "Data URL",
            "data URL",
            0,
            true,
            true
    ),
    DATA_RIGHTS(
            "dataRights",
            "Data Rights",
            "Data rights",
            28,
            true,
            false
    ),
    MT_FLAG(
            "mtFlag",
            "Moving Target",
            "Moving Target Flag",
            29,
            true,
            false
    ),
    SRC_DEN(
            "srcDen",
            "Number of Catalog Objects",
            "Number of cataloged objects found in observation",
            30,
            true,
            false
    ),
    OBSID(
            "obsid",
            "Product Group ID",
            "Database identifier for obs_id",
            31,
            true,
            false
    ),
    DISTANCE(
            "distance",
            "Distance (\")",
            "Angular separation between searched coordinates and center of obsevation",
            32,
            true,
            false
    ),
    SELECTED(
            "_selected_",
            "Selected",
            "Selected",
            33,
            false,
            false
    ),
    OBJID(
            "objID",
            "Object ID",
            "Plane ID of observation at given calibration level",
            34,
            false,
            false
    );


    private final String  workName;
    private final String  label;
    private final String  description;
    private final int     order;
    private final boolean visible;
    private final boolean transformed;


    CaomFields(String workName, String label, String description,
               int order,
               boolean visible, boolean transformed) {
        this.workName    = workName;
        this.label       = label;
        this.description = description;
        this.order       = order;
        this.visible     = visible;
        this.transformed = transformed;
    }

    @Override
    public String toString() {
        return label;
    }

    public String describe() {
        return description;
    }


}
