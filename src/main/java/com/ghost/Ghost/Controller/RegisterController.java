package com.ghost.Ghost.Controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ghost.Ghost.Respository.RegisterRepository;
import com.ghost.Ghost.model.Register;
@RestController
@RequestMapping("/api/Register")
public class RegisterController 
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RegisterController.class);

@Autowired
private RegisterRepository registerRepository;

@GetMapping
public List<Register> ShowRegister()
{
	return registerRepository.findAll();
}
@PostMapping

public void InsertRegister(@RequestBody @Valid Register register,BindingResult bindingResult) 
{
	if(bindingResult.hasErrors()) {
        bindingResult.getAllErrors().forEach(err -> {
            LOGGER.info("ERROR {}", err.getDefaultMessage());
        });
	}
	System.out.println("Name of Register:"+ register.getName());
	registerRepository.save(register);
}
@GetMapping("/{id}")
public Register GetRegisterbyId(@PathVariable("id") long id) 
{	
  return registerRepository.getOne(id);
}
@GetMapping("/RegisterName/{name}")
public String GetRegisterbyName(@PathVariable("name") String name) 
{	
  
	return registerRepository.findByname(name).getName();
}
@PutMapping("/{id}")
public void UpdateRegister(@RequestBody Register register,@PathVariable("id") long id) 
{
		register.setId(id);
		registerRepository.save(register);		
}
@DeleteMapping("/{id}")
public void DeleteRegister(@PathVariable("id") long id) 
{
	 registerRepository.deleteById(id);

}



}
