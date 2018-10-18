package com.hoangha.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.hoangha.demo.model.Account;
import com.hoangha.demo.repository.AccountElasticSearchRepository;

@Service
public class AccountServiceImpl implements IAccountService{

	@Autowired
	AccountElasticSearchRepository accountRepository;
	
	
	@Override
	public Account save(Account acc) {
		return accountRepository.save(acc);
	}

	@Override
	public void delete(Account acc) {
		accountRepository.delete(acc);
		
	}

	@Override
	public Account findOne(String id) {
		return accountRepository.findOne(id);
	}

	@Override
	public Iterable<Account> findAll() {
		return accountRepository.findAll();
	}

	@Override
	public Page<Account> findByTitle(String title, PageRequest pageRequest) {
		return accountRepository.findByTitle(title, pageRequest);
	}

	@Override
	public List<Account> findByName(String name) {
		return accountRepository.findByName(name);
	}

}
