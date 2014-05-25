package com.fro.order;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.SafeArray;
import com.jacob.com.Variant;

/*************************************************
Copyright (C), 2013, FRO Tech., Ltd.
File name: GuardControl // �ļ���
Author: Chan Version: V1.0 Date: 2013-02-21 // ���ߡ��汾���������
Description: �Ž�����ָ������ɣ��Ž�״̬��ȡ���ϴ�Ȩ�ޣ�ɾ��Ȩ�ޣ���ѯȨ���б�
// ������ϸ˵���˳����ļ���ɵ���Ҫ���ܣ�������ģ��
// �����Ľӿڣ����ֵ��ȡֵ��Χ�����弰������Ŀ�
// �ơ�˳�򡢶����������ȹ�ϵ

Others: ��// �������ݵ�˵��
Function List: // ��Ҫ�����б�ÿ����¼Ӧ���������������ܼ�Ҫ˵��
1. sendOrder
�����Ž�����ָ��
2��getStatus
��ȡ�Ž�״̬
History: // �޸���ʷ��¼�б�ÿ���޸ļ�¼Ӧ�����޸����ڡ��޸�
// �߼��޸����ݼ���
1. Date:
Author:
Modification:
2. ...
*************************************************/

public class GuardControl {
	
	private ActiveXComponent xl;
	private Dispatch dispatch;
	private String[] strin;
	private Socket socket;
	private InputStream socketReader;
	private OutputStream socketWriter;
	private String IP;
	private int port;
	private boolean flag_send;
	
	/*************************************************
	Function: GuardControl 
	Description: ���캯��
	*************************************************/
//	public GuardControl(){
//		 ComThread.InitSTA(); //��ʼ��com���߳�
//		 xl = new ActiveXComponent("ECardDerviceSDKMJ.pubOpterDerviceMJ");
//		 dispatch = xl.getObject();  
//		 strin = new String[101];
//		 formatArray(strin);
//		 flag_send = false;
//	}
	
	public GuardControl(String IP, int port){
		 ComThread.InitSTA(); //��ʼ��com���߳�
		 xl = new ActiveXComponent("ECardDerviceSDKMJ.pubOpterDerviceMJ");
		 dispatch = xl.getObject();  
		 strin = new String[101];
		 formatArray(strin);
		 this.IP = IP;
		 this.port = port;
		 flag_send = false;
	
	}
	
	/*************************************************
	Function: getSendFlag 
	Description: ��ȡsendOrder���
	*************************************************/
	public boolean getSendFlag(){
		return flag_send;
	}
	
	/*************************************************
	Function: formatArray 
	Description: ��ʼ������
	*************************************************/
	private void formatArray(String[] str){
		for(int i=0;i<str.length;i++){
			str[i] = "";
		}
	}

	/*************************************************
	Function: sendOrder 
	Description: ���Ϳ���ָ����ж�Ȩ�ޣ�ֱ�ӷ��Ϳ���ָ��
	Calls: ��
	Called By: ��
	Table Accessed: ��
	Table Updated: ��
	Input: jobNum,���Ź���
	Output: ��
	Return: ��
	Others: ��
	*************************************************/
    public byte[] openOrder(String doorNum){
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		strin[0] = "01";
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","1A",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
    }

	/*************************************************
	Function: getStatus 
	Description: ��ȡ�Ž�״̬
	Calls: ��
	Called By: ��
	Table Accessed: ��
	Table Updated: ��
	Input: ��
	Output: ��
	Return: ��
	Others: ��
	*************************************************/
    public String getStatus(){
    	return null;
    }
    
	/*************************************************
	Function: getLimit 
	Description: ��ȡ�Ž�Ȩ���б�
	Calls: ��
	Called By: ��
	Table Accessed: ��
	Table Updated: ��
	Input: ��
	Output: ��
	Return: ��
	Others: ��
	*************************************************/
    public String getLimit(){
    	return null;
    }
    
	/*************************************************
	Function: addLimit 
	Description: �����Ž�Ȩ��
	Input: 
	doorNum �����к�
	jobID Ա�����
	jobNum��Ȩ�޹���
	timeNum ��Чʱ����
	password ����
	name Ա������
	       
	Output: ��
	Return: ��
	*************************************************/
    public byte[] addLimit(String doorNum, String jobID, 
    		String jobNum, String timeNum, String password, String name){
    	
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		
		strin[0] = jobID;
		strin[1] = jobNum;
		strin[2] = "01";
		strin[3] = "2010-01-01";
		strin[4] = "2030-01-01";
		strin[5] = timeNum;
		strin[6] = password;
		strin[7] = name;
		strin[8] = "0";
		
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","1D",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
    	
    }
    
	/*************************************************
	Function: delLimit 
	Description: ɾ���Ž�Ȩ��
	Calls: ��
	Called By: ��
	Table Accessed: ��
	Table Updated: ��
	Input: jobNum��Ȩ�޹��� ,doorNum,�Ž����к�
	Output: ��
	Return: ��
	Others: ��
	*************************************************/
    public byte[] delLimit(String jobNum ,String doorNum){

    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		strin[0] = jobNum;
		strin[1] = "01";
		strin[2] = "0";
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","1E",doorNum,var);
    	order = stringToByte(variant.toString());
    	System.out.println(variant);
    	ComThread.Release();//�ͷ������Դ
    	return order;
    
    }
    
	/*************************************************
	Function: delLimitAll 
	Description: ɾ�������Ž�Ȩ��
	Input: doorNum,�����к�
	Return: ��Ӧָ��
	*************************************************/
    public byte[] delLimitAll(String doorNum){
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","2A",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
    }
    
	/*************************************************
	Function: reBoot 
	Description: �����Ž�
	Input: doorNum,�����к�
	Return: ��Ӧָ��
	*************************************************/
    public byte[] reBoot(String doorNum){
    	
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","10",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
    	
    }
    
	/*************************************************
	Function: uploadTimingOfOpenDoor 
	Description: �ϴ���ʱ����ʱ��
	Input: ��һ�����գ�ÿ������ʱ�Σ�ÿ��ʱ�ηֱ��忪ʼ�ͽ���ʱ�䣬��ʽ24Сʱ"hh:mm"������"08:01"
	       str���鳤��Ϊ 42 ����
	Output: ��
	Return: ��ʱ����ʱ��ָ��
	Others: ��
	*************************************************/
    public byte[] uploadTimingOfOpenDoor(String doorNum, String[] str){
    	
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		strin[0] = "01";
		for(int i=0;i<str.length;i++){
			strin[i+1] = str[i];
		}
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","12",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
    	
    }
    
	/*************************************************
	Function: uploadBaseTime 
	Description: �ϴ�����ʱ���
	Input: str���鳤��16�����ֶ�������ȡֵ��Χ���£�
	1������ʱ�����ţ� 2~99
	2����ʱ���Ƿ����ƽ�����һ�Σ� 0������1����
	3��ʱ��1��ʼʱ�䣺��:��08:01��
	4��ʱ��1����ʱ�䣺��:��08:01��
	5��ʱ��2��ʼʱ�䣺��:��08:01��
	6��ʱ��2����ʱ�䣺��:��08:01��
	7��ʱ��3��ʼʱ�䣺��:��08:01��
	8��ʱ��3����ʱ�䣺��:��08:01��
	9��ǰ����ʱ����ͨ�з�ʽ��0(���뿪��)1(ˢ������)2(ˢ�������뿪��)3(ˢ�������뿪��)
	10��ʱ��4��ʼʱ�䣺��:��08:01��
	11��ʱ��4����ʱ�䣺��:��08:01��
	12��ʱ��5��ʼʱ�䣺��:��08:01��
	13��ʱ��5����ʱ�䣺��:��08:01��
	14��ʱ��6��ʼʱ�䣺��:��08:01��
	15��ʱ��6����ʱ�䣺��:��08:01��
	16��������ʱ����ͨ�з�ʽ��0(���뿪��)1(ˢ������)2(ˢ�������뿪��)3(ˢ�������뿪��)

	Output: ��
	Return: ��ʱ����ʱ��ָ��
	Others: ��
	*************************************************/
    public byte[] uploadBaseTime(String doorNum, String[] str){
    	
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		for(int i=0;i<str.length;i++){
			strin[i] = str[i];
		}
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","14",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
    	
    }
    
	/*************************************************
	Function: uploadTimeGroup 
	Description: �ϴ�ʱ����
	Input: str���鳤��8�����ֶ�������ȡֵ��Χ���£�
	1��ʱ�����ţ� 2~99
	2�����ջ���ʱ��α�ţ� 2~99
	3����һ����ʱ��α�ţ� 2~99
	4���ܶ�����ʱ��α�ţ� 2~99
	5����������ʱ��α�ţ� 2~99
	6�����Ļ���ʱ��α�ţ� 2~99
	7���������ʱ��α�ţ� 2~99
	8����������ʱ��α�ţ� 2~99

	Output: ��
	Return: ��ʱ����ʱ��ָ��
	Others: ��
	*************************************************/
    public byte[] uploadTimeGroup(String doorNum, String[] str){
    	
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		for(int i=0;i<str.length;i++){
			strin[i] = str[i];
		}
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","13",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
    	
    }
    
	/*************************************************
	Function: synchronizationTime 
	Description: ͬ��ʱ��
	Input: 
	Output: ��
	Return: ͬ��ʱ��ָ��
	Others: ��
	*************************************************/
    public byte[] synchronizationTime(String doorNum){
    	
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","19",doorNum,var);
    	order = stringToByte(variant.toString());
    	System.out.println(variant);
    	ComThread.Release();//�ͷ������Դ
    	return order;
    	
    }
    
	/*************************************************
	Function: stringToByte 
	Description: String����ת��Ϊbyte[]���ͣ�������10���Ƶ�16���Ƶ����㡣
	                                  ���磬"16"ת��Ϊ"0x16";
	Input: in,��ת��������
	Output: ��
	Return: ת�����byte[]
	Others: ��
	*************************************************/
    public byte[] stringToByte(String in) {
    	
    	byte[] b = new byte[in.length()/2];
    	int j=0;
    	String strin[] = truncateString(in);
    	
    	in = "";
    	for (int i=0; i<strin.length; i++){
    		in = in + strin[i];
    	}
    	StringBuffer buf = new StringBuffer(2);
    	for (int i=0; i<in.length(); i++, j++) {
    	buf.insert(0, in.charAt(i));
    	buf.insert(1, in.charAt(i+1));
    	int t = Integer.parseInt(buf.toString(),16);
    	b[j] = (byte)t;
    	i++;
    	buf.delete(0,2);
    	}
    	return b;
    	
    }
    
	/*************************************************
	Function: byteToString 
	Description: byte[]����ת��ΪString���ͣ�������10���Ƶ�16���Ƶ����㡣
	                                  ���磬"0x16"ת��Ϊ"16";
	Input: in,��ת��������
	Output: ��
	Return: ת�����String
	Others: ��
	*************************************************/
    public String byteToString(byte[] in) {
    	
    	String b = "";
    	for(int i=0;i<in.length;i++){
        	b += Integer.toHexString(((in[i]) & 0x000000FF) | 0xFFFFFF00).substring(6);
    	}
    	return b;
    	
    }
    
	/*************************************************
	Function: truncateString 
	Description: �ȳ���ȡ�ַ�����2���ַ����һ���µ��ַ���
	Input: in,���ȡ������
	Output: ��
	Return: ת�����String[]
	Others: ��
	*************************************************/
	public String[] truncateString(String in) {
		
		String[] str = new String[in.length()/2]; 
		for(int i=0;i<in.length()/2;i++){
			str[i] = in.substring(i*2, i*2+2);
		}
		return str;
		
	}
	
	/*************************************************
	Function: sendOrder 
	Description: ��Ŀ��IP����ָ��
	Input: in,���͵�ָ��
	Output: �ı�flag_sendֵ
	*************************************************/
	public void sendOrder(byte[] in){
		flag_send=false;
		try {
			socket = new Socket(IP,port);
			socketReader = socket.getInputStream();
			socketWriter = socket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int flag = 0;
		 try{
			 Thread.sleep(1000);
			 socketWriter.write(in);
			 socketWriter.flush();
			 //ˢ���������ʹServer�����յ����ַ���
			 
			 while(socketReader.available()==0&&flag<100){
				 Thread.sleep(100);
				 flag ++;
			 }
			 byte[] readline2 = new byte[socketReader.available()];
			 if(readline2.length>10){
				 flag_send = true;
			 }
		 }catch(Exception e){
			 e.printStackTrace();
			 try{
				 socket.close(); //�ر�Socket
				 socketWriter.close();
				 socketReader.close();
			 }catch (Exception e1) {
			}
		 }
	}
	
	/*************************************************
	Function: formatTime 
	Description: ��ʽ����ʱ����ʱ���飬ע�⴫���������һ���貹һ��ʱ��Σ�����0,00:00,00:00�����򽫶�ʧ���һ��ʱ���
	Input: in,���͵�ָ��
	Output: �ı�flag_sendֵ
	*************************************************/
	public String[] formatTime(String[][] str){
		String[] resut = new String[42];
		for(int i=0;i<resut.length;i++){
			resut[i] = "00:00";
		}
		String starttime = "";
		String endtime = "";
		int flag = 0;
		SimpleDateFormat dfs = new SimpleDateFormat("HH:mm");
		try {		
			for(int i=0;i<str.length-1;i++){
				if(str[i][0].equals("1") && flag<6){
					if(starttime.equals("")){
						starttime = str[i][1];
					}
					java.util.Date begin = dfs.parse(str[i][2]);
					java.util.Date end = dfs.parse(str[i+1][1]);
					long between=(end.getTime()-begin.getTime())/60000;	
					if(!str[i][0].equals(str[i+1][0])){//��ͬһ�����
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}else if(between>30){
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}
				}
				else if(str[i][0].equals("2") && flag<12){
					for(int j=flag;j<6;j++){
						resut[flag] = "00:00";
						flag = 6;
					}
					if(starttime.equals("")){
						starttime = str[i][1];
					}
					java.util.Date begin = dfs.parse(str[i][2]);
					java.util.Date end = dfs.parse(str[i+1][1]);
					long between=(end.getTime()-begin.getTime())/60000;	
					if(!str[i][0].equals(str[i+1][0])){//��ͬһ�����
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}else if(between>30){
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}
				}
				else if(str[i][0].equals("3") && flag<18){
					for(int j=flag;j<12;j++){
						resut[flag] = "00:00";
						flag = 12;
					}
					if(starttime.equals("")){
						starttime = str[i][1];
					}
					java.util.Date begin = dfs.parse(str[i][2]);
					java.util.Date end = dfs.parse(str[i+1][1]);
					long between=(end.getTime()-begin.getTime())/60000;	
					if(!str[i][0].equals(str[i+1][0])){//��ͬһ�����
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}else if(between>30){
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}
				}
				else if(str[i][0].equals("4") && flag<24){
					for(int j=flag;j<18;j++){
						resut[flag] = "00:00";
						flag = 18;
					}
					if(starttime.equals("")){
						starttime = str[i][1];
					}
					java.util.Date begin = dfs.parse(str[i][2]);
					java.util.Date end = dfs.parse(str[i+1][1]);
					long between=(end.getTime()-begin.getTime())/60000;	
					if(!str[i][0].equals(str[i+1][0])){//��ͬһ�����
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}else if(between>30){
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}
				}
				else if(str[i][0].equals("5") && flag<30){
					for(int j=flag;j<24;j++){
						resut[flag] = "00:00";
						flag = 24;
					}
					if(starttime.equals("")){
						starttime = str[i][1];
					}
					java.util.Date begin = dfs.parse(str[i][2]);
					java.util.Date end = dfs.parse(str[i+1][1]);
					long between=(end.getTime()-begin.getTime())/60000;	
					if(!str[i][0].equals(str[i+1][0])){//��ͬһ�����
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}else if(between>30){
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}
				}
				else if(str[i][0].equals("6") && flag<36){
					for(int j=flag;j<30;j++){
						resut[flag] = "00:00";
						flag = 30;
					}
					if(starttime.equals("")){
						starttime = str[i][1];
					}
					java.util.Date begin = dfs.parse(str[i][2]);
					java.util.Date end = dfs.parse(str[i+1][1]);
					long between=(end.getTime()-begin.getTime())/60000;	
					if(!str[i][0].equals(str[i+1][0])){//��ͬһ�����
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}else if(between>30){
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}
				}
				else if(str[i][0].equals("7") && flag<42){
					for(int j=flag;j<36;j++){
						resut[flag] = "00:00";
						flag = 36;
					}
					if(starttime.equals("")){
						starttime = str[i][1];
					}
					java.util.Date begin = dfs.parse(str[i][2]);
					java.util.Date end = dfs.parse(str[i+1][1]);
					long between=(end.getTime()-begin.getTime())/60000;	
					if(!str[i][0].equals(str[i+1][0])){//��ͬһ�����
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}else if(between>30){
						resut[flag] = starttime;
						resut[flag+1] = str[i][2];
						starttime = "";
						flag = flag + 2;
					}
				}
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resut;
	}
	
	/*************************************************
	Function: emergencyOrder 
	Description: ����������ָ��
	Input: doorNum,�Ž����к�
	Output: ��
	Return: ����������ָ��
	Others: ��
	*************************************************/
	public byte[] emergencyOrder(String doorNum) {
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		strin[0] = "01";
		strin[1] = "1";
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","39",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
	} 
	
	/*************************************************
	Function: cancelEmergencyOrder 
	Description: ȡ������������ָ��
	Input: doorNum,�Ž����к�
	Output: ��
	Return: ȡ������������ָ��
	Others: ��
	*************************************************/
	public byte[] cancelEmergencyOrder(String doorNum) {
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		strin[0] = "01";
		strin[1] = "3";
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","39",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
	} 
	
	
	/*************************************************
	Function: initialize 
	Description: ��ʼ���Ž�
	Input: doorNum,�����к�
	Return: ��Ӧָ��
	*************************************************/
    public byte[] initialize(String doorNum){
    	
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","1F",doorNum,var);
    	order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
    	
    }
	
	
	
	/*************************************************
	Function: doorStatus 
	Description: ��ѯ�Ž�״̬
	Input: doorNum,�Ž����к�
	Output: ��
	Return: �Ž�״̬���
	Others: ��
	*************************************************/
	public String doorStatus(String doorNum) {
    	String order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","18",doorNum,var);//��ȡ�豸�����Ϣ
    	order = variant.toString();
    	byte[] temp = stringToByte(variant.toString());
		flag_send=false;
		try {
			socket = new Socket(IP,port);
			socketReader = socket.getInputStream();
			socketWriter = socket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return doorNum+"|lose";
		} catch (IOException e) {
			e.printStackTrace();
			return doorNum+"|lose";
		}
		int flag = 0;
		 try{
			 Thread.sleep(1000);
			 socketWriter.write(temp);
			 socketWriter.flush();
			 temp = null;
			 //ˢ���������ʹServer�����յ����ַ���
			 
			 while(socketReader.available()==0&&flag<100){
				 Thread.sleep(50);
				 flag ++;
			 }
			 byte[] readline2 = new byte[socketReader.available()];
			 socketReader.read(readline2);
			 if(readline2.length>10){
				 flag_send = true;
				 temp = readline2;
			 }
			 order = byteToString(temp);
			 Variant recordMax = Dispatch.call(dispatch,"MJGetCountRecordsFromRunInfo",order);//��ȡ�豸����¼��Ϣ
		     formatArray(strin);
			 sa = new SafeArray(Variant.VariantString,101);
			 var = new Variant();
			 strin[0] = recordMax.toString();
			 sa.fromStringArray(strin);
			 var.putSafeArrayRef(sa);// Passing arrays trackback address  
			 variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","20",doorNum,var);//��ȡ�豸�����Ϣ
		     order = variant.toString();
		     temp = stringToByte(order);
			 socketWriter.write(temp);
			 socketWriter.flush();//ˢ���������ʹServer�����յ����ַ���
			 temp = null;
			 flag = 0;//����flag���㳬ʱʱ��
			 while(socketReader.available()==0&&flag<100){
				 Thread.sleep(50);
				 flag ++;
			 }
			 readline2 = new byte[socketReader.available()];
			 socketReader.read(readline2);
			 if(readline2.length>10){
				 flag_send = true;
				 temp = readline2;
			 }
			 order = byteToString(temp);
			 Variant status = Dispatch.call(dispatch,"MJGetRecordStatusRunInfo34OR26",order,"0");//��ȡ�豸����¼��Ϣ
			 order = status.toString();
		 }catch(Exception e){
			 try{
				 socket.close(); //�ر�Socket
				 socketWriter.close();
				 socketReader.close();
			 }catch (Exception e1) {
			}
		 }finally{
			 try{
				 socket.close(); //�ر�Socket
				 socketWriter.close();
				 socketReader.close();
			 }catch (Exception e1) {
			}
		 }
		 //����������ж�״̬
		 if(order.equals("0000")){
			 order = doorNum+"|off";
		 }else if(order.equals("0001")){
			 order = doorNum+"|on";
		 }else{
			 order = doorNum+"|lose";
		 }
		 ComThread.Release();//�ͷ������Դ
    	return order;
	} 
	
	/************************************************
	��ȡ���һ����Ϣ  ���ҽ��н���
	ParseSingleRecord(0):���Ż��ű��
	ParseSingleRecord(1):ͨ����־λ
	ParseSingleRecord(5)��ͨ������,1:����ˢ��(����ͨ��δͨ��),2:����(�������Կ��š���ť���š��Լ���������չ����)
	
	********************************/
	public String[] getCardNo(String doorNum) {
		String[] str=new String[3];
    	String order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","18",doorNum,var);//��ȡ�豸�����Ϣ
    	order = variant.toString();
    	byte[] temp = stringToByte(variant.toString());
		flag_send=false;
		try {
			socket = new Socket(IP,port);
			socketReader = socket.getInputStream();
			socketWriter = socket.getOutputStream();
		} catch (UnknownHostException e) {
			e.printStackTrace();
			return str=new String[1];
		} catch (IOException e) {
			e.printStackTrace();
			return str=new String[1];
		}
		int flag = 0;
		 try{
			 Thread.sleep(1000);
			 socketWriter.write(temp);
			 socketWriter.flush();
			 temp = null;
			 //ˢ���������ʹServer�����յ����ַ���
			 
			 while(socketReader.available()==0&&flag<100){
				 Thread.sleep(50);
				 flag ++;
			 }
			 byte[] readline2 = new byte[socketReader.available()];
			 socketReader.read(readline2);
			 if(readline2.length>10){
				 flag_send = true;
				 temp = readline2;
			 }
			 order = byteToString(temp);
			 Variant recordMax = Dispatch.call(dispatch,"MJGetCountRecordsFromRunInfo",order);//��ȡ�豸����¼��Ϣ
		     formatArray(strin);
			 sa = new SafeArray(Variant.VariantString,101);
			 var = new Variant();
			 strin[0] = recordMax.toString();
			 sa.fromStringArray(strin);
			 var.putSafeArrayRef(sa);// Passing arrays trackback address  
			 variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","20",doorNum,var);//��ȡ�豸�����Ϣ
		     order = variant.toString();
		     temp = stringToByte(order);
			 socketWriter.write(temp);
			 socketWriter.flush();//ˢ���������ʹServer�����յ����ַ���
			 temp = null;
			 flag = 0;//����flag���㳬ʱʱ��
			 while(socketReader.available()==0&&flag<100){
				 Thread.sleep(50);
				 flag ++;
			 }
			 readline2 = new byte[socketReader.available()];
			 socketReader.read(readline2);
			 if(readline2.length>10){
				 flag_send = true;
				 temp = readline2;
			 }
			 order = byteToString(temp);
			 Variant status = Dispatch.call(dispatch,"MJGetRecordRunInfo34OR26",order,"0");//��ȡ  �������¼����ϸ��Ϣ
			 String numberStr=status.toString();
			 System.out.println("���ص�"+numberStr+"----------------------------");
			 
			 String startStr=numberStr.substring(0, 7);
			 if(!startStr.equals("0000000")){
				 str[0]=numberStr.substring(0, 8);
				 str[1]=numberStr.substring(8,9);
				 str[2]=numberStr.substring(10, 14)+"-"+numberStr.substring(14,16)+"-"+numberStr.substring(16, 18)+" "+numberStr.substring(18, 20)+":"+numberStr.substring(20, 22)+":"+numberStr.substring(22, 24);
				 System.out.println(str[0]);
				 System.out.println(str[2]+"---------------------");
			 }else{
				 System.out.println("�������Զ�̿��š���");
					 str=new String[1];
					str[0]="�������Զ�̿��š���";
					return str;
			 }
			 
			 
			 
		 }catch(Exception e){
			 try{
				 socket.close(); //�ر�Socket
				 socketWriter.close();
				 socketReader.close();
			 }catch (Exception e1) {
			}
		 }finally{
			 try{
				 socket.close(); //�ر�Socket
				 socketWriter.close();
				 socketReader.close();
			 }catch (Exception e1) {
			}
		 }
		 
		 ComThread.Release();//�ͷ������Դ
    	return str;
	} 
	
	
	
	
	
	
	
	
	
	
	/*************************************************
	Function: modifyPassword 
	Description: �޸��Ž�����
	Input: doorNum,�Ž����кţ�password������
	Output: ��
	Return: ָ��
	Others: ��
	*************************************************/
	public byte[] modifyPassword(String doorNum,String password){
    	byte[] order = null;
    	formatArray(strin);
		SafeArray sa = new SafeArray(Variant.VariantString,101);
		Variant var = new Variant();
		strin[0] = password;
		sa.fromStringArray(strin);
		var.putSafeArrayRef(sa);// Passing arrays trackback address  
		Variant variant = Dispatch.call(dispatch,"CreateBstrFuncDataByArray","25",doorNum,var);
    	System.out.println(variant.toString());
		order = stringToByte(variant.toString());
    	ComThread.Release();//�ͷ������Դ
    	return order;
	}
	
	
	/**********test**********/
	public static void main(String[] args) {
		
//		System.out.println(System.getProperty("java.library.path"));
//		String[] str = new String[42];
//		for(int i=0;i<42;i++){
//			str[i] = "00:00";
//		}
//		str[0] = "08:00";
//		str[1] = "12:00";
//		GuardControl gc = new GuardControl("192.168.0.11",4001);
//		byte[] b = gc.uploadTimingOfOpenDoor("0144596",str);
//		gc.sendOrder(b);
//		System.out.println("OK");
//		System.out.println(gc.getSendFlag());
		/*********ʱ�����*****************
		SimpleDateFormat dfs = new SimpleDateFormat("HH:mm");
		try {
			java.util.Date begin=dfs.parse("11:30");
			java.util.Date end = dfs.parse("10:31");
			long between=(end.getTime()-begin.getTime())/60000;
			System.out.println(between);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String[][] strd = new String[][]{{"1","08:00","09:00"},{"1","09:10","10:00"},{"1","13:10","15:00"},{"2","13:10","15:00"},{"3","13:10","15:00"},{"3","16:10","19:00"},{"0","00:00","00:00"},{"0","00:00","00:00"},{"0","00:00","00:00"},{"0","00:00","00:00"},{"0","00:00","00:00"}};
		GuardControl gc = new GuardControl();
		String[] result = gc.formatTime(strd);
		System.out.println("OK");
		***************************************/
		
		GuardControl doorC = new GuardControl("192.168.0.111",4001);
	//	System.out.println(gc.modifyPassword("0151917","123456"));
	//	doorC.sendOrder(doorC.addLimit("0151917", "2392482622", "2392482622", "1","000000","dd"));
		//doorC.getCardNo("0147886");

		
//		gc.sendOrder(gc.cancelEmergencyOrder("0151917"));
	//	System.out.println(gc.flag_send);
//		String str = gc.doorStatus("0144596");
//		System.out.println(str);
		doorC.sendOrder(doorC.cancelEmergencyOrder("0147886"));

	}
}
