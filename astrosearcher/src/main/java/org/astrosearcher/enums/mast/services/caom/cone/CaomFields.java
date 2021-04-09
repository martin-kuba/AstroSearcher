package org.astrosearcher.enums.mast.services.caom.cone;

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
    DATA_URL(
            "dataURL",
            "Data",
            "data URL",
            "fas fa-box-open",
            0,
            true
    ),
    JPEG_URL(
            "jpegURL",
            "jpeg",
            "Preview Image URL",
            "far fa-file-image",
            1,
            true
    ),
    RA(
            "ra",
            "RA (input)",
            "Right ascension from query input",
            2,
            true
    ),
    DEC(
            "dec",
            "DEC (input)",
            "Declination from query input",
            3,
            true

    ),
    INTENT_TYPE(
            "intentType",
            "Observation type",
            "Whether observation is for science or calibration",
            4,
            true
    ),
    OBS_COLLECTION (
            "obs_collection",
            "Mission",
            "Collection",
            5,
            true
    ),
    PROVENANCE_NAME(
            "provenance_name",
            "Provenance Name",
            "Provenance name, or source of data",
            6,
            true
    ),
    INSTRUMENT_NAME(
            "instrument_name",
            "Instrument",
            "Instrument Name",
            7,
            true
    ),
    PROJECT(
            "project",
            "Project",
            "Processing project",
            8,
            true
    ),
    FILTERS(
            "filters",
            "Filters",
            "Instrument filters",
            9,
            true
    ),
    WAVELENGTH_REGION(
            "wavelength_region",
            "Waveband",
            "Energy Band",
            10,
            true
    ),
    TARGET_NAME(
            "target_name",
            "Target Name",
            "Target Name",
            11,
            true
    ),
    TARGET_CLASSIFICATION(
            "target_classification",
            "Target Classification",
            "Type of target",
            12,
            true
    ),
    OBS_ID(
            "obs_id",
            "Observation ID",
            "Observation identifier, given by mission",
            13,
            true
    ),
    S_RA(
            "s_ra",
            "RA",
            "Observation Right Ascension",
            14,
            true
    ),
    S_DEC(
            "s_dec",
            "Dec",
            "Observation Declination",
            15,
            true
    ),
    DATAPRODUCT_TYPE(
            "dataproduct_type",
            "Product type",
            "Type of product",
            16,
            true
    ),
    PROPOSAL_PI(
            "proposal_pi",
            "Principal Investigator",
            "Principal investigator's last name",
            17,
            true
    ),
    CALIB_LEVEL(
            "calib_level",
            "Calibration Level",
            "Calibration level",
            18,
            true
    ),
    T_MIN(
            "t_min",
            "Start Time",
            "Observation start datetime",
            19,
            true
    ),
    T_MAX(
            "t_max",
            "End Time",
            "Observation end datetime",
            20,
            true
    ),
    T_EXPTIME(
            "t_exptime",
            "Exposure Length",
            "Exposure time",
            21,
            true
    ),
    EM_MIN(
            "em_min",
            "Min. Wavelength",
            "Minimum Wavelength",
            22,
            true
    ),
    EM_MAX(
            "em_max",
            "Max. Wavelength",
            "Maximum Wavelength",
            23,
            true
    ),
    OBS_TITLE(
            "obs_title",
            "Observation Title",
            "Observation description from proposal",
            24,
            true
    ),
    T_OBS_RELEASE(
            "t_obs_release",
            "Release Date",
            "Dataset release date",
            25,
            true
    ),
    PROPOSAL_ID(
            "proposal_id",
            "Proposal ID",
            "Proposal ID",
            26,
            true
    ),
    PROPOSAL_TYPE(
            "proposal_type",
            "Proposal Type",
            "Type of telescope proposal",
            27,
            true
    ),
    SEQUENCE_NUMBER(
            "sequence_number",
            "Sequence Number",
            "Sequence number, e.g. Kepler quarter or TESS sector",
            28,
            true
    ),
    S_REGION(
            "s_region",
            "s_region",
            "STC/S Footprint",
            29,
            false
    ),
    DATA_RIGHTS(
            "dataRights",
            "Data Rights",
            "Data rights",
            30,
            true
    ),
    MT_FLAG(
            "mtFlag",
            "Moving Target",
            "Moving Target Flag",
            31,
            true
    ),
    SRC_DEN(
            "srcDen",
            "Number of Catalog Objects",
            "Number of cataloged objects found in observation",
            32,
            true
    ),
    OBSID(
            "obsid",
            "Product Group ID",
            "Database identifier for obs_id",
            33,
            true
    ),
    DISTANCE(
            "distance",
            "Distance (\")",
            "Angular separation between searched coordinates and center of obsevation",
            34,
            true
    ),
    SELECTED(
            "_selected_",
            "Selected",
            "Selected",
            35,
            false
    ),
    OBJID(
            "objID",
            "Object ID",
            "Plane ID of observation at given calibration level",
            36,
            false
    );


    private String  workName;
    private String  label;
    private String  description;
    private String  iconClass;
    private int     order;
    private boolean visible;


    CaomFields(String workName, String label, String description,
               int order,
               boolean visible) {
        setAttributes(workName, label, description, null, order, visible);
    }

    CaomFields(String workName, String label, String description, String iconClass,
               int order,
               boolean visible) {
        setAttributes(workName, label, description, iconClass, order, visible);
    }

    private void setAttributes(String workName, String label, String description, String iconClass, int order, boolean visible) {
        this.workName = workName;
        this.label = label;
        this.description = description;
        this.order = order;
        this.visible = visible;
        this.iconClass = iconClass;
    }

    @Override
    public String toString() {
        return label;
    }

    public String describe() {
        return description;
    }

    public boolean equals(String workName) {
        return this.workName.equals(workName);
    }

    public String getIconClass() {
        return iconClass;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isIcon() {
        return iconClass != null;
    }

    public String getWorkName() {
        return workName;
    }
}
