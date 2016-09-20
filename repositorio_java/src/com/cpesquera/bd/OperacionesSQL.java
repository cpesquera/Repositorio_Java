/**
 * Esta clase sirve para realizar operaciones CRUD (Create, Read, Update y Delete) contra la BD.
 * 
 * @author Carlos Pesquera Nieto
 * @since 20 Julio 2015
 */

package com.cpesquera.bd;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OperacionesSQL {

    /**
     * Actualizamos en la BD un objeto (INSERT, UPDATE o DELETE).
     * 
     * @param conexion la conexion con la base de datos.
     * @param plantilla el paciente que vamos a crear.
     * @throws Exception 
     * @throws FalloGeneral si se produce un error grave.
     */
	public int actualizacionBD(StringBuffer consulta) throws Exception {
		Connection conexion = FactoriaConexiones.conexion("tipoBaseDatos", "direccionServidor", "puerto", "nombreBaseDatos", "usuario", "password");
		//Ejemplo: Connection conexion = FactoriaConexiones.conexion("MYSQL", "localhost", "3306", "health4you", "root", "");
        PreparedStatement preparedStatement = null;
    	int filasActualizadas = 0;     

        try {
        	preparedStatement = conexion.prepareStatement(consulta.toString());

        	filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas != 1)
                throw new Exception("FalloGeneral en el número de registro actualizados en la BD.");

        } catch (SQLException e) {
            throw new Exception("FalloGeneral en la actualización de la BD.", e);
        } finally {
            OperacionesGenerales.cerrar(preparedStatement);
            OperacionesGenerales.cerrar(conexion);
        }

        return filasActualizadas;
	}

	public void leerBD(StringBuffer consulta) throws Exception {
		Connection conexion = FactoriaConexiones.conexion("tipoBaseDatos", "direccionServidor", "puerto", "nombreBaseDatos", "usuario", "password");
		//Ejemplo: Connection conexion = FactoriaConexiones.conexion("MYSQL", "localhost", "3306", "health4you", "root", "");
        PreparedStatement preparedStatement = null;
    	
        try {
        	preparedStatement = conexion.prepareStatement(consulta.toString());

        	preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new Exception("FalloGeneral en la actualización de la BD.", e);
        } finally {
            OperacionesGenerales.cerrar(preparedStatement);
            OperacionesGenerales.cerrar(conexion);
        }
	}
}