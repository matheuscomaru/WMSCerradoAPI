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
import com.tecgesco.wmscerradoapi.Tools;
import com.tecgesco.wmscerradoapi.dao.OrdemProducaoDao;
import com.tecgesco.wmscerradoapi.model.OrdemProducao;

@WebServlet(urlPatterns = { "/listarops" })
public class ListarOrdensPcpHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	OrdemProducao pcp = new OrdemProducao();
	OrdemProducaoDao pcpDao = new OrdemProducaoDao();

	Tools ts = new Tools();
	String datainicial;
	String datafinal;
	String situacao;
	String resposta;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		App.context = request.getServletContext();

		String datainicial = request.getParameter("datainicial");
		String datafinal = request.getParameter("datafinal");
		String situacao = request.getParameter("situacao");

		if (validarCampos(request, response)) {

			response.setContentType("application/json; charset=UTF-8");
			response.setStatus(HttpServletResponse.SC_OK); // Código de status 200 OK

			ArrayList<OrdemProducao> lista = pcpDao.getAll(datainicial, datafinal, Integer.parseInt(situacao));

			String resultado = OrdemProducao.listToJson(lista);

			try (OutputStream os = response.getOutputStream()) {
				os.write(resultado.getBytes(StandardCharsets.UTF_8));
				os.flush();
			}
		}
	}

	private boolean validarCampos(HttpServletRequest request, HttpServletResponse response) {

		String datainicial = request.getParameter("datainicial");
		String datafinal = request.getParameter("datafinal");
		String situacao = request.getParameter("situacao");

		if (datainicial == null || datainicial.isEmpty()) {
			enviarErro(response, "Informe a data inicial");
			return false;
		}

		if (datafinal == null || datafinal.isEmpty()) {
			enviarErro(response, "Informe a data final");
			return false;
		}

		if (situacao == null || situacao.isEmpty()) {
			enviarErro(response, "Informe a situacao. 0: aberto 1:finalizada 2:estornada ");
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