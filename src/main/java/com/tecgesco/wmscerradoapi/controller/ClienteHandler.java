package com.tecgesco.wmscerradoapi.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tecgesco.wmscerradoapi.App;
import com.tecgesco.wmscerradoapi.dao.ClienteDao;
import com.tecgesco.wmscerradoapi.model.Cliente;

@WebServlet(urlPatterns = { "/cliente" })
public class ClienteHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		App.context = request.getServletContext();
		response.setContentType("application/json; charset=UTF-8");

		String cnpj = request.getParameter("cnpj");

		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.getByCnpj(cnpj);
		String resultado = cliente.toJson();

		try (OutputStream os = response.getOutputStream()) {
			os.write(resultado.getBytes(StandardCharsets.UTF_8));
			os.flush();
		}

	}

}