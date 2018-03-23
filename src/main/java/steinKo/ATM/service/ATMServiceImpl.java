package steinKo.ATM.service;





import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import steinKo.ATM.domain.ATM;
import steinKo.ATM.domain.Bank;
import steinKo.ATM.repository.BankRepository;







@Transactional
@Service
@Scope("prototype")
public class ATMServiceImpl implements ATMService {
	
	
	private ATM atm;
	
	
	@Autowired
	private BankRepository bankRepository;
	
	

	private final static Logger logger = LoggerFactory.getLogger(ATMServiceImpl.class);
	
	
	 
	
	private Bank getBank(BankRepository bankRepository) 
	  {
		  List<Bank> banks=  bankRepository.findAll();
		  Bank bank = null;
		  if (!banks.isEmpty())
		  {
	          bank = banks.get(0);
		      logger.info("Bank found");
		      return bank;
		  } else
			try {
				
				throw new Exception("No bank found ");
				
			} catch (Exception e) {
				 logger.error("No bank found", e);
				
			}
		 return bank;
	  }
	

	@Override
	public String display()  {
		
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		
		  return  atm.display();
		
	}
	
	
	@Override
	public void buttomAPushed(String text) {
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		
		  atm.buttomAPushed(text);
		
		
	}

	@Override
	public void buttomBPushed(String text) {
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		 
		
		atm.buttomBPushed(text);
		
	}

	@Override
	public void buttomCPushed(String text) {
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		 
		
		atm.buttomCPushed(text);
		
	}



	@Override
	public void withdraw(Integer amount) {
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		
		atm.withdraw(amount);
		
	}



	@Override
	public Integer getBalance() {
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		 
		return atm.getBalance();
		
	}



	@Override
	public boolean isCheckingAccount() {
		
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		 
		return atm.isCheckingAccount();
		
	}



	@Override
	public void deposit(int paKonto) {
		
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		
		atm.deposit(paKonto);
		
	}
	
	@Override
	public void reset() {
		
		if (atm == null)
		{
			Bank bank = getBank(bankRepository);
			atm = new ATM(bank);
			
		}
		 
		atm.reset();
		
	}
	


}
