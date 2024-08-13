package spring.boot.week7day3ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import spring.boot.week7day3ex.Model.Users;
import spring.boot.week7day3ex.Service.UsersService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;


    @GetMapping("/get")
    public ResponseEntity getUsers() {
        return ResponseEntity.status(200).body(usersService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@Valid @RequestBody Users users, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        usersService.addUser(users);
        return ResponseEntity.status(200).body("Successfully added user");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@Valid @RequestBody Users users, @PathVariable Integer id, Errors error) {
        if (error.hasErrors()) {
            String message = error.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        usersService.updateUser(users, id);
        return ResponseEntity.status(200).body("Successfully updated");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        usersService.deleteUser(id);
        return ResponseEntity.status(200).body("Successfully deleted");
    }
    @GetMapping("/getUserByEmail")
    public ResponseEntity getUserByEmail(@RequestParam String email) {
        Users user = usersService.getUserByEmail(email);
        return ResponseEntity.status(200).body(user);
    }
    @PostMapping("/login")
    public ResponseEntity login(@RequestParam String username, @RequestParam String password) {
        Users user = usersService.checkUserNameAndPassword(username, password);
        return ResponseEntity.status(200).body(user);
    }
    @GetMapping("/getUsersByRole/{role}")
    public ResponseEntity getUsersByRole(@PathVariable String role) {
        List<Users> users = usersService.getUsersByRole(role);
        return ResponseEntity.status(200).body(users);
    }

    @GetMapping("/getByAgeOrAbove/{age}")
    public ResponseEntity getUsersByAgeOrAbove(@PathVariable int age) {
        List<Users> users = usersService.getUsersByAgeOrAbove(age);
        return ResponseEntity.status(200).body(users);
    }
}
