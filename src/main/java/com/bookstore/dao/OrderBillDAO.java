package com.bookstore.dao;

import com.bookstore.model.OrderBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderBillDAO extends JpaRepository<OrderBill, Integer> {

    List<OrderBill> getAllBills();

    List<OrderBill> getBillByUsername(@Param("name") String username);

}
