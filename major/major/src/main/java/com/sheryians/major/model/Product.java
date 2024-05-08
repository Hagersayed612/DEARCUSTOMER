package com.sheryians.major.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable= false )
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="category_id" ,referencedColumnName = "category_id")
    private Category category;

    @Column(nullable= false )
    private double price;
    @Column(nullable= false )
    private double AvailableNumber;
    @Column(nullable= false )
    private String description;
    @Column(nullable= false )
    private String imageName;

     


}
