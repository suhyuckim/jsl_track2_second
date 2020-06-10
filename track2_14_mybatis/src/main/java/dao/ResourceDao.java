package dao;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import dto.ResourceVO;

public interface ResourceDao {
	// 번호 생성
	public String getMaxNo();
	
	// 저장
	public int resourceSave(Map<String, Object> map);
	
	//조회
	public ArrayList<ResourceVO> getResourceList();
	
	public ArrayList<ResourceVO> getResourceList_2(Map<String, Object> map);
	
	//상세조회
	public ResourceVO getResourceView(String no);
	
	//수정
	public int resourceUpdate(Map<String, Object> map);
	
	
}