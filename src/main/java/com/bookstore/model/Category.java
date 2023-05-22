package com.bookstore.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "Category.getAllCategory", query = "SELECT c from Category c where c.id in (SELECT p.category from Product p where p.status='true')")

@NamedQuery(name = "Category.getGraph", query = "SELECT c.name, COUNT (p.id) FROM Category c LEFT JOIN Product p ON c.id=p.category.id GROUP BY c.name")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

}
