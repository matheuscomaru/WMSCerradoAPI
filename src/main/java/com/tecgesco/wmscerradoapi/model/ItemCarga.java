package com.tecgesco.wmscerradoapi.model;

import java.util.ArrayList;

public class ItemCarga {

	private String productCode = ""; // Código/Id do produto no ER
	private double quantity = 0; // * quantidade agrupada de todos os pedidos na ordem de carga
	private String loadOrderId = ""; // * id da ordem de carga no ERP

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public String getLoadOrderId() {
		return loadOrderId;
	}

	public void setLoadOrderId(String loadOrderId) {
		this.loadOrderId = loadOrderId;
	}

	public String toJson() {
		StringBuilder jsonBuilder = new StringBuilder();
		jsonBuilder.append("{");
		jsonBuilder.append("\"productCode\":").append("\"").append(productCode).append("\",");
		jsonBuilder.append("\"quantity\":").append(quantity).append(",");
		jsonBuilder.append("\"loadOrderId\":").append("\"").append(loadOrderId).append("\"");
		jsonBuilder.append("}");
		return jsonBuilder.toString();
	}

	public String listToJson(ArrayList<ItemCarga> list) {
		StringBuilder jsonArray = new StringBuilder();
		jsonArray.append("[");

		for (int i = 0; i < list.size(); i++) {
			jsonArray.append(list.get(i).toJson());
			if (i < list.size() - 1) {
				jsonArray.append(",");
			}
		}

		jsonArray.append("]");
		return jsonArray.toString();
	}

}
