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
			
			No SIGA, após digitar e conferir os livros: <br />
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
			
			Ou:
			
			<ol>
				<li>
					Casa de oração > Voluntário > Apontamentos
				</li>
				<li>
					Filtros
					<ul>
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
										<option value="month">Mês</option>
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
				    			<li>
				    				O arquivo gerado será um arquivo compactado contendo uma ou mais planilhas do seguro. Cada planilha tem 10 abas, uma para cada localidade encontrada no arquivo
				    				gerado pelo SIGA. Se o arquivo tiver mais de 10 localidades, mais de uma planilha será gerada.
				    			</li>
				    			<li>
				    				Descompacte o arquivo baixado e:
				    				<ol>
				    					<li>
				    						<b>Confira todos os dados cuidadosamente.</b>
				    					</li>
				    					<li>
				    						Preencha os campos do cabeçalho de cada aba.
			    						</li>
				    				</ol>
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