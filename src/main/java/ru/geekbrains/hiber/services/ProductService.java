package ru.geekbrains.hiber.services;

import org.springframework.beans.factory.annotation.Autowired;
import ru.geekbrains.hiber.entities.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.hiber.repositories.ProductRepository;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

 //   @Autowired
 //   public void SetProductRepository(ProductRepository productRepository){this.productRepository = productRepository;}

    public List<Product> getProducts() {
        return productRepository.read();
    }

    public void addProduct(Product product) {productRepository.create("newProduct", 23.23);}

    public void updateProduct(Long id, String newtitle, double newcost) {
        productRepository.update(id,newtitle, newcost);
    }

    public void deleteProduct(long id) {
        productRepository.delete(id);
    }


}
