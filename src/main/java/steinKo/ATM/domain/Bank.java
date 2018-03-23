package steinKo.ATM.domain;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.OneToMany;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import steinKo.ATM.domain.Customer;

@Entity

public class Bank implements Serializable {
	private final static Logger logger = LoggerFactory.getLogger(Bank.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@JoinColumn(name="BANK_ID") 
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    private List<Customer> customers =new ArrayList<>();
	  
	public static Bank create()
	{
		return new Bank();
	}
	   
   protected Bank ()  {
    	    
    	     System.out.println(this.toString());
          }
       
       public Long getId() {

   		return id;
   	}
   

	public Customer findCustomerByPersonId(Long personId) {
		
		   for (Customer customer : customers) {
			   if (customer.getPersonId().equals (personId))
			   return customer;
			
		   }
		   return null;
	 }

	 public void createCustomer(String name, long personId, Long customerNumber,int pin ) {
		
		Customer customer = new Customer(name,personId, customerNumber,pin);
		customers.add(customer);
		
		
		
	  }

	  public Customer findCustomerByName(String name) {
		
		for ( Customer customer : customers)
		{
		
			
			if (customer.getName().equals(name))
			return customer;	
		}
		return null;
	}

	public Customer findCustomerByCustomerNumber(Long customerNumber) {	
			for (Customer customer: customers)
				if (customer.getCustomerNumber().compareTo(customerNumber)==0)
					return customer;
		return null;
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
	    Bank bank = (Bank) o;
	    // field comparison
	    return Objects.equals(id, bank.id);
	            
	}
	
	
	
	public void createCustomersFromFile() {   
	    customers = new ArrayList<>();
		File file;
		file = new File("src/test/resources/Customers");
		Long customerNumber;
		int pin;
		String surname;
		String familyname;
		String name;
		Long personId;
		Customer customer;
		Scanner scanner = null;
		
		try {
			scanner = new Scanner(file);
			while(scanner.hasNext())
			{
				surname = scanner.next();
				familyname = scanner.next();
				name = surname + familyname;
				personId = scanner.nextLong();
				customerNumber = scanner.nextLong();
				pin = scanner.nextInt();
				customer = new Customer(name,personId,customerNumber,pin);
			    customers.add(customer);
			}

		} catch (FileNotFoundException e) {
		
			logger.error("File not found");
		} finally {
			if (scanner != null)
			scanner.close();
			
		}
		
		
	}


}
