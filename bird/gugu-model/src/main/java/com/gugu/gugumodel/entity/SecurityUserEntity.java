package com.gugu.gugumodel.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ren
 */
public class SecurityUserEntity implements UserDetails {
    private String account;
    private String password;
    private String role;
    public SecurityUserEntity(String account,String password,String role){
        this.account=account;
        this.password=password;
        this.role=role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority=new SimpleGrantedAuthority(role);
        ArrayList<GrantedAuthority> grantedAuthorities=new ArrayList<>();
        grantedAuthorities.add(simpleGrantedAuthority);
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
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
