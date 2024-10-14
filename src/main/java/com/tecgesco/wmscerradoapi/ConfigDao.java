package com.tecgesco.wmscerradoapi;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigDao {

	public void salvarConfig(String param, String value) {

		App.pathconfigfile = App.context.getRealPath("/WEB-INF/config.properties");

		try {

			File configFile = new File(App.pathconfigfile);

			if (!configFile.exists()) {
				configFile.createNewFile();
			}

			FileInputStream in = new FileInputStream(configFile);
			Properties props = new Properties();
			props.load(in);
			in.close();

			FileOutputStream out = new FileOutputStream(configFile);
			props.setProperty(param, value);
			props.store(out, null);
			out.close();

		} catch (IOException io) {
			io.printStackTrace();
		}

	}

	public String lerConfig(String param) {

		App.pathconfigfile = App.context.getRealPath("/WEB-INF/config.properties");
		System.out.println("App.pathconfigfile.:" + App.pathconfigfile);

		File configFile = new File(App.pathconfigfile);
		System.out.println("arquivo.:" + configFile.getAbsolutePath());

		try (InputStream input = new FileInputStream(configFile)) {

			Properties prop = new Properties();
			prop.load(input);
			if (prop.containsKey(param)) {
				return prop.getProperty(param);
			} else {
				System.out.println("A propriedade " + param + " não foi encontrada.");
			}

		} catch (IOException ex) {
			ex.printStackTrace();

		}
		return "";
	}

}