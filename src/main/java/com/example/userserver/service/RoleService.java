package com.example.userserver.service;

import com.example.userserver.common.PageParam;
import com.example.userserver.model.Role;


public interface RoleService {



	public Object getAllRole(PageParam<Role> pageParam);

    public boolean removeRoleById(int id);

    public Object addRole(Role role);

    public boolean updateRole(Role role);

    public Role getRoleById(int id);


}