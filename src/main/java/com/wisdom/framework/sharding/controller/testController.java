package com.wisdom.framework.sharding.controller;
import com.wisdom.framework.sharding.service.ActionLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/testController")
@Api(tags = "分库分表测试V1.0")
@SuppressWarnings({"unchecked","rawtypes"})
public class testController {
    @Autowired
    private ActionLogService actionLogService;

    @GetMapping("/find")
    @ApiOperation(value="查询接口信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startPage", value = "页码（从1开始）", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "pageLimit", value = "分页偏移量", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "beginDate", value = "开始时间", required =false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "end_date", value = "结束时间", required =false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required = false, dataType = "String", paramType = "query"),
    })
    public Object find(
            @RequestParam(value="startPage",required=false) String startPage,
            @RequestParam(value="pageLimit",required=false) String pageLimit,
            @RequestParam(value="beginDate",required=false)String beginDate,
            @RequestParam(value="end_date",required=false)String end_date,
            @RequestParam(value="status",required=false)String status){
        return actionLogService.selectAll(startPage,pageLimit,beginDate,end_date,status);
    }


    @GetMapping("/insertOrder")
    @ApiOperation(value="查询接口信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", required = false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "date", value = "时间", required =false, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "status", value = "状态", required =false, dataType = "String", paramType = "query"),
    })
    public Object insert(
            @RequestParam(value="id",required=false)String id,
            @RequestParam(value="date",required=false)String date,
            @RequestParam(value="status",required=false)String status
            ){
        return actionLogService.insertOrder(id,date,status);
    }

}
