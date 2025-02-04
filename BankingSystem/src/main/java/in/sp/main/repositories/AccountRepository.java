package in.sp.main.repositories;
import org.springframework.data.jpa.repository.JpaRepository;

import in.sp.main.useraccount.UserAccount;




public interface AccountRepository extends JpaRepository<UserAccount, Integer> {
	
	
	UserAccount  findByAccountNum(String accountNum);
	
	//here we use to get user detail if we use all getbyall method it give all detail
	
	UserAccount findByEmail(String email);
}



