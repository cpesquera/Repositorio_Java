/**
 * Esta clase encapsula las conexiones a diferentes BD's.
 * 
 * @author Carlos Pesquera Nieto
 * @since 20 Julio 2015
 */

package com.cpesquera.bd;

import java.sql.*;

public final class FactoriaConexiones {

    /** 
     * Impide que se creen instancias de esta clase utilidad, ya que el 
     * contructor es privado.
     */
    private FactoriaConexiones() {
    	
    }

    /**
     * Devuelve la conexion de tipo <code>Connection</code> de la base de datos si no es    
     * <code>null</code>.
     *   
     * @param tipoBaseDatos el objeto <code>String</code> con el tipo de la base de datos (MYSQL, SQLSERVER, FIREBIRD, ODBC, SQLITE).
     * @param direccionServidor el objeto <code>String</code> con la dirección del servidor.
     * @param puerto el objeto <code>String</code> con el puerto de conexión de la base de datos.
     * @param nombreBaseDatos el objeto <code>String</code> con el nombre de la base de datos.
     * @param usuario el objeto <code>String</code> con el usuario de conexión a la base de datos.
     * @param password el objeto <code>String</code> con el password de conexión a la base de datos.
     */
    public static Connection conexion(String tipoBaseDatos, String direccionServidor, String puerto, 
    		String nombreBaseDatos, String usuario, String password) {

    	String nombreDriver = null, baseDatosURL = null;

    	//Nombre de la clase del driver, en este caso es para MySQL.
    	//Definimos el origen de datos.
    	switch (tipoBaseDatos.toUpperCase()) {
	    	case "MYSQL":
	    		nombreDriver = "com.mysql.jdbc.Driver";
	    		baseDatosURL = "jdbc:mysql://" + direccionServidor + ":" + puerto + "/" + nombreBaseDatos;
	    	break;

	    	case "SQLSERVER":
	    		nombreDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	    		baseDatosURL = "jdbc:sqlserver://"+ direccionServidor + ":" + puerto + ";" + "databaseName=" + nombreBaseDatos + ";";
	    	break;

	    	case "FIREBIRD":
	            nombreDriver = "org.firebirdsql.jdbc.FBDriver";
	            baseDatosURL = "jdbc:firebirdsql:"+ direccionServidor + "/" + puerto + ":" + nombreBaseDatos;
	    	break;

	    	case "ODBC":
	    		nombreDriver = "sun.jdbc.odbc.JdbcOdbcDriver";
	    		baseDatosURL = "jdbc:odbc:" + nombreBaseDatos; //En este caso, nombreBaseDatos se refiere al nombre del DSN
    		break;
    		
	    	case "SQLITE":
	            //Ejemplo URL: jdbc:sqlite:D:\test.db
	    		nombreDriver = "org.sqlite.JDBC";
	            baseDatosURL = "jdbc:sqlite:" + nombreBaseDatos;
	        break;
    	}

        Connection conexion = null;

        try {
            //Iniciamos y registramos la clase Driver necesaria para nuestra BD.
            Class.forName(nombreDriver);

            if (tipoBaseDatos == "SQLITE") {
	            //Establecemos la conexion mediante la clase DriverManager.
	            conexion = DriverManager.getConnection(
	                baseDatosURL, usuario, password);
            } else {
            	//Establecemos la conexion mediante la clase DriverManager.
	            conexion = DriverManager.getConnection(
	                baseDatosURL);
            }     
        }
        //SQLException es lanzada por la mayoria de metodos de JDBC.
        catch (SQLException e) {
            System.err.println("Error SQLException! " + e);
        }
        //Excepcion lanzada por el metodo forName
        catch (ClassNotFoundException e) {
            System.err.println("Driver no encontrado en el classpath! " + e);
        }

        return(conexion);
    }
}