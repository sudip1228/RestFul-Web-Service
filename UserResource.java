package rest.user;
//now no need to mention class name to access methods from webmvclinkbuilder.
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}
/*
	@GetMapping("/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		return service.findOne(id);
	}*/
	//CREATED
	// input - details of user
	// output - CREATED & Return the created URI
	//@PostMapping("/users")//post is used to create something.use postman app to use postrequest.
	//public void createUser(@RequestBody User user){//@requestbody do mapping to the class fields.
	//	User savedUser = service.save(user);
	//}
	
	@PostMapping("/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user) {//creating a user and sending a response back.
		User savedUser = service.save(user);     //@Valid validates the user.we are using @past and @size in user class along with @valid.
		// CREATED
		// /user/{id}     savedUser.getId()
		
		URI location = ServletUriComponentsBuilder
			.fromCurrentRequest()
			.path("/{id}")
			.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);
		
		if(user==null) {
			throw new UserNotFoundException("id-"+ id);
		}
		//"all-users", SERVER_PATH + "/users"   //now we are adding web-link along with the users when we retrieve his details.these codes below helps to add link.
				//retrieveAllUsers
				EntityModel<User> model = new EntityModel<User>(user);
				
				WebMvcLinkBuilder linkTo = 
						linkTo(methodOn(this.getClass()).retrieveAllUsers());
				
				model.add(linkTo.withRel("all-users"));
				
				//HATEOAS
		return model;
	}

	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id) {
		User user = service.deleteById(id);//if user is deleted it returns status of 200 which means it is successsful.
		
		if(user==null)
			throw new UserNotFoundException("id-"+ id);		
	}
	
}