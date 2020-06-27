package app.entities;

@Entity
public class JobApplication {

	@Getter
	@Setter
	@Id
	private Long id;

	@Getter
	@Setter
	@Column(name="related_offer_id")
	@OneToOne
	private JobOffer relatedOffer;

	@Getter
	@Setter
	@Column(name="candidate_email")
	private String candidateEmail;

	@Getter
	@Setter
	@Column(name="resume_text")
	private String resumeText;

	@Getter
	@Setter
	@Column(name="application_status")
	private JobApplicationStatus applicationStatus;

}