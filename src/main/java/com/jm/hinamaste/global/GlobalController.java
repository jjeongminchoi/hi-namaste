package com.jm.hinamaste.global;

import com.jm.hinamaste.global.auth.UserPrincipal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class GlobalController {

    @GetMapping("/")
    public String main() {
        return "메인 페이지입니다.";
    }

    @GetMapping("/member")
    public String member(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        log.info("getUserId={}", userPrincipal.getUserId());
        return "사용자 페이지입니다.";
    }

    @GetMapping("/admin")
    public String admin() {
        return "관리자 페이지입니다.";
    }
}
