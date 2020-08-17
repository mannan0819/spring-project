package com.example.demo.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class mysql implements Database{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	DataSource dataSource;  
	
    @Autowired
    private UserRepository userRepository;
	
	@Override
	public Map<String, Object> lstUser() {
		// TODO Auto-generated method stub
		//userRepository.save(new User("bob","po"));
		//jdbcTemplate.setDataSource(dataSource);
		String sqlstatement = "Select * from user"; 
		String sql = "INSERT INTO user (id, Firstname, Lastname) VALUES ('2','P','o')";
		System.out.println(jdbcTemplate.getDataSource().toString());
		System.out.println(sqlstatement);
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sqlstatement);
		//jtem.execute(sqlstatement);
		//int rows = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM USER", Integer.TYPE);
		System.out.println(rows);
		Map<String, Object> f = rows.get(0);
		
		return f;
	}
	
	

}
