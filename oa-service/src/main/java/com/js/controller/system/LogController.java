package com.js.controller.system;

import com.github.pagehelper.PageInfo;
import com.js.common.annotation.Log;
import com.js.common.enums.StatusCode;
import com.js.common.exception.SystemException;
import com.js.common.response.BaseResponse;
import com.js.form.system.BasePageForm;
import com.js.service.system.SysLogService;
import com.js.vo.system.SysLogVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: 姜爽
 * @Description: 系统日志Controller
 * @Date: 2020/5/4 9:51
 */
@RestController
@RequestMapping("/system/log")
@Api("系统日志Controller")
@Slf4j
public class LogController {

    @Autowired
    private SysLogService sysLogService;

    @PostMapping("getLog")
    @ApiOperation(value = "分页获取系统日志", notes = "分页获取系统日志")
    @Log(value = "分页获取系统日志")
    public BaseResponse<PageInfo<SysLogVo>>
        getLogMess(@RequestBody BasePageForm basePageForm/*,@RequestHeader("studentNumber") String studentNumber*/) {
        PageInfo<SysLogVo> sysLogDtoPageInfo = new PageInfo<>();
        try {
            sysLogDtoPageInfo = sysLogService.showAllLog(basePageForm.getOffset(), basePageForm.getLimit());
        } catch (Exception e) {
            log.info("查询出现的异常为{}", e);
            throw new SystemException("日志查询失败");
        }
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), sysLogDtoPageInfo);
    }
}
