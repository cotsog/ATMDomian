package steinKo.ATM.test.integration;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import java.util.concurrent.CountDownLatch;


import org.junit.BeforeClass;
import org.junit.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;




import steinKo.ATM.BankFactory;
import steinKo.ATM.Domain;

import steinKo.ATM.repository.BankRepository;
import steinKo.ATM.test.integration.AbstractIntegration;



public class BankFactoryTest extends AbstractIntegration {
	final Logger logger = LoggerFactory.getLogger(BankFactoryTest.class);

	static private SpringApplication springApplication;
	static private ApplicationContext applicationContext;
	static private Runnable bankFactory;
	static private CountDownLatch countDownLatch;
	static private ApplicationContext toBeLoaded;
	
	
	
	@BeforeClass
	public static void setUp()
	{
		springApplication =new SpringApplication(Domain.class);
		String[] args;
		args = new String[1];
		args[0] = "";
		
		applicationContext = springApplication.run(args);
		countDownLatch = new CountDownLatch(1);
		bankFactory = new BankFactory(applicationContext, countDownLatch);
		toBeLoaded = new AnnotationConfigApplicationContext(Domain.class);
		
	}
	
	
	@Test
	public void test() {
		
	
		
		Thread tread = new Thread(bankFactory);
		tread.run();
		tread.start();
		try {
			countDownLatch.await();
			logger.info("countdown finished");
		} catch (InterruptedException e) {
			logger.info("bank factory interuptedexception");
			e.printStackTrace();
		}
		assertNotNull(bankFactory);
		BankRepository bankRepository = toBeLoaded.getBean(BankRepository.class);
		assertEquals(bankRepository.count(), 2);
		
	}
	
	

}
