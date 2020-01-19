package com.example.mydemo.service;

import com.example.mydemo.core.bo.SysLogBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SysLogService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public boolean save(SysLogBO sysLogBO) {
        logger.info("************  " + sysLogBO.getParams());
        return true;
    }
}