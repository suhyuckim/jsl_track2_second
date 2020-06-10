package dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;

import dto.NextPrevVO;
import dto.NoticeVO;

public interface NoticeDao {
	// 목록조회
//	public ArrayList<NoticeVO> getNoticeList_1();
	public ArrayList<NoticeVO> getNoticeList_2(Map<String, Object> map);
	public ArrayList<NoticeVO> getNoticeList_3(@Param("s_select") String sel, @Param("s_search") String sea);
	
	//번호 생성
	public String getMaxNo();
	
	//등록
	public int noticeSave(Map<String, Object> map);

	//상세조회
	public NoticeVO getNoticeView_1(@Param("noti") String no);
	public NoticeVO getNoticeView_2(String no);
	
	//조회수 증가
	public int noticeHit(String no);
	
	//이전글 다음글
	public NextPrevVO getNextPrev(String no);
	
	//수정
	public int noticeUpdate(Map<String, Object> map);
	
	//삭제
	public int noticeDelete(String no);
}