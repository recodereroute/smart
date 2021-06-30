package repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

public class EmployeeRepository {
	@Autowired
	SqlSession sqlSession;
	//mappers.employee.employeeMapper 이 맞지않은지? - mapper의 namespace에서 지정해준값으로 이동하니까 이게 맞음
	String namespace = "mappers.employeeMapper";
	String statement;
	public String empNo() {
		statement = namespace + ".empNo";
		return sqlSession.selectOne(statement);
	}
}