package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

//����! :
//�����ͺ��̽� ������ �̹� shopping-servlet.xml���� DriverManagerDataSoueceŬ������ ���� �� ���Ҵ�.

//Ŭ���� ���� : �̸� ����� ������ ���̽��� ������ �����Ͽ� ����� ���� �޾��ִ� Ŭ����
public class ShoppingDAO {
	
	//������ ������Ѽ� �����͸� �����ü� �ֵ������ִ� ��ü���� �������� (preparedstatement��ü�� ��������)
	SimpleJdbcTemplate template;  //pstmt�� ����
	
	//���� : 
	//shopping-servlet.xml�� �������踦 �̿��Ͽ�....
	// <bean id="shoppingDao" class="model.ShoppingDAO"  p:dataSource-ref="dataSource"  />���..
	// ��� �� ���� ������...
	//������ ���̽��� �����Ͽ� �����͸� �о���ü� �ִ� Ŀ�ؼ�Ǯ ��ü�� ���� ���� ���� ����
	DataSource dataSource;
	//����! ���������...  p:dataSource-ref ��!! dataSource�̸��� ���� ���������� ����� �Ѵ�.
	

	//setter�޼ҵ� 
	//������� ��ü�� ������Ű���ʰ� dataSource���
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource = dataSource;
		 
		//Ŀ�ؼǿ� �ִ� DB���ᰴü�� ��������... 
		//������ ������Ѽ� �����͸� �����ü� �ֵ������ִ� ��ü���� 
		template = new SimpleJdbcTemplate(dataSource); //pstmt�� ����
	}
	
	
	
	//��� ���۾� ������ �������ִ� �޼ҵ�
		public List<SuBean> getAllSutool() {
			
			/*������� ��1���,��2��Ŀ������?.......*/
			
			//�ؾ� �ߴ���1. DB���� �˻��� ������ڵ带  ������ SuBean��ü�� ���....ArrAyList��ü�� SuBean��ü�� ���� ���..
			//List���·� �����ϱ����� ArrayList��ü���� �ؾ� �Ѵ�.
			
			//�ؾ� �ߴ���2. DB���� �˻��� ������ڵ带 ���� SuBean��ü �����ؾ� ��.	
			//�ؾ� �ߴ��� 3.  Ŀ�ؼ� Ǯ���� DB���� ��ü�� �����ͼ� �غ� ( DB����)	
			//�ؾ� �ߴ��� 4.  DB����  ��� ���۾����� �˻�  select����  ������ ��.		
			//�ؾ� �ߴ��� 5.  PreparedStatement��ü�� �̿��Ͽ� select���� �ؾ���.		
			//�ؾ� �ߴ��� 6.   select�� �����  ���̺���������  ResultSet�� ���� �ؾ���.		
			//�ؾ� �ߴ��� 7.  while�����̿��Ͽ�  rs.next()�̿��Ͽ�  �ϳ��� �÷��� �ִ� �����͸� SuBean��ü�� �����Ͽ� ���� �ؾ���.		
			//�ؾ� �ߴ���8.   select�Ͽ� �˻��� ����� ������ SuBean��ü�� �ٽ�.. ArrayList�÷��ǰ�ü��  add��Ű��....		
			//�ؾ� �ߴ���9.   ��� while���� ������  return list; �ؾ���.
			//---------------------------------------------------------------------------------------
			/*������ ���*/		
			
			//��� ���۾����� �˻� ���� �غ�
			String sql ="select * from sutool";
			
			//RowMapper : ���� ����� ��ü�� ��ȯ

			//BeanPropertRowMapperŬ���� ���� : 
			//Subean��Ŭ������ ������� DB�� SUTOOL���̺��� �÷����� ��Ī�� ��ü ���� 
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			
			//template��ü�� �̿��Ͽ� ������ �����ų��..
			//query()�޼ҵ忡 ������ select����, DB�� SUTOOL���̺��� �÷���� ��Ī�� ��ü�� �����Ͽ�..
			//select�� ��� ���ڵ� ������ŭ list�� ���  list��ü�� ���� ���ش�.
			// ����! ���Ϲ��� list�� �Ǵٽ� getAllSutool()�޼ҵ带 ȣ���� ������ ���� �ϱ�
			return template.query(sql, rm); 
		}

		
		
		//���� ī�װ� �޴��� ���õ� ���۾� ������ �������ִ� �޼ҵ�
		public List<SuBean> getSelectSutool(String num) {//���� ī�װ� �޴��� ���õ� �޴� ��ȣ
			//���� �غ�
			String sql ="select * from sutool where sucate=?";
			//��Ŭ����(�÷���� �Ȱ��� Ŭ������  ������ ��ü)�� �÷����� ��Ī�ϴ� ��ü ����
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			return template.query(sql, rm, num);
		}



		//���۾� ������ȣ �ϳ��� ���޹޾� ...�ϳ��� ���������� �����ϴ� �޼ҵ�
		public SuBean getOneSutool(int suno) {
			//�����غ�
			String sql="select * from sutool where suno=?";		
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			//template��ü�� �̿��Ͽ� ������ �����ų��..
			//query()�޼ҵ忡 ������ select����, DB�� SUTOOL���̺��� �÷���� ��Ī�� ��ü�� �����Ͽ�..
			//select�� ����� SuBean��ü ������.. �ְ� ����Ŭ������ ObjectŸ������ ���� �޴´�.
			// ����! ���Ϲ���  SuBean��ü�� �Ǵٽ� getOneSutool()�޼ҵ带 ȣ���� ������ ���� �ϱ�
			return template.queryForObject(sql, rm, suno);
		}
		
		
		//���̵� �ִ��� �ľ�
		public int getLogin(MemberBean mbean) {
			// ���� �غ� : ȸ�����Խ� �ۼ��� ���̵� �ش��ϴ� ���ڵ� ���� ���ϱ�
			String sql="select count(*) from member3 where memid=?";
			//select�� ���������  ���ڵ� ������ �˻��Ǹ� 1�� ���� �Ͽ�..
			//�ٽ� getLogin()�޼ҵ带 ȣ���� ������ 1�� ����
			//select�� ���������  ���ڵ� ������ �˻����� ������ 0�� ���� �Ͽ�..
			//�ٽ� getLogin()�޼ҵ带 ȣ���� ������ 0�� ����
			return template.queryForInt(sql, mbean.getMemid());
		}
		
		/*ȸ������ �޼ҵ�*/
		public void insertMember(MemberBean mbean) {
			// ���� �ۼ�
			String sql="insert into member3 values (:memid,:mempasswd1,:mempasswd2,"
					+ ":memname,:memphone,:memdate)";
			//��Ŭ������ �������� 1:1���εǵ��� �ڵ�����
			SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(mbean);
			//���� �����Ͻÿ�
			template.update(sql, sqlsource);
			 
			
		}



		//�α��� ó���� ���̵�� �н����尡 �ִ����� ���� ���θ� �ľ�
		//- LoginForm.jsp���� �ۼ��� ���̵�� �н����带 �Ű������� ���޹޾�..
		//  ���̵�� �н����忡 �ش��ϴ� ȸ������ �˻� �ϴµ� �˻����� �����ϸ� 1����ȯ, �����ϸ� 0�� ��ȯ 
		public int getLoginProc(MemberBean mbean) {
			String sql="select count(*) from member3 where memid=? and mempasswd1=?";
			return template.queryForInt(sql, mbean.getMemid() , mbean.getMempasswd1());
		}



		//�Խ����� ��� �������� ������ ����
		public int getCount() {
			String sql = "select count(*) from board3";
			return template.queryForInt(sql);
		}


		//�Խñ��� ���� ��ȣ�� �� ��ȣ�� �������� �����͸� �������ÿ�
		public List<BoardBean> getAllContent(int startRow, int endRow) {
//			String sql = "select * from"
//					+"(select A.*, @Rownum:=@Rownum+1 Rnum from "
//					+"(select * from board3 order by ref desc, re_level Asc)A "
//					+"where(@Rownum:=0) = 0) B "
//					+"where Rnum>? and Rnum <=?;";
			String sql = "select * from board3 order by ref desc, re_level Asc limit ?, ?";
			RowMapper<BoardBean> rm = new BeanPropertyRowMapper<BoardBean>(BoardBean.class);
			
			return template.query(sql, rm, startRow , (endRow - startRow));
		}
		
		
		//�Խñ� �ϳ��� �����ϴ� �޼ҵ�	
		public void boardInsert(BoardBean bean) {
			int ref = 1; //�۱׷�
			int re_step=1;//�θ������ �ڽı�����
			int re_level=1;//���� ������ ������ �����ϴ� ����
			int readcount=0;
			
			//�۱׷��� ���� ������ �˾ƾ� �ҽ��� �ۼ�
			String refmax ="select max(ref) from board3";
			int refdata = template.queryForInt(refmax);
			if(refdata >= 1 ){
				ref = refdata+1;
			}
			//��� �����Ͱ� �غ� �Ϸ�Ǿ��ֱ⿡ �����͸� ����
			bean.setRef(ref);
			bean.setRe_step(re_step);
			bean.setRe_level(re_level);
			bean.setReadcount(readcount);
			
			 String sql="insert into board3(writer,email,subject,passwd,reg_date,readcount,ref,re_step,re_level,content) "
			            + "values(:writer,:email,:subject,:passwd,now(),:readcount,:ref,:re_step,:re_level,:content)";

			SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(bean);
			//��������
			template.update(sql, sqlsource);
			
			
			
		}
		
		//�ϳ��� �Խñ��� �����ϴ� �޼ҵ�
		public BoardBean getOneContent(int num) {
			
			//��ȸ�� ���� ����
			String countsql ="update board3 set readcount=readcount+1 where num=?";
			template.update(countsql, num);
			
			//�Խñ۸���
			String sql ="select * from board3 where num=?";
			RowMapper<BoardBean> rm = new BeanPropertyRowMapper<BoardBean>(BoardBean.class);
			return template.queryForObject(sql, rm, num);
		}

		//��۾���
		public void reWriteboard(BoardBean bean) {
			//�亯�۷� ���Ͽ� ������ �亯�۵��� ������ 1�� �����ϴ� ����
			String levelsql="update board3 set re_level=re_level+1 where ref=? and re_level >?";
			template.update(levelsql, bean.getRef(), bean.getRe_level());
			
			//���� �亯���� �����ϴ� �޼ҵ�
			bean.setRe_step(bean.getRe_step()+1);
			bean.setRe_level(bean.getRe_level()+1);
			
			 String sql="insert into board3(writer,email,subject,passwd,reg_date,readcount,ref,re_step,re_level,content) "
					 + "values(:writer,:email,:subject,:passwd,now(),:readcount,:ref,:re_step,:re_level,:content)";

			SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(bean);
			//��������
			template.update(sql, sqlsource);
		}

		//�Խñ� ���� 
		public void boardUpdate(BoardBean bean) {
			String sql="update board3 set content=? where num=? and passwd=?";
			template.update(sql, bean.getContent(), bean.getNum(), bean.getPasswd());
		}

		//�Խñ� ����
		public void boardDelete(BoardBean bean) {
			String sql="delete from board3 where num=? and passwd=?";
			template.update(sql, bean.getNum(), bean.getPasswd());
			
		}
		
}//Ŭ���� ��








