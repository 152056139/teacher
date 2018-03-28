package com.gray;

import java.sql.*;
import java.util.*;
import org.junit.jupiter.api.*;
import net.sf.json.JSONArray;
import database.Users;

public class TestClass {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		testCreateJsonArray();
	}
	@Test
    public static void testCreateJsonArray() {
        //Java集合
        List<Users> list = new ArrayList<Users>();
        list.add(new Users("zhangSan","13"));
        list.add(new Users("liSi","14"));
        //创建json集合
        JSONArray jsonArray = JSONArray.fromObject(list);
        System.out.println(jsonArray.toString());
    }
}
