package com.internship.internship.services;


import com.internship.internship.AppUser.UserRepository;
import com.internship.internship.entity.User;
import com.internship.internship.repository.RoleRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired                               //call of the user Repository class
    private UserRepository userRepository;




    public List<User> findAll() {
        return userRepository.findAll();
    }
//used in the pagination
    public Page<User> findPage(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber - 1,5);
        return userRepository.findAll(pageable);
    }


    public List<User> search(String value) {
        return userRepository.search(value);
    }

    public void delete(int idUser) {
        userRepository.deleteById(idUser);
    }

    public User findBy(int idUser) {
        return userRepository.findById(idUser).get();
    }

    public User save(User user) {

        System.out.println("\n ---------+"+ userRepository.allUserDetails()+"\n-------------------");
        /////Decrypt password in the registration process with BCrypt
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);

        return userRepository.save(user);
    }



    public User update(User user, int idUser) {
        User oldUser = userRepository.findById(idUser).get();
        System.out.println(" the old one::::" + oldUser);
        //oldUser.setIdUser(user.getIdUser());
        oldUser.setFirstName(user.getFirstName());
        oldUser.setLastName(user.getLastName());
        oldUser.setHireDate(user.getHireDate());
        oldUser.setAddress(user.getAddress());
        oldUser.setSex(user.getSex());
        oldUser.setPassword(user.getPassword());
        oldUser.setTel(user.getTel());
        oldUser.setBirthDate(user.getBirthDate());
        oldUser.setRoles(user.getRoles());
        oldUser.setEmail(user.getEmail());
        oldUser.setDepartment(user.getDepartment());
        //System.out.println("the new firstname is::::" + user.getFirstName());
       //System.out.println("the new to user" + user);
        userRepository.save(user);
        return user;
    }


/////Work with Jasper report to generate PDF and Html file
public String exportReport(String format) throws FileNotFoundException {

        String path="C://jasper//";
    //File file = ResourceUtils.getFile("classpath:UserReport.jrxml");
    File file = ResourceUtils.getFile("classpath:userDetails.jrxml");

    try {
        List<Map<String, Object>>userList=userRepository.allUserDetails();
        System.out.println("\n"+userList+"\n");
        JasperReport jasper= JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource ds =new JRBeanCollectionDataSource(getAllUsers());
        Map<String, Object> paramters=new HashMap<String , Object>();
        paramters.put(" empty", "paramters");

        JasperPrint jasperPrint= JasperFillManager.fillReport(jasper,paramters,ds);
        if(format.equalsIgnoreCase("html")){
            JasperExportManager.exportReportToHtmlFile(jasperPrint,path+"//myReport.html");
        } if(format.equalsIgnoreCase("pdf")){
            JasperExportManager.exportReportToPdfFile(jasperPrint,path +"//myReport.pdf");

        }

    } catch (JRException e) {
        throw new RuntimeException(e);
    }
    return "path: "+path;
}

    public List<Map<String, Object>>getAllUsers(){
        List<Map<String, Object>> users= new ArrayList<>();

        users=userRepository.allUserDetails();
        return users;
    }











}


