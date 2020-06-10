package dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import dto.ResourceVO;

public interface ResourceDao {
	// ��ȣ ����
	public String getMaxNo();
	
	// ����
	public int resourceSave(Map<String, Object> map);
	
	//��ȸ
	public ArrayList<ResourceVO> getResourceList();
	
	public ArrayList<ResourceVO> getResourceList_2(Map<String, Object> map);
	
	//����ȸ
	public ResourceVO getResourceView(String no);
	
	//����
	public int resourceUpdate(Map<String, Object> map);
	
	
}