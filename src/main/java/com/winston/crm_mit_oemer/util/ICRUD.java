package com.winston.crm_mit_oemer.util;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/** interface to make CRUD functions quickly **/
public interface ICRUD<T, U> {
    boolean save (T t) throws SQLException;
    List<T> findAll() throws SQLException;
    boolean update (T t) throws SQLException;
    boolean delete (T t) throws SQLException;
    List<T> findById(int id) throws SQLException;
    T create (ResultSet rs) throws SQLException;
    boolean addNote(U u) throws SQLException;
    boolean updateNote(U u) throws SQLException;
    U findNoteById(int id) throws SQLException;
}
