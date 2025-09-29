package com.codes.productcatalog.cofig;

import com.codes.productcatalog.model.Category;
import com.codes.productcatalog.model.Product;
import com.codes.productcatalog.repository.CategoryRepository;
import com.codes.productcatalog.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
//        clear all Existing data
        productRepository.deleteAll();
        categoryRepository.deleteAll();

//        Create Category
        Category electronics = new Category();
        electronics.setName("Refrigerator");

        Category autoMobile = new Category();
        autoMobile.setName("Audi");

        Category home = new Category();
        home.setName("Dream Home");

        categoryRepository.saveAll(Arrays.asList(electronics, autoMobile, home));

        // Create Products
        Product p1 = new Product();
        p1.setName("Samsung Double Door Fridge");
        p1.setDescription("Energy efficient, frost-free refrigerator");
        p1.setPrice(35000.0);
        p1.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQq9FBY7clZyjvOOmB7MyHrP8Iyu0CaIKh4IQ&s");
        p1.setCategory(electronics);

        Product p2 = new Product();
        p2.setName("LG Smart Refrigerator");
        p2.setDescription("Smart inverter with WiFi control");
        p2.setPrice(42000.0);
        p2.setImageUrl("https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRniSAjwwbg2Lh_6rHdp5h8fpIoIvumJgkHVXO2_5gaWJUQHX30H1Xbf71vv52ddFHUCiO5KQiAoK8_0tRQFcWs1cpzOlUSl5IWaKQJACY");
        p2.setCategory(electronics);

        Product p3 = new Product();
        p3.setName("Audi A6");
        p3.setDescription("Luxury sedan with premium comfort");
        p3.setPrice(5500000.0);
        p3.setImageUrl("https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRWMg0h0cUuML6HzRA9rXHjUEQPfXB6fx-cRl00kQSddWCuBfU-ZXYPhYV5HdfQs9HE2kC3eWi9Qf4B7Gb7dVaaZpvf0wcSNv3Xf9KBwA");
        p3.setCategory(autoMobile);

        Product p4 = new Product();
        p4.setName("Audi Q7");
        p4.setDescription("Luxury SUV with quatrain drive");
        p4.setPrice(7500000.0);
        p4.setImageUrl("https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcT57jpxt7qyi1zrgwpBWyV_40S739oiyCjNVVJvgPSGsITXpXoLLOJchb4eeAQzLie3EEtEFIdplyRPPpSlVwpVLLDu-s7d44sqDoskHCw");
        p4.setCategory(autoMobile);

        Product p5 = new Product();
        p5.setName("Modern Villa");
        p5.setDescription("3BHK villa with swimming pool");
        p5.setPrice(15000000.0);
        p5.setImageUrl("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTmlFPsLhoBC1ka68w1WDiIWMEDQ0k9UctMHg&s");
        p5.setCategory(home);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));
    }
}
