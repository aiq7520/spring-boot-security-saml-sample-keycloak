package com.vdenotaris.spring.boot.security.saml.web.entity;


import com.vdenotaris.spring.boot.security.saml.web.common.utils.Constants;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

/**
 * @ClassName SysUser
 * @Description
 * @Date 2022/4/18 13:43
 * @Created by ge.ji
 */
@Table
@Entity
@Data
@NoArgsConstructor
public class SysUser {
    /** 用户ID */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    /** 用户名 */
    @NotEmpty(message=Constants.REGISTER_USERNAME_NULL_MES)
    private String username;

    /** 密码 */
    @NotEmpty(message=Constants.REGISTER_PASSWORD_NULL_MES)
    @Length(message = Constants.REGISTER_PASSWORD_LENGTH_MES,min = 6,max = 10)
    private String password;

    public SysUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
