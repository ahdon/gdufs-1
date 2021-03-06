package com.fro.service;

import java.util.List;

import com.fro.entity.ClassSchedule;
import com.fro.entity.ClasshourInfo;
import com.fro.entity.DepartmentInfo;
import com.fro.entity.GroupInfo;
import com.fro.entity.LabInfo;
import com.fro.entity.MajorInfo;
import com.fro.entity.UserInfo;

public interface LoginService {

	UserInfo login(String userId);

	String getWeek();

	String findGroupid(String userId);

	String findByGroupid(String groupid);

//	void setLabStau(String userid,String logmodel);

	void addUserInfo(UserInfo userInfo)throws Exception;

	List<DepartmentInfo> getDepartment();

	List<GroupInfo> getRight();

	List<MajorInfo> getMajor();

	List<UserInfo> findByCondition(UserInfo userInfo,int currentPage,int pageSize);

	int getTotalPage(UserInfo userInfo,int pageSize);

	int getRecodes(UserInfo userInfo);

	int deleteByUserId(String userId);

	int deleteByChoose(String str);

	List<List<Object>> makeExcel(List<UserInfo> userInfos);

	 List<String> makeExcelName();

	List<LabInfo> getLabInfo();

	int importUserInfo(List<UserInfo> userInfos);

	int updUserInfo(UserInfo userInfo);

	List<String> getRightId(String userid)throws Exception;

	void addGroupInfo(String userId, String groupId);

	List<UserInfo> getUserInfos();

	void RightSet(String userId, String[] checkbox) throws Exception;

	List<UserInfo> toExcel(String str);

	List<ClassSchedule> findByCondition(ClassSchedule c, int currentPage,
			int pageSize);

	int getRecodes2(ClassSchedule c);

	void addUserGroup(GroupInfo groupInfo);

	List<GroupInfo> getGroupInfos();

	void modifyGroupInfo(GroupInfo groupInfo);

	int deleteByChooseLabInfo(String str);

	List<String> getRightId2(String groupId);

	void RightSet2(String groupId, String[] checkbox);

	List<Object[]> getUserInfosByGroupId(String groupId);

	void sureModifyGroup(String userId, String groupId, String str);

	String getStauSet();

	void openByUserId(String userId);

	int updLabInfoRun(String labId, String modelSet);

	void labRightSet(String labId, String strUserId, String strGroupId);

String getRightByLabId(String labId);

List<LabInfo> getDoorNum();

int deleteDoorNum(String doorNum, String doorRFID);

int addDoorNum(String doorNum, String doorRFID, String userId);

void addLabRight(String labId, String userid);

String getLabIDByExec(String userid);

void deleteBySql(String string);

void deleteByUserIdReal(String rfid);

	

	

	

	

}
