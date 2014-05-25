package com.fro.dao;

import java.util.List;

import com.fro.entity.ClassSchedule;
import com.fro.entity.DepartmentInfo;
import com.fro.entity.GroupInfo;
import com.fro.entity.LabInfo;
import com.fro.entity.LabRight;
import com.fro.entity.MajorInfo;
import com.fro.entity.UserGroup;
import com.fro.entity.UserInfo;
import com.fro.entity.UserRight;

public interface LoginDao {

	public UserInfo findByUserId(String userId);


	public UserRight findByGroupid(String groupid);

	public UserGroup findGroupid(String userId);


	public void setLabStau(String userid,String logmodel);


	public void addUserInfo(UserInfo userInfo)throws Exception;


	public List<DepartmentInfo> getDepartment();


	public List<GroupInfo> getRight();


	public List<MajorInfo> getMajor();


	public List<UserInfo> findByCondition(String hql,int currentPage,int pageSize);


	public int getTotalPage(String hql,int pageSize);


	public int getRecodes(String hql);


	public int deleteByUserId(String userId);


	public List<LabInfo> getLabInfo();



	public List<UserInfo> findAll();


	public int addUserInfo(List<UserInfo> userInfos);


	public int updUserInfo(UserInfo userInfo);


	public List<String> getRightId(String userid) throws Exception;


	public void addGroupInfo(String userId, String groupId);


	public List<UserInfo> getUserInfos();



	public void addUserRight(String userId, List<String> ad);


	public void delUserRight(String userId, List<String> del);


	public int stopByUserId(String userId);


	public void deleteGroupByUserId(String string);


	public void deleteUserRight(String string);


	public List<ClassSchedule> findByCondition1(String string, int currentPage,
			int pageSize);


	public List<UserGroup> getAdminUserInfo(String string);


	public void addUserGroup(GroupInfo groupInfo);


	public List<GroupInfo> getGroupInfos();


	public void modifyGroupInfo(GroupInfo groupInfo);


	public void deleteGroupInfo(String trim);


	public List<UserRight> getUserRight(String groupId);


	public void addUserRight2(String groupId, List<String> ad);


	public void delUserRight2(String groupId, List<String> del);


	public void deleteUserRight2(String trim);


	public List<UserGroup> getUserGrops(String trim);


	public List<Object[]> getUserInfosByGroupId(String groupId);


	public void deleteGroupInfo(String trim, String groupId);


	public String getStauSet();


	public void openByUserId(String userId);


	public String getBeginDateByCurseClass(String labId);


	public void updLabInfoRun(String sql);


	public void labRightSet(LabRight lr);


	public void deleteRight(String labId);


	public List<LabRight> getRightByLabId(String labId);


	public List<LabInfo> getDoorNum(String string);


	public int deleteDoorNum(String doorNum, String doorRFID);


	public List<String> getDoorNumByRFID(String rfid);


	public int addDoorNum(String doorNum, String doorRFID);


	public void addLabRight(String labId, String userid);


	public String getLabIDByExec(String userid);


	public String getLabIdByDoorNum(String doorNum);


	public void deleteBySql(String string);


	
}
