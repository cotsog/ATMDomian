package steinKo.ATM.test.integration.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


import steinKo.ATM.domain.BankAccount;
import steinKo.ATM.repository.BankAccountRepository;
import steinKo.ATM.test.integration.AbstractIntegration;

public class BankAccountTest  extends AbstractIntegration{
	
	    @Autowired
	    private  BankAccountRepository repository;
	    
	   
	 
	 @Test
		public void shouldFindNoBankAccounIfRepositoryIsEmpty() {
		
		 repository.deleteAll();
			Iterable<BankAccount> accounts = repository.findAll();
					assertThat(accounts).isEmpty();
					
		}
	 
	@Test
	public void shuoldFindOneBankAccount() {
		     repository.deleteAll();
		     BankAccount account = new  BankAccount(100);
		     repository.save(account);
		    long noOfAccounts =  repository.count();
		    assertEquals(1l,noOfAccounts);
		  }
	
	@Test
	public void shouldDeleteAllBankAccounts() {
		repository.deleteAll();
		repository.save(new BankAccount(100));
		repository.save(new BankAccount(200));

		repository.deleteAll();

		assertThat(repository.findAll()).isEmpty();
	}
	
	@Test
	public void shouldFindBankAccountById() {
		repository.deleteAll();
		BankAccount account1 = new BankAccount(100);
		repository.save(account1);

		BankAccount account2 = new BankAccount(200);
		repository.save(account2);
		Optional<BankAccount> optionalFoundAccount = repository.findById(account2.getId());
		BankAccount foundAccount =  optionalFoundAccount.get();
		assertThat(foundAccount.getId()).isEqualTo(account2.getId());
	}
	@Test
	public void shouldFindAllCustomers() {
		repository.deleteAll();
		BankAccount account1 = new BankAccount(100);
		repository.save(account1);

		BankAccount account2 = new BankAccount(200);
		repository.save(account2);

		BankAccount account3 = new BankAccount(300);
		repository.save(account3);


		Iterable<BankAccount> bankaccounts = repository.findAll();
		assertThat(bankaccounts).hasSize(3).contains(account1, account2, account3);
	}
	
	@Test
	public void shouldFindSaldo() {
		repository.deleteAll();
		BankAccount account1 = new BankAccount(100);
		repository.save(account1);
		Optional<BankAccount> optioalFoundAccount = repository.findById(account1.getId());
		BankAccount foundAccount = optioalFoundAccount.get();
        assertEquals(foundAccount.saldo(),100);
	}
	
	@Test
	public void shouldFindNewSaldo() {
		repository.deleteAll();
		BankAccount account1 = new BankAccount(100);
		repository.save(account1);
		account1.gi(100);
		repository.save(account1);
		Optional<BankAccount> oprionalFoundAccount = repository.findById(account1.getId());
		BankAccount foundAccount = oprionalFoundAccount.get();
		assertEquals(foundAccount.saldo(),200);
	}
	
	@Disabled
	@Test
	public void shouldFindOldSaldo() {
		repository.deleteAll();
		BankAccount account1 = new BankAccount(100);
		repository.save(account1);
		account1.gi(100);
		Optional<BankAccount> optioalFoundAccount = repository.findById(account1.getId());
		BankAccount foundAccount = optioalFoundAccount.get();
		assertEquals(foundAccount.saldo(),100);
	}
	
}	

