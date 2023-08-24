package com.javaguide.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import com.javaguide.task.Task;
import com.javaguide.task.Task.Priority;
import com.javaguide.task.Task.Status;
import java.util.List;

public class TaskDao {
	static final String db="jdbc:mysql://localhost:3306/?user=root";
	static final String user="root";
	static final String pass="1122";
	
//	private static final String INSERT_TASK="insert into new_schema.task values (?,?,?,?,?,?);";
//	
//	private static final String SELECT_TASK_BY_ID="select * from new_schmea.task where id=?;";
//	
//	private static final String SELECT_ALL_TASK="select * from new_schema.task;";
//	
//	private static final String DELETE_TASK_BY_ID="delete from new_schema.task where id=?";
//	
//	private static final String UPDATE_TASK_BY_ID="update new_schema.task set title=?,description=?,due_date=?,status=?,priority=? where id=?";
	
	public TaskDao() {}
	protected Connection getConnection() {
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(db,user,pass);
		}
		catch(SQLException e) {e.printStackTrace();}
		catch(ClassNotFoundException e) {e.printStackTrace();}
		return con;
	}
	
	public void insertTask(Task task) throws SQLException {
		System.out.println("Inserting new Task");
		try(Connection con=getConnection();
			Statement stmt=con.createStatement()){
			String insertStatement="insert into new_schema.task values ("+task.getId()+",'"+task.getTitle()+"',"+"'"+task.getDescription()+"',"+"'"+task.getDue_date()+"',"+"'"+task.getStatus()+"',"+"'"+task.getPriority()+"'"+");";
			stmt.executeUpdate(insertStatement);
			System.out.println("New task added.");
		}
		catch(SQLException e) {e.printStackTrace();}
	}
	
	public Task selectTask(Long id){
		Task task=null;
		try(Connection con=getConnection();
				Statement stmt=con.createStatement()){
			System.out.println("Reteriving Task from the Table : Task");
			 String fetchStatement="select * from new_schema.task where task_id ="+id+";";
			 ResultSet rs=stmt.executeQuery(fetchStatement);
			 while(rs.next())
				 task= new Task(rs.getLong("task_id"),rs.getString("title"),rs.getString("description"),rs.getDate("due_date").toLocalDate(),(Priority.valueOf(rs.getString("priority"))),(Status.valueOf(rs.getString("status"))));
		}
		catch(SQLException e) {e.printStackTrace();}
		return task;
	}
	
	public List<Task> selectAllTasks() {
		List<Task> tasks=new ArrayList<Task>();
		 try(Connection con=getConnection();
				 Statement stmt=con.createStatement();
				 ){
			 System.out.println("Reteriving all tasks from the Task\n");
			 String fetchStatement="select * from new_schema.task";
			 ResultSet rs=stmt.executeQuery(fetchStatement);	
			 
				 while(rs.next()) {
					 long id=rs.getLong("task_id");
					 String title=rs.getString("title");
					 String description=rs.getString("description");
					 LocalDate date=rs.getDate("due_date").toLocalDate();
					 Priority priority=Priority.valueOf(rs.getString("priority"));
					 Status status=Status.valueOf(rs.getString("status"));
					 tasks.add(new Task(id,title,description,date,priority,status));
				 }
		 }
		 catch(SQLException e) {
			 e.printStackTrace();
		 }
		return tasks;
}
	
	public boolean deleteTask(long id) throws SQLException{
		boolean rowDeleted=false;
		try(Connection con=getConnection();
				 Statement stmt=con.createStatement();
				 ){
			String deleteStatement="delete from new_schema.task where task_id="+id;
			rowDeleted=stmt.executeUpdate(deleteStatement)>0;
		}
		catch(SQLException e) {e.printStackTrace();}
		return rowDeleted;
	}
	
	public boolean updateTask(Task task,long id) throws SQLException{
		boolean rowUpdated=false;
		try(Connection con=getConnection();
				 Statement stmt=con.createStatement();
				 ){
			String updateStatement="update new_schema.task set title='"+task.getTitle()+
					 "',description='"+task.getDescription()+"',due_date='"+task.getDue_date()
					 +"',status='"+task.getStatus()+"',priority='"+task.getPriority()+"' where task_id="+id;
			rowUpdated=stmt.executeUpdate(updateStatement)>0;
		}
		catch(SQLException e) {e.printStackTrace();}
		return rowUpdated;
	}
	
}
