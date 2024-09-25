/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.service.impl;

import java.util.List;
import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import za.co.material.Author;
import za.co.material.dao.AuthorDAO;
import za.co.material.dao.impl.AuthorDAOImpl;
import za.co.material.service.AuthorService;

/**
 *
 * @author TRAIN 76
 */
public class AuthorServiceImpl implements AuthorService {
    private AuthorDAO authordao;

    public AuthorServiceImpl(BasicDataSource dbm) {
        authordao = new AuthorDAOImpl(dbm);
    }

    @Override
    public boolean add(Author author) {
        return author != null ? authordao.add(author) : false;
    }

    @Override
    public boolean delete(int aid) {
        return aid > 0 ? authordao.delete(aid) : false;
    }

    @Override
    public boolean update(Author author) {
        return author != null ? authordao.update(author) : false;
    }

    @Override
    public Author get(int aid) {
        return aid > 0 ? authordao.get(aid) : null;
    }

    @Override
    public List<Author> getAll() {
        return authordao.getAll();
    }
    
}
