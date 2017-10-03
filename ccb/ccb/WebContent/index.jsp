<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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