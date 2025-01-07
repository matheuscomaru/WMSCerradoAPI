<!DOCTYPE html>
<html lang="pt-br">

<head>
	<meta charset="UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<title>Documenta��o da API</title>
	<!-- Bootstrap CSS -->
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" />
</head>

<body>
	<div class="container mt-5">
		
		<h1 class="text-center mb-4">Documenta��o da API</h1>
		
		<div class="card mb-4">
		<div class="card-header bg-primary text-white">
				<h4>TecGesco Gest�o - API WMS Cerrado Vers�o 1.1.0</h4>
			</div>
			<div class="card-body">
				<p>
					Nos Enpoints, pode estar usando o DNS <strong>chsistemas251.eunanuvem.com.br</strong> no lugar do IP <strong>179.190.0.182</strong>
				</p>
				</div>
			<div class="card-footer">
				<small class="text-muted">�ltima atualiza��o: 07/01/2025</small>
			</div>
		</div>
		

		<!-- Buscar produto por ID (codigo) -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Buscar produto por ID (codigo)</h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/produto
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna as informa��es de um produto com
					base no c�digo fornecido.
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><code>id</code></td>
							<td>Integer</td>
							<td>Informar o c�digo do produto no CH ERP.</td>
							<td>Sim</td>
						</tr>
					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/produto?id=103
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">
{
  "productCode": "000103",
  "description": "NINO DOG ADULTO CARNE 06 KG",
  "ean": "7898674131990",
  "dun": "",
  "packingInfo": "",
  "factoryCode": "103",
  "dunQuantity": 120,
  "grossWeight": 6.06,
  "netWeight": 6,
  "palletDataQuantity": 120
}
</pre>
			</div>
		</div>

		<!-- Listar produtos -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Listar produtos</h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/listarprodutos
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna uma lista de produtos. Nessa
					lista ser� retornada somente produtos ativos, com tipo Revenda ou
					Acabado e que tenha c�digo de barras informado no cadastro.
				</p>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/listarprodutos
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">
[
    {
        "productCode": "000102",
        "description": "NINO DOG ADULTO CARNE 02 KG 8X2",
        "ean": "17898674131836",
        "dun": "",
        "packingInfo": "",
        "factoryCode": "102",
        "dunQuantity": 40.0,
        "grossWeight": 16.24,
        "netWeight": 16.0,
        "palletDataQuantity": 40
    }
]
</pre>
			</div>
		</div>

		<!-- Listar Ops -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Listar OPs (Ordens de Produ��o)</h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/listarops
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna uma lista de ordens de produ��o
					lan�adas no sistema.
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><code>datainicial</code></td>
							<td>String</td>
							<td>
								Ser� considerada data de lan�amento se a situa��o for 0:aberta e data de fechamento se a situa��o for 1:finalizada ou 2:estornada. A Data deve estar no padr�o
								yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>

						<tr>
							<td><code>datafinal</code></td>
							<td>String</td>
							<td>
								Ser� considerada data de lan�amento se a situa��o for 0:aberta e data de fechamento se a situa��o for 1:finalizada ou 2:estornada. A Data deve estar no padr�o
								yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>

						<tr>
							<td><code>situacao</code></td>
							<td>Integer</td>
							<td>
								C�digo da situa��o da OP sendo 0:aberto 1:finalizada
								2:estornada
							</td>
							<td>Sim</td>
						</tr>
					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/produto?id=103
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">
[
    {
        "id": "001129",
        "productionBatch": "0026/11-2024",
        "expirationDate": "2025-11-27",
        "manufacturingDate": "2024-11-28",
        "note": "",
        "items": [
            {
                "productId": "000107",
                "quantity": 6000
            }
        ]
    }
]
</pre>
			</div>
		</div>




		<!-- Buscar OP por ID (codigo) -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Buscar OP por ID (N� da Ordem)<h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/op
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna os dados da ordem de produ��o filtrada
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><code>id</code></td>
							<td>Integer</td>
							<td>
								Numero da ordem de produ��o
							</td>
							<td>Sim</td>
						</tr>
					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/op?id=506
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">
{
	"id": "000506",
	"productionBatch": "0005/06-2024",
	"expirationDate": "2025-06-05",
	"manufacturingDate": "2024-06-06",
	"note": "",
	"items": [
		{
			"productId": "000206",
			"quantity": 113
		}
	]
}
</pre>
			</div>
		</div>


		<!-- Listar Cargas Faturadas -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Listar Cargas Faturadas<h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/listarcargasfaturadas
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna uma lista de todas as ordens de cargas (lote pedido), cujo todos
					os pedidos
					associados a ela tenham sido faturados e o registro no TMS >> Fretes ainda esteja com status Aberto
					al�m de estar com motorista e veiculo associado.
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><code>datainicial</code></td>
							<td>String</td>
							<td>
								Data incial no padr�o yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>

						<tr>
							<td><code>datafinal</code></td>
							<td>String</td>
							<td>
								Data final no padr�o yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>
					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/listarcargasfaturadas?datainicial=2024-12-01&datafinal=2024-12-02
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">
[
	{
		"responsibleId": "000014",
		"responsibleName": "WELITON",
		"helperId": "",
		"helperName": "",
		"referenceId": "000061",
		"route": {
			"id": "000001",
			"description": "REDES"
		},
		"vehicle": {
			"id": "NJN8I73",
			"description": "M.BENZ-710"
		},
		"note": ""
	}
]
</pre>
			</div>
		</div>



		<!-- Listar Cargas Finalizadas -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Listar Cargas Finalizadas<h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/listarcargasfinalizadas
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna uma lista de todas as ordens de cargas (lote pedido), cujo todos
					os pedidos
					associados a ela tenham sido faturados e o registro no TMS >> Frete esteja com status Finalizado.
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><code>datainicial</code></td>
							<td>String</td>
							<td>
								Data incial no padr�o yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>

						<tr>
							<td><code>datafinal</code></td>
							<td>String</td>
							<td>
								Data final no padr�o yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>
					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/listarcargasfinalizadas?datainicial=2024-12-01&datafinal=2024-12-02
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">
[
	{
		"responsibleId": "000014",
		"responsibleName": "WELITON",
		"helperId": "",
		"helperName": "",
		"referenceId": "000061",
		"route": {
			"id": "000001",
			"description": "REDES"
		},
		"vehicle": {
			"id": "NJN8I73",
			"description": "M.BENZ-710"
		},
		"note": ""
	}
]
</pre>
			</div>
		</div>




		<!-- Listar Cargas Canceladas -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Listar Cargas Canceladas<h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/listarcargascanceladas
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna uma lista todas as ordens de cargas (lote pedido), cujo todos os
					pedidos
					associados a ela tenham sido faturados e o registro no TMS >> Frete esteja com status Cancelado.
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><code>datainicial</code></td>
							<td>String</td>
							<td>
								Data incial no padr�o yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>

						<tr>
							<td><code>datafinal</code></td>
							<td>String</td>
							<td>
								Data final no padr�o yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>
					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/listarcargascanceladas?datainicial=2024-12-01&datafinal=2024-12-02
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">
[
	{
		"responsibleId": "000014",
		"responsibleName": "WELITON",
		"helperId": "",
		"helperName": "",
		"referenceId": "000061",
		"route": {
			"id": "000001",
			"description": "REDES"
		},
		"vehicle": {
			"id": "NJN8I73",
			"description": "M.BENZ-710"
		},
		"note": ""
	}
]
</pre>
			</div>
		</div>


		<!-- Listar Notas por ID Carga (Lote) -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Listar Notas por ID de Carga (Lote pedido)<h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/listarnotas
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna uma lista com todas as notas faturadas de uma ordem de carga.
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><code>id</code></td>
							<td>Integer</td>
							<td>
								Codigo da ordem de carga (lote pedido saida)
							</td>
							<td>Sim</td>
						</tr>

					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/listarnotas?id=233736
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">
[
	{
		"senderId": "31178921000172",
		"senderName": "VIDAN INDUSTRIA E COMERCIO DE RACAO ANIMAL LTDA",
		"receiverId": "51483561000120",
		"receiverName": "JUVELINA PINHEIRO COSTA",
		"orderId": "029964",
		"sequence": 0,
		"key": "51240931178921000172550010000288101203262027",
		"number": 28810,
		"series": 1,
		"amount": 162.28,
		"weight": 23.94,
		"quantity": 7.0,
		"items": [
			{
				"itemNumber": 1,
				"productCode": "000910",
				"productName": "MAIK�O FILHOTE CARNE E LEITE 80 GR 40X80",
				"ean": "17898674132376",
				"quantity": 2.0,
				"amountUnit": 12.72,
				"amountTotal": 25.44,
				"measurementUnit": "FD"
			},
			{
				"itemNumber": 2,
				"productCode": "000924",
				"productName": "MAIK�O PREMIUM FORT CARNE ADULTO 80 GR 40X80",
				"ean": "17898674132383",
				"quantity": 1.0,
				"amountUnit": 12.72,
				"amountTotal": 12.72,
				"measurementUnit": "FD"
			}
		],
		"paymentInfo": {
			"id": "PIX",
			"billingId": "",
			"description": ""
		},
		"installments": []
	}
]
</pre>
			</div>
		</div>


		<!-- Buscar cliente por CNPJ/CPF -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Buscar cliente por CNPJ/CPF<h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/cliente
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna dados de cadastro do cliente recebendo como parametro o CPF ou
					CNPJ.
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td><code>cnpj</code></td>
							<td>String</td>
							<td>
								Informar o CPF ou CNPJ sem pontos e tra�os, ou seja, somente numeros.
							</td>
							<td>Sim</td>
						</tr>

					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/cliente?cnpj=31178921000172
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">

{
	"identifier": "31178921000172",
	"referenceId": "000001",
	"fiscalName": "VIDAN INDUSTRIA E COMERCIO DE RACAO ANIMAL LTDA",
	"fantasyName": "VIDAN NUTRICAO ANIMAL",
	"phone": "(65) 4042 4270",
	"address": {
		"latitude": "",
		"longitude": "",
		"place": "EST PAULINO PINTO DE GODOY (LOT C PEQUI)",
		"addressNumber": "SN",
		"neighborhood": "CAPAO DO PEQUI",
		"cityName": "VARZEA GRANDE (MT)",
		"stateCode": "MT",
		"zipCode": "78134-252",
		"countryName": "BRASIL"
	}
}
</pre>
			</div>
		</div>


		<!-- listar-ordens-de-carga -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Listar Ordens de Carga para separa��o<h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/listar-ordens-de-carga-separacao
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna uma lista de Ordens de Cargas (Lote pedido saida) cujo todos os
					pedidos associados esteja com situa��o de entrega esteja como Confirmado.
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>

						<tr>
							<td><code>datainicial</code></td>
							<td>String</td>
							<td>
								Data incial no padr�o yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>

						<tr>
							<td><code>datafinal</code></td>
							<td>String</td>
							<td>
								Data final no padr�o yyyy-MM-dd
							</td>
							<td>Sim</td>
						</tr>

					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/listar-ordens-de-carga-separacao?datainicial=2024-12-02&datafinal=2024-12-02
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">

[
	{
		"referenceId": "000065",
		"route": {
			"id": "000000",
			"description": "SEM ROTA"
		},
		"note": "",
		"createDate": "",
		"branchId": "4"
	}
]
</pre>
			</div>
		</div>


		<!-- listar-itens-ordens-de-carga -->
		<div class="card mb-4">
			<div class="card-header bg-primary text-white">
				<h4>Listar Itens da Ordem de Carga<h4>
			</div>
			<div class="card-body">
				<p>
					<strong>M�todo:</strong> <span class="badge bg-success">GET</span>
				</p>
				<p>
					<strong>Endpoint:</strong>
					179.190.0.182:8093/WMSCerradoApi/itens-carga
				</p>
				<p>
					<strong>Descri��o:</strong> Retorna uma lista agrupada de itens da ordem cargas (Lote pedido saida).
				</p>

				<h5>Par�metros</h5>
				<table class="table table-bordered">
					<thead class="table-light">
						<tr>
							<th>Par�metro</th>
							<th>Tipo</th>
							<th>Descri��o</th>
							<th>Obrigat�rio</th>
						</tr>
					</thead>
					<tbody>

						<tr>
							<td><code>carga</code></td>
							<td>String</td>
							<td>
								C�digo da carga/lote pedido
							</td>
							<td>Sim</td>
						</tr>

						<tr>
							<td><code>empresa</code></td>
							<td>Int</td>
							<td>
								C�dido da empresa
							</td>
							<td>Sim</td>
						</tr>

					</tbody>
				</table>

				<h5>Exemplo de Requisi��o</h5>
				<pre class="bg-light p-3 border rounded">
GET 179.190.0.182:8093/WMSCerradoApi/itens-carga?carga=2&empresa=4
</pre>

				<h5>Exemplo de Resposta</h5>
				<pre class="bg-light p-3 border rounded">

[
	{
		"productCode": "000171",
		"quantity": 3.0,
		"loadOrderId": "000002"
	},
	{
		"productCode": "000178",
		"quantity": 1.0,
		"loadOrderId": "000002"
	}
]
</pre>
			</div>
		</div>




		<!-- Bootstrap Bundle with Popper -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js"></script>
</body>

</html>