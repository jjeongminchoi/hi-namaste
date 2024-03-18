package com.jm.hinamaste.domain.member.config;

import com.jm.hinamaste.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@RequiredArgsConstructor
@Configuration
public class MemberTicketScheduling {

    private final MemberService memberService;

    @Scheduled(cron = "* * 0 * * ?")
    public void verifyMemberTicketExpiry() {
        memberService.verifyMemberTicketExpiry();
    }
}
