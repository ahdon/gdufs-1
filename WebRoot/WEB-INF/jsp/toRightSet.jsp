<%@ page language="java" pageEncoding="GBK"%>
<%@taglib uri="/struts-tags"  prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>�û�Ȩ������</title>
<link href="css/wu.css" rel="stylesheet" type="text/css" />
<script  src="js/jquery-1.4.min.js"></script>
<SCRIPT type="text/javascript">
$(function(){
$("#stas").trigger("click");	


$('#stas').click(function(){
var userId=$('#userId').val();

$.post('systemManger_chaxunStas',{'userId':userId},function(data){
	for(var i=0;i<30 ;i++){
	for(var j=0;j<data.length;j++){
		if($('#checkbox'+i).val()==data[j]){
		$('#checkbox'+i).attr('checked','checked');
		}
	
	}
	
	}	
		
		
		},'json')

});

$('#allchoose').toggle(function(){
 $("input[name='checkbox']:checkbox").each(function(){
            
                $(this).attr("checked",true);  
                
            })

},
function(){
 $("input[name='checkbox']:checkbox").each(function(){
            
                $(this).attr("checked",false);   
                
            })}

);



});


</SCRIPT>


</head>

<body>
<form id="form1" name="form1" method="post" action="systemManger_RightSet?userId=${userId}">
  <table width="95%" border="1" cellspacing="0" cellpadding="0">
    <tr>
      <td height="20" colspan="3" align="center">�ʺţ�
    <input type="text" id="userId" value="${userId}" disabled="disabled"/>
        <input type="button" value="��ѯȨ��" id="stas">
        <input type="checkbox"  id="allchoose" />ȫѡ
      	</td>
    </tr>
    <tr>
      <td width="34%"><label>
        <input type="checkbox" name="checkbox" value="sys01" id="checkbox0" />
        �豸ά������</label></td>
      <td width="33%"><label>
        <input type="checkbox" name="checkbox" value="sys02" id="checkbox1"/>
        ʵ����ʹ������ </label></td>  
      <td width="33%"><label>
        <input type="checkbox" name="checkbox" value="sys03" id="checkbox2"/>
        ��Ϣ��ѯ</label></td>
    </tr>
    <tr>
      <td><label>
        <input type="checkbox" name="checkbox" value="sys04" id="checkbox3"/>
        ��Ƶ���</label></td>
      <td><label>
        <input type="checkbox" name="checkbox" value="sys05" id="checkbox4"/>
        �ƹ���</label></td>
      <td><label>
        <input type="checkbox" name="checkbox" value="sys06" id="checkbox5"/>
        �յ����</label></td>
    </tr>
    <tr>
      <td><label>
        <input type="checkbox" name="checkbox" value="sys08" id="checkbox7"/>
        ��Դ���</label></td>
      <td><label>
        <input type="checkbox" name="checkbox" value="sys09" id="checkbox8"/>
        �������</label></td>
        <td><label>
        <input type="checkbox" name="checkbox" value="sys10" id="checkbox9"/>
        �豸ά��ȷ��</label></td>
    </tr>
    <tr>
      <td><label>
        <input type="checkbox" name="checkbox" value="sys11" id="checkbox10"/>
        ʵ��������ȷ��</label></td>
        
          <td><label>
        <input type="checkbox" name="checkbox" value="sys07" id="checkbox6"/>
        ʵ����ֵ��Ǽ�</label></td>
         
           <td><label>
        <input type="checkbox" name="checkbox" value="sys12" id="checkbox12"/>
        �豸����</label></td>   
             
    </tr>
    
  
 
  
  
    
    <tr>

      <td><label>
        <input type="checkbox" name="checkbox" value="sys13" id="checkbox13"/>
       �Ҿ����</label></td>
     
      <td><label>
        <input type="checkbox" name="checkbox" value="sys14" id="checkbox14"/>
       �ĲĹ���</label></td>
     
        <td><label>
        <input type="checkbox" name="checkbox" value="sys16" id="checkbox16"/>
        ������鿴</label></td> 
    </tr>
    
      <tr>
      
        
      
       
        <td width="33%"><label>
        <input type="checkbox" name="checkbox" value="sys24" id="checkbox11"/>
        ��������� </label></td> 
      
       <td><label>
        <input type="checkbox" name="checkbox" value="sys25" id="checkbox24"/>
        ʱ��ι���</label></td>
       
       
           <td><label>
        <input type="checkbox" name="checkbox" value="sys18" id="checkbox18"/>
      �ı�������</label></td> 
        
    </tr>
    
    <tr>
     
      
        
        
        <td><label>
        <input type="checkbox" name="checkbox" value="sys19" id="checkbox19"/>
      ���ܿ�¼��</label></td>
        
         <td><label>
        <input type="checkbox" name="checkbox" value="sys28" id="checkbox17"/>
       �û������</label></td>
          
          
    
       <td width="33%"><label>
        <input type="checkbox" name="checkbox" value="sys29" id="checkbox28"/>
        ��ȫ�� </label></td> 
    </tr>
    <tr>
    
    
      <td><label>
        <input type="checkbox" name="checkbox" value="sys20" id="checkbox20"/>
        ����ģʽ</label></td>
           <td><label>
        <input type="checkbox" name="checkbox" value="sys21" id="checkbox21"/>
        �����趨</label></td>
        
         <td><label>
        <input type="checkbox" name="checkbox" value="sys23" id="checkbox23"/>
        ����ʵ����</label></td>
    </tr>
    <tr>
     <td width="33%"><label>
        <input type="checkbox" name="checkbox" value="sys27" id="checkbox25"/>
       �����豸���� </label></td> 
  <td width="33%"><label>
        <input type="checkbox" name="checkbox" value="sys26" id="checkbox26"/>
        �����豸���� </label></td>
     
          <td><label>
        <input type="checkbox" name="checkbox" value="sys22" id="checkbox22"/>
        ʹ�ü�¼��ѯ</label></td>
    </tr>
    
       <tr>
     <td width="33%" ><label>
        <input type="checkbox" name="checkbox" value="sys29" id="checkbox29"/>
      �Ž������޸� </label></td> 
  <td width="33%"><label>
  <td>&nbsp</td>
  <td>&nbsp</td>
    </tr>
    
    <tr>
      <td colspan="3" align="center"><label>
     <input type="submit"  value="�ύ" /></label></td>
    </tr>
  </table>
</form>
</body>
</html>
