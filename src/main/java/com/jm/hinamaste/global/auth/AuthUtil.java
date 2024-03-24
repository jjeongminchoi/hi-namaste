package com.jm.hinamaste.global.auth;

import com.jm.hinamaste.domain.member.constant.MemberType;
import com.jm.hinamaste.global.exception.InvalidMember;
import com.jm.hinamaste.global.exception.MemberNotFound;
import org.springframework.stereotype.Component;

@Component
public class AuthUtil {

    public String getAuthority(UserPrincipal userPrincipal) {
        return userPrincipal.getAuthorities().stream()
                .findFirst()
                .orElseThrow(MemberNotFound::new)
                .getAuthority();
    }

    public void checkInvalidMember(Long memberId, UserPrincipal userPrincipal) {
        if (getAuthority(userPrincipal).equals("ROLE_" + MemberType.MEMBER)) {
            if (!userPrincipal.getUserId().equals(memberId)) {
                throw new InvalidMember();
            }
        }
    }
}
