package io.codewithgx.recorddemo.service.impl;

import org.springframework.stereotype.Service;

import io.codewithgx.recorddemo.model.ApplicationLog;
import io.codewithgx.recorddemo.repo.ApplicationLogRepository;
import io.codewithgx.recorddemo.service.ApplicationLogService;
import lombok.RequiredArgsConstructor;

/**
 * Created by @author Ifeanyichukwu Otiwa
 * 16/09/2022
 */

@Service
@RequiredArgsConstructor
public class ApplicationLogServiceImpl implements ApplicationLogService {

    private final ApplicationLogRepository applicationLogRepository;

    @Override
    public void saveApplicationLog(final ApplicationLog applicationLog) {
        applicationLogRepository.save(applicationLog);
    }
}
