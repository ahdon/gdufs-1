<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<%@taglib uri="/struts-tags"  prefix="s"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�ƹ����</title>
<link href="css/bar.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	background-image: url(image/bg.png);background-repeat: no-repeat;
}
.STYLE1 {font-size: large}
-->
</style>
<script  src="js/jquery-1.4.min.js"></script>
<script  src="js/bar.js"></script>
<script type="text/javascript">
function lightControl(o,l,d){
  var url = "lab_startLightControl?link="+l+"&action="+o+"&deviceId="+d;
  if (window.XMLHttpRequest) { 
    req = new XMLHttpRequest(); 
  }else if (window.ActiveXObject){ 
    req = new ActiveXObject("Microsoft.XMLHTTP"); 
  } 

  if(req){ 
     req.open("GET",url, true); 
     req.onreadystatechange = gotmessage; 
     req.send(null); 
}
}
/*���շ�����Ϣ�����ֽ�Ϊ��������*/
function gotmessage(){
  if (req.readyState == 4) { 
    if (req.status == 200) { 
      var strResult = unescape(req.responseText);
      var str = strResult.split(',');
      var result = new Array();
      for(var i=0;i<str.length;i++){
        result[i] = new Array();
        result[i]=str[i].split('|');
      }
      setResult(result);
    } else { 
      alert('System Error!'); 
    } 
  } 
}
/*���ݽ������״̬һ��*/
function setResult(o){
  for(var i=0;i<o.length;i++){
 if(null!=(document.getElementById("light"+(o[i][0])))){
     if(o[i][1]=="off"){ document.getElementById("light"+(o[i][0])).innerText = "�ر�";} else{document.getElementById("light"+(o[i][0])).innerText = "��";}
     }
    }
}


	function lightList(labId,deviceType){
	
	
	
	$.post('lab_lightList',{'labId':labId,'str':deviceType},function(date){
	$('#prompt').hide();
	
	$('#lightList').html(date);
	$("#chaxun").trigger("click");
	})
	
	}


   $(function(){     
       $("A").click(function () {
              
                 $("A").css("color","white").css("font-weight","normal");
                $(this).css("color","blue").css("font-weight","bold"); 
            });
});

</script>
</head>
<body>
<div align="center">
<form id="form1" name="form1" method="post" action="">
<table >
	<tr><td height="71" colspan="5">&nbsp;</td></tr>
<tr>
  <td height="26" colspan="5" style="height: 33px;width: 1002px;background:url(image/dh.png);margin: 0px;padding: 0px;"></td>
</tr>

<tr><td colspan="5"><DIV class="nav">
  <UL>
  <s:iterator value="labInfos" var="lab_Info">
    <LI><A  href="javascript:lightList('${lab_Info.labId}','�ƹ�')" >${lab_Info.labDesc}</A></LI>
   </s:iterator>  
  </UL>
</DIV></td></tr>

<tr>
  <td width="95" rowspan="2">&nbsp;</td>
  <td width="750">
  <div id="lightList" align="center"></div>
</td>
<td width="100" rowspan="2"></td>
</tr>

<tr><td colspan="5" align="center"> <div id="prompt"><h4 style="color:purple;margin-top: 130px;">��ܰ��ʾ��<br/>
��ѡ������ʵ����</h4></div></td></tr>
</table>
</form>
</div>
</body>
</html>