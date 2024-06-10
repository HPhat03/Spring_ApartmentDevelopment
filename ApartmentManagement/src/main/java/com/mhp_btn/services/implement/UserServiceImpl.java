/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.services.implement;

import com.mhp_btn.pojo.ApartmentUser;
import com.mhp_btn.repositories.UserRepository;
import com.mhp_btn.services.UserService;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository up;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public List<ApartmentUser> getUsers() {
        return up.getUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        ApartmentUser user = up.getUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Không tồn tại!");
        }
        
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public ApartmentUser getUsersByUsername(String username) {
        return up.getUserByUsername(username);
    }

    @Override
    public void save(ApartmentUser user, boolean resetPassword) {
        if (user.getId() == null || resetPassword)
             user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        up.save(user);
    }

    @Override
    public ApartmentUser getUserByID(int id) {
        return up.getUsersByID(id);
    }

    @Override
    public ApartmentUser ChangeOrInitialize(ApartmentUser user, Map<String, String> data, boolean isInit) {
        return up.ChangeOrInitialize(user, data, isInit);
    }

    @Override
    public boolean authUser(String username, String password) {
        return this.up.authUser(username, password);
    }

}
