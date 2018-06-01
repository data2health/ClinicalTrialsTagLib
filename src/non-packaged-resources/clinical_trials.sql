CREATE TABLE clinical_trials.clinical_study (
       id INT NOT NULL
     , org_study_id TEXT
     , nct_id TEXT
     , brief_title TEXT
     , acronym TEXT
     , official_title TEXT
     , source TEXT
     , overall_status TEXT
     , why_stopped TEXT
     , phase TEXT
     , start_date DATE
     , end_date DATE
     , completion_date DATE
     , primary_completion_date DATE
     , study_type TEXT
     , study_design TEXT
     , target_duration TEXT
     , number_of_arms TEXT
     , number_of_groups TEXT
     , enrollment TEXT
     , condition TEXT
     , biospec_retention TEXT
     , verification_date DATE
     , lastchanged_date DATE
     , firstreceived_date DATE
     , firstreceived_results_date DATE
     , is_fda_regulated TEXT
     , is_section_801 TEXT
     , has_expanded_access TEXT
     , PRIMARY KEY (id)
);

CREATE TABLE clinical_trials.intervention (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , intervention_type TEXT
     , intervention_name TEXT
     , description TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_intervention_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.eligibility (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , sampling_method TEXT
     , gender TEXT
     , minimum_age TEXT
     , maximum_age TEXT
     , healthy_volunteers TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_eligibility_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.location (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , name TEXT
     , city TEXT
     , state TEXT
     , zip TEXT
     , country TEXT
     , status TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_location_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.clinical_results (
       id INT NOT NULL
     , pi_employee TEXT
     , restrictive_agreement TEXT
     , contact_name_or_title TEXT
     , contact_organization TEXT
     , contact_phone TEXT
     , contact_email TEXT
     , PRIMARY KEY (id)
     , CONSTRAINT FK_clinical_results_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.sponsor (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , lead_sponsor_agency TEXT
     , lead_sponsor_agency_class TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_sponsor_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.participant_flow (
       id INT NOT NULL
     , recruitment_details TEXT
     , pre_assignment_details TEXT
     , PRIMARY KEY (id)
     , CONSTRAINT FK_participant_flow_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_results (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.participant_flow_period (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , title TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_participant_flow_period_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.participant_flow (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.participant_flow_drop_withdraw (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , mileseqnum INT NOT NULL
     , title TEXT
     , PRIMARY KEY (id, seqnum, mileseqnum)
     , CONSTRAINT FK_participant_flow_drop_withdraw_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.participant_flow_period (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.baseline_measure (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , title TEXT
     , description TEXT
     , units TEXT
     , param TEXT
     , dispersion TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_baseline_measure_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_results (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.participant_flow_milestone (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , mileseqnum INT NOT NULL
     , title TEXT
     , PRIMARY KEY (id, seqnum, mileseqnum)
     , CONSTRAINT FK_participant_flow_milestone_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.participant_flow_period (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.baseline_category (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , measeqnum INT NOT NULL
     , sub_title TEXT
     , PRIMARY KEY (id, seqnum, measeqnum)
     , CONSTRAINT FK_baseline_category_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.baseline_measure (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.results_outcome (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , posting_date TEXT
     , type TEXT
     , title TEXT
     , description TEXT
     , time_frame TEXT
     , safety_issue TEXT
     , population TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_results_outcome_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_results (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.results_measure (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , measeqnum INT NOT NULL
     , title TEXT
     , description TEXT
     , units TEXT
     , param TEXT
     , dispersion TEXT
     , PRIMARY KEY (id, seqnum, measeqnum)
     , CONSTRAINT FK_results_measure_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.results_outcome (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.results_analysis (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , anaseqnum INT NOT NULL
     , groups_desc TEXT
     , non_inferiority TEXT
     , non_inferiority_desc TEXT
     , p_value TEXT
     , p_value_desc TEXT
     , method TEXT
     , method_desc TEXT
     , param_type TEXT
     , param_value TEXT
     , ci_percent TEXT
     , ci_n_sides TEXT
     , ci_lower_limit TEXT
     , ci_upper_ilmit TEXT
     , estimate_desc TEXT
     , dispersion_type TEXT
     , dispersion_value TEXT
     , PRIMARY KEY (id, seqnum, anaseqnum)
     , CONSTRAINT FK_results_analysis_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.results_outcome (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.reported_events (
       id INT NOT NULL
     , time_frame TEXT
     , description TEXT
     , serious_frequency_threshold TEXT
     , serious_default_vocab TEXT
     , serious_default_assessment TEXT
     , other_frequency_threshold TEXT
     , other_default_vocab TEXT
     , other_default_assessment TEXT
     , PRIMARY KEY (id)
     , CONSTRAINT FK_reported_events_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_results (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.results_category (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , measeqnum INT NOT NULL
     , catseqnum INT NOT NULL
     , sub_title TEXT
     , PRIMARY KEY (id, seqnum, measeqnum, catseqnum)
     , CONSTRAINT FK_results_category_1 FOREIGN KEY (id, seqnum, measeqnum)
                  REFERENCES clinical_trials.results_measure (id, seqnum, measeqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.other_category (
       id INT NOT NULL
     , seqnum TEXT NOT NULL
     , title TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_other_category_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.reported_events (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.serious_event (
       id INT NOT NULL
     , seqnum TEXT NOT NULL
     , eveseqnum INT NOT NULL
     , sub_title TEXT
     , description TEXT
     , assessment TEXT
     , PRIMARY KEY (id, seqnum, eveseqnum)
     , CONSTRAINT FK_serious_event_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.serious_category (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.other_event (
       id INT NOT NULL
     , seqnum TEXT NOT NULL
     , eveseqnum INT NOT NULL
     , sub_title TEXT
     , description TEXT
     , assessment TEXT
     , PRIMARY KEY (id, seqnum, eveseqnum)
     , CONSTRAINT FK_other_event_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.other_category (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.serious_category (
       id INT NOT NULL
     , seqnum TEXT NOT NULL
     , title TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_serious_category_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.reported_events (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.nct_alias (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , nct_alias TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_nct_alias_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.collaborator (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , collseqnum INT NOT NULL
     , agency TEXT
     , agency_class TEXT
     , PRIMARY KEY (id, seqnum, collseqnum)
     , CONSTRAINT FK_collaborator_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.sponsor (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.brief_summary (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , textblock TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_brief_summary_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.detailed_description (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , textblock TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_detailed_description_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.primary_outcome (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , measure TEXT
     , time_frame TEXT
     , safety_issue TEXT
     , description TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_primary_outcome_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.secondary_outcome (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , measure TEXT
     , time_frame TEXT
     , safety_issue TEXT
     , description TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_secondary_outcome_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.other_outcome (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , measure TEXT
     , time_frame TEXT
     , safety_issue TEXT
     , description TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_other_outcome_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.arm_group (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , arm_group_label TEXT
     , arm_group_type TEXT
     , description TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_arm_group_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.intervention_arm_group (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , intseqnum INT NOT NULL
     , arm_group_label TEXT
     , PRIMARY KEY (id, seqnum, intseqnum)
     , CONSTRAINT FK_intervention_arm_group_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.intervention (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.intervention_other_name (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , intseqnum INT NOT NULL
     , other_name TEXT
     , PRIMARY KEY (id, seqnum, intseqnum)
     , CONSTRAINT FK_intervention_other_name_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.intervention (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.biospec_descr (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , textblock TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_biospec_descr_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.study_pop (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , elseqnum INT NOT NULL
     , textblock TEXT
     , PRIMARY KEY (id, seqnum, elseqnum)
     , CONSTRAINT FK_study_pop_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.eligibility (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.criteria (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , elseqnum INT NOT NULL
     , textblock TEXT
     , PRIMARY KEY (id, seqnum, elseqnum)
     , CONSTRAINT FK_criteria_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.eligibility (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.overall_official (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , first_name TEXT
     , middle_name TEXT
     , last_name TEXT
     , degrees TEXT
     , role TEXT
     , affiliation TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_overall_official_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.overall_contact (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , first_name TEXT
     , middle_name TEXT
     , last_name TEXT
     , degrees TEXT
     , phone TEXT
     , phone_ext TEXT
     , email TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_overall_contact_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.official (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , locseqnum INT NOT NULL
     , first_name TEXT
     , middle_name TEXT
     , last_name TEXT
     , degrees TEXT
     , role TEXT
     , affiliation TEXT
     , PRIMARY KEY (id, seqnum, locseqnum)
     , CONSTRAINT FK_official_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.location (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.contact (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , locseqnum INT NOT NULL
     , first_name TEXT
     , middle_name TEXT
     , last_name TEXT
     , degrees TEXT
     , phone TEXT
     , phone_ext TEXT
     , email TEXT
     , PRIMARY KEY (id, seqnum, locseqnum)
     , CONSTRAINT FK_contact_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.location (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.location_countries (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , country TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_location_countries_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.removed_countries (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , country TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_removed_countries_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.link (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , url TEXT
     , description TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_removed_countries_1_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.reference (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , citation TEXT
     , pmid TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_reference_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.results_reference (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , citation TEXT
     , pmid TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_result_reference_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.responsible_party (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , name_title TEXT
     , organization TEXT
     , responsible_party_type TEXT
     , investigator_affiliation TEXT
     , investigator_full_name TEXT
     , investigator_title TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_responsible_party_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.keyword (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , keyword TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_keyword_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.condition_browse (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , mesh TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_condition_browse_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.intervention_browse (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , mesh TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_intervention_browse_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.participant_flow_group (
       id INT NOT NULL
     , group_id TEXT NOT NULL
     , title TEXT
     , description TEXT
     , PRIMARY KEY (id, group_id)
     , CONSTRAINT FK_participant_flow_group_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.participant_flow (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.participant_flow_milestone_participant (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , mileseqnum INT NOT NULL
     , group_id TEXT NOT NULL
     , count TEXT
     , PRIMARY KEY (id, seqnum, mileseqnum, group_id)
     , CONSTRAINT FK_participant_flow_milestone_participant_1 FOREIGN KEY (id, seqnum, mileseqnum)
                  REFERENCES clinical_trials.participant_flow_milestone (id, seqnum, mileseqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.participant_flow_drop_withdraw_participant (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , mileseqnum INT NOT NULL
     , group_id TEXT NOT NULL
     , count TEXT
     , PRIMARY KEY (id, seqnum, mileseqnum, group_id)
     , CONSTRAINT FK_participant_flow_drop_withdraw_participant_1 FOREIGN KEY (id, seqnum, mileseqnum)
                  REFERENCES clinical_trials.participant_flow_drop_withdraw (id, seqnum, mileseqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.baseline_group (
       id INT NOT NULL
     , group_id TEXT NOT NULL
     , title TEXT
     , description TEXT
     , PRIMARY KEY (id, group_id)
     , CONSTRAINT FK_baseline_group_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_results (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.baseline_measurement (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , measeqnum INT NOT NULL
     , group_id TEXT NOT NULL
     , value TEXT
     , spread TEXT
     , lower_limit TEXT
     , upper_limit TEXT
     , PRIMARY KEY (id, seqnum, measeqnum, group_id)
     , CONSTRAINT FK_baseline_measurement_1 FOREIGN KEY (id, seqnum, measeqnum)
                  REFERENCES clinical_trials.baseline_category (id, seqnum, measeqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.results_group (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , group_id TEXT NOT NULL
     , title TEXT
     , description TEXT
     , PRIMARY KEY (id, group_id, seqnum)
     , CONSTRAINT FK_results_group_1 FOREIGN KEY (id, seqnum)
                  REFERENCES clinical_trials.results_outcome (id, seqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.results_measurement (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , measeqnum INT NOT NULL
     , catseqnum INT NOT NULL
     , group_id TEXT NOT NULL
     , value TEXT
     , spread TEXT
     , lower_limit TEXT
     , upper_limit TEXT
     , PRIMARY KEY (id, seqnum, measeqnum, group_id, catseqnum)
     , CONSTRAINT FK_results_measurement_1 FOREIGN KEY (id, seqnum, measeqnum, catseqnum)
                  REFERENCES clinical_trials.results_category (id, seqnum, measeqnum, catseqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.results_analysis_group (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , anaseqnum INT NOT NULL
     , group_id TEXT NOT NULL
     , PRIMARY KEY (id, group_id, seqnum, anaseqnum)
     , CONSTRAINT FK_results_analysis_group_1 FOREIGN KEY (id, seqnum, anaseqnum)
                  REFERENCES clinical_trials.results_analysis (id, seqnum, anaseqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.events_group (
       id INT NOT NULL
     , group_id TEXT NOT NULL
     , title TEXT
     , description TEXT
     , PRIMARY KEY (id, group_id)
     , CONSTRAINT FK_events_group_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.reported_events (id) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.other_event_count (
       id INT NOT NULL
     , seqnum TEXT NOT NULL
     , eveseqnum INT NOT NULL
     , group_id TEXT NOT NULL
     , subjects_affected TEXT
     , subjects_at_risk TEXT
     , events TEXT
     , PRIMARY KEY (id, seqnum, eveseqnum, group_id)
     , CONSTRAINT FK_other_event_count_1 FOREIGN KEY (id, seqnum, eveseqnum)
                  REFERENCES clinical_trials.other_event (id, seqnum, eveseqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials.serious_event_count (
       id INT NOT NULL
     , seqnum TEXT NOT NULL
     , eveseqnum INT NOT NULL
     , group_id TEXT NOT NULL
     , subjects_affected TEXT
     , subjects_at_risk TEXT
     , events TEXT
     , PRIMARY KEY (id, seqnum, eveseqnum, group_id)
     , CONSTRAINT FK_serious_event_count_1 FOREIGN KEY (id, seqnum, eveseqnum)
                  REFERENCES clinical_trials.serious_event (id, seqnum, eveseqnum) ON DELETE CASCADE
);

CREATE TABLE clinical_trials_local.cui_cache (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , cui TEXT
     , phrase TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_cui_cache_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id)
);

CREATE TABLE clinical_trials.secondary_id (
       id INT NOT NULL
     , seqnum INT NOT NULL
     , secondary_id TEXT
     , PRIMARY KEY (id, seqnum)
     , CONSTRAINT FK_secondary_id_1 FOREIGN KEY (id)
                  REFERENCES clinical_trials.clinical_study (id) ON DELETE CASCADE
);

