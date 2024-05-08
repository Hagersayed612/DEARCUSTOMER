package com.sheryians.major.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "category_id")
    private int id;

    @Column(nullable= false )
    private String name;

    public Category(int categoryID, String categoryName) {
        id = categoryID;
        name = categoryName;
    }

    public Category() {
    }
   
    public int getCategoryID() {
        return id;
    }

    public void setCategoryID(int categoryID) {
        this.id = categoryID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
