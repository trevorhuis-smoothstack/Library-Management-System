package com.ss.lms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class BaseDAO<T> {
    public Connection conn = null;

    public BaseDAO(Connection conn){
        this.conn = conn;
    }

    public void save(String sql, Object[] vals) throws ClassNotFoundException, SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);

        if(vals!=null){
            int index = 1;
            for(Object o: vals) {
                pstmt.setObject(index, o);
            }
        }
        pstmt.executeUpdate();
    }

    public List<T> read(String sql, Object[] vals) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement(sql);

        if(vals!=null) {
            int index = 1;
            for(Object o: vals) {
                pstmt.setObject(index, o);
                index++;
            }
        }
        return extractData(pstmt.executeQuery());
    }

    public abstract List<T> extractData(ResultSet rs) throws SQLException;
}