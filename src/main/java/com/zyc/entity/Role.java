package com.zyc.entity;

import com.zyc.proto.service.RoleMsg;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Role {
    Integer id;
    String name;
    String nameZh;

    public static RoleMsg convert2RoleMsg(Role role) {
        return RoleMsg.newBuilder()
            .setId(role.id)
            .setName(role.name)
            .setNameZh(role.name)
            .build();
    }

    public static Role convert2Role(RoleMsg roleMsg) {
        return Role.builder()
            .name(roleMsg.getName())
            .id(roleMsg.getId())
            .nameZh(roleMsg.getNameZh())
            .build();
    }
}
