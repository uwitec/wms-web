package com.teeny.wms.security;

import com.teeny.wms.core.domain.UserEntity;
import com.teeny.wms.core.enums.RoleType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by lilei on 2017/7/10.
 *
 */
public class WmsUser implements UserDetails {

    private UserEntity user;

    public WmsUser(UserEntity user) {
        super();
        this.user = user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<WmsAuthority> authorities = new ArrayList<WmsAuthority>();
        WmsAuthority authority = new WmsAuthority(RoleType.GENERAL_USER);
        authorities.add(authority);
        return authorities;
    }

    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getUsername();
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return true;
    }

    public UserEntity getUser() {
        return this.user;
    }
}
