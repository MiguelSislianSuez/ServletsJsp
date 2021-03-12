/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datos;

import java.sql.*;
//import javax.activation.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

/**
 *
 * @author Kentucky
 */
public class Conexion {

    private static final String JDBC_URL = 
            "jdbc:mysql://localhost:3306/control_clientes?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "13091988*Miguel*";

    public static BasicDataSource getDataSource(){
        BasicDataSource ds = new BasicDataSource();
        ds.setUrl(JDBC_URL);
        ds.setUsername(JDBC_USER);
        ds.setPassword(JDBC_PASSWORD);
        ds.setInitialSize(50);//maximo de 50 conns
        return ds;
    }
    
    public static Connection getConnection() throws SQLException{
        return getDataSource().getConnection(); //para poder obetener una conn a parti de nuestro pool de conns
        
    }
    
    public static void close(ResultSet rs){
        try{
            rs.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    public static void close(PreparedStatement stml){
        try{
            stml.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
    public static void close(Connection conn){
        try{
            conn.close();
        }catch(SQLException ex){
            ex.printStackTrace(System.out);
        }
    }
}
