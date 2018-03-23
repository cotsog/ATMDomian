package steinKo.ATM.test.unit.domain;



import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import steinKo.ATM.Category.UnitTest;
import steinKo.ATM.domain.BankAccount;
@UnitTest
public class BankAccountTest {

	@Test
	public void testgi() {
		BankAccount bankaccount = new BankAccount(0);
		bankaccount.gi(100);
		assertEquals(bankaccount.saldo(),100);
	}
	
	@Test
	public void testta() {
		BankAccount bankaccount = new BankAccount(0);
		bankaccount.gi(100);
		bankaccount.ta(100);
		assertEquals(bankaccount.saldo(),0);
	}

}
