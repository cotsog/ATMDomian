package steinKo.ATM.test.integration;

import static org.junit.Assert.assertNotNull;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import steinKo.ATM.Domain;
import steinKo.ATM.repository.BankRepository;
import steinKo.ATM.service.ATMS;
import steinKo.ATM.service.ATMService;
import steinKo.ATM.test.integration.framework.AbstractIntegration;

class DomainTest extends AbstractIntegration{
	
	private final static Logger logger = LoggerFactory.getLogger(DomainTest.class);
	static private ApplicationContext applicationContext;
	@BeforeAll
	public static void  init()
	{
		String args[];
		args = new String[1];
		args[0] = "";
		Domain.main(args);
		applicationContext = Domain.getApplicationContext();
		
		
	}
	
	@Test
	public void shouldContainATMServicImplBean()
	{
		ATMService atmService = applicationContext.getBean(ATMService.class);
		assertNotNull(atmService);
		assertEquals(atmService.display(),ATMS.States.startMessage);
		
	}
	
	@Test 
	public void shouldContainBankRepository()
	{
		BankRepository bankRepository = applicationContext.getBean(BankRepository.class);
		assertNotNull( bankRepository);
		assertEquals(bankRepository.count(),1);
	}
	
	@Test
	public void shouldDisplayNamesOnAllBeans() {
		
		
		String[] beansName = applicationContext.getBeanDefinitionNames();
		assertNotNull(applicationContext);
		for (String beanName : beansName)
			logger.info(beanName);
		
		
		
	}

}
