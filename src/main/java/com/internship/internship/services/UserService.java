package com.internship.internship.services;


import com.internship.internship.AppUser.UserRepository;
import com.internship.internship.entity.User;
import com.internship.internship.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
@Autowired
private RoleRepository roleRepository;


    /**
     * Get All todos
     *
     * @return List<Todo>
     */


    public List<User> findAll() {

        return userRepository.findAll();
    }

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
        /////user.setPassword(passwordEncoder().encode(user.getPassword()));


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
        System.out.println("the new Titile is::::" + user.getFirstName());
        System.out.println("the new to user" + user);
        userRepository.save(user);
        return user;
    }
















}