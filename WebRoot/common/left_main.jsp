<%@ page language="java" import="java.util.*" pageEncoding="GB18030" isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<title>Untitled Document</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="css/style.css" rel="stylesheet" type="text/css">
<script  src="js/jquery-1.4.min.js"></script>
<script language="JavaScript" type="text/JavaScript">
<!--
function MM_goToURL() { //v3.0
  var i, args=MM_goToURL.arguments; document.MM_returnValue = false;
  for (i=0; i<(args.length-1); i+=2) eval(args[i]+".location='"+args[i+1]+"'");
}
//-->
</script>
</head>
<script language="JavaScript">	
	function MenuClick( MenuID )
	{
		var i = 0;
		var idMenu = null;
		for (i = 1; i <= 7; i++)
		{
			idMenu = "Menu" + i;
			if (idMenu == MenuID)
			{
				eval(idMenu).style.display = "";
			}
			else
			{
				eval(idMenu).style.display = "none";
			}
		}
	}
	
	


	
	
	
	
</script>
<body>
<table width="250" height="100%" border="0" cellpadding="0" cellspacing="0"  background="image/run.jpg">
  
  <tr height="8%">
	<td>
 		<table border="1" width="100%">
   			<tr><td rowspan="2" width="40%" >�¶ȣ�<span style="color:red" id="w"></span>��C<br>ʪ�ȣ�<span style="color:red" id="s"></span> %</td><td>���,<span style="color: red">${userInfo.userId}</span></td></tr>
   			<tr><td>[${userInfo.userName},<a href="sysOut" >�˳�</a>]</td></tr>
   		</table>
	</td>
</tr>
  
  <!-- ϵͳ����Ա -->
  
  
   <tr style="cursor: hand" onClick="MenuClick('Menu1')"> 
    <td width="100%" height="20" background="image/menu3.gif" bgcolor="#CCCCCC" align="center">ʵ����ʹ�ú��豸ά������&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
  </tr>
  <tr id="Menu1"> 
    <td valign="top">
    	<table width="100%" border="0" cellspacing="2" cellpadding="0">
    	
    	
    	
    	<s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys01'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="#" target="mainFrame">�豸ά������</a></td>
        </tr>
        </s:if>
        </s:iterator>
      
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys02'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="#" target="mainFrame">ʵ����ʹ������</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys03'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="#" target="mainFrame">��Ϣ��ѯ</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
      </table>
    </td>
  </tr> 
  <tr style="cursor: hand" onClick="MenuClick('Menu2')"> 
    <td width="100%" height="20" background="image/menu3.gif" bgcolor="#CCCCCC" align="center">ʵ����״̬���&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
  </tr>
  <tr id="Menu2" style="display: none"> 
    <td valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
    
    <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys04'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="lab_spControl" target="mainFrame" >ʵ������Ƶ���</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys05'">
        <tr> 
          <td bgcolor="#D3E3F5" width="100%"><a href="lab_lightControl" target="right">�ƹ���</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys06'">
        <tr> 
          <td bgcolor="#D3E3F5" width="100%"><a href="lab_airConditioner" target="right">�յ����</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys07'">
        <tr> 
          <td bgcolor="#D3E3F5" width="100%"><a href="lab_doorContorl" target="right">�Ž����</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys08'">
        <tr> 
          <td bgcolor="#D3E3F5" width="100%"><a href="lab_switchControl" target="right">��Դ���</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys09'">
        <tr> 
          <td bgcolor="#D3E3F5" width="100%"><a href="lab_safeControl" target="right">�������</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
      </table>
  </tr>
  <tr style="cursor: hand" onClick="MenuClick('Menu3')"> 
    <td height="20" width="100%" background="image/menu3.gif" bgcolor="#CCCCCC" align="center">ʵ�����ճ�����&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
  </tr>
  <tr id="Menu3" style="display: none"> 
    <td valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
    
   		 <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys10'">
        <tr> 
          <td bgcolor="#D3E3F5" ><a href="#" target="mainFrame">�豸ά������ȷ�ϣ�VIP�ʺ���Ч)</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys11'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="#" target="mainFrame">ʵ����ʹ������ȷ�ϣ�VIP�ʺ���Ч)</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <!-- ���� -->
        
        <s:if test='#session.userInfo.logMode=="1"'>
                <tr> 
          <td bgcolor="#D3E3F5"><a href="shebei!addLabinfo.action" target="right">ʵ�����豸����VIP�ʺ���Ч)</a></td>
        </tr>
         <tr> 
          <td bgcolor="#D3E3F5"><a href="can!listshiyanshi.action" target="right">ʵ�����豸��������VIP�ʺ���Ч)</a></td>
        </tr>
        </s:if> 
      </table>
  </tr>
  <tr style="cursor: hand" onClick="MenuClick('Menu4')"> 
    <td height="20" background="image/menu3.gif" bgcolor="#CCCCCC" align="center">ʵ��������Ŀ����VIP�ʺ���Ч)&nbsp&nbsp&nbsp&nbsp&nbsp</td>
  </tr>
  <tr id="Menu4" style="display: none"> 
    <td valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
    
    <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys12'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="#"target="mainFrame">�������</a></td>
        </tr>
       </s:if>
       </s:iterator>
       
       <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys13'"> 
        <tr> 
          <td bgcolor="#D3E3F5"><a href="#"target="mainFrame">���ĳ���</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys14'">
         <tr> 
          <td bgcolor="#D3E3F5"><a href="#" target="mainFrame">����ѯ</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys15'">
      <tr> 
          <td bgcolor="#D3E3F5"><a href="#" target="mainFrame">�������</a></td>
        </tr>
        </s:if>
        </s:iterator>
      </table></td>
  </tr>
  
  <tr style="cursor: hand" onClick="MenuClick('Menu5')"> 
    <td width="100%" height="20" background="image/menu3.gif" bgcolor="#CCCCCC" align="center">��������&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
  </tr>
  <tr id="Menu5" style="display: none"> 
    <td valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
    
    <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys16'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="paike!toPaiKe.action" target="right" >ʵ�����ſ�</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys17'">
        <tr> 
          <td bgcolor="#D3E3F5" width="100%"><a href="paike!listClass.action" target="right">�༭ʵ���ҵ�����</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
         <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys24'">
        <tr> 
          <td bgcolor="#D3E3F5" width="100%"><a href="paike!classManager.action" target="right">�α����</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
      </table>
  </tr>
  <tr style="cursor: hand" onClick="MenuClick('Menu6')"> 
    <td height="20" width="100%" background="image/menu3.gif" bgcolor="#CCCCCC" align="center">�û�����&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
  </tr>
  <tr id="Menu6" style="display: none"> 
    <td valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
    
    <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys18'">
        <tr> 
          <td bgcolor="#D3E3F5" ><a href="systemManger_toUserInfo" target="right">�û���Ϣ�ı�������</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys19'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="systemManger_toImportUserInfo" target="right">�û���Ϣ���ܿ�¼��</a></td>
        </tr>
        </s:if>
        </s:iterator>
       
        
      </table>
  </tr>
  <tr style="cursor: hand" onClick="MenuClick('Menu7')"> 
    <td height="20" background="image/menu3.gif" bgcolor="#CCCCCC" align="center">ϵͳ����&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</td>
  </tr>
  <tr id="Menu7" style="display: none"> 
    <td valign="top"><table width="100%" border="0" cellspacing="2" cellpadding="0">
    
    <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys20'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="systemManger_toLabStauSet"target="right">ʵ����״̬�趨</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys21'">
        <tr> 
          <td bgcolor="#D3E3F5"><a href="systemManger_toAutoSet"target="right">�Զ����������趨</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys22'">
         <tr> 
          <td bgcolor="#D3E3F5"><a href="systemManger_toLabRecode" target="right">ʵ����ʹ�ü�¼��ѯ</a></td>
        </tr>
        </s:if>
        </s:iterator>
        
        <s:iterator value="#session.rightids" status="status">
    	<s:if test="#session.rightids[#status.index]=='sys23'">
      <tr> 
          <td bgcolor="#D3E3F5"><a href="#" target="mainFrame">����ʵ����</a></td>
        </tr>
        </s:if>
        </s:iterator>
      </table></td>
  </tr>
  
 

  
    <tr> 
    <td width="100%" align="center" height="20" background="image/menu3.gif" bgcolor="#CCCCCC"><a href="sysOut">��&nbsp&nbsp&nbsp&nbsp��&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp</a></td>
  </tr>
  
</table>
</body>
</html>
