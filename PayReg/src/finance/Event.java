package finance;

import java.util.Date;
import java.util.Hashtable;
import java.util.TreeMap;

public class Event {

	
	private String name;
	private int load;
	private int cost;
	private int totalCollected;
	private boolean isActive;
	private Hashtable<String, TreeMap<Date,Payment>> data;
	
	public Event(String name, int cost) {
		super();
		this.isActive=true;
		this.name = name;
		this.load = 0;
		this.cost = cost;
		this.totalCollected = 0;
		this.data = new Hashtable<String, TreeMap<Date,Payment>> ();
	}

	public boolean isActive() {
		return isActive;
	}

	protected void isActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	public int getLoad() {
		return load;
	}

	protected void setLoad(int load) {
		this.load = load;
	}

	public int getCost() {
		return cost;
	}

	protected void setCost(int cost) {
		this.cost = cost;
	}


	public int getTotalCollected() {
		return totalCollected;
	}


	protected void setTotalCollected(int totalCollected) {
		this.totalCollected = totalCollected;
	}

	public Hashtable<String, TreeMap<Date, Payment>> getData() {
		return data;
	}

	protected void setData(Hashtable<String, TreeMap<Date, Payment>> data) {
		this.data = data;
	}
	
	public int getCostByPerson() {
		if(this.load==0)return -1;
		return this.cost/this.load;
	}
	
	public void addToCost(int addAmount) {
		this.setCost(this.cost+addAmount);
	}
	
	public int getRemains() {
		return this.getCost()-this.getTotalCollected();
	}
	
	public void addNewPayment(String personId, Date date, int amount) {
		TreeMap<Date, Payment> paymentList = this.data.get(personId);
		Payment payment = new Payment(date, amount, personId);
		if (paymentList!=null){
			paymentList.put(date, payment);
		} else {
			paymentList = new TreeMap<Date, Payment>();
			paymentList.put(date, payment);
			this.data.put(personId, paymentList);
		}
	}
	
	public Payment searchPayment(String personId, Date date) {
		Payment payment = this.data.get(personId).get(date);
		if(payment == null) return null;
		else return payment;
	}
	
	protected Payment deletePayment(String personId, Date date) {
		return this.data.get(personId).remove(date);
	}

	@Override
	public String toString() {
		return "[" + name + ", " + isActive + ", " + load + " personas, " + cost
				+ ", " + this.getCostByPerson() + " por persona, " + totalCollected + ", faltan "+ this.getRemains()+"]";
	}
	
}
