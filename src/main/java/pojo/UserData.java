package pojo;

public class UserData {

    private String name;
    private String mail;
    private String password;
    
    
    /**
     * @brief Constructor vacio
     */
    public UserData() {
        // required by serialization
    }

    /**
     * @return Devuelve el nombre
     */
    public String getName() {
        return this.name;
    }

    /**
     * @brief Asignar el nombre
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
     * @return Devuelve el mail
     */
    public String getMail() {
		return mail;
	}
    
    /**
     * @brief Asignar el mail
     * @param mail
     */
    public void setMail(String mail) {
		this.mail = mail;
	}

    /**
     * @return Devuelve la password
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * @brief Asigna la password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *@brief Metodo para convertir el usuario en un string
     *@details Convierte el name y mail en un solo string
     *@return Devulve un string
     */
    public String toString() {
        return "[Name: " + name + ", mail: " + mail + "]";
    }
   
    
}