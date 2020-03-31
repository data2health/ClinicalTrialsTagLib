CREATE TABLE clinical_trials.study (
       id INT NOT NULL
     , nct_id TEXT
     , brief_title TEXT
     , official_title TEXT
     , acronym TEXT
     , org_study_id TEXT
     , org_study_id_type TEXT
     , org_study_id_domain TEXT
     , org_study_id_link TEXT
     , org_full_name TEXT
     , org_class TEXT
     , status_verified_date TEXT
     , overall_status TEXT
     , last_known_status TEXT
     , delayed_posting TEXT
     , why_stopped TEXT
     , has_expanded_access TEXT
     , expanded_access_nct_id TEXT
     , expanded_access_status_for_nct_id TEXT
     , start_date TEXT
     , start_date_type TEXT
     , primary_completion_date TEXT
     , primary_completion_date_type TEXT
     , completion_date TEXT
     , completion_date_type TEXT
     , study_first_submit_date TEXT
     , study_first_submit_qc_date TEXT
     , study_first_post_date_struct TEXT
     , study_first_post_date_struct_type TEXT
     , results_first_submit_date TEXT
     , results_first_submit_qc_date TEXT
     , results_first_post_date_struct TEXT
     , results_first_post_date_struct_type TEXT
     , disp_first_submit_date TEXT
     , disp_first_submit_qc_date TEXT
     , disp_first_post_date_struct TEXT
     , disp_first_post_date_struct_type TEXT
     , last_update_submit_date TEXT
     , last_update_submit_qc_date TEXT
     , last_update_post_date_struct TEXT
     , last_update_post_date_struct_type TEXT
     , responsible_party_type TEXT
     , responsible_party_invetigator_full_name TEXT
     , responsible_party_investigator_title TEXT
     , responsible_party_investigator_affiliation TEXT
     , responsible_party_old_name_title TEXT
     , responsible_party_old_organization TEXT
     , lead_sponsor_name TEXT
     , lead_sponsor_class TEXT
     , oversight_has_dmc TEXT
     , is_fda_regulated_drug TEXT
     , is_fda_regulated_device TEXT
     , is_unapproved_device TEXT
     , is_ppsd TEXT
     , is_us_export TEXT
     , brief_summary TEXT
     , detailed_description TEXT
     , study_type TEXT
     , exp_acc_type_individual TEXT
     , exp_acc_type_intermediate TEXT
     , exp_acc_type_treatment TEXT
     , patient_registry TEXT
     , target_duration TEXT
     , design_allocation TEXT
     , design_intervention_model TEXT
     , design_intervention_model_description TEXT
     , design_primary_purpose TEXT
     , design_masking TEXT
     , design_masking_description TEXT
     , bio_spec_retention TEXT
     , bio_spec_description TEXT
     , enrollment_count TEXT
     , enrollment_type TEXT
     , eligibility_criteria TEXT
     , healthy_volunteers TEXT
     , gender TEXT
     , gender_based TEXT
     , gender_description TEXT
     , minimum_age TEXT
     , maximum_age TEXT
     , study_population TEXT
     , sampling_method TEXT
     , ipd_sharing TEXT
     , ipd_sharing_description TEXT
     , ipd_sharing_time_frame TEXT
     , ipd_sharing_access_criteria TEXT
     , ipd_sharing_url TEXT
     , unposted_responsible_party TEXT
     , version_holder TEXT
     , PRIMARY KEY (id)
);

CREATE TABLE clinical_trials.intervention (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , intervention_type TEXT
     , intervention_name TEXT
     , intervention_description TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_intervention_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.location (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , location_facility TEXT
     , location_status TEXT
     , location_city TEXT
     , location_state TEXT
     , location_zip TEXT
     , location_country TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_location_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.reference (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , reference_pmid TEXT
     , reference_type TEXT
     , reference_citation TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_reference_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.arm_group (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , arm_group_label TEXT
     , arm_group_type TEXT
     , arm_group_description TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_arm_group_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.avail_ipd (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , avail_ipd_id TEXT
     , avail_ipd_type TEXT
     , avail_ipd_url TEXT
     , avail_ipd_comment TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_avail_ipd_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.central_contact (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , central_contact_name TEXT
     , central_contact_role TEXT
     , central_contact_phone TEXT
     , central_contact_phone_ext TEXT
     , central_contact_email TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_central_contact_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.collaborator (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , collaborator_name TEXT
     , collaborator_class TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_collaborator_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.condition (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , condition TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_condition_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.condition_ancestor (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , condition_ancestor_id TEXT
     , condition_ancestor_term TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_condition_ancestor_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.condition_browse_branch (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , condition_browse_branch_abbrev TEXT
     , condition_browse_branch_name TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_condition_browse_branch_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.condition_browse_leaf (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , condition_browse_leaf_id TEXT
     , condition_browse_leaf_name TEXT
     , condition_browse_leaf_as_found TEXT
     , condition_browse_leaf_relevance TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_condition_browse_leaf_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.condition_mesh (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , condition_mesh_id TEXT
     , condition_mesh_term TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_condition_mesh_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.design_observational_model (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , design_observational_model TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_design_observational_model_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.design_time_perspective (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , design_time_perspective TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_design_time_perspective_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.design_who_masked (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , design_who_masked TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_design_who_masked_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.info (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , secondary_id TEXT
     , secondary_id_type TEXT
     , secondary_id_domain TEXT
     , secondary_id_lonk TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_info_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.intervention_ancestor (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , intervention_ancestor_id TEXT
     , intervention_ancestor_term TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_intervention_ancestor_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.intervention_arm_group_label (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , seqnum2 INT NOT NULL
     , intervention_arm_group_label TEXT
     , PRIMARY KEY (id, seqnum, seqnum2)
     , CONSTRAINT FK_intervention_arm_group_label_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.intervention (id, seqnum) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.intervention_browse_branch (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , intervention_browse_branch_abbrev TEXT
     , intervention_browse_branch_name TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_intervention_browse_branch_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.intervention_browse_leaf (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , intervention_browse_leaf_id TEXT
     , intervention_browse_leaf_name TEXT
     , intervention_browse_leaf_as_found TEXT
     , intervention_browse_leaf_relevance TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_intervention_browse_leaf_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.intervention_mesh (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , intervention_mesh_id TEXT
     , intervention_mesh_term TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_intervention_mesh_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.intervention_other_name (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , seqnum2 INT NOT NULL
     , intervention_other_name TEXT
     , PRIMARY KEY (id, seqnum, seqnum2)
     , CONSTRAINT FK_intervention_other_name_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.intervention (id, seqnum) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.ipd_sharing_info_type (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , ipd_sharing_info_type TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_ipd_sharing_info_type_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.keyword (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , keyword TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_keyword_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.large_doc (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , large_doc_type_abbrev TEXT
     , loarge_doc_has_protocol TEXT
     , large_doc_has_sap TEXT
     , large_doc_has_icf TEXT
     , large_doc_label TEXT
     , large_doc_date TEXT
     , large_doc_upload_date TEXT
     , large_doc_filename TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_large_doc_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.location_contact (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , seqnum2 INT NOT NULL
     , location_contact_name TEXT
     , location_contact_role TEXT
     , location_contact_phone TEXT
     , location_contact_phone_ext TEXT
     , location_contact_email TEXT
     , PRIMARY KEY (id, seqnum, seqnum2)
     , CONSTRAINT FK_location_contact_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.location (id, seqnum) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.nct_id_alias (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , nct_id_alias TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_nct_id_alias_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.other_outcome (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , other_outcome_measure TEXT
     , other_outcome_description TEXT
     , other_outcome_time_frame TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_other_outcome_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.overall_official (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , overall_official_name TEXT
     , overall_official_affiliation TEXT
     , overall_official_role TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_overall_official_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.primary_outcome (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , primary_outcome_measure TEXT
     , primary_outcome_description TEXT
     , primary_outcome_time_frame TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_primary_outcome_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.removed_country (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , removed_country TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_removed_country_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.retraction (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , seqnum2 INT NOT NULL
     , retraction_pmid TEXT
     , retraction_source TEXT
     , PRIMARY KEY (id, seqnum, seqnum2)
     , CONSTRAINT FK_retraction_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.reference (id, seqnum) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.secondary_outcome (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , secondary_outcome_measure TEXT
     , secondary_outcome_description TEXT
     , secondary_outcome_time_frame TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_secondary_outcome_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.see_also_link (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , see_also_link_label TEXT
     , see_also_link_url TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_see_also_link_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.std_age (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , std_age TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_std_age_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.unposted_event (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , unposted_event_type TEXT
     , unposted_event_date TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_unposted_event_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.phase (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , phase TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_phase_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.study (id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE clinical_trials.arm_group_intervention_name (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , seqnum2 INT NOT NULL
     , arm_group_intervention_name TEXT
     , PRIMARY KEY (id, seqnum, seqnum2)
     , CONSTRAINT FK_arm_group_intervention_name_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.arm_group (id, seqnum) ON DELETE CASCADE ON UPDATE CASCADE
);

