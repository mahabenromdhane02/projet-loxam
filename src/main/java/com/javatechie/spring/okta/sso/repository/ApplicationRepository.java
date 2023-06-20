package com.javatechie.spring.okta.sso.repository;

import com.javatechie.spring.okta.sso.dao.Applicationinformations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository<Applicationinformations, Long> {
    Optional<Applicationinformations> findByidApp(String idApp);
}
