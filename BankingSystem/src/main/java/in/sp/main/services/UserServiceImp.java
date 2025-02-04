package in.sp.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import in.sp.main.repositories.AccountRepository;
import in.sp.main.repositories.UserRegistration;
import in.sp.main.user.UserRegister;
import in.sp.main.useraccount.UserAccount;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private UserRegistration userregisteration;
    

    // Method to generate a unique account number
    private String generateUniqueAccountNumber() {
        return String.valueOf((int) (Math.random() * 90000) + 10000); // Random number between 10000 and 99999
    }

    // Account creation and saving method
    @Override
    public boolean createAccount(UserAccount user) {
        try {
            // Generate a unique account number and assign it to the user
            user.setAccountNum(generateUniqueAccountNumber());

            // Set initial balance to 0 (if not already set in the UserAccount constructor)
            user.setBalance(0.0);

            // Save the user account to the database
            accountRepository.save(user);

            return true;
        } catch (Exception e) {
            // Log the error using a proper logging framework
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public UserRegister getEmailRegister(String email, String pass) {
        // Prevent NullPointerException
    	UserRegister validUser = userregisteration.findByEmail(email);

        if (validUser != null && validUser.getPassword().equals(pass)) {
        	
            return validUser;
        }

        return null;
    }
    
   
///////////update////////////////////
    @Override
    public String updateAccount(String accountNum, Double balance ) {
    	
        UserAccount updateAccountUser = accountRepository.findByAccountNum(accountNum);

        if (updateAccountUser != null) {
            // Update the account details
          
             
       double NewBalance = updateAccountUser.getBalance() + balance;
        updateAccountUser.setBalance(NewBalance);
            		accountRepository.save(updateAccountUser);
            		
            		 return "Succesfully Deposite:" + balance;
        } else {
            // Throw a custom exception
            throw new RuntimeException("User not found with account number: " + accountNum);
        }
    }

    
    
	@Override
	public UserAccount getDetailEmail(String email) {
	
		 UserAccount SendData =accountRepository.findByEmail(email);
		 
		 
		 if (SendData != null) {
	            return SendData;
	        }

	        return null;	
	}
	
	  
		
//////////////////////////registeration part///////////////////////////
	@Override
	public boolean registerAcoount(UserRegister Reguser) {
		
		 try {
	            
	            userregisteration.save(Reguser);

	            return true;
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	            return false;
	        }
	    }
	
	
	
	 ///////////////////////

	@Override
	public UserAccount getEmailCheck(String email) {
		  UserAccount validUser = accountRepository.findByEmail(email);
		return validUser;
	}

	
	//////////////////////////////ATM////////////////////////////////////////////////////
	@Override
	public UserRegister accountActivate(String accountUser) {
	    return userregisteration.findByAccountNum(accountUser);
	}



////////////////////set pin//////////////////////
	

	@Override
	public boolean setPincode(String account, int pincode) {
	    UserRegister userVerify = userregisteration.findByAccountNum(account);

	    if (userVerify != null) {
	        // Update the account details
	        userVerify.setPincode(pincode);
	        userregisteration.save(userVerify);
	        return true;
	    } else {
	        // Optionally log or throw an exception here to handle failure more explicitly
	        return false;
	    }
	}
/////////////////////////verify Atm pin////////////
	
	@Override
	public UserRegister  pinCheck(int atmpin) {
		UserRegister pincode = userregisteration.findByPincode(atmpin);
	    
	    return pincode;
	}


	
	
	
}

    
