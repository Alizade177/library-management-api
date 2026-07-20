package com.farid.libraryapi.service;

import com.farid.libraryapi.dto.request.MemberRequest;
import com.farid.libraryapi.dto.response.MemberResponse;

import java.util.List;

public interface MemberService {
    MemberResponse createMember(MemberRequest request);

    List<MemberResponse> getAllMembers();

    MemberResponse getMemberById(Long id);

    MemberResponse updateMember(Long id ,MemberRequest request);

    void  deleteMember(Long id);
}
