package com.tecgesco.wmscerradoapi.model;

import java.util.ArrayList;

public class CargaDms {

	private String referenceId = ""; // * id da carga no erp
	private Route route = new Route();
	private String note = ""; // observação
	private String createDate = ""; // * data de cadastro do registro no ERP
	private String branchId = ""; // id da filial

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	// Método para converter um objeto em JSON usando StringBuilder
	public String toJson() {
		StringBuilder json = new StringBuilder();
		json.append("{");
		json.append("\"referenceId\":\"").append(referenceId).append("\",");
		json.append("\"route\":{");
		json.append("\"id\":\"").append(route.getId()).append("\",");
		json.append("\"description\":\"").append(route.getDescription()).append("\"");
		json.append("},");
		json.append("\"note\":\"").append(note).append("\",");
		json.append("\"createDate\":\"").append(createDate).append("\",");
		json.append("\"branchId\":\"").append(branchId).append("\"");
		json.append("}");
		return json.toString();
	}

	// Método para converter uma lista de objetos em JSON usando StringBuilder
	public String listToJson(ArrayList<CargaDms> list) {
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
