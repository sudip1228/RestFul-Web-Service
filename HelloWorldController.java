package rest.Restful_web_services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

//Controller
@RestController//it means it is handling htp request
public class HelloWorldController {
	
	@Autowired
	private MessageSource messageSource; 
	
	//mapping uri
	@GetMapping(path = "/hello-world")//here you can also specify method=RequestMethod.GET before path under annotation @RequestMapping.
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")//it returns the output in JASON format.
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
	}
	
	///hello-world/path-variable/in28minutes
	@GetMapping(path = "/hello-world/path-variable/{name}")//using path variable.whatever you type afterpath-variable/ that will be mapped to {name}.name is called path parameter or variable.
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
	@GetMapping(path = "//hello-world-internationalized")
	public String helloWorldInternationalized() {
		return messageSource.getMessage("good.morning.message", null,LocaleContextHolder.getLocale());
	}
	
}
//springboot autoconfiguration configures error,spring mvc,json sothat we donot need to put lot of work in configuraion.
//dispatcher servlet handles all the request of spring mvc.using (/)means dispatcher servlet is being used.Dispatcher servlet does mapping.Also it converts output to JASON.