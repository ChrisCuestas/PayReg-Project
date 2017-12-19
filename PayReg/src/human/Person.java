package human;

public class Person {

    public enum NotifWay{
        SMS,
        WHATSAPP,
        EMAIL,
    }

    private String id; //This ID is to handle the data within a Key

    //Personal information
    private String firstName;
    private String lastName1;
    private String lastName2;
    private long phone;
    private boolean hasWhatsapp;
    private String eMail;
    private NotifWay notifWay;

    public Person(String id, 
    		String firstName, 
    		String lastName1, 
    		String lastName2, 
    		long phone, 
    		boolean hasWhatsapp, 
    		String eMail, 
    		NotifWay notifWay) {
        this.id = id;
    	this.firstName = firstName;
        this.lastName1 = lastName1;
        this.lastName2 = lastName2;
        this.phone = phone;
        this.hasWhatsapp = hasWhatsapp;
        this.eMail = eMail;
        this.notifWay = notifWay;
    }

    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName1() {
        return lastName1;
    }

    public String getLastName2() {
        return lastName2;
    }

    public long getPhone() {
        return phone;
    }

    protected void setPhone(long phone) {
        this.phone = phone;
    }

    public boolean hasWhatsapp() {
        return hasWhatsapp;
    }

    protected void setHasWhatsapp(boolean hasWhatsapp) {
        this.hasWhatsapp = hasWhatsapp;
    }

    public String getEMail() {
        return eMail;
    }

    protected void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public NotifWay getNotifWay() {
        return notifWay;
    }

    protected void setNotifWay(NotifWay notifWay) {
        this.notifWay = notifWay;
    }

	@Override
	public String toString() {
		return  id + ", " + firstName + ", " + lastName1 + ", " + lastName2	+ ", " + 
				phone + ", " + hasWhatsapp + ", " + eMail + ", " + notifWay;
	}  
}

