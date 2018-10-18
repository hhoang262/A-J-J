package com.hoangha.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.hoangha.demo.model.Account;

public interface IAccountService {

	Account save(Account acc);
	void delete(Account acc);
	Account findOne(String id);
	Iterable<Account> findAll();
	Page<Account> findByTitle(String title, PageRequest pageRequest);
	List<Account> findByName(String name);
}
