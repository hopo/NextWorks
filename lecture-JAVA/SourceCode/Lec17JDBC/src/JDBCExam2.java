import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.List;

import kr.or.nextit.conn.ConnectionPool;
import kr.or.nextit.member.dao.MemberDao;
import kr.or.nextit.member.model.Member;

public class JDBCExam2 {

	public static void main(String[] args) throws IOException {

		// MemberDao memberDao = new MemberDao();
		MemberDao memberDao = MemberDao.getInstance(); // ;; Singleton Pattern instance

		ConnectionPool connectionPool = ConnectionPool.getInstance();

		// ! #2 데이터베이스 연결
		Connection conn = null;

		// String query = null;

		try {

			// ! 커넥션 객체 생성(BD connecting)
			conn = connectionPool.getConnection();
			System.out.println("커넥션 성공.");

			// ! #3 쿼리 실행
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			Member member = new Member();

			// ; 값을 입력 받습니다
			System.out.print("ID: ");
			member.setMem_id(reader.readLine());

			System.out.print("PASS: ");
			member.setMem_pwd(reader.readLine());

			System.out.print("NAME: ");
			member.setMem_name(reader.readLine());

			System.out.print("PHONE: ");
			member.setMem_phone(reader.readLine());

			System.out.print("EMAIL: ");
			member.setMem_email(reader.readLine());

			int updCnt = memberDao.insertMember(conn, member);

			System.out.println(updCnt + " 명이 정상 등록 되었습니다.");

			List<Member> memberList = memberDao.selectMemberList(conn);

			System.out.println("=============== 회원목록 ===============");
			System.out.println("아이디\t 비밀번호\t 이름\t 전화번호\t 이메일\t 가입일자\t");

			for (Member m : memberList) {
				System.out.printf("%s\t %s\t %s\t %s\t %s\t %s\n", m.getMem_id(), m.getMem_pwd(), m.getMem_name(),
						m.getMem_phone(), m.getMem_email(), m.getReg_date());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// ! #4 연결 종료
			try {
				if (conn != null) {
					// conn.close(); // ;; 풀을 이용하면 no close()
					connectionPool.releaseConnection(conn);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
