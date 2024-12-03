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
import com.tecgesco.wmscerradoapi.dao.ItemCargaDao;
import com.tecgesco.wmscerradoapi.model.ItemCarga;

@WebServlet(urlPatterns = { "/itens-carga" })
public class ItensCargaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		App.context = request.getServletContext();

		response.setContentType("application/json; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);

		ItemCargaDao itemCargaDao = new ItemCargaDao();
		ItemCarga itemCarga = new ItemCarga();

		String carga = request.getParameter("carga");
		String empresa = request.getParameter("empresa");

		if (validarCampos(request, response)) {

			ArrayList<ItemCarga> lista = itemCargaDao.getAll(carga, Integer.parseInt(empresa));

			System.out.println("tamanho lista.:" + lista.size());

			String resultado = itemCarga.listToJson(lista);

			try (OutputStream os = response.getOutputStream()) {
				os.write(resultado.getBytes(StandardCharsets.UTF_8));
				os.flush();
			}

		}
	}

	private boolean validarCampos(HttpServletRequest request, HttpServletResponse response) {

		String carga = request.getParameter("carga");
		String empresa = request.getParameter("empresa");

		if (carga == null || carga.isEmpty()) {
			enviarErro(response, "Informe o lote/ordem de carga");
			return false;
		}

		if (empresa == null || empresa.isEmpty()) {
			enviarErro(response, "Informe a empresa");
			return false;
		}

		return true;
	}

	private void enviarErro(HttpServletResponse response, String mensagemErro) {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		response.setContentType("application/json; charset=UTF-8");

		String jsonErro = "{\"erro\":\"" + mensagemErro + "\"}";

		try (OutputStream os = response.getOutputStream()) {
			try {
				os.write(jsonErro.getBytes(StandardCharsets.UTF_8));
				os.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

}