package model;

public class PanCardDetails extends UserConfidentialDetails{
	private int number;
	private String name;
	private String address;
	public PanCardDetails(int userId, int dataId, String nameOfConfidentialData, String requiredDetails) {
		super(userId, dataId, nameOfConfidentialData, requiredDetails);
	}
	public PanCardDetails(int userId, int dataId, String nameOfConfidentialData, String requiredDetails, int number,String name, String address) {
		super(userId, dataId, nameOfConfidentialData, requiredDetails);
		this.number = number;
		this.name = name;
		this.address = address;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
