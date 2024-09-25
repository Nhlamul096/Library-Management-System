/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.service.impl;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Book;
import za.co.material.dao.LoansDAO;
import za.co.material.dao.impl.LoansDAOImpl;
import za.co.material.service.LoansService;

/**
 *
 * @author TRAIN 76
 */
public class LoansServiceImpl implements LoansService {

    LoansDAO loandao;

    public LoansServiceImpl(BasicDataSource dbm) {
        loandao = new LoansDAOImpl(dbm);
    }
    
    @Override
    public Book loanBook(String title, String IDNo) {
        if (title != null && !title.isEmpty() && IDNo != null && !IDNo.isEmpty()) {
            return loandao.loanBook(title, IDNo);
        }
        return null;
    }

    @Override
    public Book returnBook(String title, String IDNo) {
        if (title != null && !title.isEmpty() && IDNo != null && !IDNo.isEmpty()) {
            return loandao.returnBook(title, IDNo);
        }
        return null;
    }

    @Override
    public Book renewBook(String title, String IDNo) {
        if (title != null && !title.isEmpty() && IDNo != null && !IDNo.isEmpty()) {
            return loandao.renewBook(title, IDNo);
        }
        return null;
    }

}
