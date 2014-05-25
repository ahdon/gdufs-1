package com.fro.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.fro.entity.UserGroup;
import com.fro.entity.UserGroupPK;
import com.fro.entity.UserInfo;
import com.fro.service.LoginService;
import com.fro.service.impl.LoginServiceImpl;
import com.fro.utils.HttpUtils;

public class LoginAction extends BaseAction{

	private String userid;
	private String password;
	
LoginService loginService=new LoginServiceImpl();

	public String login() throws Exception {
		
		
		UserInfo userInfo=loginService.login(userid);
		if(userInfo!=null){
				if(userInfo.getPassword().equals(password)){
					//ͨ��userid��user_group���в���groupid
			List<String>  rightids=loginService.getRightId(userid);
			session.put("userInfo", userInfo);
			HttpSession  httpSession=request.getSession();
			httpSession.setAttribute("ui", userInfo);
			session.put("rightids",rightids);
			
			return "main";
		 }	
		}
		
		session.put("Msg_error", "�û�����������󣡣�");
		return "login";
	}


	public String schoolLogin(){
		System.out.println("����У԰��ڡ���");
		int k=1;
		String url="http://passport.mygdufs.com/Api/checkIsRight?studentNumber="+userid+"&password="+password+"";
		System.out.println(url);
		k=HttpUtils.getStatus(url);
		
		if(k==1){
			
			//��Ҫ�ټ�һ���ж�  �жϴ˿��ŵ���ʦ��15�������Ƿ��и�������Ŀγ� ���ҷ���ʵ����ID
			String labId=loginService.getLabIDByExec(userid);
			if(labId!=null){
				System.out.println("��¼�ɹ���");
				UserInfo userInfo=loginService.login(userid);
				if(userInfo==null){
				//   2������û���ӵ��û�����   1������û���ӵ�  common/���ֹ����� ��         3�������ʱ�û������ʱ��ʵ����Ȩ��
					userInfo=new UserInfo();
					userInfo.setUserId(userid); 
					userInfo.setPassword(password);
					userInfo.setUserName("��ʱ�û�");
					userInfo.setStatus("1");
					try {
						loginService.addUserInfo(userInfo);
						loginService.addGroupInfo(userid, "common");
						loginService.addLabRight(labId,userid);
					} catch (Exception e) {
						e.printStackTrace();
					}
		
				}
				
				
				List<String> rightids=new ArrayList<String>();
				try {
					rightids = loginService.getRightId(userid);
					session.put("rightids",rightids);
				} catch (Exception e) {
				}
				session.put("userInfo", userInfo);
				HttpSession  httpSession=request.getSession();
				httpSession.setAttribute("ui", userInfo);	
				return "main";
				
			}	
		}

		session.put("Msg_error", "�û�����������󣡣�");
		return "login";
	}
	


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}









	
	
	
}
