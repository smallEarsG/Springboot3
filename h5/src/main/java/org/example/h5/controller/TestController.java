package org.example.h5.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.extern.slf4j.Slf4j;
import org.example.h5.common.CommonResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "账户验证相关", description = "包括用户登录、注册、验证码请求等操作。")
@RestController
public class TestController {
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "测试成功"),
            @ApiResponse(responseCode = "500", description = "测试失败")   //不同返回状态码描述
    })
    @Operation(summary = "请求用户数据测试接口")   //接口功能描述
    @GetMapping("/")
    public CommonResponseDto<String> test(){
//        log.info("===test===");
        log.error("oldA error");
        log.info("oldA info");
        log.warn("oldA warn");
        return CommonResponseDto.success("wodecehis");
    }
}
