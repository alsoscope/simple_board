package com.p.project.persistence;

import java.util.List;
import com.p.project.model.BoardVO;
import com.p.project.model.Criteria;

//�������̽�
public interface BoardDAO {
	//01 �Խñ� �ۼ�
	public void create(BoardVO vo)throws Exception;
	//02 �Խñ� �󼼺���
	public BoardVO read(int bno) throws Exception;
	//03 �Խñ� ����
	public void update(BoardVO vo)throws Exception;
	//04 �Խñ� ����
	public void delete(int bno)throws Exception;
	//05 �Խñ� ��ü ��� --> �˻��ɼ�, Ű���� �Ű����� �߰�
	public List<BoardVO> listAll(String searchOptin, String keyword)throws Exception;
	//06 �Խñ� ��ȸ ����
	public void increaseViewcnt(int bno)throws Exception;
	//07 �Խñ� ���ڵ� ���� �޼��� �߰�
	public int countArticle(String searchOption, String keyword) throws Exception;
	
	/*//����¡ ó�� public List<BoardVO> listPage(int page)throws Exception;*/
	//criteria ��ü�� �Ķ���ͷ� ���� �޾�, BoardDAO�� ����Ʈ�� ��� getPageStart(), getPerPageNum() �޼ҵ� ȣ��
	public List<BoardVO> listCriteria(Criteria cri)throws Exception;
	//totalCount ��ȯ�Ͽ� ���� ����¡ ó��
	public int countPaging(Criteria cri)throws Exception;
}
