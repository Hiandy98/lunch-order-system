package com.lunch.ops.backend.user.service.model;

import com.lunch.ops.backend.user.entity.User;

public record UserUpdateCommand(
        String realName,
        String nickName,
        String classroom,
        Integer number
) {
    public UserUpdateCommand mergeWith(User currentUser) {
        return new UserUpdateCommand(
                this.realName != null ? this.realName : currentUser.getRealName(),
                this.nickName != null ? this.nickName : currentUser.getNickName(),
                this.classroom != null ? this.classroom : currentUser.getClassroom(),
                this.number != null ? this.number : currentUser.getNumber()
        );
    }
}
