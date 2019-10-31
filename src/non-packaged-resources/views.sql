create view sections as
select
    id,
    ((((contents->>'FullStudy')::jsonb->>'Study')::jsonb)->>'ProtocolSection')::jsonb as protocol_section,
    ((((contents->>'FullStudy')::jsonb->>'Study')::jsonb)->>'ResultsSection')::jsonb as results_section,
    ((((contents->>'FullStudy')::jsonb->>'Study')::jsonb)->>'AnnotationSection')::jsonb as annotation_section,
    ((((contents->>'FullStudy')::jsonb->>'Study')::jsonb)->>'DocumentSection')::jsonb as document_section,
    ((((contents->>'FullStudy')::jsonb->>'Study')::jsonb)->>'DerivedSection')::jsonb as derived_section
from clinical_trials_staging.raw;

create view study as
select
    id,
    (((protocol_section->>'IdentificationModule')::jsonb)->>'NCTId')::text as nct_id,
    (((protocol_section->>'IdentificationModule')::jsonb)->>'BriefTitle')::text as brief_title,
    (((protocol_section->>'IdentificationModule')::jsonb)->>'OfficialTitle')::text as official_title,
    (((protocol_section->>'IdentificationModule')::jsonb)->>'Acronym')::text as acronym,
    (((((protocol_section->>'IdentificationModule')::jsonb)->>'OrgStudyIdInfo')::jsonb)->>'OrgStudyId')::text as org_study_id,
    (((((protocol_section->>'IdentificationModule')::jsonb)->>'OrgStudyIdInfo')::jsonb)->>'OrgStudyIdType')::text as org_study_id_type,
    (((((protocol_section->>'IdentificationModule')::jsonb)->>'OrgStudyIdInfo')::jsonb)->>'OrgStudyIdDomain')::text as org_study_id_domain,
    (((((protocol_section->>'IdentificationModule')::jsonb)->>'OrgStudyIdInfo')::jsonb)->>'OrgStudyIdLink')::text as org_study_id_link,
    (((((protocol_section->>'IdentificationModule')::jsonb)->>'Organization')::jsonb)->>'OrgFullName')::text as org_full_name,
    (((((protocol_section->>'IdentificationModule')::jsonb)->>'Organization')::jsonb)->>'OrgClass')::text as org_class,
    (((protocol_section->>'StatusModule')::jsonb)->>'StatusVerifiedDate')::text as status_verified_date,
    (((protocol_section->>'StatusModule')::jsonb)->>'OverallStatus')::text as overall_status,
    (((protocol_section->>'StatusModule')::jsonb)->>'LastKnownStatus')::text as last_known_status,
    (((protocol_section->>'StatusModule')::jsonb)->>'DelayedPosting')::text as delayed_posting,
    (((protocol_section->>'StatusModule')::jsonb)->>'WhyStopped')::text as why_stopped,
    (((((protocol_section->>'StatusModule')::jsonb)->>'ExpandedAccessInfo')::jsonb)->>'HasExpandedAccess')::text as has_expanded_access,
    (((((protocol_section->>'StatusModule')::jsonb)->>'ExpandedAccessInfo')::jsonb)->>'ExpandedAccessNCTId')::text as expanded_access_nct_id,
    (((((protocol_section->>'StatusModule')::jsonb)->>'ExpandedAccessInfo')::jsonb)->>'ExpandedAccessStatusForNCTId')::text as expanded_access_status_for_nct_id,
    (((((protocol_section->>'StatusModule')::jsonb)->>'StartDateStruct')::jsonb)->>'StartDate')::text as start_date,
    (((((protocol_section->>'StatusModule')::jsonb)->>'StartDateStruct')::jsonb)->>'StartDateType')::text as start_date_type,
    (((((protocol_section->>'StatusModule')::jsonb)->>'PrimaryCompletionDateStruct')::jsonb)->>'PrimaryCompletionDate')::text as primary_completion_date,
    (((((protocol_section->>'StatusModule')::jsonb)->>'PrimaryCompletionDateStruct')::jsonb)->>'PrimaryCompletionDateType')::text as primary_completion_date_type,
    (((((protocol_section->>'StatusModule')::jsonb)->>'CompletionDateStruct')::jsonb)->>'CompletionDate')::text as completion_date,
    (((((protocol_section->>'StatusModule')::jsonb)->>'CompletionDateStruct')::jsonb)->>'CompletionDateType')::text as completion_date_type,
    (((protocol_section->>'StatusModule')::jsonb)->>'StudyFirstSubmitDate')::text as study_first_submit_date,
    (((protocol_section->>'StatusModule')::jsonb)->>'StudyFirstSubmitQCDate')::text as study_first_submit_qc_date,
    (((((protocol_section->>'StatusModule')::jsonb)->>'StudyFirstPostDateStruct')::jsonb)->>'StudyFirstPostDateStruct')::text as study_first_post_date_struct,
    (((((protocol_section->>'StatusModule')::jsonb)->>'StudyFirstPostDateStruct')::jsonb)->>'StudyFirstPostDateStructType')::text as study_first_post_date_struct_type,
    (((protocol_section->>'StatusModule')::jsonb)->>'ResultsFirstSubmitDate')::text as results_first_submit_date,
    (((protocol_section->>'StatusModule')::jsonb)->>'ResultsFirstSubmitQCDate')::text as results_first_submit_qc_date,
    (((((protocol_section->>'StatusModule')::jsonb)->>'ResultsFirstPostDateStruct')::jsonb)->>'ResultsFirstPostDateStruct')::text as results_first_post_date_struct,
    (((((protocol_section->>'StatusModule')::jsonb)->>'ResultsFirstPostDateStruct')::jsonb)->>'ResultsFirstPostDateStructType')::text as results_first_post_date_struct_type,
    (((protocol_section->>'StatusModule')::jsonb)->>'DispFirstSubmitDate')::text as disp_first_submit_date,
    (((protocol_section->>'StatusModule')::jsonb)->>'DispFirstSubmitQCDate')::text as disp_first_submit_qc_date,
    (((((protocol_section->>'StatusModule')::jsonb)->>'DispFirstPostDateStruct')::jsonb)->>'DispFirstPostDateStruct')::text as disp_first_post_date_struct,
    (((((protocol_section->>'StatusModule')::jsonb)->>'DispFirstPostDateStruct')::jsonb)->>'DispFirstPostDateStructType')::text as disp_first_post_date_struct_type,
    (((protocol_section->>'StatusModule')::jsonb)->>'LastUpdateSubmitDate')::text as last_update_submit_date,
    (((protocol_section->>'StatusModule')::jsonb)->>'LastUpdateSubmitQCDate')::text as last_update_submit_qc_date,
    (((((protocol_section->>'StatusModule')::jsonb)->>'LastUpdatePostDateStruct')::jsonb)->>'LastUpdatePostDateStruct')::text as last_update_post_date_struct,
    (((((protocol_section->>'StatusModule')::jsonb)->>'LastUpdatePostDateStruct')::jsonb)->>'LastUpdatePostDateStructType')::text as last_update_post_date_struct_type,
    (((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'ResponsibleParty')::jsonb)->>'ResponsiblePartyType')::text as responsible_party_type,
    (((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'ResponsibleParty')::jsonb)->>'ResponsiblePartyInvestigatorFullName')::text as responsible_party_invetigator_full_name,
    (((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'ResponsibleParty')::jsonb)->>'ResponsiblePartyInvestigatorTitle')::text as responsible_party_investigator_title,
    (((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'ResponsibleParty')::jsonb)->>'ResponsiblePartyInvestigatorAffiliation')::text as responsible_party_investigator_affiliation,
    (((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'ResponsibleParty')::jsonb)->>'ResponsiblePartyOldNameTitle')::text as responsible_party_old_name_title,
    (((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'ResponsibleParty')::jsonb)->>'ResponsiblePartyOldOrganization')::text as responsible_party_old_organization,
    (((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'LeadSponsor')::jsonb)->>'LeadSponsorName')::text as lead_sponsor_name,
    (((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'LeadSponsor')::jsonb)->>'LeadSponsorClass')::text as lead_sponsor_class,
    (((protocol_section->>'OversightModule')::jsonb)->>'OversightHasDMC')::text as oversight_has_dmc,
    (((protocol_section->>'OversightModule')::jsonb)->>'IsFDARegulatedDrug')::text as is_fda_regulated_drug,
    (((protocol_section->>'OversightModule')::jsonb)->>'IsFDARegulatedDevice')::text as is_fda_regulated_device,
    (((protocol_section->>'OversightModule')::jsonb)->>'IsUnapprovedDevice')::text as is_unapproved_device,
    (((protocol_section->>'OversightModule')::jsonb)->>'IsPPSD')::text as is_ppsd,
    (((protocol_section->>'OversightModule')::jsonb)->>'IsUSExport')::text as is_us_export,
    (((protocol_section->>'DescriptionModule')::jsonb)->>'BriefSummary')::text as brief_summary,
    (((protocol_section->>'DescriptionModule')::jsonb)->>'DetailedDescription')::text as detailed_description,
    (((protocol_section->>'DesignModule')::jsonb)->>'StudyType')::text as study_type,
    (((((protocol_section->>'DesignModule')::jsonb)->>'ExpandedAccessTypes')::jsonb)->>'ExpAccTypeIndividual')::text as exp_acc_type_individual,
    (((((protocol_section->>'DesignModule')::jsonb)->>'ExpandedAccessTypes')::jsonb)->>'ExpAccTypeIntermediate')::text as exp_acc_type_intermediate,
    (((((protocol_section->>'DesignModule')::jsonb)->>'ExpandedAccessTypes')::jsonb)->>'ExpAccTypeTreatment')::text as exp_acc_type_treatment,
    (((protocol_section->>'DesignModule')::jsonb)->>'PatientRegistry')::text as patient_registry,
    (((protocol_section->>'DesignModule')::jsonb)->>'TargetDuration')::text as target_duration,
    (((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignAllocation')::text as design_allocation,
    (((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignInterventionModel')::text as design_intervention_model,
    (((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignInterventionModelDescription')::text as design_intervention_model_description,
    (((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignPrimaryPurpose')::text as design_primary_purpose,
    (((((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignMaskingInfo')::jsonb)->>'DesignMasking')::text as design_masking,
    (((((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignMaskingInfo')::jsonb)->>'DesignMaskingDescription')::text as design_masking_description,
    (((((protocol_section->>'DesignModule')::jsonb)->>'BioSpec')::jsonb)->>'BioSpecRetention')::text as bio_spec_retention,
    (((((protocol_section->>'DesignModule')::jsonb)->>'BioSpec')::jsonb)->>'BioSpecDescription')::text as bio_spec_description,
    (((((protocol_section->>'DesignModule')::jsonb)->>'EnrollmentInfo')::jsonb)->>'EnrollmentCount')::text as enrollment_count,
    (((((protocol_section->>'DesignModule')::jsonb)->>'EnrollmentInfo')::jsonb)->>'EnrollmentType')::text as enrollment_type,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'EligibilityCriteria')::text as eligibility_criteria,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'HealthyVolunteers')::text as healthy_volunteers,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'Gender')::text as gender,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'GenderBased')::text as gender_based,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'GenderDescription')::text as gender_description,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'MinimumAge')::text as minimum_age,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'MaximumAge')::text as maximum_age,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'StudyPopulation')::text as study_population,
    (((protocol_section->>'EligibilityModule')::jsonb)->>'SamplingMethod')::text as sampling_method,
    (((protocol_section->>'IPDSharingStatementModule')::jsonb)->>'IPDSharing')::text as ipd_sharing,
    (((protocol_section->>'IPDSharingStatementModule')::jsonb)->>'IPDSharingDescription')::text as ipd_sharing_description,
    (((protocol_section->>'IPDSharingStatementModule')::jsonb)->>'IPDSharingTimeFrame')::text as ipd_sharing_time_frame,
    (((protocol_section->>'IPDSharingStatementModule')::jsonb)->>'IPDSharingAccessCriteria')::text as ipd_sharing_access_criteria,
    (((protocol_section->>'IPDSharingStatementModule')::jsonb)->>'IPDSharingURL')::text as ipd_sharing_url,
    (((((annotation_section->>'AnnotationModule')::jsonb)->>'UnpostedAnnotation')::jsonb)->>'UnpostedResponsibleParty')::text as unposted_responsible_party,
    (((derived_section->>'MiscInfoModule')::jsonb)->>'VersionHolder')::text as version_holder
from clinical_trials_staging.sections;

create view nct_id_alias as
select
    id,
    t.seqnum,
    t.nct_id_alias
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((protocol_section->>'IdentificationModule')::jsonb)->>'NCTIdAliasList')::jsonb)->>'NCTIdAlias')::jsonb) with ordinality as t(nct_id_alias,seqnum)
;

create view info as
select
    id,
    t.seqnum,
    (info->>'SecondaryId')::text as secondary_id,
    (info->>'SecondaryIdType')::text as secondary_id_type,
    (info->>'SecondaryIdDomain')::text as secondary_id_domain,
    (info->>'SecondaryIdLink')::text as secondary_id_lonk
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'IdentificationModule')::jsonb)->>'SecondaryIdInfoList')::jsonb)->>'SecondaryIdInfo')::jsonb) with ordinality as t(info,seqnum)
;

create view collaborator as
select
    id,
    t.seqnum,
    (collaborator->>'CollaboratorName')::text as collaborator_name,
    (collaborator->>'CollaboratorClass')::text as collaborator_class
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'SponsorCollaboratorsModule')::jsonb)->>'CollaboratorList')::jsonb)->>'Collaborator')::jsonb) with ordinality as t(collaborator,seqnum)
;

create view condition as
select
    id,
    t.seqnum,
    t.condition
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((protocol_section->>'ConditionsModule')::jsonb)->>'ConditionList')::jsonb)->>'Condition')::jsonb) with ordinality as t(condition,seqnum)
;

create view keyword as
select
    id,
    t.seqnum,
    t.keyword
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((protocol_section->>'ConditionsModule')::jsonb)->>'KeywordList')::jsonb)->>'Keyword')::jsonb) with ordinality as t(keyword,seqnum)
;

create view design_observational_model as
select
    id,
    t.seqnum,
    t.design_observational_model
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignObservationalModelList')::jsonb)->>'DesignObservationalModel')::jsonb) with ordinality as t(design_observational_model,seqnum)
;

create view design_time_perspective as
select
    id,
    t.seqnum,
    t.design_time_perspective
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignTimePerspectiveList')::jsonb)->>'DesignTimePerspective')::jsonb) with ordinality as t(design_time_perspective,seqnum)
;

create view design_who_masked as
select
    id,
    t.seqnum,
    t.design_who_masked
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((((((protocol_section->>'DesignModule')::jsonb)->>'DesignInfo')::jsonb)->>'DesignMaskingInfo')::jsonb)->>'DesignWhoMaskedList')::jsonb)->>'DesignWhoMasked')::jsonb) with ordinality as t(design_who_masked,seqnum)
;

create view arm_group as
select
    id,
    t.seqnum,
    (arm_group->>'ArmGroupLabel')::text as arm_group_label,
    (arm_group->>'ArmGroupType')::text as arm_group_type,
    (arm_group->>'ArmGroupDescription')::text as arm_group_description,
    (arm_group->>'ArmGroupInterventionList')::jsonb as arm_group_intervention_list
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'ArmsInterventionsModule')::jsonb)->>'ArmGroupList')::jsonb)->>'ArmGroup')::jsonb) with ordinality as t(arm_group,seqnum)
;

create view arm_group_intervention_name as
select
    id,
    seqnum,
    seqnum2,
    arm_group_intervention_name
from
    clinical_trials_staging.arm_group
cross join lateral
    jsonb_array_elements_text((arm_group_intervention_list->>'ArmGroupInterventionName')::jsonb) with ordinality as t(arm_group_intervention_name,seqnum2)
;

create view intervention as
select
    id,
    t.seqnum,
    (intervention->>'InterventionType')::text as intervention_type,
    (intervention->>'InterventionName')::text as intervention_name,
    (intervention->>'InterventionDescription')::text as intervention_description,
    (intervention->>'InterventionArmGroupLabelList')::jsonb as intervention_arm_group_label_list,
    (intervention->>'InterventionOtherNameList')::jsonb as intervention_other_name_list
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'ArmsInterventionsModule')::jsonb)->>'InterventionList')::jsonb)->>'Intervention')::jsonb) with ordinality as t(intervention,seqnum)
;

create view primary_outcome as
select
    id,
    t.seqnum,
    (primary_outcome->>'PrimaryOutcomeMeasure')::text as primary_outcome_measure,
    (primary_outcome->>'PrimaryOutcomeDescription')::text as primary_outcome_description,
    (primary_outcome->>'PrimaryOutcomeTimeFrame')::text as primary_outcome_time_frame
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'OutcomesModule')::jsonb)->>'PrimaryOutcomeList')::jsonb)->>'PrimaryOutcome')::jsonb) with ordinality as t(primary_outcome,seqnum)
;

create view secondary_outcome as
select
    id,
    t.seqnum,
    (secondary_outcome->>'SecondaryOutcomeMeasure')::text as secondary_outcome_measure,
    (secondary_outcome->>'SecondaryOutcomeDescription')::text as secondary_outcome_description,
    (secondary_outcome->>'SecondaryOutcomeTimeFrame')::text as secondary_outcome_time_frame
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'OutcomesModule')::jsonb)->>'SecondaryOutcomeList')::jsonb)->>'SecondaryOutcome')::jsonb) with ordinality as t(secondary_outcome,seqnum)
;

create view other_outcome as
select
    id,
    t.seqnum,
    (other_outcome->>'OtherOutcomeMeasure')::text as other_outcome_measure,
    (other_outcome->>'OtherOutcomeDescription')::text as other_outcome_description,
    (other_outcome->>'OtherOutcomeTimeFrame')::text as other_outcome_time_frame
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'OutcomesModule')::jsonb)->>'OtherOutcomeList')::jsonb)->>'OtherOutcome')::jsonb) with ordinality as t(other_outcome,seqnum)
;

---------------

create view intervention_arm_group_label as
select
    id,
    seqnum,
    seqnum2,
    intervention_arm_group_label
from
    clinical_trials_staging.intervention
cross join lateral
    jsonb_array_elements_text((intervention_arm_group_label_list->>'InterventionArmGroupLabel')::jsonb) with ordinality as t(intervention_arm_group_label,seqnum2)
;

create view intervention_other_name as
select
    id,
    seqnum,
    seqnum2,
    intervention_other_name
from
    clinical_trials_staging.intervention
cross join lateral
    jsonb_array_elements_text((intervention_other_name_list->>'InterventionOtherName')::jsonb) with ordinality as t(intervention_other_name,seqnum2)
;

create view std_age as
select
    id,
    t.seqnum,
    std_age
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((protocol_section->>'EligibilityModule')::jsonb)->>'StdAgeList')::jsonb)->>'StdAge')::jsonb) with ordinality as t(std_age,seqnum)
;

create view central_contact as
select
    id,
    t.seqnum,
    (central_contact->>'CentralContactName')::text as central_contact_name,
    (central_contact->>'CentralContactRole')::text as central_contact_role,
    (central_contact->>'CentralContactPhone')::text as central_contact_phone,
    (central_contact->>'CentralContactPhoneExt')::text as central_contact_phone_ext,
    (central_contact->>'CentralContactEMail')::text as central_contact_email
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'ContactsLocationsModule')::jsonb)->>'CentralContactList')::jsonb)->>'CentralContact')::jsonb) with ordinality as t(central_contact,seqnum)
;

create view overall_official as
select
    id,
    t.seqnum,
    (overall_official->>'OverallOfficialName')::text as overall_official_name,
    (overall_official->>'OverallOfficialAffiliation')::text as overall_official_affiliation,
    (overall_official->>'OverallOfficialRole')::text as overall_official_role
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'ContactsLocationsModule')::jsonb)->>'OverallOfficialList')::jsonb)->>'OverallOfficial')::jsonb) with ordinality as t(overall_official,seqnum)
;

create view location as
select
    id,
    t.seqnum,
    (location->>'LocationFacility')::text as location_facility,
    (location->>'LocationStatus')::text as location_status,
    (location->>'LocationCity')::text as location_city,
    (location->>'LocationState')::text as location_state,
    (location->>'LocationZip')::text as location_zip,
    (location->>'LocationCountry')::text as location_country,
    (location->>'LocationContactList')::jsonb as location_contact_list
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'ContactsLocationsModule')::jsonb)->>'LocationList')::jsonb)->>'Location')::jsonb) with ordinality as t(location,seqnum)
;

create view location_contact as
select
    id,
    seqnum,
    seqnum2,
    (location_contact->>'LocationContactName')::text as location_contact_name,
    (location_contact->>'LocationContactRole')::text as location_contact_role,
    (location_contact->>'LocationContactPhone')::text as location_contact_phone,
    (location_contact->>'LocationContactPhoneExt')::text as location_contact_phone_ext,
    (location_contact->>'LocationContactEMail')::text as location_contact_email
from
    clinical_trials_staging.location
cross join lateral
    jsonb_array_elements((location_contact_list->>'LocationContact')::jsonb) with ordinality as t(location_contact,seqnum2)
;

create view reference as
select
    id,
    t.seqnum,
    (reference->>'ReferencePMID')::text as reference_pmid,
    (reference->>'ReferenceType')::text as reference_type,
    (reference->>'ReferenceCitation')::text as reference_citation,
    (reference->>'RetractionList')::jsonb as retraction_list
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'ReferencesModule')::jsonb)->>'ReferenceList')::jsonb)->>'Reference')::jsonb) with ordinality as t(reference,seqnum)
;

create view retraction as
select
    id,
    seqnum,
    seqnum2,
    (retraction->>'RetractionPMID')::text as retraction_pmid,
    (retraction->>'RetractionSource')::text as retraction_source
from
    clinical_trials_staging.reference
cross join lateral
    jsonb_array_elements((retraction_list->>'Retraction')::jsonb) with ordinality as t(retraction,seqnum2)
;

create view see_also_link as
select
    id,
    t.seqnum,
    (see_also_link->>'SeeAlsoLinkLabel')::text as see_also_link_label,
    (see_also_link->>'SeeAlsoLinkURL')::text as see_also_link_url
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'ReferencesModule')::jsonb)->>'SeeAlsoLinkList')::jsonb)->>'SeeAlsoLink')::jsonb) with ordinality as t(see_also_link,seqnum)
;

create view avail_ipd as
select
    id,
    t.seqnum,
    (avail_ipd->>'AvailIPDId')::text as avail_ipd_id,
    (avail_ipd->>'AvailIPDType')::text as avail_ipd_type,
    (avail_ipd->>'AvailIPDURL')::text as avail_ipd_url,
    (avail_ipd->>'AvailIPDComment')::text as avail_ipd_comment
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((protocol_section->>'ReferencesModule')::jsonb)->>'AvailIPDList')::jsonb)->>'AvailIPD')::jsonb) with ordinality as t(avail_ipd,seqnum)
;

create view ipd_sharing_info_type as
select
    id,
    t.seqnum,
    ipd_sharing_info_type
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((protocol_section->>'IPDSharingStatementModule')::jsonb)->>'IPDSharingInfoTypeList')::jsonb)->>'IPDSharingInfoType')::jsonb) with ordinality as t(ipd_sharing_info_type,seqnum)
;

create view unposted_event as
select
    id,
    t.seqnum,
    (unposted_event->>'UnpostedEventType')::text as unposted_event_type,
    (unposted_event->>'UnpostedEventDate')::text as unposted_event_date
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements(((((((annotation_section->>'AnnotationModule')::jsonb)->>'UnpostedAnnotation')::jsonb)->>'UnpostedEventList')::jsonb->>'UnpostedEvent')::jsonb) with ordinality as t(unposted_event,seqnum)
;

create view large_doc as
select
    id,
    t.seqnum,
    (large_doc->>'LargeDocTypeAbbrev')::text as large_doc_type_abbrev,
    (large_doc->>'LargeDocHasProtocol')::text as loarge_doc_has_protocol,
    (large_doc->>'LargeDocHasSAP')::text as large_doc_has_sap,
    (large_doc->>'LargeDocHasICF')::text as large_doc_has_icf,
    (large_doc->>'LargeDocLabel')::text as large_doc_label,
    (large_doc->>'LargeDocDate')::text as large_doc_date,
    (large_doc->>'LargeDocUploadDate')::text as large_doc_upload_date,
    (large_doc->>'LargeDocFilename')::text as large_doc_filename
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((document_section->>'LargeDocumentModule')::jsonb)->>'LargeDocList')::jsonb)->>'LargeDoc')::jsonb) with ordinality as t(large_doc,seqnum)
;

create view removed_country as
select
    id,
    t.seqnum,
    removed_country
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements_text((((((derived_section->>'MiscInfoModule')::jsonb)->>'RemovedCountryList')::jsonb)->>'RemovedCountry')::jsonb) with ordinality as t(removed_country,seqnum)
;

create view condition_mesh as
select
    id,
    t.seqnum,
    (condition_mesh->>'ConditionMeshId')::text as condition_mesh_id,
    (condition_mesh->>'ConditionMeshTerm')::text as condition_mesh_term
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((derived_section->>'ConditionBrowseModule')::jsonb)->>'ConditionMeshList')::jsonb)->>'ConditionMesh')::jsonb) with ordinality as t(condition_mesh,seqnum)
;

create view condition_ancestor as
select
    id,
    t.seqnum,
    (condition_ancestor->>'ConditionAncestorId')::text as condition_ancestor_id,
    (condition_ancestor->>'ConditionAncestorTerm')::text as condition_ancestor_term
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((derived_section->>'ConditionBrowseModule')::jsonb)->>'ConditionAncestorList')::jsonb)->>'ConditionAncestor')::jsonb) with ordinality as t(condition_ancestor,seqnum)
;

create view condition_browse_leaf as
select
    id,
    t.seqnum,
    (condition_browse_leaf->>'ConditionBrowseLeafId')::text as condition_browse_leaf_id,
    (condition_browse_leaf->>'ConditionBrowseLeafName')::text as condition_browse_leaf_name,
    (condition_browse_leaf->>'ConditionBrowseLeafAsFound')::text as condition_browse_leaf_as_found,
    (condition_browse_leaf->>'ConditionBrowseLeafRelevance')::text as condition_browse_leaf_relevance
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((derived_section->>'ConditionBrowseModule')::jsonb)->>'ConditionBrowseLeafList')::jsonb)->>'ConditionBrowseLeaf')::jsonb) with ordinality as t(condition_browse_leaf,seqnum)
;

create view condition_browse_branch as
select
    id,
    t.seqnum,
    (condition_browse_branch->>'ConditionBrowseBranchAbbrev')::text as condition_browse_branch_abbrev,
    (condition_browse_branch->>'ConditionBrowseBranchName')::text as condition_browse_branch_name
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((derived_section->>'ConditionBrowseModule')::jsonb)->>'ConditionBrowseBranchList')::jsonb)->>'ConditionBrowseBranch')::jsonb) with ordinality as t(condition_browse_branch,seqnum)
;

create view intervention_mesh as
select
    id,
    t.seqnum,
    (intervention_mesh->>'InterventionMeshId')::text as intervention_mesh_id,
    (intervention_mesh->>'InterventionMeshTerm')::text as intervention_mesh_term
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((derived_section->>'InterventionBrowseModule')::jsonb)->>'InterventionMeshList')::jsonb)->>'InterventionMesh')::jsonb) with ordinality as t(intervention_mesh,seqnum)
;

create view intervention_ancestor as
select
    id,
    t.seqnum,
    (intervention_ancestor->>'InterventionAncestorId')::text as intervention_ancestor_id,
    (intervention_ancestor->>'InterventionAncestorTerm')::text as intervention_ancestor_term
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((derived_section->>'InterventionBrowseModule')::jsonb)->>'InterventionAncestorList')::jsonb)->>'InterventionAncestor')::jsonb) with ordinality as t(intervention_ancestor,seqnum)
;

create view intervention_browse_leaf as
select
    id,
    t.seqnum,
    (intervention_browse_leaf->>'InterventionBrowseLeafId')::text as intervention_browse_leaf_id,
    (intervention_browse_leaf->>'InterventionBrowseLeafName')::text as intervention_browse_leaf_name,
    (intervention_browse_leaf->>'InterventionBrowseLeafAsFound')::text as intervention_browse_leaf_as_found,
    (intervention_browse_leaf->>'InterventionBrowseLeafRelevance')::text as intervention_browse_leaf_relevance
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((derived_section->>'InterventionBrowseModule')::jsonb)->>'InterventionBrowseLeafList')::jsonb)->>'InterventionBrowseLeaf')::jsonb) with ordinality as t(intervention_browse_leaf,seqnum)
;

create view intervention_browse_branch as
select
    id,
    t.seqnum,
    (intervention_browse_branch->>'InterventionBrowseBranchAbbrev')::text as intervention_browse_branch_abbrev,
    (intervention_browse_branch->>'InterventionBrowseBranchName')::text as intervention_browse_branch_name
from
    clinical_trials_staging.sections
cross join lateral
    jsonb_array_elements((((((derived_section->>'InterventionBrowseModule')::jsonb)->>'InterventionBrowseBranchList')::jsonb)->>'InterventionBrowseBranch')::jsonb) with ordinality as t(intervention_browse_branch,seqnum)
;

create materialized view queue as
select
    id
from
    clinical_trials.study
where
    not exists (select id from clinical_trials_staging.raw where study.id = raw.id)
union
select
    id
from
    clinical_trials_staging.raw
where
    not exists (select id from clinical_trials.study where study.id = raw.id)
union
select
    cts.id
from
    clinical_trials.study as ct,
    clinical_trials_staging.raw as cts
where
        ct.id=cts.id
    and ct.last_update_submit_date!=cts.last_update_submit_date
;
