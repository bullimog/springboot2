package main;

//Bean basics
//http://zetcode.com/articles/springbootbean/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication //Auto runs CommandLineRunner.run(); auto-configuration; Scan for components
public class Application implements CommandLineRunner {

    @Autowired // Finds and Auto injects AppName Bean into here.
    private AppName appName;


    @Bean //Create and register Bean within Spring container context.
          //Bean method can have input fields, for configuration.
    public AppName getAppName(@Value("${app.name}") String appName, @Value("Foo") String appName1 ) {

        //The bean constructs a concrete instance of the required interface type.
        //The method is implemented, as required.
        return new AppName() {

            @Override
            public String getName() {
                return appName + appName1;
            }
            public int getAge() {
                return 77;
            }
        };
    }

    @Override //CommandLineRunner
    public void run(String... args) throws Exception {
        
        //appName is a reference to the autowired Bean.
        //getName() implementation is defined in the Bean definition.
        System.out.println(appName.getName() + appName.getAge());
    }
    
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }    
}
