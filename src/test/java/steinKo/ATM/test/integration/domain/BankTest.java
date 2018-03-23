package steinKo.ATM.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import steinKo.ATM.domain.Bank;
import steinKo.ATM.domain.BankAccount;
import steinKo.ATM.domain.Customer;
import steinKo.ATM.repository.BankRepository;
import steinKo.ATM.repository.CustomerRepository;
import steinKo.ATM.test.integration.framework.AbstractIntegration;



public class BankTest extends AbstractIntegration {
	
	private final static Logger logger = LoggerFactory.getLogger(BankTest.class);
	
	@Autowired
	private BankRepository bankRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private Bank bank;
	
	@Autowired
	private BankRepository bankRepository2;
	
	@BeforeEach
	public void setUp()
	{
		 bank = Bank.create();
	}
	
	@Test
	public void shouldFindNoBankIfRepositoryIsEmpty() {
		bankRepository.deleteAll();
		Iterable<Bank> banks = bankRepository.findAll();
				assertThat(banks).isEmpty();
				
	}	
	
	 @Test
		public void shuoldFindOneBank() {
		       bankRepository.deleteAll();
			   bankRepository.save(bank);
			    long noOfCustomers =   bankRepository.count();
			    assertEquals(1l,noOfCustomers);
			  }
	 
	 @Test
		public void shouldDeleteAllBanks() {
		 bankRepository.deleteAll();
		 bankRepository.save(bank);
		 bankRepository.save(Bank.create());

		 bankRepository.deleteAll();

			assertThat( bankRepository.findAll()).isEmpty();
		}
	 
	 @Test
		public void shouldFindBanktById() {
		    bankRepository.deleteAll();
			bankRepository.save(bank);
			Bank bank2 = Bank.create();
			bankRepository.save(bank2);
			Optional<Bank> optionafoundBank = bankRepository.findById(bank2.getId());
			Bank foundBank = optionafoundBank.get();
			assertThat(foundBank).isEqualTo(bank2);
		}
	 
	 @Test
	    public void shouldCreateACustomer() {
		  bankRepository.deleteAll();
		  bank.createCustomer(" ", 1l, 1l, 1234);
		  bankRepository.save(bank);
		  Customer customerFound1 = bank.findCustomerByCustomerNumber(1l);
		  Iterable<Customer>   customersFound = customerRepository.findAll();
		  Iterator<Customer> iterator = customersFound.iterator();
		  Customer customerFound2 = iterator.next();
		  assertEquals(customerFound1.getId(),customerFound2.getId());
		  
	 }
	 
	 @Test 
	 @Transactional
	  public void shouldStoreaAggregate() {
		 
		    bankRepository.deleteAll();
			bank.createCustomer("", 0L, 101L, 4567);
			Customer customer = bank.findCustomerByCustomerNumber(101L);
			BankAccount savingAccount = customer.savingAccount();
			savingAccount.gi(400);
			bankRepository.save(bank);
			
			bank = bankRepository2.getOne(bank.getId());
			String message = "Bank id" + bank.getId();
			logger.info(message);
			
			Customer customer2 = bank.findCustomerByCustomerNumber(101L);
			assertEquals(customer.getId(),customer2.getId());
			savingAccount = customer.savingAccount();
		    assertEquals(savingAccount.saldo(),400);
			
	 }
	  
	 

}
