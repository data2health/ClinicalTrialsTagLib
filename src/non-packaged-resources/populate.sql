truncate clinical_trials.study cascade;

insert into clinical_trials.study select * from clinical_trials_staging.study;
insert into clinical_trials.nct_id_alias select * from clinical_trials_staging.nct_id_alias;
insert into clinical_trials.info select * from clinical_trials_staging.info;
insert into clinical_trials.collaborator select * from clinical_trials_staging.collaborator;
insert into clinical_trials.condition select * from clinical_trials_staging.condition;
insert into clinical_trials.keyword select * from clinical_trials_staging.keyword;
insert into clinical_trials.design_observational_model select * from clinical_trials_staging.design_observational_model;
insert into clinical_trials.design_time_perspective select * from clinical_trials_staging.design_time_perspective;
insert into clinical_trials.design_who_masked select * from clinical_trials_staging.design_who_masked;
insert into clinical_trials.primary_outcome select * from clinical_trials_staging.primary_outcome;
insert into clinical_trials.secondary_outcome select * from clinical_trials_staging.secondary_outcome;
insert into clinical_trials.other_outcome select * from clinical_trials_staging.other_outcome;
insert into clinical_trials.std_age select * from clinical_trials_staging.std_age;
insert into clinical_trials.central_contact select * from clinical_trials_staging.central_contact;
insert into clinical_trials.overall_official select * from clinical_trials_staging.overall_official;
insert into clinical_trials.see_also_link select * from clinical_trials_staging.see_also_link;
insert into clinical_trials.avail_ipd select * from clinical_trials_staging.avail_ipd;
insert into clinical_trials.ipd_sharing_info_type select * from clinical_trials_staging.ipd_sharing_info_type;
insert into clinical_trials.unposted_event select * from clinical_trials_staging.unposted_event;
insert into clinical_trials.large_doc select * from clinical_trials_staging.large_doc;
insert into clinical_trials.removed_country select * from clinical_trials_staging.removed_country;
insert into clinical_trials.condition_ancestor select * from clinical_trials_staging.condition_ancestor;
insert into clinical_trials.condition_browse_branch select * from clinical_trials_staging.condition_browse_branch;
insert into clinical_trials.condition_browse_leaf select * from clinical_trials_staging.condition_browse_leaf;
insert into clinical_trials.condition_mesh select * from clinical_trials_staging.condition_mesh;
insert into clinical_trials.intervention_ancestor select * from clinical_trials_staging.intervention_ancestor;
insert into clinical_trials.intervention_browse_branch select * from clinical_trials_staging.intervention_browse_branch;
insert into clinical_trials.intervention_browse_leaf select * from clinical_trials_staging.intervention_browse_leaf;
insert into clinical_trials.intervention_mesh select * from clinical_trials_staging.intervention_mesh;

insert into clinical_trials.arm_group select id,seqnum,arm_group_label,arm_group_type,arm_group_type from clinical_trials_staging.arm_group;
insert into clinical_trials.arm_group_intervention_name select * from clinical_trials_staging.arm_group_intervention_name;

insert into clinical_trials.intervention select id,seqnum,intervention_type,intervention_name,intervention_description from clinical_trials_staging.intervention;
insert into clinical_trials.intervention_arm_group_label select * from clinical_trials_staging.intervention_arm_group_label;
insert into clinical_trials.intervention_other_name select * from clinical_trials_staging.intervention_other_name;

insert into clinical_trials.location select id,seqnum,location_facility,location_status,location_city,location_state,location_zip,location_country from clinical_trials_staging.location;
insert into clinical_trials.location_contact select * from clinical_trials_staging.location_contact;

insert into clinical_trials.reference select id,seqnum,reference_pmid,reference_type,reference_citation from clinical_trials_staging.reference;
insert into clinical_trials.retraction select * from clinical_trials_staging.retraction;

