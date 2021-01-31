package model;

public class DebitCardDetails extends UserConfidentialDetails{
	private String number;
	private String nameOnCard;
	private String cvv;
	
	public DebitCardDetails(int userId, String requiredDetails, String number,String nameOnCard, String cvv) {
		super(userId, "Debit card", requiredDetails);
		this.number = number;
		this.nameOnCard = nameOnCard;
		this.cvv = cvv;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getNameOnCard() {
		return nameOnCard;
	}
	public void setNameOnCard(String nameOnCard) {
		this.nameOnCard = nameOnCard;
	}
	public String getCvv() {
		return cvv;
	}
	public void setCvv(String cvv) {
		this.cvv = cvv;
	}
	
	
}
