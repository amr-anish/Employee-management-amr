package com.capestart.api.employee.model;


import javax.persistence.*;

@Entity
@Table(name="desigination")
public class Desigination {

    @Id
    private int desigination_id;
    private String post;

    public int getDesigination_id() {
        return desigination_id;
    }



    public String getPost() {
        return post;
    }


}
