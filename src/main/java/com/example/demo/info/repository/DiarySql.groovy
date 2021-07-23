package com.example.demo.info.repository

class DiarySql {
	public static final String SELECT = """
		SELECT id, num, content FROM diary;
	""";

	public static final String INSERT = """
		INSERT INTO diary (id, num, content) values (:id, :num, :content);
	""";

	public static final String UPDATE = """
		UPDATE diary SET content = :content WHERE 1=1  
	""";

	public static final String DELETE = """
		DELETE FROM diary WHERE 1=1  
	""";

	public static final String ID_CONDITION = """
		AND id = :id
	""";
	
	public static final String NUM_CONDITION = """
		AND num = :num
	""";
}