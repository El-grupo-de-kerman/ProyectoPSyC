package server;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.ws.rs.core.Response;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import jdo.User;
import server.Server;
import pojo.*;






public class ServerTest {
	
	 private Server resource;

	    @Mock
	    private PersistenceManager persistenceManager;

	    @Mock
	    private Transaction transaction;

	    @Before
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);

	        // initializing static mock object PersistenceManagerFactory
	        try (MockedStatic<JDOHelper> jdoHelper = Mockito.mockStatic(JDOHelper.class)) {
	            PersistenceManagerFactory pmf = mock(PersistenceManagerFactory.class);
	            jdoHelper.when(() -> JDOHelper.getPersistenceManagerFactory("datanucleus.properties")).thenReturn(pmf);
	            
	            when(pmf.getPersistenceManager()).thenReturn(persistenceManager);
	            when(persistenceManager.currentTransaction()).thenReturn(transaction);

	            // instantiate tested object with mock dependencies
	            resource = new Server();
	        }
	    }

	

	@Test
	public void testRegisterUser() {
		
	}

	@Test
	public void testLoginUser() {
		
	}

}
