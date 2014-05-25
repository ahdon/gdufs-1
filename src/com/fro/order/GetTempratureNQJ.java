package com.fro.order;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/*************************************************
Copyright (C), 2013, FRO Tech., Ltd.
File name: GetTempratureNQJ // �ļ���
Author: Chan Version: V1.0 Date: 2013-04-8 // ���ߡ��汾���������
Description: ��ʪ�ȴ��������ݻ�ȡ������socket���ӽ���������ָ��������ݹ��ܡ�

Others: ��
Function List: 
1. getData
��ȡ��ʪ�ȷ�������
2��reformat
��ʽ�����ص�����
3��getForTemperature
�����صĽ��byte[]ת��Ϊ�ַ���
History: ��
// �߼��޸����ݼ���
1. Date:
Author:
Modification:
2. ...
*************************************************/

public class GetTempratureNQJ{

	 private Socket socket;
	 private InputStream socketReader;
	 private OutputStream socketWriter;
	 
		/*************************************************
		Function: getData 
		Description: �ֱ�����ʪ�Ȼ�ȡָ��ȷ����¶ȡ��ٷ����¶ȣ�
		                                  Ȼ����ݷ��ؽ�������������ӣ��ٰ�λ����ȡ��ʪ�ȡ�
		Input: IP,Port
		Output: ��
		Return: �¶ȡ�ʪ��
		Others: ��
		*************************************************/
	 public String getData(String IP,String port){
		 String result = "";
			try {
				socket = new Socket(IP,Integer.parseInt(port));
				socketReader = socket.getInputStream();
				socketWriter = socket.getOutputStream();
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			 int flag = 0;
			 //�¶�ָ��
			 byte[] b = new byte[8]; 
			 b[0] =(byte)0x01;
			 b[1] =(byte)0x04;
			 b[2] =(byte)0x00;
			 b[3] =(byte)0x00;
			 b[4] =(byte)0x00;
			 b[5] =(byte)0x01;
			 b[6] =(byte)0x31;
			 b[7] =(byte)0xca;
			 
			 //�¶�ָ��
			 byte[] b2 = new byte[8]; 
			 b2[0] =(byte)0x01;
			 b2[1] =(byte)0x04;
			 b2[2] =(byte)0x00;
			 b2[3] =(byte)0x01;
			 b2[4] =(byte)0x00;
			 b2[5] =(byte)0x01;
			 b2[6] =(byte)0x60;
			 b2[7] =(byte)0x0a;
			 try{
			 socketWriter.write(b);
			 socketWriter.flush();
			 //ˢ���������ʹServer�����յ����ַ���
			 
			 while(socketReader.available()==0&&flag<100){
				 Thread.sleep(100);
				 flag ++;
			 };
			 flag = 0;
			 byte[] readline = new byte[socketReader.available()];
			 socketReader.read(readline);
			 byte [] bb = reformat(readline);
			 
			 socketWriter.write(b2);
			 socketWriter.flush();
			 //ˢ���������ʹServer�����յ����ַ���
			 
			 while(socketReader.available()==0&&flag<100){
				 Thread.sleep(100);
				 flag ++;
			 };
			 flag = 0;
			 byte[] readline2 = new byte[socketReader.available()];
			 socketReader.read(readline2);
			 byte [] bb2 = reformat(readline2);
			 
			 
			 if(bb.length>0&&bb2.length>0){
				 byte [] bb3 = new byte[]{bb[0],bb[1],bb[2],bb[3],bb[4],bb2[3],bb2[4],bb2[5],bb2[6]};
				 result = getForTemperature(bb3,3,4,1) + "," + getForTemperature(bb3,5,6,1);
			 }
			 else{
				 result = "����ʧ��,����ʧ��";
			 }
			 }catch(Exception e){
				 try{
					 socket.close(); //�ر�Socket
					 socketWriter.close();
					 socketReader.close();
				 }catch (Exception e1) {
				}
			 }
			 
			 try{
				 socket.close(); //�ر�Socket
				 socketWriter.close();
				 socketReader.close();
			 }catch (Exception e1) {}
			 
			 return result;
		}

		/*************************************************
		Function: reformat 
		Description: ��ʽ����������
		Input: ��
		Output: ��
		Return: �¶ȡ�ʪ��
		Others: ��
		*************************************************/
	public byte[] reformat(byte[] b){
		byte[] c = new byte[9];
		byte cc=(byte)0xff;
		if(b.length>9){
			for(int i=0;i<b.length;i++){
				if(!Integer.toHexString(((b[i]) & 0x000000FF) | 0xFFFFFF00).substring(6).equals(Integer.toHexString(((cc) & 0x000000FF) | 0xFFFFFF00).substring(6))&&b.length-i>=9){
					for(int j = 0;j<9;j++){
						c[j]=b[i+j];
						System.out.println(Integer.toHexString(((c[j]) & 0x000000FF) | 0xFFFFFF00).substring(6));

					}
					String str1="";
					for(int j=0;j<c.length-2;j++){
						str1=str1+(""+Integer.toHexString(((c[j]) & 0x000000FF) | 0xFFFFFF00).substring(6));
					}
					byte[] sbuf = CRC16M.getSendBuf(str1);
					str1=CRC16M.getBufHexStr(sbuf).substring(CRC16M.getBufHexStr(sbuf).length()-4, CRC16M.getBufHexStr(sbuf).length());
					String str2="";
					for(int j=0;j<2;j++){
						str2=str2+(""+Integer.toHexString(((c[c.length-2+j]) & 0x000000FF) | 0xFFFFFF00).substring(6));
					}
					if(str2.equalsIgnoreCase(str1)){
					break;
					}
				}
				
			}
		}else{
			c=b;
		}
		return c;
	}
	
	/*************************************************
	Function: getForTemperature 
	Description: �����������ݣ�������String���ͽ��
	Input: ��
	Output: ��
	Return: �¶ȡ�ʪ��
	Others: ��
	*************************************************/
	public String getForTemperature(byte[] retval2,int start,int end,int flag){//��ȡ�����¶�flag����Ϊ1,��ȡԤ���¶�flag����Ϊ0
		byte[] retval1=reformat(retval2);
		String str=null;
	    str=(Integer.toHexString(((retval1[start]) & 0x000000FF) | 0xFFFFFF00).substring(6))+(Integer.toHexString(((retval1[end]) & 0x000000FF) | 0xFFFFFF00).substring(6));
		java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance();   
	    nf.setMinimumFractionDigits(2);
	    str=String.valueOf(nf.format(Integer.parseInt(str, 16)));
	    for(int i=0;i<str.length();i++){
	    	if(str.substring(i, i+1).equals(",")){
	    		str=str.substring(0, i)+str.substring(i+1,str.length());
	    	}
	    }
	    Double db=Double.parseDouble(str);
	    if(flag==1){
	    db=db/10;
	    }
	    if(flag==2){
	        db=db/100;
	        }
	    if(flag==3){
	    	db=db/1000;
	    }
	    str=String.valueOf(nf.format(db));
		return str;
	}
}
