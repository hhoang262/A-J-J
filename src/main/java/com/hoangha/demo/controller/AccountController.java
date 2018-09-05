package com.hoangha.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
@RequestMapping(value = "/api-v1")
public class AccountController {

	@RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = { "application/json"})
	@ResponseBody
	public ResponseEntity<String> getListAccount(){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		on.put("result", "Sucessful");
		return new ResponseEntity<>(on.toString(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.POST, produces = { "application/json"})
	@ResponseBody
	public ResponseEntity<String> createNewListAccount(@RequestBody String body){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		on.put("result", "Sucessful");
		return new ResponseEntity<>(on.toString(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET, produces = { "application/json"})
	@ResponseBody
	public ResponseEntity<String> selectAccount(@PathVariable String id){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		on.put("result", "Sucessful");
		return new ResponseEntity<>(on.toString(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.PUT, produces = { "application/json"})
	@ResponseBody
	public ResponseEntity<String> updateAccount(@PathVariable String id){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		on.put("result", "Sucessful");
		return new ResponseEntity<>(on.toString(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/accounts", method = RequestMethod.DELETE, produces = { "application/json"})
	@ResponseBody
	public ResponseEntity<String> deleteAccount(@PathVariable String id){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		on.put("result", "Sucessful");
		return new ResponseEntity<>(on.toString(),HttpStatus.OK);
	}
	
}
