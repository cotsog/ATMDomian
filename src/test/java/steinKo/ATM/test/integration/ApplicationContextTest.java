package steinKo.ATM.test.integration;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import steinKo.ATM.Domain;


import steinKo.ATM.repository.BankRepository;
import steinKo.ATM.service.ATMS;
import steinKo.ATM.service.ATMService;
import steinKo.ATM.test.integration.AbstractIntegration;


public class ApplicationContextTest extends AbstractIntegration{
	
	private static AnnotationConfigApplicationContext applicationContext;
	private final static Logger logger = LoggerFactory.getLogger(ApplicationContextTest.class);
	
	@BeforeAll
	static public void setUp()
	{
		applicationContext = new AnnotationConfigApplicationContext(Domain.class);
	}
	
	
	@Disabled
	@Test
	public void shouldDisplayeBeans()
	{
		applicationContext = new AnnotationConfigApplicationContext(Domain.class);
		
		 String[] beans =applicationContext.getBeanDefinitionNames();
        for (String bean : beans)
		 {
			 logger.info(bean.toString());
			 
		 }
		 
		 Object bean =applicationContext.getBean("dataSource" );
		 logger.info(bean.toString());
	}
	@Disabled
	@Test
	public void shouldContainATMServicImplBean()
	{   
		ATMService atmService = applicationContext.getBean(ATMService.class);
		assertNotNull(atmService);
		logger.info(atmService.display());
		logger.info(ATMS.States.startMessage);
		assertEquals(atmService.display(),ATMS.States.startMessage);
		
	}
	
	@Test 
	public void shouldContainBankRepository()
	{
		
		BankRepository bankRepository = applicationContext.getBean(BankRepository.class);
		assertNotNull( bankRepository);
		assertEquals(bankRepository.count(),0);
	}
}
