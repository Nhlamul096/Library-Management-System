/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.service;

import za.co.material.Book;
import za.co.process.ProcessInt;

/**
 *
 * @author TRAIN 76
 */
public interface LoansService {
    
    public Book loanBook(String title, String IDNo);
    public Book returnBook(String title, String IDNo);
    public Book renewBook(String title, String IDNo);
    
}
