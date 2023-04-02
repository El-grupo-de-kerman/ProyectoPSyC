package jdo;

import java.util.Set;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;
import java.util.HashSet;

@PersistenceCapable
public class User {
	@PrimaryKey
	String name = null;
	String mail = null;
	String password = null;
	
	/*
	@Persistent(mappedBy="user", dependentElement="true")
	@Join
	Set<Message> messages = new HashSet<>();
	*/
	
	
	public User(String name, String mail, String password) {
		this.name = name;
		this.mail = mail;
		this.password = password;
	}
	
	/*
	public void addMessage(Message message) {
		messages.add(message);
	}

	public void removeMessage(Message message) {
		messages.remove(message);
	}
	*/

	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	 //public Set<Message> getMessages() {return this.messages;}
	 
	 public String toString() {
		 /*
		StringBuilder messagesStr = new StringBuilder();
		for (Message message: this.messages) {
			messagesStr.append(message.toString() + " - ");
		}
		*/
        return "User: name --> " + this.name + ", mail -->  " + this.mail + "";
        		//+ ", messages --> [" + messagesStr + "]";
    }
}
