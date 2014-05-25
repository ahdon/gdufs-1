package com.fro.order;


/*************************************************
Copyright (C), 2013, FRO Tech., Ltd.
File name: SMPSControl 
Author: Chan Version: V1.0 Date: 2013-02-21
Description: ��Դ���ؿ��ƣ�������Դ����ָ���ѯ��尴������״ָ̬�
                                   ��尴�����������ָ�״̬��ѯָ�

Others: ��// �������ݵ�˵��
Function List: // ��Ҫ�����б�ÿ����¼Ӧ���������������ܼ�Ҫ˵��
1. onOrder
��Դ��ָ��
2��offOrder
��Դ��ָ��
History: // �޸���ʷ��¼�б�ÿ���޸ļ�¼Ӧ�����޸����ڡ��޸�
// �߼��޸����ݼ���
1. Date:
Author:
Modification:
2. ...
*************************************************/
public class SMPSControl {
	
	ByteStringConvert bsc = new ByteStringConvert();/*byte String ��ת*/
	
	/*************************************************
	Function: onOrder 
	Description: ��ָ����·ָ��
	Input: link,ָ����·
	Return: ��ָ��
	*************************************************/
	public byte[] onOrder(int link){
		String order = "[001O" + link + "T0]";
		byte[] getorder = bsc.stringToByte(order);
		return getorder;
	}
	
	/*************************************************
	Function: onOrderAll 
	Description: ��ȫ����·ָ��
	Input: ��
	Return: ��ȫ����·ָ��
	*************************************************/
	public byte[] onOrderAll(){
		String order = "[001O12345678T0]";
		byte[] getorder = bsc.stringToByte(order);
		return getorder;
	}
	
	/*************************************************
	Function: offOrder 
	Description: �ر�ָ����·ָ��
	Input: link,ָ����·
	Return: �ر�ָ��
	*************************************************/
	public byte[] offOrder(int link){
		String order = "[001C" + link + "T0]";
		byte[] getorder = bsc.stringToByte(order);
		return getorder;
	}
	
	/*************************************************
	Function: offOrderAll 
	Description: �ر�ȫ����·ָ��
	Input: ��
	Return: �ر�ȫ����·ָ��
	*************************************************/
	public byte[] offOrderAll(){
		String order = "[001C12345678T0]";
		byte[] getorder = bsc.stringToByte(order);
		return getorder;
	}
	
	/*************************************************
	Function: statusOrder 
	Description: ���ɲ�ѯ��·״ָ̬��
	Input: ��
	Return: ��ѯ��·״ָ̬��
	*************************************************/
	public byte[] statusOrder(){
		String order = "[001QCS]";
		byte[] getorder = bsc.stringToByte(order);
		return getorder;
	}
	
	/*************************************************
	Function: buttonstatusOrder 
	Description: ���ɲ�ѯ��尴ť״ָ̬��
	Input: ��
	Return: ��ѯ��尴ť״ָ̬��
	*************************************************/
	public byte[] buttonstatusOrder(){
		String order = "[dddQKS]";
		byte[] getorder = bsc.stringToByte(order);
		return getorder;
	}
	
	/*************************************************
	Function: statusOrder 
	Description: ��·״̬���ݽ���
	other:����11����������أ�����[001-T0-O1]
	         12��������������������[ddd-LCKKEY]
	         14����·����״̬��ѯ������[ddd-ssssssss]
	         13��15����ѯ���������ر�״̬
	         18�����е�·�Ŀ���أ�����[001-T0-C12345678]
	Input: byte
	Return: ��·״̬����
	*************************************************/
	public String getStatusResult(byte[] b){
	String	froData = new String(b);
	int n=0;
	for(int i=0;i<froData.length();i++){
		char m=froData.charAt(i);
		if(m=="[".toCharArray()[0]){
			n=i;
		}
		if(m=="]".toCharArray()[0]){
			froData = froData.substring(n, i+1);
			System.out.println(froData);
			System.out.println("ddddddddd");;
		}
		
	}
	
	
		String str = new String(froData);
		String result = "";
		if(str.length() == 11){
			if(str.substring(str.length()-3, str.length()-2).equalsIgnoreCase("C")){
				result = str.substring(str.length()-2, str.length()-1) + "|off,";
			}else if(str.substring(str.length()-3, str.length()-2).equalsIgnoreCase("O")){
				result = str.substring(str.length()-2, str.length()-1) + "|on,";
			}
		}else if(str.length() == 12){
			if(str.substring(str.length()-7, str.length()-1).equalsIgnoreCase("LCKKEY")){
				result = "button|off,";
			}else if(str.substring(str.length()-7, str.length()-1).equalsIgnoreCase("UNLKEY")){
				result = "button|on,";
			}
		}else if(str.length() == 14){
			for(int i=0;i<8;i++){
				if(str.substring(i+5,i+6).equalsIgnoreCase("C")){
					result = result + (i + 1) + "|off,";
				}else if(str.substring(i+5,i+6).equalsIgnoreCase("O")){
					result = result + (i + 1) + "|on,";
				}
			}
		}else if(str.length() == 18){
			if(str.substring(8, 9).equalsIgnoreCase("C")){
				for(int i=1;i<9;i++){
					result = result + i + "|off,";
				}
			}else if(str.substring(8, 9).equalsIgnoreCase("O")){
				for(int i=1;i<9;i++){
					result = result + i + "|on,";
				}
			}
		}else if(str.length() == 13){
			result = "button|off,";
		}else if(str.length() == 15){
			result = "button|on,";
		}
		return result.substring(0, result.length()-1);
	}
}
