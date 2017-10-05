<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="version" content="20171005_6">
<title>CCB</title>
</head>
<body>

<table>
	<tr>
		<td>
			
			No SIGA: <br />
			<ol>
				<li>
					ADM > Voluntário > Apontamentos
				</li>
				<li>
					Filtros
					<ul>
						<li> 
							Localidade: * Todos *
						</li>
						<li> 
							Data: 01/09/2017 30/09/2017		
						</li>
						<li> 
							Consultar
						</li> 
					</ul>
				</li>
				<li>
					Imprimir > Excel
				</li>
				<li>
					Salvar localmente
				</li>				
			</ol>
		</td>
	</tr>
	<tr>
		<td>
			<form action="seguro" method="post" enctype="multipart/form-data">
			    <table>
			    	<tr>
			    		<td>
			    			Aqui:
			    			
			    			<ol>
				    			<li>
				    				Preencha os dados de cada igreja. Cada uma será uma aba na planilha do seguro: <br />
				    				<table>
					    				<tr>
					    					<td>
					    					
					    					</td>
					    					<td>
					    						Código:
					    					</td>
					    					<td>
					    						Bairro:
					    					</td>
					    					<td>
					    						Administração:
					    					</td>
					    					<td>
					    						CNPJ da Administração:
					    					</td>
					    					<td>
					    						Nome do responsável:
					    					</td>
					    					<td>
					    						Telefone do responsável:
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 1:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_1" value="22-2413"/>
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_1" value="Centro"/>
					    					</td>
					    					<td>
					    						<input type="text" name="adm_1" value="Pedreira-SP"/>
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_1" value="46.964.664/0001-71"/>
					    					</td>
					    					<td>
					    						<input type="text" name="resp_1" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_1" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 2:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_2" />
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_2" />
					    					</td>
					    					<td>
					    						<input type="text" name="adm_2" />
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_2" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_2" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_2" />
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 3:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_3" />
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_3" />
					    					</td>
					    					<td>
					    						<input type="text" name="adm_3" />
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_3" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_3" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_3" />
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 4:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_4" />
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_4"/>
					    					</td>
					    					<td>
					    						<input type="text" name="adm_4" />
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_4" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_4" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_4" />
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 5:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_5" />
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_5" />
					    					</td>
					    					<td>
					    						<input type="text" name="adm_5" />
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_5" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_5" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_5" />
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 6:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_6" />
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_6" />
					    					</td>
					    					<td>
					    						<input type="text" name="adm_6" />
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_6" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_6" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_6" />
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 7:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_7"/>
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_7" />
					    					</td>
					    					<td>
					    						<input type="text" name="adm_7" />
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_7" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_7" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_7" />
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 8:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_8" />
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_8" />
					    					</td>
					    					<td>
					    						<input type="text" name="adm_8" />
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_8" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_8" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_8" />
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 9:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_9" />
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_9" />
					    					</td>
					    					<td>
					    						<input type="text" name="adm_9" />
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_9" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_9" />
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_9" />
					    					</td>
					    				</tr>
					    				<tr>
					    					<td>
					    						Bairro 10:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_10"/>
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_name_10"/>
					    					</td>
					    					<td>
					    						<input type="text" name="adm_10"/>
					    					</td>
					    					<td>
					    						<input type="text" name="cnpj_10"/>
					    					</td>
					    					<td>
					    						<input type="text" name="resp_10"/>
					    					</td>
					    					<td>
					    						<input type="text" name="resp_phone_10"/>
					    					</td>
					    				</tr>					    							    								    				
				    				</table>
				    			</li>
				    			
				    			<li>
				    				Selecione o mês dos apontamentos: <br />
				    				<select name="month">
										<option value="0">Janeiro</option>
										<option value="1">Fevereiro</option>
										<option value="2">Março</option>
										<option value="3">Abril</option>
										<option value="4">Maio</option>
										<option value="5">Junho</option>
										<option value="6">Julho</option>
										<option value="7">Agosto</option>
										<option value="8">Setembro</option>
										<option value="9">Outubro</option>
										<option value="10">Novembro</option>
										<option value="11">Dezembro</option>					
									</select> 
				    			</li>
				    			
				    			<li>
				    				Selecione o arquivo gerado pelo SIGA: <br />
				    				<input type="file" name="file" />
				    			</li>
				    			
				    			<li>
				    				Clique em Gerar para gerar a planilha para a seguradora e salve o arquivo localmente. <br />
				    				<b>NOTA: </b> por recomendação da seguradora, voluntários com mais de 70 anos são excluídos da planilha, mesmo que estejam nos apontamentos do SIGA. <br />
				    				<button name="send" value="send" type="submit">Gerar</button>
				    			</li>			    			
			    			</ol>
			    		</td>
			    	</tr>			    
			    </table>
			</form>
		</td>			
	</tr>
</table>

</body>
</html>