package com.internship.internship.repository;


import com.internship.internship.entity.Department;
import com.internship.internship.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {





    @Query(
            value = "SELECT u.id_department," +
                    " COUNT(u.id_department) as total, u.first_name as name ,u.last_name as lname, d.department_name as Dname" +
                    " FROM user u join department d " +
                    "on u.id_department= d.id_department" +
                    " GROUP BY id_department HAVING id_department= ?1",
            nativeQuery = true)
    List<String > ListOfEmployesInADepartment(int idDepartment);


    @Query(
            value = "select u.* from department d join user u " +
                    "on u.id_department=d.id_department where d.id_department",
            nativeQuery = true)
    List<User> listUser();

}

