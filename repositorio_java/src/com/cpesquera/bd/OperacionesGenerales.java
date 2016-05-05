/**
 * Esta clase encapsula las operaciones comunes efectuadas sobre una BD (no basada en EJB).
 * 
 * @author Carlos Pesquera Nieto
 * @since 20 Julio 2015
 */

package com.cpesquera.bd;

import java.sql.*;

public final class OperacionesGenerales {   

    /** 
     * Impide que se creen instancias de esta clase utilidad, ya que el 
     * contructor es privado. 
     */      
    private OperacionesGenerales() {
    	
    }  

    /** 
     * Cierra un objeto de tipo <code>ResultSet</code> si no es
     * <code>null</code>. 
     *   
     * @param resultSet el <code>ResultSet</code> que debe ser cerrado.
     */ 
    public static void cerrar(ResultSet resultSet) {
            
        if (resultSet != null) {	
            try {	
                resultSet.close();
                resultSet = null;

            } catch (SQLException e) {
                System.err.println(e);
            }	
        }   
    }  
    
    /**  
     * Cierra un objeto de tipo <code>Statement</code> si no es    
     * <code>null</code>. 
     *   
     * @param statement el <code>Statement</code> que debe ser cerrado.   
     */  
    public static void cerrar(Statement statement) {	
            
        if (statement != null) {	
            try {		
                statement.close();	 
                statement = null;

            } catch (SQLException e) {
                System.err.println(e);	
            }	
        }   
    }

    /**  
     * Cierra un objeto de tipo <code>PreparedStatement</code> si no es    
     * <code>null</code>. 
     *   
     * @param preparedStatement el <code>PreparedStatement</code> que debe ser cerrado.   
     */  
    public static void cerrar(PreparedStatement preparedStatement) {	
            
        if (preparedStatement != null) {	
            try {		
            	preparedStatement.close();	 
            	preparedStatement = null;

            } catch (SQLException e) {
                System.err.println(e);	
            }	
        }   
    }    
    
    /**
     * Cierra un objeto de tipo <code>Connection</code> si no es    
     * <code>null</code>. 
     *   
     * @param conexion el objeto <code>Connection</code> que debe ser cerrado.   
     */
    public static void cerrar(Connection conexion) {	

        if (conexion != null) {	
            try {		
                conexion.close();
                conexion = null;
                
            } catch (SQLException e) {
                System.err.println(e);
            }	
        }
    }
}