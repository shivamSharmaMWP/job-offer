
CREATE TABLE job_offer (
	id INT PRIMARY KEY,
	job_title VARCHAR(200) UNIQUE,
	start_date date,
	number_of_applications INT DEFAULT 0
);

CREATE TYPE job_application_status AS ENUM('APPLIED', 'INVITED', 'REJECTED', 'HIRED');

CREATE TABLE job_application (
	id INT PRIMARY KEY,
	related_offer_id INT,
	candidate_email VARCHAR(100),
	resume_text VARCHAR(300),
	application_status VARCHAR(20) NOT NULL,

	UNIQUE (related_offer_id, candidate_email),

	FOREIGN KEY (related_offer_id) REFERENCES job_offer(id)
);
