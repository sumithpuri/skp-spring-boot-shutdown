/**
 * 
 */
package com.springboot.shutdown.controller;

import javax.annotation.PreDestroy;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.shutdown.SpringBootShutdownApplication;

/**
 * @author sumith.puri
 *
 */
@RestController
public class SpringBootShutdownController {

	@RequestMapping(value = "/quote", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getQuote() {

		StringBuffer quote = new StringBuffer();
		quote.append("Success is often achieved by those who don't know that failure is inevitable.");
		return new ResponseEntity<>(quote.toString(), HttpStatus.OK);
	}

	
	// Starting Threads using Runnable is Not Always the Best Way..
	// Do Explore Other Integrated Approaches such as Async Servlet
	@RequestMapping(value = "/shutdown2",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Object> closeContext() {
		System.out.println("Entry Thread Id (Debug): " + Thread.currentThread().getName());
		Runnable runnable= () -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				System.out.println("Thread was Interrupted! Error in Thread Sleep (2 Seconds!)");
			}
			System.out.println("Callable Thread Id: " + Thread.currentThread().getName());
			SpringBootShutdownApplication.closeApplicationContext();		
		};
		
		new Thread(runnable).start();
		System.out.println("Exit Thread Id (Debug): " + Thread.currentThread().getName());
		return new ResponseEntity<>("Shutdown Requested via Closing Application Context - Will Shutdown in Next 2 Seconds!", HttpStatus.OK);
	}
	
	// Starting Threads using Runnable is Not Always the Best Way..
	// Do Explore Other Integrated Approaches such as Async Servlet
	@RequestMapping(value = "/shutdown3",  method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<Object> exitApplication() {
		System.out.println("Entry Thread Id (Debug): " + Thread.currentThread().getName());
		Runnable runnable= () -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				System.out.println("Thread was Interrupted! Error in Thread Sleep (2 Seconds!)");
			}
			System.out.println("Callable Thread Id: " + Thread.currentThread().getName());
			SpringBootShutdownApplication.exitApplication();	
		};
		
		new Thread(runnable).start();
		System.out.println("Exit Thread Id (Debug): " + Thread.currentThread().getName());
		return new ResponseEntity<>("Shutdown Requested via Spring Application Exit - Will Shutdown in Next 2 Seconds!", HttpStatus.OK);
	}


	
	@PreDestroy
	public void requestShutdown2PreDestroy() {

		System.out.println("Requested Shutdown (via Context) of the Spring Boot Container");
	}
}
