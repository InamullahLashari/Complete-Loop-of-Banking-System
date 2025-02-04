package in.sp.main.services;

import in.sp.main.user.UserRegister;
import in.sp.main.useraccount.UserAccount;

public interface UserService {
   
	
boolean createAccount(UserAccount user);
boolean registerAcoount(UserRegister Reguser);
String updateAccount(String accountNum, Double balance );
UserAccount getEmailCheck(String email);
UserAccount getDetailEmail(String email);
UserRegister getEmailRegister(String email, String pass);
UserRegister  accountActivate( String accountUser);
boolean setPincode(String account, int pincode);
UserRegister pinCheck(int atmpin);

}