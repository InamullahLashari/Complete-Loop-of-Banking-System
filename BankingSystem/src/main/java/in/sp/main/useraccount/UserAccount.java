package in.sp.main.useraccount;

import jakarta.persistence.*;
import in.sp.main.user.UserRegister;

@Entity
@Table(name = "btab")
public class UserAccount {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column
    private int id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String phoneNum;

    @Column
    private String cnic;

    @Column
    private String accountNum; // Changed from AccountNum to accountNum

    @Column
    private Double balance = 0.0; // Changed from double to Double

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_register_id", referencedColumnName = "id")
    private UserRegister userRegister;

    // Default constructor
    public UserAccount() {
        // No need to generate account number here; it will be handled in the service layer
    }

    // Getter and Setter methods for all fields
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

    public String getCnic() {
        return cnic;
    }

    public void setCnic(String cnic) {
        this.cnic = cnic;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public UserRegister getUserRegister() {
        return userRegister;
    }

    public void setUserRegister(UserRegister userRegister) {
        this.userRegister = userRegister;
    }
}
