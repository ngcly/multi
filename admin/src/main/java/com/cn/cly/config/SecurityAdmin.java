package com.cn.cly.config;

import com.cn.cly.entity.Admin;
import com.cn.cly.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

/**
 * Created by chen on 2017/6/23.
 */
public class SecurityAdmin extends Admin implements UserDetails{
    private static final long serialVersionUID = 1L;
    public SecurityAdmin(Admin admin) {
        if(admin != null)
        {
            this.setId(admin.getId());
            this.setUsername(admin.getUsername());
            this.setRealName(admin.getRealName());
            this.setPassword(admin.getPassword());
            this.setSex(admin.getSex());
            this.setRoleList(admin.getRoleList());
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> userRoles = this.getRoleList();

        if(userRoles != null)
        {
            for (Role role : userRoles) {
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
                authorities.add(authority);
            }
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
