/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.material.dao;

/**
 *
 * @author TRAIN 76
 */
public interface ReserveDAO {
    public boolean reservebook(String title, String ISBNo, String memberid);
    public String checkreserve(String title, String ISBNo, String memberid);
}
