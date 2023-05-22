package com.bookstore.serviceImpl;

import com.bookstore.JWT.JWTUtil;
import com.bookstore.JWT.JwtFilter;
import com.bookstore.constents.BookStoreConstant;
import com.bookstore.dao.ProductDAO;
import com.bookstore.model.Category;
import com.bookstore.model.Product;
import com.bookstore.service.ProductService;
import com.bookstore.utils.BookStoreUtils;
import com.bookstore.wrapper.ProductWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductDAO productDAO;

    @Autowired
    JwtFilter jwtFilter;

    @Autowired
    JWTUtil jwtUtil;

    @Override
    public ResponseEntity<String> addNewProduct(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()) {
                if(validateProductMap(requestMap, false)) {
                    productDAO.save(getProductFromMap(requestMap, false));
                    return BookStoreUtils.getResponseEntity("Product Added Successfully!", HttpStatus.OK);
                }
                return BookStoreUtils.getResponseEntity(BookStoreConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
            } else
                return BookStoreUtils.getResponseEntity(BookStoreConstant.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookStoreUtils.getResponseEntity(BookStoreConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
        if(requestMap.containsKey("title") && requestMap.containsKey("author")) {
            if(requestMap.containsKey("id") && validateId) {
                return true;
            } else if(!validateId) {
                return true;
            }
        }
        return false;
    }

    private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
        Category category = new Category();
        category.setId(Integer.parseInt(requestMap.get("categoryId")));

        Product product = new Product();
        if(isAdd) {
            product.setId(Integer.parseInt(requestMap.get("id")));
        } else {
            product.setStatus("true");
        }
        product.setCategory(category);
        product.setTitle(requestMap.get("title"));
        product.setAuthor(requestMap.get("author"));
        product.setDescription(requestMap.get("description"));
        product.setPrice(Integer.parseInt(requestMap.get("price")));
        return product;
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getAllProduct() {
        try {
            return new ResponseEntity<>(productDAO.getAllProduct(), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()) {
                if(validateProductMap(requestMap, true)) {
                    Optional<Product> optional = productDAO.findById(Integer.parseInt(requestMap.get("id")));
                    if(!optional.isEmpty()) {
                        Product product = getProductFromMap(requestMap, true); // we need to pass it true so we get the id for this object/product
                        product.setStatus(optional.get().getStatus());
                        productDAO.save(product);
                        return BookStoreUtils.getResponseEntity("Product Updated Successfully!", HttpStatus.OK);
                    } else {
                        return BookStoreUtils.getResponseEntity("Product id doesn't exist.", HttpStatus.OK);
                    }
                } else {
                    return BookStoreUtils.getResponseEntity(BookStoreConstant.INVALID_DATA, HttpStatus.BAD_REQUEST);
                }
            } else {
                return BookStoreUtils.getResponseEntity(BookStoreConstant.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookStoreUtils.getResponseEntity(BookStoreConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> deleteProduct(Integer id) {
        try {
            if(jwtFilter.isAdmin()){
                Optional optional = productDAO.findById(id);
                if(!optional.isEmpty()) {
                    productDAO.deleteById(id);
                    return BookStoreUtils.getResponseEntity("Product Deleted Successfully!", HttpStatus.OK);
                } else {
                    return BookStoreUtils.getResponseEntity("Product id doesn't exist!", HttpStatus.OK);
                }
            } else {
                return BookStoreUtils.getResponseEntity(BookStoreConstant.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookStoreUtils.getResponseEntity(BookStoreConstant.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {
        try {
            if(jwtFilter.isAdmin()) {
                Optional optional = productDAO.findById(Integer.parseInt(requestMap.get("id")));
                if(!optional.isEmpty()) {
                    productDAO.updateProductStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
                    return BookStoreUtils.getResponseEntity("Status Updated Successfully!", HttpStatus.OK);
                } else {
                    return BookStoreUtils.getResponseEntity("Product id doesn't exist!", HttpStatus.OK);
                }
            } else {
                return BookStoreUtils.getResponseEntity(BookStoreConstant.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return BookStoreUtils.getResponseEntity(BookStoreConstant.SOMETHING_WENT_WRONG, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {
        try {
            return new ResponseEntity<>(productDAO.getProductByCategory(id), HttpStatus.OK);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<ProductWrapper> getById(Integer id) {
        try {
            return new ResponseEntity<>(productDAO.getProductById(id), HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
