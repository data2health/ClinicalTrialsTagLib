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

select distinct log(((select count(*) from clinical_trials.study) * 1.0)
				   / (select count(distinct id) from clinical_trials_local.cui_cache where phrase='covid-19'))
from clinical_trials_local.cui_cache where phrase='covid-19';
