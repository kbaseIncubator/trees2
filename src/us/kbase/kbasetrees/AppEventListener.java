package us.kbase.kbasetrees;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppEventListener implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		try {
			KBaseTreesServer.getTaskQueue();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			KBaseTreesServer.getTaskQueue().stopAllThreads();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
