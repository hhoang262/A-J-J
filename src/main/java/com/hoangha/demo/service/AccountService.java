package com.hoangha.demo.service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoangha.demo.repository.AccountRepository;

@Service
//@EnableScheduling
public class AccountService {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	@Value("${storeLocationSubmitPackage}")
	String pathLocation;

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	protected ElasticsearchTemplate template;

	public String createUserAccount(String body) throws IOException, SQLException, ClassNotFoundException {
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(body);
		String username = node.get("username").asText().trim();
		String password = node.get("password").asText().trim();
		return accountRepository.registerUser(username, password);
	}

	@Scheduled(fixedDelay = 10000)
	private void processPackageSubmit() throws IOException {
		LOGGER.info("AHIHI LEN ROI NEK !!!!! \n\n\n");
		LOGGER.info("AHIHI READ DATA NEK!!!! \n\n\n");
		File directory = new File(pathLocation + "submitPackage");
		FileUtils.forceMkdir(directory);
		IOFileFilter fileFilter = new WildcardFileFilter("*.json");
		Collection<File> fileList = FileUtils.listFiles(directory, fileFilter, FalseFileFilter.FALSE);
		List<Integer> givenList = Arrays.asList(0, 0, 0, 0, 1);
		fileList.forEach(file -> {
			try {
				if (givenList.get(new Random().nextInt(givenList.size())) == 1) {
					FileUtils.moveFileToDirectory(file, new File(pathLocation + "processed"), true);
				} else {
					FileUtils.moveFileToDirectory(file, new File(pathLocation + "error"), true);
				}
			} catch (Exception e) {
				try {
					FileUtils.moveFileToDirectory(file, new File(pathLocation + "error"), true);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

	}

}
