package com.sdm.hw.common.capability;

import static com.sdm.hw.common.capability.CapabilityConstant.EXPRESSION_DELIMITER;

/**
 * This enum works with CapabilityManager class. Following steps should be performed to add a new
 * Boolean capability:
 * <pre>
 *  Step 1. Create a new Enum and corresponding to a string in the format of capabilityGroup[.capabiityGroup].capbility
 *  	e.g.: testGroup1L1.testGroup1L2.testBooleanCapability
 *  Step 2. Add addition group and capability in the corresponding capability XML. The XML must conforms to capability
 *  	XML Schema Definition.
 *  	e.g.:
 *  ===============================
 *      <capabilityGroup name="testGroup1L1">
 *          <enabled code="NS">true</enabled>
 *          <enabled code="NB">true</enabled>
 *          <enabled code="ON">true</enabled>
 *          <capabilityGroup name="testGroup1L2">
 *              <enabled code="ON">true</enabled>
 *              <capability name="testStringCapability" type="string">
 *                  <value code="NS"/>
 *                  <value code="NB"/>
 *                  <value code="ON">abc</value>
 *              </capability>
 *              <capability name="testBooleanCapability" type="boolean">
 *                  <value code="NS"/>
 *                  <value code="NB"/>
 *                  <value code="ON">true</value>
 *              </capability>
 *              <capability name="testLongCapability" type="long">
 *                  <value code="NS"/>
 *                  <value code="NB"/>
 *                  <value code="ON">999</value>
 *              </capability>
 *              <capability name="testDoubleCapability" type="boolean">
 *                  <value code="NS"/>
 *                  <value code="NB"/>
 *                  <value code="ON">10.9</value>
 *              </capability>
 *          </capabilityGroup>
 *      </capabilityGroup>
 *  ===============================
 * </pre>
 *
 * @author Jasbir Minhas
 * @version 1.0
 * @since 2017-11-07
 */
public enum CapabilityBooleanKey implements CapabilityKey {
    //Sub-eHealth Status
    // Note: Sub-eHealth expression ends with a "." which indicates that it is a group
	EHEALTH("eHealth."),
    //ePrescribing Enabled Check
    EPRESCRIBING("eHealth.ePrescribing"),
    //Rx Order Id Enabled Check
    PRINT_RX_ORDER_ID("eHealth.printRxOrderId"),
    //Manage Consent Enabled Check
    MANAGE_CONSENT("eHealth.manageConsent"),
    //Add DIS Provincial Condition
    ADD_DIS_PROVINCIAL_CONDITION("eHealth.addDISProvincialCondition"),
    //View Reason For Access Popup
    REASON_TO_ACCESS_POPUP("eHealth.reasonToAccessPopup"),
    //Link to Provincial Rx
    LINK_PROVINCIAL_RX("eHealth.linkProvincialRx"),
    //View Status Change
    VIEW_STATUS_CHANGE("eHealth.viewStatusChange"),
    //Trigger DIS ONOK
    TRIGGER_DIS_ONOK("eHealth.triggerDISONOK"),
    //Allergy Status Drop Down Option
    ALLERGY_STATUS("eHealth.allergyStatus"),
    //Intolerance Status Dropdown Option
    INTOL_STATUS("eHealth.intolStatus"),
    //View ADR Status
    ADR_STATUS("eHealth.adrStatus"),
    //Store Province Option
    STORE_PROVINCE_OPTION("eHealth.storeProvinceOption"),
    //Enable Provincial Patient Search On Client Registry
    PATIENT_SEARCH("eHealth.patientSearch"),
    //Provincial Patient Linking Is Required Or Not
    PATIENT_LINKAGE("eHealth.patientLinkage"),
    //Non-Fill Actions on Provincial Rx require Intake Enabled
    PROVINCIAL_RX_NON_FILL_ACTIONS_REQUIRE_INTAKE("eHealth.provincialRxNonFillActionsRequireIntake"),
    //There is a sub-ehealth configurable capability mode in HW (On/Off) to make the patient profile review
    //mandatory during CV.
    PATIENT_PROFILE_REVIEW_IS_MANDATORY_AT_CV("eHealth.patientProfileReviewIsMandatoryAtCV"),
    //Display Management History At CV
    DISPLAY_MANAGEMENT_HISTORY_AT_CV("eHealth.displayManagementHistoryAtCV"),
    //There is a sub-ehealth configurable capability mode in HW (On/Off)  for the Tx of selected Provincial
    DISPLAY_TX_FOR_PROVINCIAL_RX("eHealth.displayTxForProvincialRx"),
    //There is a sub-ehealth configurable capability mode in HW (On/Off)  for the comprehensive profile to
    // be user selectable or mandatory.
    COMPREHENSIVE_MODIFIER("eHealth.comprehensiveModifier"),
    //Display Provincial Data
    DISPLAY_PROVINCIAL_DATA("eHealth.displayProvincialData"),
    //There is a sub-ehealth configurable capability mode in HW (On/Off)  for the medication profile
    //to be user selectable or not.-->
    PROV_MEDICATION_PROFILE("eHealth.provMedicationProfile"),
    //check for  Provincial Note
    // Start BP 350 Changes
    SEND_PATIENT_NOTE("eHealth.sendPatientNote"),
    // End BP 350 Changes
    //Provincial Provider Search Enabled Check
    // Start code EHR1.BP.310 for Query DIS Prescriber Record.
    PROVIDER_SEARCH("eHealth.providerSearch"),
    // End code EHR1.BP.310 for Query DIS Prescriber Record.
    //Provincial Location Search Enabled Check
    //Start code EHR1.BP.320 for Query DIS Location Record.
    LOCATION_SEARCH("eHealth.locationSearch"),
    //End code EHR1.BP.320 for Query DIS Location Record.
    //View Provincial Observation
    // Start BP 580 Changes
    QUERY_PROVINCIAL_OBSERVATIONS("eHealth.queryProvincialObservations"),
    // End BP 580 Changes
    //Sub-Ehealth View Provincial Reactions
    VIEW_PROVINCIAL_REACTIONS("eHealth.viewProvincialReactions"),
    //Sub-Ehealth View Provincial Allergies-->
    VIEW_PROVINCIAL_ALLERGIES("eHealth.viewProvincialAllergies"),
    //View Provincial Patient Conditions
    VIEW_PROV_MEDICAL_CONDITIONS("eHealth.viewProvMedicalConditions"),
    //View Provincial Allergies is Mandatory
    VIEW_ALLERGIES_MANDATORY("eHealth.viewAllergiesMandatory"),
    //View replaced version status Changes for medical condition
    CONDITION_REPLACED_VERSION("eHealth.conditionReplacedVersion"),
    //Sub-Ehealth View Provincial ADRs
    VIEW_PROVINCIAL_ADRS("eHealth.viewProvincialAdrs"),
    //View replaced version status Changes for Allergy/Intolerance
    ALLERGY_REPLACED_VERSION("eHealth.allergyReplacedVersion"),
    //Phone Enabled
    PHONE_ENABLED("eHealth.phoneEnabled"),
    //Postal Code Enabled
    POSTAL_CODE_ENABLED("eHealth.postalCodeEnabled"),
    //Sub E-health configurable capability to check whether DIN exists for the entered chemical or not
    CHEMICAL_DIN_CHECK("eHealth.chemicalDINCheck"),
    //Transfer Province Location Id
    //Changes for Transfer screen enhancement SPrint 3 NS
    TRANSFER_PROVINCE_LOCATION_ID("eHealth.transferProvinceLocationId"),
    //Configuration Capability to disable/enable the not sent report from Action menu of Exception Queue.
    // start - Code changes for BP 640 Request DIS Provide Sent/Not Sent Report - Release- 16.2
    SEND_PROVINCIAL_NOT_SENT_REPORT("eHealth.sendProvincialNotSentReport"),
    // end - Code changes for BP 640 Request DIS Provide Sent/Not Sent Report - Release- 16.2
    //There is a sub-ehealth configurable capability mode in HW (On/Off)  for the user to select rx/tx note
    // to be sent to province from Data entry screen.
    // start - Code changes for BP 350 Add Rx Tx Notes - Release- 16.2
    SEND_RX_TX_NOTES("eHealth.sendRxTxNotes"),
    // end - Code changes for BP 350 Add Rx Tx Notes - Release- 16.2
    //There is a sub-ehealth configurable capability mode in HW (On/Off) to enable importing
    //provincial prescriptions.
    // Start code changes for BP 2000 Import Provincial Rx Release 16.2 on 29/04/2016
    IMPORT_PROVINCIAL_PRESCRIPTIONS("eHealth.importProvincialPrescriptions"),
    // End code changes for BP 2000 Import Provincial Rx Release 16.2 on 29/04/2016
    //There must be a sub-ehealth configuration capability (ON/OFF) in HW to allow the system to execute the
    //Producer ID check when linking provincial patient to local.
    // Start - Code changes for BP 240 Configuration added Release-16.2 on 26/05/2016
    PRODUCER_ID_CHECK("eHealth.producerIDCheck"),
    // End - Code changes for BP 240 Configuration added Release-16.2 on 26/05/2016
    //Enable Link to Prov Indicator
    // Start - Code changes for Link To Prov. Indicator Configuration added Release-16.2 on 05/08/2016
    LINK_STATUS_INDICATOR("eHealth.linkStatusIndicator"),
    // End - Code changes for Link To Prov. Indicator Configuration added Release-16.2 on 05/08/2016
    //Undisc Rx Action
    // Start - Code changes for CR 77
    UNDISC_RX_ACTION("eHealth.undiscRxAction"),
    // End - Code changes for CR 77
    //HW_COPY_PATIENT_CONFIG("HW_COPY_PATIENT_CONFIG"),
    //Eligible For Trial
    ELIGIBLE_FOR_TRIAL("eHealth.eligibleForTrial"),
	PROV_DUR_DISPLAY_CONFIG("eHealth.provDurDisplayConfig");


    /**
     * a String representing a path to the capability
     */
    private final String capabilityPath;

    /**
     * constructor for Enum
     * The capabilityPath passed as a parameter should in the the following format:
     * capabilityGroupName.[capabilityGroupName].capabiltyName
     * A capability name can be under one group level of multi-level groups
     *
     * @param capabilityPath String representing the capability path.
     */
    CapabilityBooleanKey(final String capabilityPath) {
        this.capabilityPath = capabilityPath;
    }

    /**
     * @see java.lang.Enum#toString()
     */
    @Override
    public String toString() {
        return capabilityPath;
    }

    /**
     * This method returns true or faslse based on the key of current enum instance
     *
     * @return capability string
     */
    public boolean isEnabled() {
        return CapabilityManager.getInstance().getBoolean(this);
    }

    /**
     * This method returns true if this capability is a group
     */
    @Override
    public boolean isGroup() {
        return capabilityPath.endsWith(EXPRESSION_DELIMITER);
    }
}