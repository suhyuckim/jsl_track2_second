package dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import dto.NextPrevVO;
import dto.NoticeVO;

public interface NoticeDao {
	// �����ȸ
//	public ArrayList<NoticeVO> getNoticeList_1();
	public ArrayList<NoticeVO> getNoticeList_2(Map<String, Object> map);
	public ArrayList<NoticeVO> getNoticeList_3(@Param("s_select") String sel, @Param("s_search") String sea);
	
	//��ȣ ����
	public String getMaxNo();
	
	//���
	public int noticeSave(Map<String, Object> map);

	//����ȸ
	public NoticeVO getNoticeView_1(@Param("noti") String no);
	public NoticeVO getNoticeView_2(String no);
	
	//��ȸ�� ����
	public int noticeHit(String no);
	
	//������ ������
	public NextPrevVO getNextPrev(String no);
	
	//����
	public int noticeUpdate(Map<String, Object> map);
	
	//����
	public int noticeDelete(String no);
}