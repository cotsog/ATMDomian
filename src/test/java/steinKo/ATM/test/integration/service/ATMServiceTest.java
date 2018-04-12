package steinKo.ATM.test.integration.service;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import steinKo.ATM.domain.Bank;
import steinKo.ATM.domain.BankAccount;
import steinKo.ATM.domain.Customer;
import steinKo.ATM.repository.BankRepository;
import steinKo.ATM.service.ATMS;
import steinKo.ATM.service.ATMService;
import steinKo.ATM.test.integration.AbstractIntegration;

public class ATMServiceTest extends AbstractIntegration{
	
	private final static Logger logger = LoggerFactory.getLogger(ATMServiceTest.class);
   
	//@Autowired
	private ATMService atmService;
	
	//@Autowired
	private BankRepository  repository;
	
	
	@Disabled
	@Test
	public void souldWitdrawMonyFromSavingAccount() {
		Bank bank =  Bank.create();
		bank.createCustomer("stein korsveien", 23412334L, 100L, 1234);
		Customer customer = bank.findCustomerByPersonId(23412334L);
		BankAccount chekingAccount = customer.checkingAccount();
		chekingAccount.gi(1000);
		BankAccount savingAccount = customer.savingAccount();
		savingAccount.gi(600);
		repository.save(bank);
		
		
		logger.info("Start MonyFromSavingAccount");
		assertEquals(atmService.display(),ATMS.States.startMessage);
		atmService.buttomAPushed("100");
		assertEquals(atmService.display(),ATMS.States.pinMessage);
		atmService.buttomAPushed("1234");
		assertEquals(atmService.display(),ATMS.States.autorizedMessag);
		atmService.buttomAPushed(ATMS.States.savingAccount);
		assertEquals(atmService.display(),"Balance= 1000 kr\nA=Deposit\nB=Withdrawal\nC=Cancel");
		atmService.buttomAPushed("200");
		assertEquals(atmService.display(),ATMS.States.autorizedMessag);
		
	}

}
