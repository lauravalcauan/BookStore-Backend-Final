package com.bookstore.model;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@NamedQuery(name = "OrderBill.getAllBills", query = "SELECT b from OrderBill b ORDER BY b.id desc")
@NamedQuery(name = "OrderBill.getBillByUsername", query = "SELECT b FROM OrderBill b where b.createdBy=:name ORDER BY b.id desc")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "orderbill")
public class OrderBill implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "contactnumber")
    private String contactNumber;

    @Column(name = "paymentmethod")
    private String paymentMethod;

    @Column(name = "total")
    private Integer total;

    @Column(name = "productdetails", columnDefinition = "json")
    private String productDetails;

    @Column(name = "createdby")
    private String createdBy;

}
