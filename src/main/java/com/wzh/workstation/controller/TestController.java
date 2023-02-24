package com.wzh.workstation.controller;

import com.wzh.workstation.dto.TestReqDTO;
import com.wzh.workstation.dto.TestRspDTO;
import com.wzh.workstation.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzhframework.workstation.common.dto.GenericRspDTO;

/**
 * @author wzh
 * @since 2023/2/24
 */
@RestController
public class TestController {
    @Autowired
    private ITestService testService;

    @GetMapping("/test")
    public GenericRspDTO<TestRspDTO> testStr(TestReqDTO reqDTO) {
        TestRspDTO rspDTO = new TestRspDTO();
        rspDTO.setReturnStr(reqDTO.getParam());
        testService.testDao();
        return GenericRspDTO.newSuccessInstance(rspDTO);
    }
}
