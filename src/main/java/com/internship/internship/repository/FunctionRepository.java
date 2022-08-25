package com.internship.internship.repository;


import com.internship.internship.entity.Function;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface FunctionRepository extends JpaRepository<Function,  Integer>  {

//using this query in filter of list of urls of the current connected user
    @Query(  value ="select * from function f   JOIN functions_roles fr on f.id_function= fr.id_function"+
            "  JOIN roles r on fr.id_role = r.id_role " +
            "  JOIN  users_roles ur on ur.id_role=r.id_role" +
            "  JOIN  user u on u.id_user=ur.id_user and u.email like :username "
            , nativeQuery = true
             )
    List<Function> urlsFinder( @Param("username") String  username);


    @Modifying
    @Transactional
    @Query(value ="delete from function where id_function = ?1"     ,nativeQuery=true)
    void deleteByParentId(Integer id);


}
