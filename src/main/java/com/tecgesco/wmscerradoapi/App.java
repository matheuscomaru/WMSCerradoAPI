package com.tecgesco.wmscerradoapi;

import javax.servlet.ServletContext;

public class App {

	public static String versao = "1.0.2";
	public static String pathconfigfile = "";
	public static ServletContext context;

	public static String getPathconfigfile() {
		return pathconfigfile;
	}

	public static void setPathconfigfile(String pathconfigfile) {
		App.pathconfigfile = pathconfigfile;
	}

	public static ServletContext getContext() {
		return context;
	}

	public static void setContext(ServletContext context) {
		App.context = context;
	}

}
