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
	
	/**
	 * @brief Constructor de la clase User.
	 * @details Asigna los parametros name, mail y password.
	 * @param name
	 * @param mail
	 * @param password
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

	/**
	 * @return Devuelve el nombre.
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * @return Devuelve el mail.
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * @return Devuelve la password.
	 */
	public String getPassword() {
		return this.password;
	}
	
	/**
	 * @details Asigna la password.
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	 //public Set<Message> getMessages() {return this.messages;}
	 
	 /**
	 *@brief Metodo "toString".
	 *@details Convierte un usuario con su nombre, mail y password en un solo String.
	 *@return Devuelve el String del usuario.
	 */
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
