package com.example.Stage.repository;

import com.example.Stage.entity.Role_User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
@Repository
public interface Role_UserRepository extends JpaRepository<Role_User, Integer> {

}
