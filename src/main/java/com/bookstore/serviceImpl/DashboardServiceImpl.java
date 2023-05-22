package com.bookstore.serviceImpl;

import com.bookstore.dao.CategoryDAO;
import com.bookstore.dao.OrderBillDAO;
import com.bookstore.dao.ProductDAO;
import com.bookstore.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    ProductDAO productDAO;

    @Autowired
    OrderBillDAO orderBillDAO;

    @Override
    public ResponseEntity<Map<String, Object>> getDetails() {
        Map<String, Object> map = new HashMap<>();
        map.put("category", categoryDAO.count());
        map.put("product", productDAO.count());
        map.put("bill", orderBillDAO.count());
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
