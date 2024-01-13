package com.jm.hinamaste.global.auth;

import com.jm.hinamaste.global.auth.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

import static com.jm.hinamaste.domain.member.constant.MemberStatus.ACTIVE;

@Slf4j
@RequiredArgsConstructor
public class UserPrincipal implements UserDetails {

    private final UserDto user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(() -> "ROLE_" + user.getMemberType());
        return collect;
    }

    public Long getUserId() {
        return user.getId();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
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
        return ACTIVE.equals(user.getMemberStatus());
    }
}
