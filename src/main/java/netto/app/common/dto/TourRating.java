package netto.app.common.dto;

public class TourRating {

	private String tourCode;	
	private Integer score;
	private String comment;
	
	public TourRating() { }
	
	public TourRating(String tourCode, Integer score, String comment) { 
		this.tourCode=tourCode;
		this.score=score;
		this.comment=comment;
	}

	public String getTourCode() {
		return tourCode;
	}

	public void setTourCode(String tourCode) {
		this.tourCode = tourCode;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	

    public String toString() {
    	return new StringBuilder("tourCode: ").append(tourCode)
    			.append(", score: ").append(score)
    			.append(", comment: ").append(comment).toString();
    }
}
