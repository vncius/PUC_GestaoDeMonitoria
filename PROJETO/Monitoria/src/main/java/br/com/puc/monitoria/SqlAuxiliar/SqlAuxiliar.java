package br.com.puc.monitoria.SqlAuxiliar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Statement;

public class SqlAuxiliar {

	public static boolean verificaRotaReferenciada(Long id) {
		String sql = "SELECT r.id FROM rota r INNER JOIN viagem v ON r.id = v.rota_id WHERE r.id = "+id;
		return consultaSql(sql);
	}
	
	public static boolean verificaOnibusReferenciado(Long id) {
		String sql = "SELECT b.id FROM onibus b INNER JOIN viagem v ON b.id = v.onibus_id WHERE b.id = "+id;
		return consultaSql(sql);
	}

	public static boolean verificaViagemReferenciada(Long id) {
		String sql = "SELECT vi.id FROM viagem vi INNER JOIN venda ve ON vi.id = ve.viagem_id WHERE vi.id = "+id;
		return consultaSql(sql);
	}
	
	private static boolean consultaSql(String sql) {
		Connection connection = Conexao.conectar();
		boolean retorno = false;

		try {
			Statement stat = (Statement) connection.createStatement();
			ResultSet rs = stat.executeQuery(sql);
			if(rs.next()) {
				retorno =  true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Conexao.closeConnection(connection);
		return retorno;
	}
}
