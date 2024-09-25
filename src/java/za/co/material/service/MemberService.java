package za.co.material.service;

import za.co.material.Member;

public interface MemberService {

    public Member getMember(String idNumber);

    public boolean addMember(Member member);

    public boolean updateMember(Member mem);

    public boolean deleteMember(String idNumber);

    boolean setRole(String idNumber, int role);
    
    Member login(String inNum, String passwd);
    public Member changePassword(String idno, String password);
}
