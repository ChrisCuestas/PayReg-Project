package finance;

import java.util.Date;

public class Payment {
	
	private Date date;
	private int amount;
	private String personId;
	
	public Payment(Date date, int amount, String personId) {
		super();
		this.date = date;
		this.amount = amount;
		this.personId = personId;
	}

	public Date getDate() {
		return date;
	}

	protected void setDate(Date date) {
		this.date = date;
	}

	public int getAmount() {
		return amount;
	}

	protected void setAmount(int amount) {
		this.amount = amount;
	}

	public String getPersonId() {
		return personId;
	}

	protected void setPersonId(String personId) {
		this.personId = personId;
	}

	@Override
	public String toString() {
		return "[" + date.toString() + ", " + amount + ", " + personId + "]";
	}
	
}
