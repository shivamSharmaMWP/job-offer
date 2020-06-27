package app;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/job")
class JobOfferController {

    @Autowired
    private JobOfferRepository jobOfferRepository;

    @Autowired
    private JobApplicationRepository jobApplicationRepository;

    // creating job offer
    @PostMapping
    public ResponseEntity <JobOffer> createOffer(@RequestBody JobOffer newJobOffer) {
        JobOffer offer = jobOfferRepository.save(newJobOffer);
        return ResponseEntity.created(offer);
    }

    // reading single job offer
    @GetMapping("/{id}")
    public JobOffer getOne(@PathVariable Long id) {

        return jobOfferRepository.findById(id)
            .orElseThrow(() -> new Exception("JobOffer with id " + id + " not found"));
    }

    // fetching all job offer. 
    @GetMapping
    public List<JobOffer> getAll() {
        return jobOfferRepository.findAll();
    }


    // --------------------------- job applications //

    // listing applications for a particular job offer
    @GetMapping("/{jobOfferId}/application")
    public List<JobApplication> listJobApplications(@PathVariable Long jobOfferId) {
        JobOffer offer = jobOfferRepository.findById(jobOfferId);
        if (offer == null)
            throw new Exception("job offer doesn't exists for id " + jobOfferId);

        return jobApplicationRepository.findByRelatedOffer(offer);
        .orElseThrow(() -> new Exception("JobApplications for offer " + jobOfferId + " not found"));
    }

    // get one application
    @GetMapping("/{jobOfferId}/application/{jobApplicationId}")
    public List<JobApplication> getOneJobApplications(@PathVariable Long jobOfferId,
        @PathVariable Long jobApplicationId) {

        JobOffer offer = jobOfferRepository.findById(jobOfferId);
        if (offer == null)
            throw new Exception("job offer doesn't exists for id " + jobOfferId);

        return jobApplicationRepository.findById(jobApplicationId)
            .orElseThrow(() -> new Exception("JobApplications for id" + jobApplicationId + " not found"));
    }

    // creating application for particular jobOffer
    @PostMapping("/{jobOfferId}/application")
    public List<JobApplication> applyForJobOffer(
        @PathVariable Long jobOfferId,
        @RequestBody JobApplication jobApplication) {

        JobOffer jobOffer = jobOfferRepository.findById(jobOfferId);
        if (jobOffer == null)
            throw new Exception("job offer doesn't exists for id " + jobOfferId);

        jobApplication.setRelatedOffer(jobOffer);

        JobApplication application = jobApplicationRepository.saveOrUpdate(jobApplication);
        jobOffer.incrementNumberOfApplicants();
        jobOfferRepository.save(jobOffer);
        return ResponseEntity.created(application);

    }

}