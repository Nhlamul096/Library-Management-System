/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.service;

import java.util.List;
import za.co.material.Author;

/**
 *
 * @author TRAIN 76
 */
public interface AuthorService {
    boolean add(Author author);
    boolean delete(int aid);
    boolean update(Author author);
    Author get(int aid);
    List<Author> getAll();
}
