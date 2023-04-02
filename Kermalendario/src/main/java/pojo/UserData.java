package pojo;

public class UserData {

    private String name;
    private String mail;
    private String password;

    public UserData() {
        // required by serialization
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getMail() {
		return mail;
	}
    
    public void setMail(String mail) {
		this.mail = mail;
	}

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "[Name: " + name + ", mail: " + mail + "]";
    }
}