insert into countrymaster (country_id,country_code,country_name) values( 1,'+91','India');
insert into countrymaster (country_id,country_code,country_name) values(2,'+1','USA');
insert into statemaster (state_id,state_name,country_id) values(1,'Maharashtra',1);
insert into statemaster  (state_id,state_name,country_id) values(2,'Karnataka',1);
insert into statemaster  (state_id,state_name,country_id) values(3,'New Jercy',2);
insert into statemaster  (state_id,state_name,country_id) values(4,'Ohio',2);
insert into citymaster (city_id,city_name,state_id) values(1,'Satara',1);
insert into citymaster (city_id,city_name,state_id) values(2,'Pune',1);                              
insert into citymaster (city_id,city_name,state_id) values(3,'Banglore',2);                               