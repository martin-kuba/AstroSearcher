package org.astrosearcher.classes.mast.services.caom.cone;

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
    INTENT_TYPE(
            "intentType",
            "Observation type",
            "Whether observation is for science or calibration",
            2,
            true
    ),
    OBS_COLLECTION (
            "obs_collection",
            "Mission",
            "Collection",
            3,
            true
    ),
    PROVENANCE_NAME(
            "provenance_name",
            "Provenance Name",
            "Provenance name, or source of data",
            4,
            true
    ),
    INSTRUMENT_NAME(
            "instrument_name",
            "Instrument",
            "Instrument Name",
            5,
            true
    ),
    PROJECT(
            "project",
            "Project",
            "Processing project",
            6,
            true
    ),
    FILTERS(
            "filters",
            "Filters",
            "Instrument filters",
            7,
            true
    ),
    WAVELENGTH_REGION(
            "wavelength_region",
            "Waveband",
            "Energy Band",
            8,
            true
    ),
    TARGET_NAME(
            "target_name",
            "Target Name",
            "Target Name",
            9,
            true
    ),
    TARGET_CLASSIFICATION(
            "target_classification",
            "Target Classification",
            "Type of target",
            10,
            true
    ),
    OBS_ID(
            "obs_id",
            "Observation ID",
            "Observation identifier, given by mission",
            11,
            true
    ),
    S_RA(
            "s_ra",
            "RA",
            "Observation Right Ascension",
            12,
            true
    ),
    S_DEC(
            "s_dec",
            "Dec",
            "Observation Declination",
            13,
            true
    ),
    DATAPRODUCT_TYPE(
            "dataproduct_type",
            "Product type",
            "Type of product",
            14,
            true
    ),
    PROPOSAL_PI(
            "proposal_pi",
            "Principal Investigator",
            "Principal investigator's last name",
            15,
            true
    ),
    CALIB_LEVEL(
            "calib_level",
            "Calibration Level",
            "Calibration level",
            16,
            true
    ),
    T_MIN(
            "t_min",
            "Start Time",
            "Observation start datetime",
            17,
            true
    ),
    T_MAX(
            "t_max",
            "End Time",
            "Observation end datetime",
            18,
            true
    ),
    T_EXPTIME(
            "t_exptime",
            "Exposure Length",
            "Exposure time",
            19,
            true
    ),
    EM_MIN(
            "em_min",
            "Min. Wavelength",
            "Minimum Wavelength",
            20,
            true
    ),
    EM_MAX(
            "em_max",
            "Max. Wavelength",
            "Maximum Wavelength",
            21,
            true
    ),
    OBS_TITLE(
            "obs_title",
            "Observation Title",
            "Observation description from proposal",
            22,
            true
    ),
    T_OBS_RELEASE(
            "t_obs_release",
            "Release Date",
            "Dataset release date",
            23,
            true
    ),
    PROPOSAL_ID(
            "proposal_id",
            "Proposal ID",
            "Proposal ID",
            24,
            true
    ),
    PROPOSAL_TYPE(
            "proposal_type",
            "Proposal Type",
            "Type of telescope proposal",
            25,
            true
    ),
    SEQUENCE_NUMBER(
            "sequence_number",
            "Sequence Number",
            "Sequence number, e.g. Kepler quarter or TESS sector",
            26,
            true
    ),
    S_REGION(
            "s_region",
            "s_region",
            "STC/S Footprint",
            27,
            false
    ),
    DATA_RIGHTS(
            "dataRights",
            "Data Rights",
            "Data rights",
            28,
            true
    ),
    MT_FLAG(
            "mtFlag",
            "Moving Target",
            "Moving Target Flag",
            29,
            true
    ),
    SRC_DEN(
            "srcDen",
            "Number of Catalog Objects",
            "Number of cataloged objects found in observation",
            30,
            true
    ),
    OBSID(
            "obsid",
            "Product Group ID",
            "Database identifier for obs_id",
            31,
            true
    ),
    DISTANCE(
            "distance",
            "Distance (\")",
            "Angular separation between searched coordinates and center of obsevation",
            32,
            true
    ),
    SELECTED(
            "_selected_",
            "Selected",
            "Selected",
            33,
            false
    ),
    OBJID(
            "objID",
            "Object ID",
            "Plane ID of observation at given calibration level",
            34,
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
