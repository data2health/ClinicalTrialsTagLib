refresh materialized view clinical_trials_staging.queue;
delete from clinical_trials.study where id in (select id from clinical_trials_staging.queue);

insert into clinical_trials.study select * from clinical_trials_staging.study where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.nct_id_alias select * from clinical_trials_staging.nct_id_alias where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.info select * from clinical_trials_staging.info where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.collaborator select * from clinical_trials_staging.collaborator where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.condition select * from clinical_trials_staging.condition where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.keyword select * from clinical_trials_staging.keyword where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.phase select * from clinical_trials_staging.phase where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.design_observational_model select * from clinical_trials_staging.design_observational_model where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.design_time_perspective select * from clinical_trials_staging.design_time_perspective where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.design_who_masked select * from clinical_trials_staging.design_who_masked where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.primary_outcome select * from clinical_trials_staging.primary_outcome where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.secondary_outcome select * from clinical_trials_staging.secondary_outcome where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.other_outcome select * from clinical_trials_staging.other_outcome where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.std_age select * from clinical_trials_staging.std_age where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.central_contact select * from clinical_trials_staging.central_contact where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.overall_official select * from clinical_trials_staging.overall_official where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.see_also_link select * from clinical_trials_staging.see_also_link where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.avail_ipd select * from clinical_trials_staging.avail_ipd where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.ipd_sharing_info_type select * from clinical_trials_staging.ipd_sharing_info_type where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.unposted_event select * from clinical_trials_staging.unposted_event where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.large_doc select * from clinical_trials_staging.large_doc where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.removed_country select * from clinical_trials_staging.removed_country where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.condition_ancestor select * from clinical_trials_staging.condition_ancestor where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.condition_browse_branch select * from clinical_trials_staging.condition_browse_branch where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.condition_browse_leaf select * from clinical_trials_staging.condition_browse_leaf where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.condition_mesh select * from clinical_trials_staging.condition_mesh where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.intervention_ancestor select * from clinical_trials_staging.intervention_ancestor where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.intervention_browse_branch select * from clinical_trials_staging.intervention_browse_branch where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.intervention_browse_leaf select * from clinical_trials_staging.intervention_browse_leaf where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.intervention_mesh select * from clinical_trials_staging.intervention_mesh where id in (select id from clinical_trials_staging.queue);

insert into clinical_trials.arm_group select id,seqnum,arm_group_label,arm_group_type,arm_group_type from clinical_trials_staging.arm_group where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.arm_group_intervention_name select * from clinical_trials_staging.arm_group_intervention_name where id in (select id from clinical_trials_staging.queue);

insert into clinical_trials.intervention select id,seqnum,intervention_type,intervention_name,intervention_description from clinical_trials_staging.intervention where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.intervention_arm_group_label select * from clinical_trials_staging.intervention_arm_group_label where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.intervention_other_name select * from clinical_trials_staging.intervention_other_name where id in (select id from clinical_trials_staging.queue);

insert into clinical_trials.location select id,seqnum,location_facility,location_status,location_city,location_state,location_zip,location_country from clinical_trials_staging.location where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.location_contact select * from clinical_trials_staging.location_contact where id in (select id from clinical_trials_staging.queue);

insert into clinical_trials.reference select id,seqnum,reference_pmid,reference_type,reference_citation from clinical_trials_staging.reference where id in (select id from clinical_trials_staging.queue);
insert into clinical_trials.retraction select * from clinical_trials_staging.retraction where id in (select id from clinical_trials_staging.queue);

