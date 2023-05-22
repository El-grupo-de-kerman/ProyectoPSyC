package main;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import pojo.UserData;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fuentes.GestorAgenda;
import gui.LogInWindow;

public class Main {

	protected static final Logger logger = LogManager.getLogger();

	//private static final String USER = "dipina";
	//private static final String PASSWORD = "dipina";


	private Client client;
	private WebTarget webTarget;

	
	/**
	 * @brief 
	 * @details 
	 * @param hostname
	 * @param port
	 */
	public Main(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
	}

	/**
	 * @brief Metodo para registrar un usuario
	 * @details El metodo registra un usuario con su nombre, mail y password
	 * @param name
	 * @param mail
	 * @param password
	 */
	public void registerUser(String name, String mail, String password) {
		WebTarget registerUserWebTarget = webTarget.path("register");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		UserData userData = new UserData();
		userData.setName(name);
		userData.setMail(mail);
		userData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User correctly registered");
		}
	}
	
	/**
	 * @brief Metodo para hacer log con un usuaio
	 * @details Con un usuario prevamente registrado se hace log con su nombre y password
	 * @param name
	 * @param password
	 */
	public void logUser(String name, String password) {
		WebTarget registerUserWebTarget = webTarget.path("login");
		Invocation.Builder invocationBuilder = registerUserWebTarget.request(MediaType.APPLICATION_JSON);
		
		UserData userData = new UserData();
		userData.setName(name);
		userData.setPassword(password);
		Response response = invocationBuilder.post(Entity.entity(userData, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}", response.getStatus());
		} else {
			logger.info("User correctly logged");
			GestorAgenda mgt = new GestorAgenda();
			mgt.lanza();
		}
	}
	//Prueba de commit
	/*
	public void sayMessage(String login, String password, String message) {
		WebTarget sayHelloWebTarget = webTarget.path("sayMessage");
		Invocation.Builder invocationBuilder = sayHelloWebTarget.request(MediaType.APPLICATION_JSON);

		DirectMessage directMessage = new DirectMessage();
		UserData userData = new UserData();
		userData.setLogin(login);
		userData.setPassword(password);

		directMessage.setUserData(userData);

		MessageData messageData = new MessageData();
		messageData.setMessage(message);
		directMessage.setMessageData(messageData);

		Response response = invocationBuilder.post(Entity.entity(directMessage, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			logger.error("Error connecting with the server. Code: {}",response.getStatus());
		} else {
			String responseMessage = response.readEntity(String.class);
			logger.info("* Message coming from the server: '{}'", responseMessage);
		}
	}
	*/

	/**
	 * @brief Metodo del main para hacer funcionar el programa
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length != 2) {
			logger.info("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		Main main = new Main(hostname, port);
		new LogInWindow(main);
		//exampleClient.registerUser(USER, PASSWORD);
		//exampleClient.sayMessage(USER, PASSWORD, "This is a test!...");
	}
}