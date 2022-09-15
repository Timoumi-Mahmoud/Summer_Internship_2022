package com.internship.internship.AppUser;

import com.internship.internship.entity.User;
import com.internship.internship.security.MyUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);


    @Query(
            value = "select * from user where first_name like %:keyword% or email like %:keyword% ",
            nativeQuery = true)
    List<User> search(String keyword);

    @Query("SELECT user FROM User user LEFT JOIN user.roles role WHERE role.id = ?1")
    List<User> findUserByRole(int role);




    @Query( value = "SELECT email,password \n" +
            "FROM user u \n" +
            "WHERE u.email = 'admin@admin.com'" , nativeQuery = true)
    List<User> f(String ss);



@Query(value = "SELECT user.id_user,\n" +
        "\t first_name, last_name,\n" +
        "\temail,\n" +
        "\ttel,\n" +
        "\tbirth_date,\n" +
        "\tuser.created_by,\n" +
        "\tdepartment_name,\n" +
        "\tgroup_concat(DISTINCT roles.name) AS roles,\n" +
        "\tgroup_concat(DISTINCT function.name_function) AS functions\n" +
        "FROM user\n" +
        "\tinner join department ON\n" +
        "\t user.id_department = department.id_department\n" +
        "\tjoin users_roles ON\n" +
        "\t users_roles.id_user = user.id_user\n" +
        "\tjoin roles ON\n" +
        "\t users_roles.id_role = roles.id_role\n" +
        "\tjoin functions_roles ON\n" +
        "\t functions_roles.id_role = roles.id_role\n" +
        "\t OR functions_roles.id_role != roles.id_role\n" +
        "\tjoin function ON\n" +
        "\t functions_roles.id_function = function.id_function\n" +
        "GROUP BY user.id_user", nativeQuery = true)
List<Map<String, Object>> allUserDetails();







public User findByResetPasswordToken(String token);




























































}
