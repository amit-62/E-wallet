package com.amit.UserServiceApplication.dtos;

import com.amit.Utilities.UserIdentifier;
import com.amit.UserServiceApplication.models.UserType;
import com.amit.UserServiceApplication.models.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
//@RequiredArgsConstructor
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

    private String name;
    @NotBlank(message = "Contact can not be blank")
    private String contact;
    @NotBlank(message = "email can not be blank")
    private  String email;
    private String address;
    private String dob;
    @NotNull(message = "userIdentifier can not be blank")
    private UserIdentifier userIdentifier;
    @NotBlank(message = "userIdentifierValue can not be blank")
    private String userIdentifierValue;
    @NotBlank(message = "password can not be blank")
    private String password;

    public Users toUser() {
        return Users.builder().
                name(this.name).
                contact(this.contact).
                email(this.email).
                address(this.address).
                dob(this.dob).
                userIdentifierValue(this.userIdentifierValue).
                identifier(this.userIdentifier).
                enabled(true).
                accountNonExpired(true).
                accountNonLocked(true).
                credentialsNonExpired(true).
                userType(UserType.USER).
                build();
    }
}
