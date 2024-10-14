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
import com.tecgesco.wmscerradoapi.Tools;
import com.tecgesco.wmscerradoapi.dao.OrdemProducaoDao;
import com.tecgesco.wmscerradoapi.model.OrdemProducao;

@WebServlet(urlPatterns = { "/op" })
public class OpPorId extends HttpServlet {

	Tools ts = new Tools();
	String id;
	String resposta;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		App.context = request.getServletContext();
		OrdemProducao pcp = new OrdemProducao();
		OrdemProducaoDao pcpDao = new OrdemProducaoDao();

		String id = request.getParameter("id");

		if (validarCampos(request, response)) {

			pcp = pcpDao.getByCodigo(id);

			if (pcp != null) {
				String resposta = pcp.toJson();
				System.out.println(resposta);

				response.setContentType("application/json; charset=UTF-8");
				response.setStatus(HttpServletResponse.SC_OK);

				try (OutputStream os = response.getOutputStream()) {
					os.write(resposta.getBytes(StandardCharsets.UTF_8));
					os.flush();
				}

			} else {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		}

	}

	private boolean validarCampos(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String id = request.getParameter("id");

		if (id == null || id.isEmpty()) {
			enviarErro(response, "Informe o número da OP no parâmetro ID");
			return false;
		}

		return true;
	}

	private void enviarErro(HttpServletResponse response, String mensagemErro) throws IOException {

		response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

		response.setContentType("application/json; charset=UTF-8");

		String jsonErro = "{\"erro\":\"" + mensagemErro + "\"}";

		try (OutputStream os = response.getOutputStream()) {
			os.write(jsonErro.getBytes(StandardCharsets.UTF_8));
			os.flush();
		}
	}

}