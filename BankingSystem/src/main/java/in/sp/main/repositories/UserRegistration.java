package in.sp.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.user.UserRegister;
import in.sp.main.useraccount.UserAccount;
import java.util.List;


public interface UserRegistration extends JpaRepository<UserRegister, Integer> {
	UserRegister findByEmail(String email);
	UserRegister findByAccountNum(String accountNum);
	UserRegister  findByPincode(int pincode);
	

}
