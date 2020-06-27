package app;

import org.springframework.data.jpa.repository.JpaRepository;

interface JobOfferRepository extends JpaRepository<JobOffer, Long> {

}