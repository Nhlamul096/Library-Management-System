/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.service.impl;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.dao.ReserveDAO;
import za.co.material.dao.impl.ReserveDAOImpl;
import za.co.material.service.ReserveService;

/**
 *
 * @author TRAIN 76
 */
public class ReserveServiceImpl implements ReserveService {
    private ReserveDAO reservedao;

    public ReserveServiceImpl(BasicDataSource dbm) {
        reservedao = new ReserveDAOImpl(dbm);
    }

    @Override
    public boolean reservebook(String title, String ISBNo, String memberid) {
        if(title != null && !title.isEmpty() && ISBNo != null && !ISBNo.isEmpty() && memberid != null && !memberid.isEmpty()){
            return reservedao.reservebook(title, ISBNo, memberid);
        }
        return false;
    }

    @Override
    public String checkreserve(String title, String ISBNo, String memberid) {
        if(title != null && !title.isEmpty() && ISBNo != null && !ISBNo.isEmpty() && memberid != null && !memberid.isEmpty()){
            return reservedao.checkreserve(title, ISBNo, memberid);
        }
        return null;
    }
    
}
