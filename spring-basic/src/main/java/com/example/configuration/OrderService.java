package com.example.configuration;

public class OrderService {

    private final MemberRepository memberRepository;

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }

    public OrderService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
