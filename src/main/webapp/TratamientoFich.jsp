<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Tratamiento de ficheros</title>
<link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1>TRATAMIENTO FICHEROS</h1>
<form action="ServletFich" method="post">
<table>
<tr>
<td>
<p>
<label for="formato">Formato del fichero:</label>
<select name="formato" id="formato">
<option value="XLS">XLS</option>
<option value="CSV">CSV</option>
<option value="JSON">JSON</option>
<option value="XML">XML</option>
<option value="RDF">RDF</option>
</select>
</p>
<p> Que quiere hacer con el fichero?</p>
<p>
<label for="lectura">Lectura:</label>
<input type="radio" name="operacion" id="lectura" value="lectura" checked>
</p>
<p>
<label for="escritura">Escritura:</label>
<input type="radio" name="operacion" id="escritura" value="escritura">
</p>
</td>
<td>
<p><label for="dato1">DATO1:</label> <input type="text" name="dato1" id="dato1"></p>
<p><label for="dato2">DATO2:</label> <input type="text" name="dato2" id="dato2"></p>
<p><label for="dato3">DATO3:</label> <input type="text" name="dato3" id="dato3"></p>
<p><label for="dato4">DATO4:</label> <input type="text" name="dato4" id="dato4"></p>
<p><label for="dato5">DATO5:</label> <textarea name="dato5" id="dato5" rows="3" cols="30"></textarea></p>
<p><label for="dato6">DATO6:</label> <input type="text" name="dato6" id="dato6"></p>
<%
String mensaje = (String) request.getAttribute("mensajeError");
if (mensaje != null) { %>
<p style="color: red;"><strong><%=mensaje %></strong></p>
<% } %>
</td>
</tr>
</table>
<p>
<input type="submit" value="Enviar">
</p>
</form>
</body>
</html>
