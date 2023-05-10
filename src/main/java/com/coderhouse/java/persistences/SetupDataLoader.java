package com.coderhouse.java.persistences;

import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.persistences.models.Product;
import com.coderhouse.java.persistences.repositories.ClientRepository;
import com.coderhouse.java.persistences.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${initData:false}")
    private String initData;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (initData.equals("false")) return;

        Client client = Client.createWith("Bogdan", "Bedyukh", "12345678");
        clientRepository.save(client);

        Product product = Product.createWith("Coca-Cola 500cc", "CC500", 199.99, 99);
        productRepository.save(product);

        initData = "false";
    }

}