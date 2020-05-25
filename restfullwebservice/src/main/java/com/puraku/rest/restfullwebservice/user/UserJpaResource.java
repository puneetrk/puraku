package com.puraku.rest.restfullwebservice.user;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJpaResource {

	@Autowired
	UserDaoService service;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/user/{id}")
	public EntityModel<User>   retrieveUser(@PathVariable int id)
	{
		Optional<User> optional = userRepository.findById(id);
		if(!optional.isPresent())
			throw new UserNotFoundException("id:"+id);
		
		//"all-users", SERVER_PATH + "/users"
				//retrieveAllUsers
		EntityModel<User> resource = new EntityModel<User>(optional.get());
				
				WebMvcLinkBuilder linkTo = 
						linkTo(methodOn(this.getClass()).retrieveAllUsers());
				
				resource.add(linkTo.withRel("all-users"));
				
				//HATEOAS
				
		return resource;
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity<User> createUser(@RequestBody User user)
	{
		User savedUser = userRepository.save(user);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity<User> createUserPost(@PathVariable Integer id,@RequestBody Post post)
	{
		Optional<User> userOptional =  userRepository.findById(id);
		
		if(!userOptional.isPresent())
			throw new UserNotFoundException("Id:"+id);
		
		User user = userOptional.get();
		
		post.setUser(user);

		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId()).toUri(); 
				
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/user/{id}")
	public void deleteUser(@PathVariable int id)
	{
		userRepository.deleteById(id);
		
//		if(user==null)
//			throw new UserNotFoundException("Id-"+id);
//		
		return;
	}

}
