package steinKo.ATM.test.integration.controller;


import static org.junit.Assert.assertEquals;


import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import steinKo.ATM.controller.ATMServiceController;
import steinKo.ATM.domain.Bank;
import steinKo.ATM.repository.BankRepository;
import steinKo.ATM.service.ATMS;
import steinKo.ATM.test.integration.framework.AbstractIntegration;




public class  ATMServiceControllerTest extends AbstractIntegration{
	
	static private ATMServiceController atmServiceController;

	private final static Logger logger = LoggerFactory.getLogger(ATMServiceControllerTest.class);
	
	@Autowired
	static private BankRepository bankRepository ;
	
	@BeforeClass
	 public static void inint()  {
		atmServiceController = new ATMServiceController();
		createTestData();
		 
	}
	
	
	@Test
	public void shouldDisplay() {
		
		assertEquals(atmServiceController.display(),ATMS.States.startMessage);
	}
	
	
	@Test
	public void shouldPushButtonA() {
		atmServiceController.pushBottonA("1234");
		
	}
	
	
	private static void createTestData()
	{ 
		
		logger.info("Cretate test data");
		Bank bank = Bank.create();
		logger.info(bank.toString());
		bank.createCustomer("", 0L, 101L, 4567);
		logger.info(bankRepository.toString());
		bankRepository.save(bank);
	}
	
}
