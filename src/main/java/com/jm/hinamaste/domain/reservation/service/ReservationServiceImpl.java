package com.jm.hinamaste.domain.reservation.service;

import com.jm.hinamaste.domain.course.entity.Course;
import com.jm.hinamaste.domain.course.repository.CourseRepository;
import com.jm.hinamaste.domain.member.entity.Member;
import com.jm.hinamaste.domain.member.repository.MemberRepository;
import com.jm.hinamaste.domain.reservation.repository.ReservationRepository;
import com.jm.hinamaste.domain.reservation.entity.Reservation;
import com.jm.hinamaste.global.exception.CourseNotFound;
import com.jm.hinamaste.global.exception.MemberNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final CourseRepository courseRepository;
    private final MemberRepository memberRepository;

    @Override
    public Long reserve(Long courseId, Long memberId) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(CourseNotFound::new);

        Member member = memberRepository.findById(memberId)
                .orElseThrow(MemberNotFound::new);

        return reservationRepository.save(Reservation.builder()
                        .member(member)
                        .course(course)
                        .build())
                .getId();
    }
}
