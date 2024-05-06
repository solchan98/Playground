package com.example.configuration;

public class MemberService {

    private final MemberRepository memberRepository;

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
