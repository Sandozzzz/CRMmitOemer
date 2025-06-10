package com.winston.crm_mit_oemer.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ICRUD<T> {
    boolean save (T t) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean update (T t) throws SQLException;
    boolean delete (T t) throws SQLException;
    T findById(int id) throws SQLException;
    T create (ResultSet rs) throws SQLException;
}
