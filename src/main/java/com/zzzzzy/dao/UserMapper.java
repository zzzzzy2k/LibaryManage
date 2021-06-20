package com.zzzzzy.dao;

import com.zzzzzy.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface UserMapper {

    public User queryByName(@Param("username") String username);

    public List<Map<String, Object>> getUserPower(@Param("username") String username);

}
