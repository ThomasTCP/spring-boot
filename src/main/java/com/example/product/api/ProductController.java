package com.example.product.api;

import com.example.product.dto.PatchRequest;
import com.example.product.dto.ProductDto;
import com.example.product.dto.ProductResponse;
import com.example.product.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.List;

@RequestMapping("api/product")
@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Product", description = "The Product API. Contains all the operations that can be performed on a product.")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public void createProduct(@RequestBody ProductResponse productResponse){
        productService.addProduct(productResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> readProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductDtoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> readAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductResponse productResponse){
        productService.updateProduct(id,productResponse);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
    }

    //patch
    @PatchMapping("/{id}")
    public ResponseEntity<?> partialUpdateName(@RequestBody PatchRequest request, @PathVariable("id") Long id) {
        return productService.partialUpdateName(request.getName(),id);
    }

    //copy
    /*can not find*/

    //head
    @RequestMapping(value = "/file/{filename}", method = RequestMethod.HEAD)
    public ResponseEntity<?> head(@PathVariable String filename) {
        Path path = Paths.get(filename);
        if (Files.exists(path)) {
            long length = 0;
            try {
                length = Files.size(path);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error getting file size: " + e.getMessage());
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentLength(length);
            return new ResponseEntity<>(headers, HttpStatus.OK);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //trace
    @RequestMapping(value = "/trace", method = RequestMethod.TRACE)
    public ResponseEntity<String> trace(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("TRACE ").append(request.getRequestURI()).append(" ").append(request.getProtocol()).append("\n");
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String header = headers.nextElement();
            sb.append(header).append(": ").append(request.getHeader(header)).append("\n");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }

    //options
    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> options(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Allow", "GET, POST, PUT, DELETE, OPTIONS");
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    //link
    /*can not find*/

    //unlink
    /*can not find*/
    //purge

    //lock
    /*can not find*/
    //unlock
    /*can not find*/

    //propfind
    /*can not find*/

    //view
    /*can not find*/
}
