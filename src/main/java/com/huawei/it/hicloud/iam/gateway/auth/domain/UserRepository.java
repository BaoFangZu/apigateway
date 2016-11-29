package com.huawei.it.hicloud.iam.gateway.auth.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<SessionUser, Long> {
	List<SessionUser> findByUsername(String username);
}