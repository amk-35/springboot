package ProductControllerTest;

import com.example.demo.DemoApplication;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.commandhandlers.CreateProductCommandHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = DemoApplication.class)
public class CreateProductCommandHandlerTest {
    @InjectMocks
    private CreateProductCommandHandler createProductCommandHandler;

    @Mock
    private ProductRepository productRepository;

    @Test
    public void createProductCommandHandler_validProduct_returnSuccess() {
        Product product = new Product();
        product.setId(1);
        product.setName("chocolate");
        product.setDescription("chocolate bars");
        product.setPrice(199);
        product.setQuantity(34);
        ResponseEntity<Product> response=createProductCommandHandler.execute(product);
        assertEquals(HttpStatus.OK,response.getStatusCode());
    }

    @Test
    public void createProductCommandHandler_invalidProduct_throwsException() {
        Product product = new Product();
        product.setId(1);
        product.setName("chocolate");
        product.setDescription("chocolate bars");
        product.setPrice(-199);
        product.setQuantity(34);

        ProductNotValidException exception = assertThrows(ProductNotValidException.class, () -> createProductCommandHandler.execute(product));
        assertEquals("Product price cannot be negative", exception.getSimpleResponse().getMessage());
    }

}
