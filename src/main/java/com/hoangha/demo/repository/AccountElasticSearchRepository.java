package com.hoangha.demo.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.hoangha.demo.model.Account;

public interface AccountElasticSearchRepository extends ElasticsearchRepository<Account, String>{
	
	Page<Account> findByTitle(String title, Pageable pageable);
	List<Account> findByName(String name);
}
