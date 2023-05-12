package com.niziolekp;

import com.niziolekp.customer.Customer;
import com.niziolekp.customer.CustomerRepository;
import com.niziolekp.customer.Gender;
import com.niziolekp.s3.S3Service;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Random;

@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(
            CustomerRepository customerRepository,
            PasswordEncoder passwordEncoder,S3Service s3Service) {
        return args -> {
            //createRandomCustomer(customerRepository, passwordEncoder);
             testBucketUploadAndDownload(s3Service);
        };
    }

    private static void testBucketUploadAndDownload(S3Service s3Service
                                                    ) {
        s3Service.putObject(
                "travel-togheter-niziolekp-client-test",
                "foo/bar/jamila",
                "Hello World".getBytes()
        );

        byte[] obj = s3Service.getObject(
                "travel-togheter-niziolekp-client-test",
                "foo/bar/jamila"
        );

        System.out.println("Hooray: " + new String(obj));
    }

    private static void createRandomCustomer(CustomerRepository customerRepository, PasswordEncoder passwordEncoder) {
        var faker = new Faker();
        Random random = new Random();
        Name name = faker.name();
        String firstName = name.firstName();
        String lastName = name.lastName();
        int age = random.nextInt(16, 99);
        Gender gender = age % 2 == 0 ? Gender.MALE : Gender.FEMALE;
        String email = firstName.toLowerCase() + "." + lastName.toLowerCase() + "@amigoscode.com";
        Customer customer = new Customer(
                firstName +  " " + lastName,
                email,
                passwordEncoder.encode("password"),
                age,
                gender);
        customerRepository.save(customer);
        System.out.println(email);
    }

}
