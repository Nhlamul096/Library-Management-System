package za.co.material.dao;

import java.util.List;
import za.co.material.Member;

/**
 *
 * @author TRAIN 76
 */
public interface MemberDAO {

    public Member getMember(String idNumber);

    public boolean addMember(Member member);

    public boolean updateMember(Member mem);

    public boolean deleteMember(String idNumber);

    boolean setRole(String idNumber, int role);

    boolean isValidMember(String idNum, String pw);
    
    public Member changePassword(String idno, String password);
}
