package com.vick.xiu.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vick.framework.page.PageRequest;
import com.vick.framework.result.ResultModel;
import com.vick.xiu.web.response.ClassResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class IClassServiceTest {

    @Autowired
    private IClassService iClassService;

    @Test
    void list() throws JsonProcessingException {
        PageRequest request = new PageRequest();
        request.setCurrentPage(1);
        request.setPageSize(10);
        ResultModel<IPage<ClassResponse>> iPage = iClassService.list(request);
        log.error(new ObjectMapper().writeValueAsString(iPage));
    }
}