package steinKo.ATM.test.unit.domain;





import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import steinKo.ATM.Category.UnitTest;
import steinKo.ATM.domain.Bank;
import steinKo.ATM.domain.Customer;

@UnitTest
public class BankTest {

    Bank bank;
    Long personId;
    
    @BeforeEach 
    public void setUp()
    {
    	     bank = Bank.create();
    	     personId = 26076144574l;
    	     bank.createCustomer("Stein Korsveien",personId,106l,1234);
    	     bank.createCustomer("Oddmund Korsveien",234556l,100l,1234);
    	     bank.createCustomer("Bjørn Korsveien",26076544574l,101l,4567);
    	     
    }
	@Test
	public void testshouldFindCustomerByName() {
		
		Customer customer = bank.findCustomerByName("Stein Korsveien");
		
		assertEquals(personId,customer.getPersonId());
	}
	@Test
	public void testshouldFindanotherCustomerByName() {
	
		Customer customer = bank.findCustomerByName("Oddmund Korsveien");
		Long id = 234556l;
		assertEquals(id,customer.getPersonId());
	}
	
	@Test
	public void testshouldFindCustomerByPersonId() {
		Customer customer = bank.findCustomerByPersonId(26076544574l);
		assertEquals("Bjørn Korsveien",customer.getName());
	}
	
	@Test
	public void testshouldFindCustomerByCustomerNumber() {
		
		
		Customer customer = bank.findCustomerByCustomerNumber(101l);
		assertEquals("Bjørn Korsveien",customer.getName());
		Customer customer2 = bank.findCustomerByCustomerNumber(106l);
		assertEquals("Stein Korsveien",customer2.getName());
	}
	
	
	    		 
	


}
