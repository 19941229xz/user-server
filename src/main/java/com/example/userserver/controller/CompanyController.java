package com.example.userserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userserver.common.*;
import com.example.userserver.model.Company;
import com.example.userserver.service.CompanyService;

import javax.validation.Valid;

@Api(value = "company模块接口",description = "这是一个公司模块的接口文档")
@RestController
@Slf4j
public class CompanyController {

	@Autowired
    CompanyService companyService;

	@ApiOperation("查询所有公司 支持多条件分页排序查询")
    @PostMapping("/getAllCompany")
    public Object getAllCompany(@RequestBody PageParam<Company> pageParam){
        return MyRsp.success(companyService.getAllCompany(pageParam)).msg("查询成功");
    }
    
    @ApiOperation("按照关键字高级检索所有公司 支持分页和排序")
    @PostMapping("/superSearchCompany")
    public Object superSearch(@RequestBody PageParam<Company> pageParam){
        return MyRsp.success(companyService.getAllCompany(pageParam)).msg("检索成功");
    }

	@ApiOperation("通过id删除公司，同时会清空redis缓存")
    @GetMapping("/removeCompanyById/{id}")
    public Object removeCompanyByCompanyName(@PathVariable("id") int id){

        return companyService.removeCompanyById(id)?MyRsp.success(null).msg("删除成功"):MyRsp.error().msg("删除失败");
    }

	@ApiOperation("添加{table.comment}，成功会将该数据放入redis缓存")
    @PostMapping("/addCompany")
    public Object addCompany(@RequestBody @Valid Company companyParam){
        Company company=(Company)companyService.addCompany(companyParam);

        return company!=null?MyRsp.success(company).
                msg("添加成功"):MyRsp.error().msg("添加失败");
    }

	@ApiOperation("修改{table.comment}，成功会将清除该数据的redis缓存")
    @PutMapping("/updateCompany")
    public Object updateCompany(@RequestBody@Valid Company company){
        return companyService.updateCompany(company)?MyRsp.success(null)
                .msg("修改成功"):MyRsp.error().msg("修改失败");
    }

	@ApiOperation("通过id获取{table.comment}，优先从redis缓存中查")
    @GetMapping("/getCompanyById/{id}")
    public Object getCompanyById(@PathVariable("id") int id){

        Company company=companyService.getCompanyById(id);
        return company!=null?MyRsp.success(company):MyRsp.wrapper(new MyException(HttpCode.ITEM_NOT_FOUND));
    }
    
    @ApiOperation("通过id数组批量删除{table.comment}，删除成功也会清空redis缓存数据")
    @PostMapping("/batchDeleteCompanyByIds")
    public Object batchDeleteCompanyByIds(@RequestBody int[] ids){
	    int affectedNum=0;
        for (int id:ids){
            affectedNum+= (companyService.removeCompanyById(id)?1:0);
        }
	    return affectedNum==ids.length?MyRsp.success(null).msg("批量删除成功"):
                MyRsp.error().msg("批量删除失败");
    }
	
}