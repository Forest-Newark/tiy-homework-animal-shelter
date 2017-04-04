create table if not exists ANIMAL (
	ANIMALID int identity primary key,
	NAME VARCHAR(20),
	SPECIES VARCHAR(20),
	BREED  VARCHAR(20),
	DESCRIPTION VARCHAR(30)
)