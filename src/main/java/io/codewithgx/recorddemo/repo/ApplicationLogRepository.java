package io.codewithgx.recorddemo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import io.codewithgx.recorddemo.model.ApplicationLog;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 16/09/2022
 */

public interface ApplicationLogRepository extends JpaRepository<ApplicationLog, Integer> {
}
