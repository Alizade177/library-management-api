package com.farid.libraryapi.mapper;

import com.farid.libraryapi.dto.request.MemberRequest;
import com.farid.libraryapi.dto.response.MemberResponse;
import com.farid.libraryapi.entity.Member;

public class MemberMapper {
    public static Member toEntity(MemberRequest request) {
        Member member = new Member();

        member.setFullName(request.getFullName());
        member.setPhone(request.getPhone());

        return member;
    }

    public static MemberResponse toResponse(Member member) {
        MemberResponse memberResponse = new MemberResponse();

        memberResponse.setId(member.getId());
        memberResponse.setFullName(member.getFullName());
        memberResponse.setPhone(member.getPhone());

        return memberResponse;
    }
}
