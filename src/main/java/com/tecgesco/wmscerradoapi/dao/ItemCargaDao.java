package com.tecgesco.wmscerradoapi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import com.tecgesco.wmscerradoapi.ModuloConexao;
import com.tecgesco.wmscerradoapi.model.ItemCarga;

public class ItemCargaDao {

	public ArrayList<ItemCarga> getAll(String lote, int chaveEmpresa) {

		ArrayList<ItemCarga> lista = new ArrayList<>();

		//@formatter:off
		String sql = "SELECT LT.LOTE,IPS.CODPRODUTO,CAST(SUM(IPS.QTDE) AS NUMERIC(18,6)) AS QTDE \r\n"
				+ "FROM PEDIDOSAIDA PS\r\n"
				+ "JOIN ITENSPEDSAIDA IPS ON IPS.CHAVEPEDIDO = PS.CHAVE \r\n"
				+ "JOIN LOTEPEDSAIDA LT ON LT.CHAVE = PS.CHAVELOTEPEDSAIDA \r\n"
				+ "WHERE PS.ATIVO = 1 AND IPS.ATIVO = 1 AND LT.ATIVO =1\r\n"
				+ "AND LT.LOTE = LPAD(CAST(? AS VARCHAR(6)),6,'0')\r\n"
				+ "AND PS.CHAVEEMPRESA = ?\r\n"
				+ "GROUP BY 1,2";
		//@formatter:on

		try (Connection conexao = ModuloConexao.conector(); PreparedStatement pst = conexao.prepareStatement(sql)) {

			pst.setString(1, lote);
			pst.setInt(2, chaveEmpresa);

			try (ResultSet rs = pst.executeQuery();) {

				while (rs.next()) {

					ItemCarga p = new ItemCarga();
					p.setLoadOrderId(rs.getString("LOTE"));
					p.setProductCode(rs.getString("CODPRODUTO"));
					p.setQuantity(rs.getDouble("QTDE"));
					lista.add(p);

					System.out.println(p.toJson());

				}
			}

		} catch (SQLException e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1);

		}

		return lista;

	}

}
