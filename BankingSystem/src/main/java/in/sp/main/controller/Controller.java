package in.sp.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.sp.main.services.UserService;
import in.sp.main.useraccount.UserAccount;

@RestController
public class Controller {

    @Autowired
    private UserService userservice;

    @PostMapping("/user/{AccountNum}")
    public ResponseEntity<String> depositMoney(@PathVariable String AccountNum, @RequestBody UserAccount user) {
        String UpdateBalance = userservice.updateAccount(AccountNum, user.getBalance());

        if (UpdateBalance != null) {
            return ResponseEntity.ok(UpdateBalance); // Return the updated user details
        } else {
            return ResponseEntity.notFound().build(); // Return 404 if the user is not found
        }
    }
}