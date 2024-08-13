package spring.boot.week7day3ex.Service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import spring.boot.week7day3ex.Api.ApiException;
import spring.boot.week7day3ex.Model.Users;
import spring.boot.week7day3ex.Repository.UsersRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UsersService {
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    public void addUser(Users user) {
        usersRepository.save(user);
    }

    public void updateUser(Users user, Integer id) {
        Users u = usersRepository.findUsersById(id);
        if (u == null) {
            throw new ApiException("User not found");
        }
        u.setName(user.getName());
        u.setUsername(user.getUsername());
        u.setEmail(user.getEmail());
        u.setPassword(user.getPassword());
        u.setRole(user.getRole());
        u.setAge(user.getAge());

        usersRepository.save(u);
    }

    public void deleteUser(Integer id) {
        if (usersRepository.findUsersById(id) == null) {
            throw new ApiException("User not found");
        }
        usersRepository.deleteById(id);
    }

    public Users getUserByEmail(String email) {
        Users u = usersRepository.findUsersByEmail(email);
        if (u == null) {
            throw new ApiException("User not found");
        }
        return u;
    }

    public Users checkUserNameAndPassword(String username, String password) {
        Users users = usersRepository.findUsersByUsernameAndPassword(username,password);
        if (users == null) {
            throw new ApiException("User or password not match");
        }
        return users;
    }
    public List<Users> getUsersByRole(String role) {
        return usersRepository.findByRole(role);
    }
    public List<Users> getUsersByAgeOrAbove(int age) {
        return usersRepository.findUsersByAgeOrAbove(age);
    }
}
