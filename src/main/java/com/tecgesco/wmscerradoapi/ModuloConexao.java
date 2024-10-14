package com.tecgesco.wmscerradoapi;

import java.sql.Connection;
import java.sql.DriverManager;

public class ModuloConexao {

	static ConfigDao configDao = new ConfigDao();

	public static Connection conector() {

		String ip = configDao.lerConfig("ip");
		String url;
		String user = "sysdba";
		String password = "@CHx2021$";
		String cnpj = configDao.lerConfig("cnpj");

		try {

			url = "jdbc:firebirdsql:" + ip + "/3050:C:\\CHSISTEMAS\\" + cnpj + "\\DADOS\\DADOS.FDB?encoding=ISO8859_1";
			String driver = "org.firebirdsql.jdbc.FBDriver";
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
