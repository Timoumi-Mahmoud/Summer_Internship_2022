package com.internship.internship.AppUser;

import com.internship.internship.entity.User;
import com.internship.internship.security.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);


    @Query(
            value = "select * from user where first_name like %:keyword%",
            nativeQuery = true)
    List<User> search(String keyword);

    @Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = ?1")
    List<User> findUserByRole(int role);




    @Query( value = "SELECT email,password \n" +
            "FROM user u \n" +
            "WHERE u.email = 'admin@admin.com'" , nativeQuery = true)
    List<User> f(String ss);








}
