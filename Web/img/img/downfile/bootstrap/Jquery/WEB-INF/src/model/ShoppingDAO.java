package model;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

//참고! :
//데이터베이스 연결은 이미 shopping-servlet.xml에서 DriverManagerDataSouece클래스가 연결 해 놓았다.

//클래스 역할 : 미리 연결된 데이터 베이스에 쿼리를 실행하여 결과를 리턴 받아주는 클래스
public class ShoppingDAO {
	
	//쿼리를 실행시켜서 데이터를 가져올수 있도록해주는 객체선언 스프링용 (preparedstatement객체와 사용법유사)
	SimpleJdbcTemplate template;  //pstmt와 유사
	
	//설명 : 
	//shopping-servlet.xml에 의존관계를 이용하여....
	// <bean id="shoppingDao" class="model.ShoppingDAO"  p:dataSource-ref="dataSource"  />라고..
	// 등록 해 놨기 떄문에...
	//데이터 베이스에 접근하여 데이터를 읽어가져올수 있는 커넥션풀 객체를 담을 참조 변수 선언
	DataSource dataSource;
	//참고! 변수선언시...  p:dataSource-ref 중!! dataSource이름과 같이 변수선언을 해줘야 한다.
	

	//setter메소드 
	//설정취득 객체를 생성시키지않고 dataSource사용
	public void setDataSource(DataSource dataSource) {
		
		this.dataSource = dataSource;
		 
		//커넥션에 있는 DB연결객체의 힘을빌려... 
		//쿼리를 실행시켜서 데이터를 가져올수 있도록해주는 객체생성 
		template = new SimpleJdbcTemplate(dataSource); //pstmt와 유사
	}
	
	
	
	//모든 수작업 공구를 리턴해주는 메소드
		public List<SuBean> getAllSutool() {
			
			/*기존방식 모델1방식,모델2방식에서라면?.......*/
			
			//해야 했던일1. DB에서 검색한 결과레코드를  각각의 SuBean객체에 담고....ArrAyList객체에 SuBean객체를 각각 담아..
			//List형태로 리턴하기위해 ArrayList객체생성 해야 한다.
			
			//해야 했던일2. DB에서 검색한 결과레코드를 담을 SuBean객체 생성해야 함.	
			//해야 했던일 3.  커넥션 풀에서 DB연결 객체를 빌려와서 준비 ( DB연결)	
			//해야 했던일 4.  DB에서  모든 수작업공구 검색  select쿼리  만들어야 함.		
			//해야 했던일 5.  PreparedStatement객체를 이용하여 select실행 해야함.		
			//해야 했던일 6.   select한 결과를  테이블형식으로  ResultSet에 저장 해야함.		
			//해야 했던일 7.  while문을이용하여  rs.next()이용하여  하나씩 컬럼에 있는 데이터를 SuBean객체에 맵핑하여 저장 해야함.		
			//해야 했던일8.   select하여 검색한 결과를 저장한 SuBean객체를 다시.. ArrayList컬렉션객체에  add시키고....		
			//해야 했던일9.   모든 while문을 돌고난후  return list; 해야함.
			//---------------------------------------------------------------------------------------
			/*스프링 방식*/		
			
			//모든 수작업공구 검색 쿼리 준비
			String sql ="select * from sutool";
			
			//RowMapper : 쿼리 결과를 객체로 변환

			//BeanPropertRowMapper클래스 역할 : 
			//Subean빈클래스의 변수명과 DB의 SUTOOL테이블의 컬럼명을 메칭한 객체 생성 
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			
			//template객체를 이용하여 쿼리를 실행시킬때..
			//query()메소드에 실행할 select문과, DB의 SUTOOL테이블의 컬럼명과 매칭한 객체를 전달하여..
			//select한 결과 레코드 갯수만큼 list에 담아  list자체를 리턴 해준다.
			// 또한! 리턴받은 list를 또다시 getAllSutool()메소드를 호출한 곳으로 리턴 하기
			return template.query(sql, rm); 
		}

		
		
		//왼쪽 카테고리 메뉴중 선택된 수작업 공구를 리턴해주는 메소드
		public List<SuBean> getSelectSutool(String num) {//왼쪽 카테고리 메뉴중 선택된 메뉴 번호
			//쿼리 준비
			String sql ="select * from sutool where sucate=?";
			//빈클래스(컬럼명과 똑같은 클래스를  선언한 객체)와 컬럼명을 메칭하는 객체 생성
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			return template.query(sql, rm, num);
		}



		//수작업 공구번호 하나를 전달받아 ...하나의 공구정보를 리턴하는 메소드
		public SuBean getOneSutool(int suno) {
			//쿼리준비
			String sql="select * from sutool where suno=?";		
			RowMapper<SuBean> rm = new BeanPropertyRowMapper<SuBean>(SuBean.class);
			//template객체를 이용하여 쿼리를 실행시킬때..
			//query()메소드에 실행할 select문과, DB의 SUTOOL테이블의 컬럼명과 매칭한 객체를 전달하여..
			//select한 결과는 SuBean객체 이지만.. 최고 조상클래스인 Object타입으로 리턴 받는다.
			// 또한! 리턴받은  SuBean객체를 또다시 getOneSutool()메소드를 호출한 곳으로 리턴 하기
			return template.queryForObject(sql, rm, suno);
		}
		
		
		//아이디가 있는지 파악
		public int getLogin(MemberBean mbean) {
			// 쿼리 준비 : 회원가입시 작성한 아이디에 해당하는 레코드 갯수 구하기
			String sql="select count(*) from member3 where memid=?";
			//select의 결과값으로  레코드 갯수가 검색되면 1을 리턴 하여..
			//다시 getLogin()메소드를 호출한 곳으로 1을 리턴
			//select의 결과값으로  레코드 갯수가 검색되지 않으면 0을 리턴 하여..
			//다시 getLogin()메소드를 호출한 곳으로 0을 리턴
			return template.queryForInt(sql, mbean.getMemid());
		}
		
		/*회원가입 메소드*/
		public void insertMember(MemberBean mbean) {
			// 쿼리 작성
			String sql="insert into member3 values (:memid,:mempasswd1,:mempasswd2,"
					+ ":memname,:memphone,:memdate)";
			//빈클래스가 쿼리문에 1:1맵핑되도록 자동설정
			SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(mbean);
			//쿼리 실행하시오
			template.update(sql, sqlsource);
			 
			
		}



		//로그인 처리시 아이디와 패스워드가 있는지에 대한 여부를 파악
		//- LoginForm.jsp에서 작성한 아이디와 패스워드를 매개변수로 전달받아..
		//  아이디와 패스워드에 해당하는 회원수를 검색 하는데 검색ㅇㅔ 성공하면 1을반환, 실패하면 0을 반환 
		public int getLoginProc(MemberBean mbean) {
			String sql="select count(*) from member3 where memid=? and mempasswd1=?";
			return template.queryForInt(sql, mbean.getMemid() , mbean.getMempasswd1());
		}



		//게시판의 모든 데이터의 갯수를 리턴
		public int getCount() {
			String sql = "select count(*) from board3";
			return template.queryForInt(sql);
		}


		//게시글의 시작 번호와 끝 번호를 기준으로 데이터를 가져오시오
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
		
		
		//게시글 하나를 저장하는 메소드	
		public void boardInsert(BoardBean bean) {
			int ref = 1; //글그룹
			int re_step=1;//부모글인지 자식글인지
			int re_level=1;//글이 보여질 순서를 저장하는 변수
			int readcount=0;
			
			//글그룹이 현제 얼마인지 알아야 소스를 작성
			String refmax ="select max(ref) from board3";
			int refdata = template.queryForInt(refmax);
			if(refdata >= 1 ){
				ref = refdata+1;
			}
			//모든 데이터가 준비 완료되어있기에 데이터를 저장
			bean.setRef(ref);
			bean.setRe_step(re_step);
			bean.setRe_level(re_level);
			bean.setReadcount(readcount);
			
			 String sql="insert into board3(writer,email,subject,passwd,reg_date,readcount,ref,re_step,re_level,content) "
			            + "values(:writer,:email,:subject,:passwd,now(),:readcount,:ref,:re_step,:re_level,:content)";

			SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(bean);
			//쿼리싱행
			template.update(sql, sqlsource);
			
			
			
		}
		
		//하나의 게시글을 리턴하는 메소드
		public BoardBean getOneContent(int num) {
			
			//조회수 부터 증가
			String countsql ="update board3 set readcount=readcount+1 where num=?";
			template.update(countsql, num);
			
			//게시글리턴
			String sql ="select * from board3 where num=?";
			RowMapper<BoardBean> rm = new BeanPropertyRowMapper<BoardBean>(BoardBean.class);
			return template.queryForObject(sql, rm, num);
		}

		//답글쓰기
		public void reWriteboard(BoardBean bean) {
			//답변글로 인하여 기존에 답변글들의 레벨을 1씩 증가하는 쿼리
			String levelsql="update board3 set re_level=re_level+1 where ref=? and re_level >?";
			template.update(levelsql, bean.getRef(), bean.getRe_level());
			
			//실제 답변글을 저장하는 메소드
			bean.setRe_step(bean.getRe_step()+1);
			bean.setRe_level(bean.getRe_level()+1);
			
			 String sql="insert into board3(writer,email,subject,passwd,reg_date,readcount,ref,re_step,re_level,content) "
					 + "values(:writer,:email,:subject,:passwd,now(),:readcount,:ref,:re_step,:re_level,:content)";

			SqlParameterSource sqlsource = new BeanPropertySqlParameterSource(bean);
			//쿼리싱행
			template.update(sql, sqlsource);
		}

		//게시글 수정 
		public void boardUpdate(BoardBean bean) {
			String sql="update board3 set content=? where num=? and passwd=?";
			template.update(sql, bean.getContent(), bean.getNum(), bean.getPasswd());
		}

		//게시글 삭제
		public void boardDelete(BoardBean bean) {
			String sql="delete from board3 where num=? and passwd=?";
			template.update(sql, bean.getNum(), bean.getPasswd());
			
		}
		
}//클래스 끝








