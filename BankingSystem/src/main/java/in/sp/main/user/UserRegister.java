package in.sp.main.user;

import jakarta.persistence.*;
import in.sp.main.useraccount.UserAccount;

@Entity
@Table(name = "register") // Table for user registration and login
public class UserRegister {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password; // Added password for login security

    @Column
    private String accountNum;
    @Column
    private Integer pincode = null;  // Use Integer (wrapper class) to assign null


   

	public Integer getPincode() {
		return pincode;
	}

	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}

	@OneToOne(mappedBy = "userRegister", cascade = CascadeType.ALL)
    private UserAccount userAccount;

    // Default Constructor
    public UserRegister() {}

    // Getters and Setters
    public int getId() { 
        return id; 
    }

    public void setId(int id) { 
        this.id = id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getPassword() { 
        return password; 
    }

    public void setPassword(String password) { 
        this.password = password; 
    }

    public String getAccountNum() { 
        return accountNum; 
    }

    public void setAccountNum(String accountNum) { 
        this.accountNum = accountNum; 
    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }
}
