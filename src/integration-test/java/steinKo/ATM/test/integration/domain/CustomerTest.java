package steinKo.ATM.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.Iterator;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import steinKo.ATM.domain.BankAccount;
import steinKo.ATM.domain.Customer;
import steinKo.ATM.repository.BankAccountRepository;
import steinKo.ATM.repository.CustomerRepository;
import steinKo.ATM.test.integration.framework.AbstractIntegration;


public class CustomerTest extends AbstractIntegration {
	
	@Autowired
    private CustomerRepository repository;
	
	@Autowired
	BankAccountRepository bankAccuntRepository;
	
 @Test
	public void shouldFindNoCustomerIfRepositoryIsEmpty() {
	 repository.deleteAll();
		Iterable<Customer> customers = repository.findAll();
				assertThat(customers).isEmpty();
				
	}
 
 @Test
	public void shuoldFindOneCusomer() {
	        repository.deleteAll();
		    Customer customer = new  Customer("Stein Korsveien",26076144574l,1l, 1234);
		     repository.save(customer);
		    long noOfCustomers =  repository.count();
		    assertEquals(1l,noOfCustomers);
		  }
 
 @Test
	public void shouldDeleteAllBankAccounts() {
	    repository.deleteAll();
		repository.save(new  Customer("Stein Korsveien",26076144574l,1l, 1234));
		repository.save(new  Customer("Anne Korsveien",26076144545l,2l, 1234));

		repository.deleteAll();

		assertThat(repository.findAll()).isEmpty();
	}
 
 @Test
	public void shouldFindBankAccountById() {
	    repository.deleteAll();
		Customer customer1 = new  Customer("Stein Korsveien",26076144574l,1l, 1234);
		repository.save(customer1);
		Customer customer2 = new  Customer("Anne Korsveien",26076144545l,2l, 1234);
		repository.save(customer2);

		Optional<Customer> optionalCustomer = repository.findById(customer2.getId());
		Customer customer = optionalCustomer.get();
		assertThat(customer).isEqualTo(customer2);
	}
 
 @Test
	public void shouldFindAllCustomers() {
	     repository.deleteAll();
	     Customer customer1 = new  Customer("Stein Korsveien",26076144574l,1l, 1234);
		repository.save(customer1 );

		Customer customer2 = new  Customer("Anne Korsveien",26076144545l,2l, 1234);
		repository.save(customer2);

		Customer customer3 = new  Customer("Bjørn Korsveien",26076144890l,3l, 1235);
		repository.save(customer3);


		Iterable<Customer> customerss = repository.findAll();

		assertThat(customerss).hasSize(3).contains( customer1,  customer2,  customer3);
	}
 
 @Test
     public void shoulFindAccounts()
     {
	    repository.deleteAll();
	    Customer customer1 = new  Customer("Stein Korsveien",26076144574l,1l, 1234);
		repository.save(customer1 );
		Iterable<BankAccount> bankAccounts = bankAccuntRepository.findAll();
		Iterator<BankAccount> iterator = bankAccounts.iterator();
		BankAccount bankAccout1 = iterator.next();
		BankAccount bankAccout2 = iterator.next();
		assertThat(bankAccounts).hasSize(2).contains( bankAccout1,  bankAccout2);     
     }
}
