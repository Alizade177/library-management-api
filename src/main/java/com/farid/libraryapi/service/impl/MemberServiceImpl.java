package com.farid.libraryapi.service.impl;

import com.farid.libraryapi.dto.request.MemberRequest;
import com.farid.libraryapi.dto.response.MemberResponse;
import com.farid.libraryapi.entity.Member;
import com.farid.libraryapi.exception.ResourceNotFoundException;
import com.farid.libraryapi.mapper.MemberMapper;
import com.farid.libraryapi.repository.MemberRepository;
import com.farid.libraryapi.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Override
    public MemberResponse createMember(MemberRequest request) {

        Member member = MemberMapper.toEntity(request);
        Member savedMamber = memberRepository.save(member);

        return MemberMapper.toResponse(savedMamber);

    }

    @Override
    public List<MemberResponse> getAllMembers() {

        return memberRepository.findAll()
                .stream()
                .map(MemberMapper::toResponse)
                .toList();
    }

    @Override
    public MemberResponse getMemberById(Long id) {
       Member member = memberRepository.findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

       return MemberMapper.toResponse(member);
    }

    @Override
    public MemberResponse updateMember(Long id, MemberRequest request) {

        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        member.setFullName(request.getFullName());
        member.setPhone(request.getPhone());

        Member updatedMember = memberRepository.save(member);

        return MemberMapper.toResponse(updatedMember);
    }

    @Override
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));

        memberRepository.delete(member);
    }
}
