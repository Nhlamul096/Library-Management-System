/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.dao;

import java.util.List;
import za.co.material.Author;
import za.co.material.Category;

/**
 *
 * @author TRAIN 76
 */
public interface AuthorDAO {
    boolean add(Author author);
    boolean delete(int aid);
    boolean update(Author author);
    Author get(int aid);
    List<Author> getAll();
}
