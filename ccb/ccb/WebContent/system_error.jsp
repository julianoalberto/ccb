<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  
%>
    
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
			<div>
				<div id="errorDiv">
					<h2>
						Erro no sistema. Envie as informações abaixo para julianoalberto@gmail.com.
					</h2>
					
					<% 
						String message = (String) request.getSession().getAttribute("message");		
						String details = (String) request.getSession().getAttribute("details");
					%>
					<p>
						<%= message %>
					</p>
					<p>
						<%= details %>
					</p>
					
				</div>
							
			</div>
			
		</td>			
	</tr>
</table>

</body>
</html>