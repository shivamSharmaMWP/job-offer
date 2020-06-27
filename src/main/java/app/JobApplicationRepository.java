package app;

import org.springframework.data.jpa.repository.JpaRepository;

interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {

	List<JobOffer> findByRelatedOffer(JobOffer jobOffer);

}