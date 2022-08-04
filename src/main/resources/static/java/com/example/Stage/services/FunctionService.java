package com.example.Stage.services;

import com.example.Stage.entity.Function;
import com.example.Stage.entity.Role;
import com.example.Stage.repository.FunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FunctionService {


    @Autowired
    private FunctionRepository functionRepository;



    public List<Function> findAll(){return functionRepository.findAll();}
    public void delete(int id){
        functionRepository.deleteById(id);
    }
    public Function findBy(int id){
        return functionRepository.findById(id).get();
    }
    public Function  save(Function function){return functionRepository.saveAndFlush(function);}
    public Function update(Function function,int id){
        Function oldFunction= functionRepository.findById(id).get();
        oldFunction.setIdFunction(function.getIdFunction());
        oldFunction.setNameFunction(function.getNameFunction());

        functionRepository.save(function);
        return function;
    }



}
