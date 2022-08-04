package com.example.Stage.repository;


import com.example.Stage.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {



    @Query("SELECT u FROM User u WHERE u.email = ?1")
    public User findByEmail(String email);




    @Query(
            value = "select * from user where first_name like %:keyword%",
            nativeQuery = true)
    List<User> search(String keyword);






}
