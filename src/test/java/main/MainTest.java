package main;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import main.Main;
import pojo.UserData;

public class MainTest {
	
	@Mock
    private Client client;

    @Mock(answer=Answers.RETURNS_DEEP_STUBS)
    private WebTarget webTarget;

    @Captor
    private ArgumentCaptor<Entity<UserData>> userDataEntityCaptor;

    private Main main;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // prepare static mock of ClientBuilder
        try (MockedStatic<ClientBuilder> clientBuilder = Mockito.mockStatic(ClientBuilder.class)) {
            clientBuilder.when(ClientBuilder::newClient).thenReturn(client);
            when(client.target("http://localhost:8080/rest/resource")).thenReturn(webTarget);

            main = new Main("localhost", "8080");
        }
    }

	@Test
	public void testRegisterUser() {
		 when(webTarget.path("register")).thenReturn(webTarget);
		 
		 Response response = Response.ok().build();
		 when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
		 
		 verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
		 assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getName());
		 assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getMail());
		 assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
	}
	
	@Test
    public void testRegisterUserWithError() {
		when(webTarget.path("register")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
       
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getName());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getMail());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
    }
	
	@Test
	public void testLogUser() {
		 when(webTarget.path("login")).thenReturn(webTarget);
		 
		 Response response = Response.ok().build();
		 when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
		 
		 verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
		 assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getName());
		 assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getMail());
		 assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
	}
	
	@Test
    public void testLogUserWithError() {
		when(webTarget.path("login")).thenReturn(webTarget);

        Response response = Response.serverError().build();
        when(webTarget.request(MediaType.APPLICATION_JSON).post(any(Entity.class))).thenReturn(response);
       
        verify(webTarget.request(MediaType.APPLICATION_JSON)).post(userDataEntityCaptor.capture());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getName());
        assertEquals("test-login", userDataEntityCaptor.getValue().getEntity().getMail());
        assertEquals("passwd", userDataEntityCaptor.getValue().getEntity().getPassword());
    }
}
