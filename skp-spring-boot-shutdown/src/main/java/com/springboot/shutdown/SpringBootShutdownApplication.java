package com.springboot.shutdown;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@EnableAutoConfiguration
public class SpringBootShutdownApplication {

	// Reference: https://www.javadevjournal.com/spring-boot/shutdown-spring-boot-application/
	
	// Clean Shutdown Method 2 - Actuator Shutdown Endpoint - Store the Context
	// Not a Recommended Way to Use public static - Only for the Demo Purposes..
	private static ConfigurableApplicationContext ctx;

	public static void main(String[] args) {

		
		// Clean Shutdown Method 3 - Actuator Shutdown Endpoint
		// Clean Shutdown Method 4 - Close Application Context.
		// Clean Shutdown Method 5 - Exit Spring Application 
		
		 ctx = SpringApplication.run(SpringBootShutdownApplication.class, args);  
		
		 
		// Clean Shutdown Method 1a Write Process Id to a File (The, Either Write a Batch Script or Manually Kill)
		// SpringApplication springBootapplication = new SpringApplication(SpringBootShutdownApplication.class);
		// springBootapplication.addListeners(new ApplicationPidFileWriter("sbshutdownwin.pid"));
		// springBootapplication.run();
	}
	
	public static void exitApplication() {
		int staticExitCode = SpringApplication.exit(ctx, new ExitCodeGenerator() {
			
		   @Override
		   public int getExitCode() {
		    // no errors
		    return 0;
		   }
		});
	
		System.exit(staticExitCode );
	}
	
	public static void closeApplicationContext() {
		
		ctx.close();
	}
}
