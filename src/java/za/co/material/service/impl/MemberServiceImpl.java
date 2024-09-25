
package za.co.material.service.impl;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Member;
import za.co.material.dao.MemberDAO;
import za.co.material.dao.impl.MemberDAOImpl;
import za.co.material.service.MemberService;

public class MemberServiceImpl implements MemberService {
    private MemberDAO memberdao;

    public MemberServiceImpl(BasicDataSource dbm) {
        memberdao = new MemberDAOImpl(dbm);
    }

    @Override
    public Member getMember(String idNumber) {
        if (idNumber != null && idNumber.length() == 13) {
            return memberdao.getMember(idNumber);
        }
        return null;
    }

    @Override
    public boolean addMember(Member member) {
        if (member == null) {
            return false;
        }
        return memberdao.addMember(member);
    }

    @Override
    public boolean deleteMember(String idNumber) {
        if (idNumber != null && idNumber.length() == 13) {
            return memberdao.deleteMember(idNumber);
        }
        return false;
    }

    @Override
    public boolean updateMember(Member mem) {
        return mem != null ? memberdao.updateMember(mem) : false;
    }

    @Override
    public boolean setRole(String idNumber, int role) {
        if (idNumber != null && idNumber.length() == 13 && role > 0) {
            return memberdao.setRole(idNumber, role);
        }
        return false;
    }

    @Override
    public Member login(String idNum, String passwd) {
        if (idNum != null && idNum.length() == 13 && passwd != null) {
           if(memberdao.isValidMember(idNum,passwd)){
               return memberdao.getMember(idNum);
           }
        }
        return null;
    }
@Override
    public Member changePassword(String idno, String password) {
         if (idno != null && idno.length() == 13 && password != null) {
            return memberdao.changePassword(idno, password);
         }

        return null;
    }
}
