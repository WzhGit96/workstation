package com.wzh.workstation.controller;

import com.wzh.workstation.bo.WorkProjectReqBO;
import com.wzh.workstation.bo.WorkProjectRspBO;
import com.wzh.workstation.dto.WorkProjectReqDTO;
import com.wzh.workstation.dto.WorkProjectRspDTO;
import com.wzh.workstation.service.IWorkProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wzhframework.workstation.common.dto.GenericRspDTO;

@RestController
@RequestMapping("/workstation/workproject")
public class WorkProjectController {
    @Autowired
    private IWorkProjectService workProjectService;

    @GetMapping("/page-list")
    public GenericRspDTO<WorkProjectRspDTO> pageList(@Validated WorkProjectReqDTO reqDTO) {
        WorkProjectReqBO reqBO = new WorkProjectReqBO();
        BeanUtils.copyProperties(reqDTO, reqBO);
        WorkProjectRspBO rspBO = workProjectService.pageList(reqBO);
        WorkProjectRspDTO rspDTO = new WorkProjectRspDTO();
        BeanUtils.copyProperties(rspBO, rspDTO);
        return GenericRspDTO.newSuccessInstance(rspDTO);
    }
}
