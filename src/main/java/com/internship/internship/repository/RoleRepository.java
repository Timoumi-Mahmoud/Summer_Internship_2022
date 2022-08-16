package com.internship.internship.repository;

import com.internship.internship.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(
            value = "   SELECT r.name  from roles r join users_roles ur on r.id_role=ur.id_role join user u on u.id_user=?1 ;      ",
            nativeQuery = true)
    String RoleofUser(int idUser);
}


