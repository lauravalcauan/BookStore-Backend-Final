package com.bookstore.service;

import com.bookstore.model.OrderBill;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface OrderBillService {

    ResponseEntity<String> generateReport(Map<String, Object> requestMap);

    ResponseEntity<List<OrderBill>> getBills();

    ResponseEntity<String> deleteBill(Integer id);

    ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

}
