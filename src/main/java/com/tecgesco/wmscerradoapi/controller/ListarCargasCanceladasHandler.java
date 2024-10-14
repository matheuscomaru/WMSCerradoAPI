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
import com.tecgesco.wmscerradoapi.dao.CargaDao;
import com.tecgesco.wmscerradoapi.model.Carga;

@WebServlet(urlPatterns = { "/checkout" })
public class ListarCargasCanceladasHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Carga carga = new Carga();
	CargaDao cargaDao = new CargaDao();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		App.context = request.getServletContext();
		response.setContentType("application/json; charset=UTF-8");

		String datainicial = request.getParameter("datainicial");
		String datafinal = request.getParameter("datafinal");

		ArrayList<Carga> lista = cargaDao.getBySituacaoData(2, datainicial, datafinal);

		String resultado = carga.listToJson(lista);

		try (OutputStream os = response.getOutputStream()) {
			os.write(resultado.getBytes(StandardCharsets.UTF_8));
			os.flush();
		}
	}

}