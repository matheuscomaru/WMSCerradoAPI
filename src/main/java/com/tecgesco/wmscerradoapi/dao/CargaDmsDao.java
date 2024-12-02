package com.tecgesco.wmscerradoapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.tecgesco.wmscerradoapi.ModuloConexao;
import com.tecgesco.wmscerradoapi.model.CargaDms;
import com.tecgesco.wmscerradoapi.model.Route;

public class CargaDmsDao {

	public ArrayList<CargaDms> getByData(String dataInicial, String dataFinal) {

		ArrayList<CargaDms> lista = new ArrayList<>();

		// @formatter:off
		String sql = "SELECT TMS.CODIGO AS CARGA , TMS.CHAVEEMPRESA,\r\n"
				+ "COALESCE((SELECT FIRST 1 DISTINCT(ROTA.CODIGO)\r\n"
				+ "FROM PEDIDOSAIDA PS \r\n"
				+ "JOIN CLIFOR CL ON CL.CHAVE = PS.CHAVECLIFOR \r\n"
				+ "JOIN CIDADEROTA CIDROT ON CIDROT.CHAVECIDADE = CL.CHAVECIDADE \r\n"
				+ "JOIN ROTA ON CIDROT.CHAVEROTA = ROTA.CHAVE\r\n"
				+ "WHERE PS.CHAVELOTEPEDSAIDA = LOT.CHAVE ),'000000') AS CODROTA,\r\n"
				+ "COALESCE((SELECT FIRST 1 DISTINCT(ROTA.DESCRICAO)\r\n"
				+ "FROM PEDIDOSAIDA PS \r\n"
				+ "JOIN CLIFOR CL ON CL.CHAVE = PS.CHAVECLIFOR \r\n"
				+ "JOIN CIDADEROTA CIDROT ON CIDROT.CHAVECIDADE = CL.CHAVECIDADE \r\n"
				+ "JOIN ROTA ON CIDROT.CHAVEROTA = ROTA.CHAVE\r\n"
				+ "WHERE PS.CHAVELOTEPEDSAIDA = LOT.CHAVE ),'SEM ROTA') AS NOMEROTA, COALESCE(TMS.OBS,'') AS OBS\r\n"
				+ "FROM LOTEPEDSAIDA LOT\r\n"
				+ "JOIN TMSFRETES TMS ON TMS.CODIGO = LOT.LOTE\r\n"
				+ "WHERE LOT.ATIVO = 1 AND TMS.ATIVO = 1\r\n"
				+ "AND TMS.SITUACAO = 0 AND LOT.CHAVEEMPRESA = TMS.CHAVEEMPRESA \r\n"
				+ "AND TMS.EMISSAO BETWEEN ? AND ?\r\n"
				+ "AND TMS.CODIGO <> ''\r\n"
				+ "ORDER BY TMS.CODIGO";
		// @formatter:on

		try (Connection conexao = ModuloConexao.conector(); PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setString(1, dataInicial);
			pst.setString(2, dataFinal);

			try (ResultSet rs = pst.executeQuery()) {

				while (rs.next()) {

					CargaDms c = new CargaDms();
					Route route = new Route();

					c.setReferenceId(rs.getString("CARGA"));
					route.setId(rs.getString("CODROTA"));
					route.setDescription(rs.getString("NOMEROTA"));
					c.setRoute(route);
					c.setNote(rs.getString("OBS"));
					c.setBranchId(rs.getString("CHAVEEMPRESA"));
					lista.add(c);

				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1);

		}

		return lista;

	}

}
