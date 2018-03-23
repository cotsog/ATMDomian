package steinKo.ATM.domain;



import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;




@Entity

public class Customer {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@JoinColumn(name="CHECKINGACCOUNT_ID") 
	@OneToOne(cascade=CascadeType.ALL)
	private BankAccount checkingAccount;
	@JoinColumn(name="SAVINGACCONUT_ID") 
    @OneToOne(cascade=CascadeType.ALL) 
	private BankAccount savingsAccount;
	private Long customerNumber;
	private int pin;
	private String name;
	private Long personId;

	protected Customer() { }
	
	public Customer(String name,long personId, Long customerNumber, int pin)
	 {
	   this.customerNumber = customerNumber;
	   this.name = name;
	   this.personId = personId;
	   
	   this.pin = pin;
	   savingsAccount = new BankAccount(0);
	  
	   checkingAccount = new BankAccount(0);
	  
    }

	

	public BankAccount savingAccount() {
		
		return savingsAccount;
	}
	
	public BankAccount checkingAccount() {

		return checkingAccount;
	}
	
   
	public Long getCustomerNumber() {
		
		return customerNumber;
	}

	public Long getPersonId() {
		
		return personId;
	}

	public String getName() {
		
		return name;
	}

	public int getPin() {
		
		return pin;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public boolean equals(Object o) {
	    // self check
	    if (this == o)
	        return true;
	    // null check
	    if (o == null)
	        return false;
	    // type check and cast
	    if (getClass() != o.getClass())
	        return false;
	    Customer customer = (Customer) o;
	    // field comparison
	    return Objects.equals(id, customer.id);
	            
	}

}
