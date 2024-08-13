package spring.boot.week7day3ex.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import spring.boot.week7day3ex.Model.Users;

import java.util.List;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    Users findUsersById(Integer id);

    Users findUsersByEmail(String email);


    @Query("select u from Users u where u.username=?1 and u.password=?2")
    Users findUsersByUsernameAndPassword(String username, String password);

    List<Users> findByRole(String role);

    @Query("SELECT u FROM Users u WHERE u.age >= ?1")
    List<Users> findUsersByAgeOrAbove(int age);
}
