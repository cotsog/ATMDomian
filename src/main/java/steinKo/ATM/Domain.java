package steinKo.ATM;


import java.util.concurrent.CountDownLatch;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.transaction.annotation.EnableTransactionManagement;




@SpringBootApplication
@EnableTransactionManagement
public class Domain {
	private final static Logger logger = LoggerFactory.getLogger(Domain.class);
	private static ConfigurableApplicationContext applicationContext;
	
	
	
		public static void main(String[] args) {
			logger.info("Start domain main");
			applicationContext = SpringApplication.run(Domain.class, args);
			
			logger.info("After SpringApplication run");
			
			
			cretaBankFactory();
			logger.info("After createBankFactory");
			
			
			logger.info("End domain main");
			
			
		}
		private static void cretaBankFactory() {
			CountDownLatch countDownLatch = new CountDownLatch(1);
			
			Runnable bankFactory = new BankFactory(applicationContext,countDownLatch);
			logger.info("bank factory created");
			Thread tread = new Thread(bankFactory);
			tread.start();
			logger.info("bank factory tread started ");
			try {
				countDownLatch.await();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					}
		
		
		public static ApplicationContext getApplicationContext() {
			return applicationContext;
		}
		
		
	}

