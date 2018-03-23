package steinKo.ATM.test.integration.service;

import static org.junit.Assert.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import steinKo.ATM.domain.Bank;
import steinKo.ATM.repository.BankRepository;
import steinKo.ATM.service.ATMS;
import steinKo.ATM.service.ATMServiceImpl;
import steinKo.ATM.test.integration.framework.AbstractIntegration;



public class ATMServiceImplTest extends AbstractIntegration{
	 
	
	
	private static ATMServiceImpl atmService;
	
	@Autowired
	private static BankRepository bankRepository;
	
	
	
	@BeforeAll
	public static void init() {
	
	      Bank bank = Bank.create();
	      bank.createCustomer("", 0L, 0L, 0);
	      bankRepository.save(bank);
	      atmService = new ATMServiceImpl();
	   
	}
	
	@Test
	public void shuldDeliverATMStratMessage() {
		assertNotNull(atmService);
		assertEquals(atmService.display(),ATMS.States.startMessage);
	}
	
	@Test
	public void shuldChangeToStartState() {
		atmService.buttomAPushed("1234");
		assertNotNull(atmService);
		assertEquals(atmService.display(),ATMS.States.pinMessage);
		atmService.reset();
		assertEquals(atmService.display(),ATMS.States.startMessage);
	}
	
	

}
