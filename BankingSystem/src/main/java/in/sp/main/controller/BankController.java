package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import in.sp.main.services.UserService;
import in.sp.main.user.UserRegister;
import in.sp.main.useraccount.UserAccount;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller // ✅ Thymeleaf Controller (Returns HTML)
public class BankController {

    @Autowired
    private UserService userservice;

    // ✅ Show Registration Page
    @GetMapping("/regPage")
    public String registerAccount(Model model) {
        model.addAttribute("user", new UserAccount());
        return "create"; // Return Thymeleaf template: create.html
    }

 //////////////////this is the process of insert creating account ///////////////////////////////////////////
    @PostMapping("/created")
    public String AccountCreate(@ModelAttribute("user") UserAccount user, Model model) {
        boolean status = userservice.createAccount(user);
        if (status) {
            UserAccount DIsplayDEtail = userservice.getDetailEmail(user.getEmail());
            if (DIsplayDEtail != null) {
               
                model.addAttribute("name", DIsplayDEtail.getName());
                model.addAttribute("account", DIsplayDEtail.getAccountNum());
                model.addAttribute("balance", DIsplayDEtail.getBalance());
            } else {
                model.addAttribute("Errormessage", "Account created, but details could not be retrieved.");
            }
        } else {
            model.addAttribute("Errormessage", "Account creation failed. Please try again.");
        }
        return "Created"; 
    }
    
    /********/////////////////////////////////end/////////////////////////////////////////////////////////////

    
    //////////////////this is the process of registration account ///////////////////////////////////////////
    @GetMapping("/rigister")
    
    public String Userrisgter(Model model) {
    	
    	model.addAttribute("user", new UserRegister());
    	
    	return "Register";}
    
    @PostMapping("/register")  // Fix URL mapping to /register
    public String AccountRegister(@ModelAttribute("user") UserRegister user, Model model) {  
        // Assuming getemailCheck() method is correctly implemented to fetch data by email
        UserAccount verify = userservice.getEmailCheck(user.getEmail());  // Use getEmail() instead of setEmail()
        
        // Verify the email, name, and account number
        if (verify != null && 
            verify.getEmail().equals(user.getEmail()) &&
            verify.getName().equals(user.getName()) &&
            verify.getAccountNum().equals(user.getAccountNum())) {

            // If data matches, proceed to register the account
            boolean status = userservice.registerAcoount(user);
            if (status) {
                model.addAttribute("message", "Account registered successfully.");
            } else {
                model.addAttribute("Errormessage", "Account creation failed. Please try again.");
            }

        } else {
            // If data doesn't match, return error message
           model.addAttribute("Errormessage", "Account verification failed. Please check your information as you insert.");
           return "Register";
        }
        
        return "Registered";  // Return the view name
    }

    
    //*****///////////////////////////////////////end/////////////////////////////////////****//
    
    
    
    
    /////////////////////////////////logout///////////////////////////////////////
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // Invalidate session if it exists
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        // Redirect to the login page
        return "redirect:/logPage"; // Use the correct endpoint for your login page
    }
    
    /////////////home////////////
    
    @GetMapping("/home")
    
    
    public String inammPage() {
        return "redirect:/inamm.html";	
    }
    
    
    //////////////////this is the process of loginn account ///////////////////////////////////////////
    
    @GetMapping("/logPage")
    public String loginPage(Model model) {
        model.addAttribute("user", new UserRegister());
        return "login";
    }

    // ✅ Process Login Form
    @PostMapping("/logform")
    public String loginCheck(@ModelAttribute("user") UserRegister user, Model model) {
    	UserRegister valid = userservice.getEmailRegister(user.getEmail(), user.getPassword());
    	   UserAccount verify = userservice.getEmailCheck(user.getEmail()); 
        if (valid != null) {
            model.addAttribute("message", "Login Successfully");
            model.addAttribute("name", valid.getName());
            model.addAttribute("account", valid.getAccountNum());
            model.addAttribute("balance", verify.getBalance());
            return "logined";
        } else {
            model.addAttribute("errorMessage", "Invalid email or password.");
            return "login";
        }
    }
      
//////////////////this is the process of ATMaccount wher activa or not ///////////////////////////////////////////
    
    @GetMapping("/atm")
    public String amtside(Model model) {
        model.addAttribute("user", new UserRegister());
        return "Atm";
    }
    @PostMapping("/pinentry")
    public String loginChecknm(@ModelAttribute("user") UserRegister user, Model model) {
       
        // Fetch account details based on the account number
        UserRegister atmActive = userservice.accountActivate(user.getAccountNum());

        // Ensure atmActive is not null
        if (atmActive == null) {
            model.addAttribute("errorMessage", "Account must not found: " + user.getAccountNum());
            return "test";  // Redirect to the error page
        }

        // Logic for activation: Check if account exists and PIN is null
        if (atmActive.getPincode() == null) {
            return "activate";  // ATM needs activation
        } else {
            model.addAttribute("errorMessage", "ATM card is already activated.");
            return "pincode";  // ATM already activated
        }
    }
    ////////////////////////////////////////////
    
    ////////////////////aCTIVATION///////
    ///
   @PostMapping("/activatecard")
   public String AtmActivate(@ModelAttribute("user") UserRegister userAtm, Model model)
   {
	   UserRegister verify = userservice.accountActivate(userAtm.getAccountNum());
	   if (verify != null && 
	            verify.getEmail().equals(userAtm.getEmail()));
	   
	   boolean status =userservice.setPincode(userAtm.getAccountNum(), userAtm.getPincode());
	   
	   if(status) {
		   
		   
		   model.addAttribute("message", "your ATM card activate successfylly.");
		   
		   return "activate";
	   }
	   else {
		   
		   model.addAttribute("Errormessage", "System out of service.");
	   }
	   return null;}
   
   
   
   
   //////////////////////////atm pin code/////////////////
   @PostMapping("/pincode")
   public String checkPin(@ModelAttribute("user") UserRegister userpin, Model model) {
       // Check if the pin exists in the database
       UserRegister verifypin = userservice.pinCheck(userpin.getPincode());

       // Corrected if condition: Removed the semicolon after the condition
       if (verifypin != null && verifypin.getPincode().equals(userpin.getPincode())) {
           model.addAttribute("message", "Your ATM card has been activated successfully.");
           return "Atminterface"; // Return success page
       } else {
           model.addAttribute("Errormessage", "System out of service.");
           return "Atminterface"; // Return error page
       }
   }

  
   
   @GetMapping("/depositee")
   public String depositeside(Model model) {
       model.addAttribute("user", new UserRegister());
       return "deposite";
   }
   
   

}

    
    

    
    
    
    
    

