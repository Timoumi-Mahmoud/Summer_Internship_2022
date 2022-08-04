package com.example.Stage.entity;

import javax.persistence.*;

@Entity
public class Function {

    @Id
    @Column(name = "id_function")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int idFunction;

    private String nameFunction;


    public Function() {
    }

    public Function(int idFunction, String nameFunction) {
        this.idFunction = idFunction;
        this.nameFunction = nameFunction;
    }

    public int getIdFunction() {
        return idFunction;
    }

    public void setIdFunction(int idFunction) {
        this.idFunction = idFunction;
    }

    public String getNameFunction() {
        return nameFunction;
    }

    public void setNameFunction(String nameFunction) {
        this.nameFunction = nameFunction;
    }


    @Override
    public String toString() {
        return "Function{" +
                "idFunction=" + idFunction +
                ", nameFunction='" + nameFunction + '\'' +
                '}';
    }
}
