package model;

public class UserConfidentialDetails {
	private int userId;
	private String nameOfConfidentialData;
	private String requiredDetails;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getNameOfConfidentialData() {
		return nameOfConfidentialData;
	}
	public void setNameOfConfidentialData(String nameOfConfidentialData) {
		this.nameOfConfidentialData = nameOfConfidentialData;
	}
	public String getRequiredDetails() {
		return requiredDetails;
	}
	public void setRequiredDetails(String requiredDetails) {
		this.requiredDetails = requiredDetails;
	}
	
	public UserConfidentialDetails( String nameOfConfidentialData, String requiredDetails) {
		this.nameOfConfidentialData = nameOfConfidentialData;
		this.requiredDetails = requiredDetails;
	}
	
	public UserConfidentialDetails(int userId, String nameOfConfidentialData, String requiredDetails) {
		this.userId = userId;
		this.nameOfConfidentialData = nameOfConfidentialData;
		this.requiredDetails = requiredDetails;
	}
	

	
	
	
	
	
	
	
}
