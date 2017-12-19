package human;

import java.util.Scanner;
import java.util.TreeMap;

import human.Person.NotifWay;

public class PersonHandler {

	public enum Field{
        /*FIRSTNAME,
        LASTNAME1,
        LASTNAME2,*/
        PHONE,
        WHATSAPP,
        EMAIL,
        NOTIFWAY,
    }
	
	private int size;
    private TreeMap<String, Person> people;

    public PersonHandler() {
        this.size=0;
        this.people= new TreeMap<String, Person>();
    }

    public int getSize() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public TreeMap<String, Person> getPeople() {
        return people;
    }

    public void setPeople(TreeMap<String, Person> people) {
        this.people = people;
    }
    
    public Person addNewPerson(String firstName, String lastName1, String lastName2, long phone, boolean hasWhatsapp, String eMail, NotifWay notifWay) {
    	String id= buildId(firstName, lastName1, lastName2);
    	Person newPerson = new Person(id, firstName, lastName1, lastName2, phone, hasWhatsapp, eMail, notifWay);
    	Person prev = this.people.putIfAbsent(id, newPerson);
    	if(prev==null) {
    		setSize(this.size+1);
    	}
    	return prev;
    	
    }
    
    public String buildId(String firstName, String lastName1, String lastName2) {
    	if(lastName2.equals("")) return lastName1 + "."+firstName.charAt(0);
        else return lastName1 +lastName2+ "."+firstName.charAt(0);
    }
    
    public Person deletePerson(String id) {
    	Person deletedPerson = this.people.remove(id);
    	if (deletedPerson!=null) setSize(this.size-1);
    	return deletedPerson;
    }
    
    public Person deletePerson(String firstName, String lastName1, String lastName2) {
    	String idToDelete = buildId(firstName, lastName1, lastName2);
    	return deletePerson(idToDelete);
    }
    
    public Person searchPerson(String id) {
    	return this.people.get(id);
    }
    
    public Person searchPerson(String firstName, String lastName1, String lastName2) {
    	String idToSearch = buildId(firstName, lastName1, lastName2);
    	return searchPerson(idToSearch);
    }
    
    private void editPerson(Person person, int size, Field[] fields, String[] texts) {
    	for(int i=0; i<size; i++) {
    		Field field = fields[i];
    		String text = texts[i];
    		if(field.equals(Field.PHONE)) {
    			person.setPhone(Long.parseLong(text));
    		}else if(field.equals(Field.WHATSAPP)){
    			person.setHasWhatsapp(text.equals("1"));
    		}else if(field.equals(Field.EMAIL)) {
    			person.setEMail(text);
    		}else if(field.equals(Field.NOTIFWAY)) {
    			NotifWay notifWay;
    			if(text.equals("SMS"))notifWay=NotifWay.SMS;
    			else if(text.equals("WHATSAPP")) notifWay=NotifWay.WHATSAPP;
    			else notifWay=NotifWay.EMAIL;
    			person.setNotifWay(notifWay);
    		}
    	}
    }
    
    public Person editPerson(String id, int size, Field[] fields, String[] texts) {
    	Person person = searchPerson(id);
    	if(person!=null) {
        	editPerson(person, size, fields, texts);
        	return person;
    	} else return null;
    }
    
    /*public Person editPerson(String firstName, String lastName1, String lastName2, int size, Field[] fields, String[] texts) {
    	Person person = searchPerson(firstName, lastName1, lastName2);
    	if(person!=null) {
        	editPerson(person, size, fields, texts);
        	return person;
    	} else return null;
    }*/
    
    public static void main(String[] args) {
		
    	Scanner sc = new Scanner(System.in);
    	PersonHandler personHandler = new PersonHandler();
    	
    	System.out.println("##################  PayReg  ######################");
    	System.out.println();
    	System.out.println("This is PersonHandler Test.");
    	System.out.println("PersonHandler instantiated. Initial atributes:");
    	System.out.println("Number of Persons (size of the tree): "+personHandler.getSize());
    	System.out.println();
    	
    	int n= Integer.parseInt(sc.nextLine().trim());
    	for(int i=0; i<n; i++) {
    		String command = sc.nextLine().trim();
    		if(command.equals("Add")) {
    			String line = sc.nextLine().trim();
    			Scanner s = new  Scanner(line);
    			String firstName = s.next().trim();
    			String lastName1 = s.next().trim();
    			String lastName2 = s.next().trim();
    			long phone = Long.parseLong(s.next().trim());
    			int hWhatsapp = Integer.parseInt(s.next().trim());
    			String eMail = s.next().trim();
    			String nWay = s.next().trim();
    			s.close();
    			boolean hasWhatsapp;
    			if(hWhatsapp==1) hasWhatsapp=true;
    			else hasWhatsapp=false;
    			
    			NotifWay notifWay;
    			if(nWay.equals("SMS"))notifWay=NotifWay.SMS;
    			else if(nWay.equals("WHATSAPP")) notifWay=NotifWay.WHATSAPP;
    			else notifWay=NotifWay.EMAIL;
    			
    			Person prev = personHandler.addNewPerson(firstName, lastName1, lastName2, 
    												phone, hasWhatsapp, eMail, notifWay);
    			if(prev==null)System.out.println("Person was added correctly");
    			else System.out.println("Could not add the new Person. "
    					+ "A Person with the same name has added: "+prev.toString());
    		}else if(command.equals("DeleteId")) {
    			
    			String id = sc.nextLine().trim();
    			
    			Person deletedPerson = personHandler.deletePerson(id);
    			if (deletedPerson!=null)System.out.println("Deleted Person: "
    														+deletedPerson.toString());
    			else System.out.println(id +" does not exist." );
    		}else if(command.equals("DeleteName")) {
    			String line = sc.nextLine().trim();
    			Scanner s = new  Scanner(line);
    			String firstName = s.next().trim();
    			String lastName1 = s.next().trim();
    			String lastName2 = s.next().trim();
    			s.close();
    			Person deletedPerson = personHandler.deletePerson(firstName, lastName1, lastName2);
    			if (deletedPerson!=null)System.out.println("Deleted Person: "+deletedPerson.toString());
    			else System.out.println(firstName + " " + lastName1 + " " + lastName2 +" does not exist." );
    		}else if(command.equals("SearchId")) {
    			String id = sc.nextLine().trim();
    			
    			Person searchedPerson = personHandler.searchPerson(id);
    			if (searchedPerson!=null)System.out.println("Person was found: "+searchedPerson.toString());
    			else System.out.println(id +" does not exist." );
    		}else if(command.equals("SearchName")) {
    			String line = sc.nextLine().trim();
    			Scanner s = new  Scanner(line);
    			String firstName = s.next().trim();
    			String lastName1 = s.next().trim();
    			String lastName2 = s.next().trim();
    			s.close();
    			Person searchedPerson = personHandler.searchPerson(firstName, lastName1, lastName2);
    			if (searchedPerson!=null)System.out.println("Person was found: "+searchedPerson.toString());
    			else System.out.println(firstName + " " + lastName1 + " " + lastName2 +" does not exist." );
    		}else if(command.equals("EditId")) {
    			String line = sc.nextLine().trim();
    			Scanner s = new  Scanner(line);
    			String id = s.next().trim();
    			int changes = Integer.parseInt(s.next().trim());
    			Field[] fields = new Field[changes];
    			String[] texts = new String[changes];
    			for(int k=0; k<changes;k++) {
    				String fld = s.next().trim();
    				Field field;
    				if(fld.equals("PHONE"))field = Field.PHONE;
    				else if(fld.equals("WHATSAPP")) field = Field.WHATSAPP;
    				else if(fld.equals("EMAIL")) field = Field.EMAIL;
    				else field = Field.NOTIFWAY;
    				fields[k]=field;
    				
    				String text = s.next().trim();
    				texts[k]=text;
    			}
    			s.close();
    			Person editedPerson = personHandler.editPerson(id, changes, fields, texts);
    			if (editedPerson!=null)System.out.println("Person was edited: "+editedPerson.toString());
    			else System.out.println(id +" does not exist." );
    		}else {
    			System.out.println("Wrong command");
    			if (sc.hasNextLine())sc.nextLine();
    		}
    	}
    	sc.close();
    	System.out.println("Number of Persons (size of the tree): "+personHandler.getSize());
    	System.out.println("Test ended.");
	}
    
    /*
	 * Trivial example to test the Class:
	 * 
7
Add
Christian Cuestas Ibáñez 3016755660 1 cccuestasi@gmail.com WHATSAPP
SearchId
CuestasIbáñez.C
SearchName
Christian Cuestas Ibáñez
EditId
CuestasIbáñez.C 1 WHATSAPP 0
DeleteId
CuestasIbáñez.C
DeleteName
Christian Cuestas Ibáñez
cOmandrare

	 */
}
