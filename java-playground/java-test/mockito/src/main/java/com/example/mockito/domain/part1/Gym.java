package com.example.mockito.domain.part1;

import java.util.ArrayList;
import java.util.List;

public class Gym {
    private String name;
    public List<Account>  memberList = new ArrayList<>();

    public Gym(String name) {
        this.name = name;
    }

    public void setMember(Account account) {
        this.memberList.add(account);
    }

    public List<Account> getMemberList() {
        return this.memberList;
    }
}
