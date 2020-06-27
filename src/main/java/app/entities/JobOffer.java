package app.entities;

@Entity
public class Offer {

	@Getter
	@Setter
	@Id
	private Long id;

	@Getter
	@Setter
	@Column(name="job_title")
	private String jobTitle;

	@Getter
	@Setter
	@Column(name="start_date")
	private Date startDate;

	@Getter
	@Setter
	@Column(name="number_of_applications")
	private Integer numberOfApplications;

	public void incrementApplicants() {
		numberOfApplications++;
	}
}