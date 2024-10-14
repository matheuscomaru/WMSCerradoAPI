package com.tecgesco.wmscerradoapi.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tecgesco.wmscerradoapi.App;
import com.tecgesco.wmscerradoapi.dao.NotaDao;
import com.tecgesco.wmscerradoapi.model.Nota;

@WebServlet(urlPatterns = { "/listarnotas" })
public class ListarNotasHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Nota nota = new Nota();
	NotaDao notaDao = new NotaDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		App.context = request.getServletContext();
		response.setContentType("application/json; charset=UTF-8");

		String id = request.getParameter("id");

		ArrayList<Nota> lista = notaDao.getByLote(id);

		String resultado = nota.listToJson(lista);

		try (OutputStream os = response.getOutputStream()) {
			os.write(resultado.getBytes(StandardCharsets.UTF_8));
			os.flush();
		}
	}

}