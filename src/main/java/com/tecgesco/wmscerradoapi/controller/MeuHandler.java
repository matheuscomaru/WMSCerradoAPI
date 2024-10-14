package com.tecgesco.wmscerradoapi.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tecgesco.wmscerradoapi.App;

@WebServlet(urlPatterns = { "/api" })
public class MeuHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		App.context = request.getServletContext();
		String responseText = "TecGesco Gestão - API WMS Cerrado Versão " + App.versao;

		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("text/plain");

		try (PrintWriter out = response.getWriter()) {
			out.write(responseText);
		}
	}
}