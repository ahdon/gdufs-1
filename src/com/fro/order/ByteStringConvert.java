package com.fro.order;
/**
Copyright (C), 2013, FRO Tech., Ltd.
File name: ByteStringConvert 
Author: Chan Version: V1.0 Date: 2013-02-21 // ���ߡ��汾���������
Description: byte���ͺ�String���ͻ���,ASCII�롣

Others: ��// �������ݵ�˵��
Function List: // ��Ҫ�����б�ÿ����¼Ӧ���������������ܼ�Ҫ˵��
1. byteToString
byteת����String
2��stringToByte
String ת���� byte

History: // �޸���ʷ��¼�б�ÿ���޸ļ�¼Ӧ�����޸����ڡ��޸�
// �߼��޸����ݼ���
1. Date:
Author:
Modification:
2. ...
*/
public class ByteStringConvert {

	public String byteToString(byte[] b){
		return new String(b);
	}
	
	public byte[] stringToByte(String s){
		return s.getBytes();
	}
}
