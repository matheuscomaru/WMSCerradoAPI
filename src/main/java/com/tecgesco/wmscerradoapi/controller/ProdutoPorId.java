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
import com.tecgesco.wmscerradoapi.dao.ProdutoDao;
import com.tecgesco.wmscerradoapi.model.Produto;

@WebServlet(urlPatterns = { "/produto" })
public class ProdutoPorId extends HttpServlet {

	private static final long serialVersionUID = 1L;

	Produto produto = new Produto();
	ProdutoDao produtoDao = new ProdutoDao();
	Tools ts = new Tools();
	String id;
	String resposta;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		App.context = request.getServletContext();
		// Obtenção do parâmetro 'id' via HttpServletRequest
		String id = request.getParameter("id");

		// Validação dos parâmetros
		if (validarCampos(request, response)) {
			produto = produtoDao.getByCodigo(id);

			if (produto != null) {
				resposta = produto.toJson();
				System.out.println(resposta);

				// Configura a resposta com cabeçalhos e conteúdo JSON
				response.setContentType("application/json; charset=UTF-8");
				response.setStatus(HttpServletResponse.SC_OK); // Status 200 OK

				try (OutputStream os = response.getOutputStream()) {
					os.write(resposta.getBytes(StandardCharsets.UTF_8));
					os.flush(); // Assegura que a resposta seja enviada
				}

			} else {
				// Produto não encontrado, envia status 204 No Content
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			}
		}
	}

	// Método para validar os parâmetros
	private boolean validarCampos(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");

		// Verifica se o ID foi fornecido
		if (id == null || id.isEmpty()) {
			enviarErro(response, "Informe o parâmetro 'id' do produto. Campo código no ERP.");
			return false;
		}

		return true;
	}

	// Método para enviar erros no caso de campos inválidos
	private static void enviarErro(HttpServletResponse response, String erro) throws IOException {
		response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Status 400 Bad Request
		response.setContentType("application/json; charset=UTF-8");

		String jsonErro = "{\"erro\":\"" + erro + "\"}";

		try (OutputStream os = response.getOutputStream()) {
			os.write(jsonErro.getBytes(StandardCharsets.UTF_8));
			os.flush();
		}
	}

	// Método para enviar resposta "Not Found" (caso não seja necessário, pode ser
	// removido)
	private static void enviarRespostaNotFound(HttpServletResponse response) throws IOException {
		response.setStatus(HttpServletResponse.SC_NOT_FOUND); // Status 404 Not Found
		response.getOutputStream().close();
	}
}