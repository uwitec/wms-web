package com.teeny.wms.security;

import com.teeny.wms.core.domain.Employess;
import com.teeny.wms.service.SystemService;
import com.teeny.wms.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by lilei on 2017/7/10.
 */
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SystemService systemService;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Employess user = systemService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not Found");
        }
        return new WmsUser(user);
    }
}
