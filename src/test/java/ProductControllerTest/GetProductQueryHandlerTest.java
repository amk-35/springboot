package ProductControllerTest;

import com.example.demo.DemoApplication;
import com.example.demo.Exceptions.ProductNotFoundException;
import com.example.demo.Exceptions.ProductNotValidException;
import com.example.demo.Product.Model.Product;
import com.example.demo.Product.Model.ProductDTO;
import com.example.demo.Product.ProductRepository;
import com.example.demo.Product.queryhandlers.GetProductQueryHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = DemoApplication.class)
public class GetProductQueryHandlerTest {
    @InjectMocks
    private GetProductQueryHandler getProductQueryHandler;
    @Mock
    private ProductRepository productRepository;
    @Test
    public void GetProductQueryHandler_validId_returnsProductDTO() {
        Product product = new Product();
        product.setId(1);
        product.setName("chocolate");
        product.setDescription("chocolate bars");
        product.setPrice(-199);
        product.setQuantity(34);
        when(productRepository.findById(product.getId())).thenReturn(Optional.of(product));
        ProductDTO exceptedDTO=new ProductDTO(product);
        ResponseEntity<ProductDTO> actualResponse=getProductQueryHandler.execute(product.getId());
        assertEquals(exceptedDTO,actualResponse.getBody());
        assertEquals(HttpStatus.OK,actualResponse.getStatusCode());
    }

    @Test
    public void GetProductQueryHandler_invalidId_returnsProductNotFoundException() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        ProductNotFoundException exception = assertThrows(ProductNotFoundException.class, () -> getProductQueryHandler.execute(1));
        assertEquals("Product Not Found", exception.getSimpleResponse().getMessage());

    }
}
