package com.gray;

import java.sql.*;

import database.MysqlBase;

public class TestClass {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MysqlBase mysqlBase = new MysqlBase();
		Connection connection =  mysqlBase.createConnect();
		ResultSet resultset =  mysqlBase.search("select * from user;", connection);
		
		while(resultset.next()){
            // 通过字段检索
            String name  = resultset.getString("user_name");
            String password = resultset.getString("user_password");

            // 输出数据
            System.out.print("姓名: " + name);
            System.out.print("密码: " + password);
            System.out.print("\n");
        }
		mysqlBase.close(connection);
	}

}
