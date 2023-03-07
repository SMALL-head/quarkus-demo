package com.zyc.serviceImpl;

import com.zyc.entity.Role;
import com.zyc.mapper.UserMapper;

import com.zyc.proto.service.RoleListMsg;
import com.zyc.proto.service.RoleMsg;
import com.zyc.proto.service.UserMsg;
import com.zyc.proto.service.UserService;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@GrpcService
public class UserServiceImpl implements UserService {
    UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Uni<RoleListMsg> getRoleListById(UserMsg request) {
        if (request == null) {
            return null;
        }
        // 直接从数据库中查找得到的数据不能直接转化为proto消息
        List<Role> roleByUid = userMapper.getRoleByUid(request.getId());
        // 此处拷贝的花销挺大的，希望能够向外面传递消息的时候直接用原生的
        RoleListMsg.Builder builder = RoleListMsg.newBuilder();
        roleByUid.forEach(each -> {
            RoleMsg value = Role.convert2RoleMsg(each);
            builder.addRole(value);
        });
        RoleListMsg build = builder.build();
        return Uni.createFrom().item(build);
    }
}
