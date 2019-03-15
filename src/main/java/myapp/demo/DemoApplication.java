package myapp.demo;

import myapp.demo.utils.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.annotation.Resource;

@SpringBootApplication
@EnableJpaAuditing
public class DemoApplication implements CommandLineRunner {

    @Resource
    StorageService storageService;

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }


    public void run(String... args) throws Exception {
   //storageService.deleteAll();
     //  storageService.init();
    }
}

