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
import com.tecgesco.wmscerradoapi.dao.ProdutoDao;
import com.tecgesco.wmscerradoapi.model.Produto;

@WebServlet(urlPatterns = { "/listarprodutos" })
public class ListarProdutosHandler extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		App.context = request.getServletContext();

		response.setContentType("application/json; charset=UTF-8");
		response.setStatus(HttpServletResponse.SC_OK);

		ProdutoDao produtoDao = new ProdutoDao();

		ArrayList<Produto> listaProdutos = produtoDao.getAll();

		String resultado = Produto.listToJson(listaProdutos);

		try (OutputStream os = response.getOutputStream()) {
			os.write(resultado.getBytes(StandardCharsets.UTF_8));
			os.flush();
		}
	}

}