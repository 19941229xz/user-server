package com.example.userserver.dao;

import org.apache.ibatis.annotations.*;
import com.example.userserver.model.Role;

import java.util.List;

public interface RoleDao {


    List<Role> getAllRole(Role role);

    @Delete("delete from role where id = #{id}")
    int removeRoleById(int id);

    int addRole(Role role);

    int updateRole(Role role);

    @Select("select * from role where id =#{id}")
    Role getRoleById(int id);

    @Select("select * from role where roleName =#{roleName}")
    Role getRoleByRoleName(String roleName);


	List<Role> superSearch(String superSearchKeyWord);

}