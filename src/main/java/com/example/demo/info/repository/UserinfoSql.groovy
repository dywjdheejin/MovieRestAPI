package com.example.demo.info.repository

class UserinfoSql {
	public static final String SELECT = """
		SELECT id, pw, name FROM userinfo;
	""";
	
	public static final String INSERT = """
		INSERT INTO userinfo (id, pw, name) values (:id, :pw, :name);
	""";
	
	public static final String UPDATE = """
		UPDATE userinfo SET pw = :pw, name = :name WHERE 1=1  
	""";

	public static final String DELETE = """
		DELETE FROM userinfo WHERE 1=1  
	""";

	public static final String ID_CONDITION = """
		AND id = :id
	""";
}
