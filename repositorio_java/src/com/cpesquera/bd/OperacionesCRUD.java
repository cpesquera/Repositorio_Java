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

//import com.cpesquera.health4you.db.ConstantesDB;
//import com.cpesquera.health4you.db.FactoriaConexiones;
//import com.cpesquera.health4you.db.NombreTablas;
//import com.cpesquera.health4you.db.OperacionesGenerales;
//import com.cpesquera.health4you.domain.Paciente;
//import com.cpesquera.health4you.exception.FalloGeneral;
//import com.cpesquera.health4you.exception.InstanciaNoEncontrada;
//import com.cpesquera.health4you.persistence.FalloGeneralException;
//import com.cpesquera.health4you.persistence.InstanciaNoEncontradaException;

public class OperacionesCRUD {

    /**
     * Crea un objeto con un nuevo identificador.
     * 
     * @param conexion la conexion con la base de datos.
     * @param plantilla el paciente que vamos a crear.
     * @throws FalloGeneral si se produce un error grave.
     */
	public void crear(Objeto objeto) {
		Connection conexion = FactoriaConexiones.conexion("tipoBaseDatos", "direccionServidor", "puerto", "nombreBaseDatos", "usuario", "password");
		//Ejemplo: Connection conexion = FactoriaConexiones.conexion("MYSQL", "localhost", "3306", "health4you", "root", "");
		PreparedStatement preparedStatement = null;
    	int filasInsertadas = 0;

    	StringBuffer consulta = new StringBuffer("INSERT INTO");
        consulta.append(" Tabla ");
        consulta.append("(campo1, ");
        consulta.append("campo2, ");
        consulta.append("campo3, ");
        consulta.append("campo4, ");
        consulta.append("campo5, ");
        consulta.append("campo6, ");
        consulta.append("campo7, "); 
        consulta.append("campo8, "); 
        consulta.append("campo9, ");
        consulta.append("campo10, ");
        consulta.append("campo11, ");
        consulta.append("campo12)");
        consulta.append(" VALUES ");
        consulta.append("(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

        try {
        	preparedStatement = conexion.prepareStatement(consulta.toString());
            
            preparedStatement.setString(1, objeto.getCampo1());
            preparedStatement.setString(2, objeto.getCampo2());                            
            preparedStatement.setString(3, objeto.getCampo3());
            preparedStatement.setString(4, objeto.getCampo4());
            preparedStatement.setString(5, objeto.getCampo5());
            preparedStatement.setString(6, objeto.getCampo6());
            preparedStatement.setString(7, objeto.getCampo7());
            preparedStatement.setString(8, objeto.getCampo8());
            preparedStatement.setString(9, objeto.getCampo9());
            preparedStatement.setString(10, objeto.getCampo10());
            preparedStatement.setString(11, objeto.getCampo11());
            preparedStatement.setString(12, objeto.getCampo12());

            filasInsertadas = preparedStatement.executeUpdate();

            if (filasInsertadas != 1) {	
                throw new FalloGeneral("FalloGeneral al crear el " + objeto.toString());
            }

        } catch (SQLException e) {
            throw new FalloGeneral("FalloGeneral al crear el " + objeto.toString(), e);
        } finally {
            OperacionesGenerales.cerrar(preparedStatement);
            OperacionesGenerales.cerrar(conexion);
        }

        return filasInsertadas;
	}

	public void leer() {
		
	}

    /**
     * Actualiza un objeto.
     *
     * @param  objeto el objeto que se actualizara.
     * @return el objeto actualizado
     * @throws FalloGeneral si se produce un error grave.
     * @throws InstanciaNoEncontrada si no se encuentra el objeto
     *         que hay que actualizar.
     */
    public int actualizar(Objeto objeto) throws FalloGeneral, InstanciaNoEncontrada {
    	//Connection conexion = FactoriaConexiones.conexion("MYSQL", "localhost", "3306", "health4you", "root", "");
		Connection conexion = FactoriaConexiones.conexion(ConstantesDB.kTIPODB, ConstantesDB.kSERVIDOR, 
				ConstantesDB.kPUERTO, ConstantesDB.kDB, ConstantesDB.kUSUARIO, ConstantesDB.kPASSWORD);
        PreparedStatement preparedStatement = null;
    	int filasActualizadas = 0;

        StringBuffer consulta = new StringBuffer("UPDATE ");
        consulta.append(NombreTablas.kPACIENTES);

        consulta.append(" SET ");
        consulta.append("campo1 = ?, ");
        consulta.append("campo2 = ?, ");
        consulta.append("campo3 = ?, ");
        consulta.append("campo4 = ?, ");
        consulta.append("campo5 = ?, ");
        consulta.append("campo6 = ?, "); 
        consulta.append("campo7 = ?, "); 
        consulta.append("campo8 = ?, "); 
        consulta.append("campo9 = ?, ");
        consulta.append("campo10 = ?, ");
        consulta.append("campo11 = ?");

        consulta.append(" WHERE ");
        consulta.append("campo12 = ?");
        consulta.append(" and ");
        consulta.append("campo13 = ?");
        
        try {
        	preparedStatement = conexion.prepareStatement(consulta.toString());

            preparedStatement.setString(1, objeto.getCampo1());
            preparedStatement.setString(2, objeto.getCampo2());                            
            preparedStatement.setString(3, objeto.getCampo3());
            preparedStatement.setString(4, objeto.getCampo4());
            preparedStatement.setString(5, objeto.getCampo5());
            preparedStatement.setString(6, objeto.getCampo6());
            preparedStatement.setString(7, objeto.getCampo7());
            preparedStatement.setString(8, objeto.getCampo8());
            preparedStatement.setString(9, objeto.getCampo9());
            preparedStatement.setString(10, objeto.getCampo10());
            preparedStatement.setString(11, objeto.getCampo11());

            preparedStatement.setInt(12, objeto.getCampo12());
            preparedStatement.setString(13, objeto.getCampo13());

            filasActualizadas = preparedStatement.executeUpdate();

            if (filasActualizadas != 1)
            	throw new InstanciaNoEncontrada("InstanciaNoEncontrada al modificar el paciente: " + objeto.toString());

        } catch (SQLException e) {
            throw new FalloGeneral("FalloGeneral al modificar el paciente " + objeto.toString(), e);
        } finally {
            OperacionesGenerales.cerrar(preparedStatement);
            OperacionesGenerales.cerrar(conexion);
        }

        return filasActualizadas;
    }

    /**
     * Borra un paciente.
     * @param id identificador del paciente.
     * @throws FalloGeneralException si se produce un error grave.
     * @throws InstanciaNoEncontradaException si no se encuentra el paciente
     *         que hay que borrar.
     */
    public void eliminar(Paciente paciente) throws FalloGeneral, InstanciaNoEncontrada {
    	//Connection conexion = FactoriaConexiones.conexion("MYSQL", "localhost", "3306", "health4you", "root", "");
		Connection conexion = FactoriaConexiones.conexion(ConstantesDB.kTIPODB, ConstantesDB.kSERVIDOR, 
				ConstantesDB.kPUERTO, ConstantesDB.kDB, ConstantesDB.kUSUARIO, ConstantesDB.kPASSWORD);
        PreparedStatement preparedStatement = null;
        int filasBorradas = 0;

        StringBuffer consulta = new StringBuffer("DELETE FROM ");
        consulta.append(NombreTablas.kPACIENTES);

        consulta.append(" WHERE ");
        consulta.append("id = ?");
        consulta.append(" and ");
        consulta.append("idEmpresa = ?");
        
        try {
        	preparedStatement = conexion.prepareStatement(consulta.toString());
        	
            preparedStatement.setInt(1, paciente.getId());
            preparedStatement.setString(2, paciente.getIdEmpresa());

            filasBorradas = preparedStatement.executeUpdate();

            if (filasBorradas == 0)
                throw new InstanciaNoEncontrada("InstanciaNoEncontrada al borrar el paciente: " + objeto.toString());

            if (filasBorradas != 1)
                throw new InstanciaNoEncontrada("InstanciaNoEncontrada al borrar el paciente: " + objeto.toString());
        }
        catch (SQLException e) {
            throw new FalloGeneral("FalloGeneral al borrar el paciente: " + paciente.toString(), e); 
        } finally {
            OperacionesGenerales.cerrar(preparedStatement);
            OperacionesGenerales.cerrar(conexion);
        }
    }	
}