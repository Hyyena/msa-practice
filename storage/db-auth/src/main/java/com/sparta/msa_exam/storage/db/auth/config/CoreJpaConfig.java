package com.sparta.msa_exam.storage.db.auth.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EntityScan(basePackages = "com.sparta.msa_exam.storage.db.auth")
@EnableJpaRepositories(basePackages = "com.sparta.msa_exam.storage.db.auth")
class CoreJpaConfig {

}
