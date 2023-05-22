package com.bookstore.restImpl;

import com.bookstore.constents.BookStoreConstant;
import com.bookstore.model.OrderBill;
import com.bookstore.rest.OrderBillRest;
import com.bookstore.service.OrderBillService;
import com.bookstore.utils.BookStoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.awt.print.Book;
import java.util.List;
import java.util.Map;

@Controller
public class OrderBillRestImpl implements OrderBillRest {

    @Autowired
    OrderBillService orderBillService;

    @Override
    public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
        try {
            return orderBillService.generateReport(requestMap);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookStoreUtils.getResponseEntity(BookStoreConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<OrderBill>> getBills() {
        try {
            return orderBillService.getBills();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> deleteBill(Integer id) {
        try {
            return orderBillService.deleteBill(id);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookStoreUtils.getResponseEntity(BookStoreConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
        try {
            return orderBillService.getPdf(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
