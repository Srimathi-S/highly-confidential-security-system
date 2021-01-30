package model;

public class UserDetails {
	private int userId;
	private int dataId;
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
	public int getDataId() {
		return dataId;
	}
	public void setDataId(int dataId) {
		this.dataId = dataId;
	}
	
	public UserDetails( String nameOfConfidentialData, String requiredDetails) {
		this.nameOfConfidentialData = nameOfConfidentialData;
		this.requiredDetails = requiredDetails;
	}
	
	public UserDetails(int userId, int dataId, String nameOfConfidentialData, String requiredDetails) {
		this.userId = userId;
		this.dataId = dataId;
		this.nameOfConfidentialData = nameOfConfidentialData;
		this.requiredDetails = requiredDetails;
	}
	
	
	public UserDetails( int dataId,String nameOfConfidentialData, String requiredDetails) {
		this.dataId=dataId;
		this.nameOfConfidentialData = nameOfConfidentialData;
		this.requiredDetails = requiredDetails;
	}
	
	
	
	
	
	
	
}
