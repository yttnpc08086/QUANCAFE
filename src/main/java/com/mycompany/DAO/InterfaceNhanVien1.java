/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.DAO;

import com.mycompany.Model.NhanVien;

import java.util.List;

/**
 * @author HP
 */
public interface InterfaceNhanVien1 {

    abstract public void insert(NhanVien Entity);

    abstract public void update(NhanVien Entity);

    abstract public void delete(String id);

    abstract public NhanVien selectById(String id);

    abstract public NhanVien selectByAccount(String account);

    abstract public List<NhanVien> selectAll();

    abstract public List<NhanVien> selectBySql(String sql, Object... args);
}
