<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="version" content="20171005_4">
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
				    				Digite o código de cada igreja que estará na planilha. Use o código completo, incluindo o traço: <br />
				    				<table>
					    				<tr>
					    					<td>
					    						Bairro 1:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_1" value="22-2413"/>
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 2:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_2" value="22-1649" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 3:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_3" value="22-3696" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 4:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_4" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 5:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_5" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 6:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_6" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 7:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_7" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 8:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_8" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 9:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_9" />
					    					</td>
					    				</tr>
					    				
					    				<tr>
					    					<td>
					    						Bairro 10:
					    					</td>
					    					
					    					<td>
					    						<input type="text" name="local_10" />
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
				    				Clique em Gerar: <br />
				    				<button name="send" value="send" type="submit"> Gerar</button>
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