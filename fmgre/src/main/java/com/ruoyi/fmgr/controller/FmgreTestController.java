package com.ruoyi.fmgr.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.fmgr.service.utils.CodeGenerater;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/tester")
public class FmgreTestController {

    @Autowired
    private CodeGenerater codeGenerater;

    @GetMapping("/1")
    public AjaxResult test1() {
        return AjaxResult.success("Hello World");
    }

    @GetMapping("/2")
    public AjaxResult test2() {
        return AjaxResult.success(codeGenerater.generateCode("test", 4, null));
    }
}
