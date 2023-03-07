package com.zyc.mapper;

import com.zyc.entity.Role;
import com.zyc.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    User loadByUsername(@Param("username") String username);

    List<Role> getRoleByUid(@Param("uid") Integer uid);
}
