package whg;
/*
 * JdbcClinicTest.java
 * Live JUnit based test
 *
 */

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.ContextRefreshedEvent;

import java.io.IOException;

public class Main {

	/** Logger for this class */
	private static final Log logger = LogFactory.getLog(Main.class);

	public static void main(String[] args) throws IOException {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/myContext.xml");

		MyBean myBean = (MyBean) ctx.getBean("myBean");
		myBean.say();

		logger.info("##########>");
		System.out.println("---------->");
		ctx.publishEvent(new ContextRefreshedEvent(ctx));
	}

}
