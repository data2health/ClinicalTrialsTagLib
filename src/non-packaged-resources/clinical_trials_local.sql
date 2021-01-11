-- tf = term count  in doc /  total count in doc
select id,phrase,sum(count) as term_count from clinical_trials_local.cui_cache group by 1,2 order by 1,2;
select id,sum(count) as total_count from clinical_trials_local.cui_cache group by 1;

select foo.id, phrase, term_count, total_count, term_count * 1.0 / total_count
from  (select id,phrase,sum(count) as term_count from clinical_trials_local.cui_cache group by 1,2) as foo,
      (select id,sum(count) as total_count from clinical_trials_local.cui_cache group by 1 having sum(count) > 0) as bar
where foo.id=bar.id order by 1,2;

-- idf = log (  number of  documents / number  of docs that have term)
select count(*) from clinical_trials.study;
select count(distinct id) from clinical_trials_local.cui_cache where phrase='covid-19';

select phrase, log(((select count(*) from clinical_trials.study) * 1.0) / count(distinct id)) as idf from clinical_trials_local.cui_cache group by 1;

--- tf-idf

select foo.id, foo.phrase, term_count, total_count, (term_count * 1.0 / total_count) as tf, idf, (term_count * 1.0 / total_count)*idf as tf_idf
from  (select id,phrase,sum(count) as term_count from clinical_trials_local.cui_cache group by 1,2) as foo,
      (select id,sum(count) as total_count from clinical_trials_local.cui_cache group by 1 having sum(count) > 0) as bar,
      (select phrase, log(((select count(*) from clinical_trials.study) * 1.0) / count(distinct id)) as idf from clinical_trials_local.cui_cache group by 1) as dum
where foo.id=bar.id and foo.phrase=dum.phrase order by 1,2;

--- trying for speed

--- by phrase

create materialized view clinical_trials_local.total_count as
select id,sum(count) as total_count from clinical_trials_local.cui_cache group by 1 having sum(count) > 0;

create index totid on clinical_trials_local.total_count(id);

create materialized view clinical_trials_local.phrase_term_count as
select id,phrase,sum(count) as term_count from clinical_trials_local.cui_cache group by 1,2;

create index ptcid on clinical_trials_local.phrase_term_count(id);
create index ptcp on clinical_trials_local.phrase_term_count(phrase);

create materialized view clinical_trials_local.phrase_idf as
select phrase, log(((select count(*) from clinical_trials.study) * 1.0) / count(distinct id)) as idf from clinical_trials_local.cui_cache group by 1;

create index pidfp on clinical_trials_local.idf(phrase);

create materialized view clinical_trials_local.phrase_tf_idf as
select phrase_term_count.id, phrase_term_count.phrase, term_count, total_count, (term_count * 1.0 / total_count) as tf, idf, (term_count * 1.0 / total_count)*idf as tf_idf
from clinical_trials_local.phrase_term_count, clinical_trials_local.total_count, clinical_trials_local.phrase_idf
where term_count.id=total_count.id and term_count.phrase=idf.phrase order by 1,2;

create index ptfidfid on clinical_trials_local.tf_idf(id);
create index ptfidfp on clinical_trials_local.tf_idf(phrase);

--- by cui

create materialized view clinical_trials_local.cui_term_count as
select id,cui,sum(count) as term_count from clinical_trials_local.cui_cache group by 1,2;

create index ctcid on clinical_trials_local.cui_term_count(id);
create index ctcp on clinical_trials_local.cui_term_count(cui);

create materialized view clinical_trials_local.cui_idf as
select cui, log(((select count(*) from clinical_trials.study) * 1.0) / count(distinct id)) as idf from clinical_trials_local.cui_cache group by 1;

create index cidfp on clinical_trials_local.cui_idf(cui);

create materialized view clinical_trials_local.cui_tf_idf as
select cui_term_count.id, cui_term_count.cui, term_count, total_count, (term_count * 1.0 / total_count) as tf, idf, (term_count * 1.0 / total_count)*idf as tf_idf
from clinical_trials_local.cui_term_count, clinical_trials_local.total_count, clinical_trials_local.cui_idf
where cui_term_count.id=total_count.id and cui_term_count.cui=cui_idf.cui order by 1,2;

create index ctfidfid on clinical_trials_local.cui_tf_idf(id);
create index ctfidfp on clinical_trials_local.cui_tf_idf(cui);

----

select cui,str from mrconso where tty='PN' order by cui;
select * from mrsty natural join clinical_trials_local.cui_cache limit 1000;

create materialized view clinical_trials_local.cui_view as
select id,mrconso.cui,str,stn,sty,term_count,total_count,tf,idf,tf_idf
from clinical_trials_local.cui_tf_idf, umls.mrconso, umls.mrsty
where cui_tf_idf.cui=mrconso.cui
  and mrconso.cui=mrsty.cui
  and mrconso.tty='PN'
;

----

create materialized view n3c_trials.cui_cache as
select * from clinical_trials_local.cui_cache
where id in (select id from n3c_trials.study)
  and phrase !~ '^[0-9]+$'
  and phrase !~ '^[a-z]{1,2}$'
  and phrase not in ('and','all','for','with')
;

create materialized view n3c_trials.total_count as
select id,sum(count) as total_count from n3c_trials.cui_cache group by 1 having sum(count) > 0;

create index totid on n3c_trials.total_count(id);

create materialized view n3c_trials.cui_term_count as
select id,cui,sum(count) as term_count from n3c_trials.cui_cache group by 1,2;

create index ctcid on n3c_trials.cui_term_count(id);
create index ctcp on n3c_trials.cui_term_count(cui);

create materialized view n3c_trials.cui_idf as
select cui, log(((select count(*) from n3c_trials.study) * 1.0) / count(distinct id)) as idf from n3c_trials.cui_cache group by 1;

create index cidfp on n3c_trials.cui_idf(cui);

create materialized view n3c_trials.cui_tf_idf as
select cui_term_count.id, cui_term_count.cui, term_count, total_count, (term_count * 1.0 / total_count) as tf, idf, (term_count * 1.0 / total_count)*idf as tf_idf
from n3c_trials.cui_term_count, n3c_trials.total_count, n3c_trials.cui_idf
where cui_term_count.id=total_count.id and cui_term_count.cui=cui_idf.cui order by 1,2;

create index ctfidfid on n3c_trials.cui_tf_idf(id);
create index ctfidfp on n3c_trials.cui_tf_idf(cui);

create materialized view n3c_trials.phrase_term_count as
select id,cui,sum(count) as term_count from n3c_trials.cui_cache group by 1,2;

create index ptcid on n3c_trials.phrase_term_count(id);
create index ptcp on n3c_trials.phrase_term_count(cui);

create materialized view n3c_trials.phrase_idf as
select cui, log(((select count(*) from n3c_trials.study) * 1.0) / count(distinct id)) as idf from n3c_trials.cui_cache group by 1;

create index pidfp on n3c_trials.phrase_idf(cui);

create materialized view n3c_trials.phrase_tf_idf as
select phrase_term_count.id, phrase_term_count.cui, term_count, total_count, (term_count * 1.0 / total_count) as tf, idf, (term_count * 1.0 / total_count)*idf as tf_idf
from n3c_trials.phrase_term_count, n3c_trials.total_count, n3c_trials.phrase_idf
where phrase_term_count.id=total_count.id and phrase_term_count.cui=phrase_idf.cui order by 1,2;

create index ptfidfid on n3c_trials.phrase_tf_idf(id);
create index ptfidfp on n3c_trials.phrase_tf_idf(cui);

----

create table n3c_trials.cui_suppress(cui text primary key, suppress boolean);

-- scripting

ct_index
echo "refreshing UMLS total counts..."
psql -X -h hal.local -a -c "refresh materialized view clinical_trials_local.total_count" cd2h
echo "refreshing UMLS phrase term counts..."
psql -X -h hal.local -a -c "refresh materialized view clinical_trials_local.phrase_term_count" cd2h
echo "refreshing UMLS phrase idf values..."
psql -X -h hal.local -a -c "refresh materialized view clinical_trials_local.phrase_idf" cd2h
echo "refreshing UMLS phrase tf*idf values..."
psql -X -h hal.local -a -c "refresh materialized view clinical_trials_local.phrase_tf_idf" cd2h
echo "refreshing UMLS cui term counts..."
psql -X -h hal.local -a -c "refresh materialized view clinical_trials_local.cui_term_count" cd2h
echo "refreshing UMLS cui idf values..."
psql -X -h hal.local -a -c "refresh materialized view clinical_trials_local.cui_idf" cd2h
echo "refreshing UMLS cui tf*idf values..."
psql -X -h hal.local -a -c "refresh materialized view clinical_trials_local.cui_tf_idf" cd2h

echo "refreshing COVID trial counts..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.study" cd2h
echo "refreshing COVID trial central_contact..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.central_contact" cd2h
echo "refreshing COVID trial collaborator..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.collaborator" cd2h
echo "refreshing COVID trial condition..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.condition" cd2h
echo "refreshing COVID trial condition_mesh..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.condition_mesh" cd2h
echo "refreshing COVID trial covid_id..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.covid_id" cd2h
echo "refreshing COVID trial cui_cache..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.cui_cache" cd2h
echo "refreshing COVID trial cui_view..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.cui_view" cd2h
echo "refreshing COVID trial intervention..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.intervention" cd2h
echo "refreshing COVID trial intervention_mesh..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.intervention_mesh" cd2h
echo "refreshing COVID trial location..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.location" cd2h
echo "refreshing COVID trial location_contact..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.location_contact" cd2h
echo "refreshing COVID trial overall_official..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.overall_official" cd2h
echo "refreshing COVID UMLS total counts..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.total_count" cd2h
echo "refreshing COVID UMLS phrase term counts..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.phrase_term_count" cd2h
echo "refreshing COVID UMLS phrase idf values..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.phrase_idf" cd2h
echo "refreshing COVID UMLS phrase tf*idf values..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.phrase_tf_idf" cd2h
echo "refreshing COVID UMLS cui term counts..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.cui_term_count" cd2h
echo "refreshing COVID UMLS cui idf values..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.cui_idf" cd2h
echo "refreshing COVID UMLS cui tf*idf values..."
psql -X -h hal.local -a -c "refresh materialized view n3c_trials.cui_tf_idf" cd2h

