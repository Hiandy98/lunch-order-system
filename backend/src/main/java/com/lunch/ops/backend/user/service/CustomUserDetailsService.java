package com.lunch.ops.backend.user.service;

import com.lunch.ops.backend.user.entity.User;
import com.lunch.ops.backend.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor  // 自動生成必要參數
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginInput) throws UsernameNotFoundException {
        User user = userRepository.findByIdOrNickName(loginInput, loginInput)
                .orElseThrow(() -> new UsernameNotFoundException("帳號或暱稱不存在: " + loginInput));
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getId())
                .password(user.getPassword())
                .authorities(Collections.singleton(toSpringSecurityRole(user)))
                .disabled(false)
                .build();
    }

    private SimpleGrantedAuthority toSpringSecurityRole(User user) {
        return new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
    }
}
