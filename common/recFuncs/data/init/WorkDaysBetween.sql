create or replace function WorkDaysBetween(startDate in Date, endDate in Date) return integer is
  Result integer;
begin
  select (case when startDate is null or endDate is null then null else (select count(*)-1 from b_workdaysmang m where 
m.fisworkday='是' and FDate between trunc(sysdate) and trunc( endDate)) end) into Result from dual ;
  return(Result);
end WorkDaysBetween;
/
