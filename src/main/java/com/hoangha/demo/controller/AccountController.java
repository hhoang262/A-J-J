package com.hoangha.demo.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hoangha.demo.model.Account;
import com.hoangha.demo.service.AccountService;
import com.hoangha.demo.service.AccountServiceImpl;
import com.hoangha.demo.model.Project;

@Controller
@RequestMapping(value = "/api-v1")
public class AccountController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@Value("${storeLocationSubmitPackage}")
	String pathLocation;

	@Autowired
	AccountService accountService;
	
	@Autowired
	AccountServiceImpl accountServiceImpl;

	@RequestMapping(value = "/accounts", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<String> getListAccount() {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		List<Integer> givenList = Arrays.asList(0, 0, 0, 0, 1);
		
		if(givenList.get(new Random().nextInt(givenList.size())) == 1) {
			on.put("result", "false");
		}
		
		return new ResponseEntity<>(on.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.POST, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<String> createNewListAccount(@RequestBody String body)
			throws JsonProcessingException, IOException, SQLException, ClassNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode data = mapper.readTree(body);
		 
		data.get("data").forEach(d -> {
			List<Project> listProject = new ArrayList<>();
			Account a = new Account(UUID.randomUUID().toString(), d.get("title").asText(), d.get("name").asText(),listProject);
			accountServiceImpl.save(a);
		});
		
		return new ResponseEntity<>("Import Succesfully", HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts/{id}", method = RequestMethod.GET, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<String> selectAccount(@PathVariable String id) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		on.put("result", "Sucessful");
		return new ResponseEntity<>(on.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.PUT, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<String> updateAccount(@PathVariable String id) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		on.put("result", "Sucessful");
		return new ResponseEntity<>(on.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/accounts", method = RequestMethod.DELETE, produces = { "application/json" })
	@ResponseBody
	public ResponseEntity<String> deleteAccount(@PathVariable String id) {
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode on = mapper.createObjectNode();
		on.put("result", "Sucessful");
		return new ResponseEntity<>(on.toString(), HttpStatus.OK);
	}

	@RequestMapping(value = "/submitpackage", method = RequestMethod.POST, produces = { "application/json" })
	public ResponseEntity<String> submitPackage(@RequestBody String body) throws JsonProcessingException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode json = mapper.readTree(body);
		String packageId = json.path("notificationEnvelop").path("packageInfo").path("packageId").asText();
		File file = new File(this.pathLocation + "submitPackage");
		FileUtils.forceMkdir(file);
		FileUtils.writeStringToFile(new File(file.getAbsolutePath() + "/" + packageId
				+ DateFormatUtils.format(new Date(), "yyyy-MM-dd HH-mm-ss-SSS") + ".json"), body, "utf-8");
		LOGGER.info("Stored file: {} in {}", packageId, file.getAbsolutePath());
		 return new ResponseEntity<>("{\n" +
                 " \"result\" : \"Received request\"\n" +
                 "}", HttpStatus.OK);
	}

}
