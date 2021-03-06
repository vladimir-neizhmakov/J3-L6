package ru.geekbrains.hiber.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.geekbrains.hiber.entities.Customer;
import ru.geekbrains.hiber.entities.Product;
import ru.geekbrains.hiber.repositories.CustomerRepository;
import ru.geekbrains.hiber.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class HW6Service {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CustomerRepository customerRepository;
 //   @Autowired
 //   public void SetProductRepository(ProductRepository productRepository){this.productRepository = productRepository;}

    public List<Product> getProducts() {
        return productRepository.read();
    }

    public void addProduct(Product product) {productRepository.create(product);}

    public void updateProduct(Long id, String newtitle, BigDecimal newcost) {
        productRepository.update(id,newtitle, newcost);
    }

    public void deleteProduct(long id) {
        productRepository.delete(id);
    }

    public List<Customer> getCustomers() {
        return customerRepository.read();
    }

    public void addCustomer(Customer customer) {customerRepository.create(customer);}

    public void updateCustomer(Long id, String fullname) {
        customerRepository.update(id, fullname);
    }

    public void deleteCustomer(long id) {
        customerRepository.delete(id);
    }

    public List getCustomerProducts(long id){return productRepository.getCustomerProducts(id);    }
    public List getProductCustomers(long id){return customerRepository.getProductCustomers(id);    }

}
