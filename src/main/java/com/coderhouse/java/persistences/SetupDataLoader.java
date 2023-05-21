package com.coderhouse.java.persistences;

import com.coderhouse.java.persistences.models.Client;
import com.coderhouse.java.persistences.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    @Value("${initData:false}")
    private String initData;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (initData.equals("false")) return;

        Client client = Client.createWith("Bogdan", "Bedyukh", new GregorianCalendar(1995, Calendar.FEBRUARY, 12));
        clientRepository.save(client);

        initData = "false";
    }

}