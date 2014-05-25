package  com.fro.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.fro.entity.DeviceInfo;
import com.fro.entity.SessionParameter;
import com.fro.entity.UserInfo;
import com.fro.order.GuardControl;
import com.fro.order.SMPSControl;
import com.fro.order.SMPSltgkControl;
import com.fro.order.SecuritySystemControl;
import com.fro.service.LabService;
import com.fro.service.LoginService;
import com.fro.service.impl.LabServiceImpl;
import com.fro.service.impl.LoginServiceImpl;

public class LabParameter {

	private static Log log=LogFactory.getLog(LabParameter.class);
	
	public String labID;
	//������������
	public String NavigateMode;
	
	public String operateTime;
	
	
	
	public String operate;
	
	//�齭 ��
	public String rfid;
	public String endTime;
	
	public GuardControl doorC;
	public boolean isBetweent=false;
	
	public List<AutoSocketSendOrder> ass;
	
	private List<List<SessionParameter>>  sps;
	LabService lab=new LabServiceImpl();
	
	public void AutoNavigation(){
		ass = new ArrayList<AutoSocketSendOrder>();
		SMPSControl smpsc = new SMPSControl();
		List<byte[]> orderList;
		List<SessionParameter> sp;
		AutoSocketSendOrder as;
		DeviceInfo di;
		if(operate.equals("OPEN")||operate.equals("CLOSE")){
			try{
			sps=lab.getSPs("�ƹ�",labID);
			orderList=new ArrayList<byte[]>();
			sp=sps.get(0);
			di=lab.getByDeviceId(sp.get(0).getSessionParameterPK().getDeviceId());
			for (SessionParameter sessionParameter : sp) {
				if(operate.equals("OPEN")){
					orderList.add(smpsc.onOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));
					
				}else{
					orderList.add(smpsc.offOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));
				}
			}
		
			as= new AutoSocketSendOrder(di.getDeviceIp(), di.getPort(), orderList);
			 ass.add(as);
			System.out.println(ass.size());
	
			}catch(Exception e){e.printStackTrace();}
			
			try{
			sps=lab.getSPs("�յ�",labID);
			sp=sps.get(0);
			di=lab.getByDeviceId(sp.get(0).getSessionParameterPK().getDeviceId());
			orderList=new ArrayList<byte[]>();
			for (SessionParameter sessionParameter : sp) {
				if(operate.equals("OPEN")){
					orderList.add(smpsc.onOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));
				}else{
					orderList.add(smpsc.offOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));
				}
			}
			as = new AutoSocketSendOrder(di.getDeviceIp(), di.getPort(), orderList);
			
			ass.add(as);
			}catch(Exception e){}
			
			try{
			sps=lab.getSPs("�Ž�",labID);
			orderList=new ArrayList<byte[]>();
			sp=sps.get(0);
			di=lab.getByDeviceId(sp.get(0).getSessionParameterPK().getDeviceId());
			ArrayList<byte[]>	orderLists=new ArrayList<byte[]>();
			for (SessionParameter sessionParameter : sp) {
				if(labID.equalsIgnoreCase("C507")){
					if(operate.equals("OPEN")){
						orderList.add(smpsc.onOrder(7));
						orderList.add(smpsc.onOrder(8));
						GuardControl smpsc2 = new GuardControl(di.getDeviceIp(), di.getPort());
					
					// 2  ���ڸĶ�	
						orderLists.add(smpsc2.emergencyOrder(sessionParameter.getSessionParameterPK().getSessionValue().trim()));

					}else{
						//��ȡ����7 8·ָʾ��
						orderList.add(smpsc.offOrder(7));
						orderList.add(smpsc.offOrder(8));
						//����ʱ�䵽�˺� ȡ����������  
						GuardControl sm = new GuardControl(di.getDeviceIp(), di.getPort());
					
						// 2���ڸĶ�
						orderLists.add(sm.cancelEmergencyOrder(sessionParameter.getSessionParameterPK().getSessionValue().trim()));
						
					}
				}else{
					if(operate.equals("OPEN")){
						GuardControl smpsc2 = new GuardControl(di.getDeviceIp(), di.getPort());
					
					// 2  ���ڸĶ�	
						orderLists.add(smpsc2.emergencyOrder(sessionParameter.getSessionParameterPK().getSessionValue().trim()));

					}else{
						//����ʱ�䵽�˺� ȡ����������  
						GuardControl sm = new GuardControl(di.getDeviceIp(), di.getPort());
					
						// 2���ڸĶ�
						orderLists.add(sm.cancelEmergencyOrder(sessionParameter.getSessionParameterPK().getSessionValue().trim()));
						
					}
				}
			}
			as=new AutoSocketSendOrder(di.getDeviceIp(), di.getPort(),orderLists);
			ass.add(as);
			
			// 2 �����޸�   �Ž��͵��ǵƹ�IP  ��ȡ�ƹ��IP  �� Port  ����
			
			
			DeviceInfo diM=lab.getByDeviceId(sp.get(0).getSessionParameterPK().getDeviceId());
			DeviceInfo diD=lab.getByDeviceId("�ƹ�",diM.getLabId());
			as = new AutoSocketSendOrder(diD.getDeviceIp(), diD.getPort(), orderList);
			
			
			ass.add(as);
			}catch(Exception e){}
			
			try{
			sps=lab.getSPs("����",labID);
			SecuritySystemControl ssc = new SecuritySystemControl();
			orderList=new ArrayList<byte[]>();
			sp=sps.get(0);
			di=lab.getByDeviceId(sp.get(0).getSessionParameterPK().getDeviceId());
			if(operate.equals("OPEN")){
				orderList.add(ssc.offOrder());
			}else{
				orderList.add(ssc.onOrder());
			}
			as = new AutoSocketSendOrder(di.getDeviceIp(), di.getPort(), orderList);
			ass.add(as);
			}catch(Exception e){}
			
			try{
			sps=lab.getSPs("�̵���",labID);
			orderList=new ArrayList<byte[]>();
			SMPSltgkControl smpsltgc = new SMPSltgkControl();
			sp=sps.get(0);
			di=lab.getByDeviceId(sp.get(0).getSessionParameterPK().getDeviceId());
			for (SessionParameter sessionParameter : sp) {
				if(labID.equalsIgnoreCase("C507")){
					if(operate.equals("OPEN")){
						orderList.add(smpsltgc.offOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));
					}else{
						orderList.add(smpsltgc.onOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));
					}
					}else{
					if(operate.equals("OPEN")){
						orderList.add(smpsc.onOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));
					}else{
						orderList.add(smpsc.offOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));
					}
				}
			}
			as = new AutoSocketSendOrder(di.getDeviceIp(), di.getPort(), orderList);
			ass.add(as);
			}catch(Exception e){}
			int i = 0;
			while(i<30){
				i++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			for (AutoSocketSendOrder aso : ass) {
				if(!aso.send_flag){
					aso.run();
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
					}
				}
			}
			}
		}else if(operate.equals("CANCEL")){
			log.info("����ȡ��");
		}else if(operate.equals("TIMER")){
			log.info("ִ���ϴ��Ž�Ȩ��------------------------------");
			//����Ӧ���Ž��ϴ��û�����Ȩ��
			doorC=GetCuardControl.getGuardControl(labID);
			final String doorNum=lab.getDoorNumByLabId(labID);
			doorC.sendOrder(doorC.addLimit(doorNum.trim(), rfid, rfid, "1","000000",rfid));	
		
			
			//�����������������Ž�ļ�¼  ���ҽ����Ž�����һ����¼����ƥ��
			new Thread(new Runnable() {
				public void run() {
					boolean runFlag=true;
					String[] str;
					
					
					
					while(runFlag){
						log.info("ѭ������Ƿ�����ˢ��-----------------"+doorNum);
						doorC=GetCuardControl.getGuardControl(labID);
						str=doorC.getCardNo(doorNum);
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e2) {
						}
						boolean  isCommon=false;
						if(str.length!=1){
							isBetweent=false;
							try {
								java.util.Date start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(operateTime);
								java.util.Date betweenTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(str[2]);
								java.util.Date end =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
								long between1=(start.getTime()-betweenTime.getTime())/60000;
								long between2=(end.getTime()-betweenTime.getTime())/60000;
								
								//����Ҫ�ж��Ƿ��ǹ���Ա  1.�ȸ���rfid����userId   2�ٸ���userId  �ж��Ƿ��ǹ���Ա��
								UserInfo u=lab.getUserInfoByRfid(str[0].trim());
								if(u!=null){
									isCommon=lab.isUserGroup2(u.getUserId());
									System.out.println("����Ա����^^^^^^^"+isCommon);
								}
	
								if(between1<0&&between2>0){
									isBetweent=true;
								}
								
							} catch (ParseException e) {
								e.printStackTrace();
							}
							
						}
						log.info(isBetweent);
						if((str.length!=1&&str[0].trim().equals(rfid.trim())&&str[1].trim().equals("8")&&isBetweent)||(isCommon&&isBetweent)){
							doWork(labID, "OPEN");
							runFlag=false; 
							log.info("ˢ����Ч��ִ�д��豸�������������رմ��߳�");
//							ComThread.Release();
						}
						
						//���һֱû���˳ɹ�ˢ��  �����ڿγ̽��������Ӧ��ʱ���ڹرմ��߳�
						try {
							java.util.Date end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime);
							java.util.Date now_time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
							long between=(end.getTime()-now_time.getTime())/60000;
							if(between<0){
								runFlag=false;
								log.info("����ˢ��������ʱ�䵽���رմ��߳�");
//								ComThread.Release();
							}

						} catch (ParseException e1) {
							e1.printStackTrace();
						}	
						
						//ÿִ��һ�β�ѯ��¼ ��Ϣ2s
						try {
							Thread.sleep(2000);
						} catch (InterruptedException e) {
						}
					}
	
				}
			}).start();
			
			
			
			

		}else if(operate.equals("CTIMER")){
			log.info( "ִ��CTIMER-------------------------------");
			//ȡ����Ӧ�Ž��Ȩ���Լ� ɾ������ʱ�û�
			GuardControl doorC=GetCuardControl.getGuardControl(labID);
			String doorNum=lab.getDoorNumByLabId(labID);
			doorC.sendOrder(doorC.delLimit(rfid, doorNum));
			log.info("ɾ��Ȩ��OK������������������������������"+rfid);
			
			LoginService  loginService=new LoginServiceImpl();
			loginService.deleteByUserIdReal(rfid);
			loginService.deleteBySql("delete from user_group where UserID='"+rfid+"' and GroupID='common'");
			loginService.deleteBySql("delete from lab_right where UserID='"+rfid+"' and LabID='"+labID+"'");

		}else if(operate.equals("WARM")){
			log.info("ִ��Ԥ������-----------------------------");
			try{
				sps=lab.getSPs("�ƹ�",labID);
				orderList=new ArrayList<byte[]>();
				List<byte[]> closeList=new ArrayList<byte[]>();
				sp=sps.get(0);
				di=lab.getByDeviceId(sp.get(0).getSessionParameterPK().getDeviceId());
				for (SessionParameter sessionParameter : sp) {	
				orderList.add(smpsc.onOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));	
				closeList.add(smpsc.offOrder(Integer.parseInt(sessionParameter.getSessionParameterPK().getSessionValue().trim())));	
				}
			
				as= new AutoSocketSendOrder(di.getDeviceIp(), di.getPort(), closeList);
				ass.add(as);
				for (AutoSocketSendOrder aso : ass) {
						aso.run();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
				}
				
				//�ر����еƹ� ���� ������--------------------------------------------------
				Thread.sleep(3000);
				
				as= new AutoSocketSendOrder(di.getDeviceIp(), di.getPort(), orderList);
				ass.add(as);
				for (AutoSocketSendOrder aso : ass) {
						aso.run();
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
						}
				}

		
				}catch(Exception e){e.printStackTrace();}
			
		}
	}
	
	
	/**
	 * ����Ϣ��Чʱ �����豸����
	 */
	private void doWork(String labID, String operate) {
		LabParameter lp = new LabParameter();
		lp.labID = labID;
		lp.operate = operate;
		lp.AutoNavigation();

	}
	
	public static void main(String[] args) {
		LabParameter lp = new LabParameter();
		lp.labID = "C504";
    	lp.NavigateMode = "2";
    	lp.operateTime = "2014-01-16 10:22:00";
    	lp.endTime="2014-01-16 10:59:00";
    	lp.operate = "TIMER";//����������  �ϴ�Ȩ��
    	lp.rfid="21543925";//��Ӹ���ʹ��ʱ�Ŀ���
		lp.AutoNavigation();
	}
}
