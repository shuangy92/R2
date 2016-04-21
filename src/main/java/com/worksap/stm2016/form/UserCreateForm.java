package com.worksap.stm2016.form;

import com.worksap.stm2016.enums.Role;
import com.worksap.stm2016.enums.UserStatus;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Data
public class UserCreateForm {

    @NotEmpty
    private String name = "";

    @NotEmpty
    private String email = "";

    @NotEmpty
    private String password = "";

    @NotEmpty
    private String passwordRepeated = "";

    @NotNull
    private Role role = Role.WAITER;

    @NotNull
    private UserStatus status = UserStatus.NORMAL;
}