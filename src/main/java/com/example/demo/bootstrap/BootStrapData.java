package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Division;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final DivisionRepository divisionRepository;
    private final CustomerRepository customerRepository;

    public BootStrapData(CustomerRepository customerRepository, DivisionRepository divisionRepository) {
        this.divisionRepository = divisionRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (customerRepository.count() == 1) {
            Division division = new Division(1L,"New Hampshire");
            divisionRepository.save(division);

            Customer customer1 = new Customer("1", "A", "B", "1", "1", division);
            Customer customer2 = new Customer("2", "B", "C", "1", "1", division);
            Customer customer3 = new Customer("3", "C", "D", "1", "1", division);
            Customer customer4 = new Customer("4", "D", "E", "1", "1", division);
            Customer customer5 = new Customer("5", "E", "F", "1", "1", division);

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
            customerRepository.save(customer4);
            customerRepository.save(customer5);
        }
    }
}
