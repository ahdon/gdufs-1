<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>

<body>

	<div align="right" >
	
	<table border="1" cellpadding="0" cellspacing="2" width="100%" height="100%"  bgcolor="#D3E3F5">
		<tr align="center">
			<td width="100%" >
				<form action="">
					�豸���ƣ�<select>
							<option>PC��</option>
							<option>������</option>
							 </select>
					������<input type="text">	
					<br/>
					<br/>
					<input type="submit" value="�ύ">
					<input type="reset" value="ȡ��">	 
				</form>	
			</td>
		</tr>
		
		<tr>
			<td align="center">
						<form action="" method="post"
							enctype="multipart/form-data">
							�ϴ��ļ���
							<input type="file" name="file1" />
							<input type="submit" value="ȷ���ϴ�" />
						</form>
			</td>
		</tr>
	</table>

	
	</div>
	
</body>
</html>
