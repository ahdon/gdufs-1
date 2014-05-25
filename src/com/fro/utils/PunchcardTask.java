package com.fro.utils;

import java.util.List;

import com.fro.entity.DeviceInfo;
import com.fro.order.GuardControl;
import com.fro.service.LabService;
import com.fro.service.impl.LabServiceImpl;

/**
 * ��������ʵ�����Ž��¼���Ĳ�ͣ��ѯ
 * 
 * @author Administrator
 * 
 */
public class PunchcardTask extends Thread {

	@Override
	public void run() {
		System.out.println("�����Ž�������");
		startCard();
	}

	/**
	 * ��ȡ����ʵ���ҵ��Ž���Ϣ ��������
	 */
	LabService impl = new LabServiceImpl();

	private void startCard() {

		
		
		
		List<DeviceInfo> deviceInfos = impl.getDeviceInfo("�Ž�");
		for (final DeviceInfo deviceInfo : deviceInfos) {
			new Thread(new Runnable() {
				public void run() {
					doMainByDeviceInfo(deviceInfo);
				}
			}).start();
		}

	}
 
	/**
	 * ���ݱȽ��Ž��¼���ж��Ƿ���Ҫ���д��豸����
	 * 
	 * @param deviceInfo
	 */

	private void doMainByDeviceInfo(DeviceInfo deviceInfo) {

		while (true) {
			boolean flag = false;
			GuardControl doorC = GetCuardControl.getGuardControl(deviceInfo
					.getLabId());
			
			// ���õ��Ŀ������ʱ�˸�ʵ����  �����Ͽε� �����˵�rfid���бȽ�
			String rfid=impl.getRfidByLabId(deviceInfo.getLabId());
			if(rfid!=null){
				
				//��ȡ���һ����¼���з���   �õ�ˢ���˵Ŀ���    
				String doorNum=impl.getDoorNumByLabId(deviceInfo.getLabId());
				String[] str=doorC.getCardNo(doorNum);
				if(str.length!=1&&str[0].equals(rfid)&&str[1].equals("8")){
					flag=true; 
				}

				if (flag) {
					doWork(deviceInfo.getLabId(), "OPEN");
				} else {
					System.out.println("ˢ���˼�¼��Ч������ˢ����");
				}
				
			}

			
			// ����
			try {
				Thread.sleep(10 * 1000);
			} catch (InterruptedException e) {
			}

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

}
