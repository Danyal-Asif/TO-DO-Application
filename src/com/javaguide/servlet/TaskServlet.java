package com.javaguide.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaguide.Dao.TaskDao;
import com.javaguide.task.Task;
import com.javaguide.task.Task.Priority;
import com.javaguide.task.Task.Status;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaskDao taskDao;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init() {
    	taskDao=new TaskDao(); 
    }
    
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action=request.getServletPath();
		try {
			switch(action) {
			case "/new":
				showNewForm(request,response);
				break;
			case "/insert":
				insertTask(request,response);
				break;
			case "/delete":
				deleteTask(request,response);
				break;
			case "/edit":
				showEditForm(request,response);
				break;
			case "/update":
				updateTask(request,response);
				break;
			default :
				listTask(request,response);
				break;
			}
		}
		catch(SQLException e) {
			throw new ServletException(e);
		}
	}
	
	private void listTask(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException,SQLException{
		List<Task> list=taskDao.selectAllTasks();
		request.setAttribute("listTask", list);
		request.setAttribute("date", LocalDate.now());
		RequestDispatcher dispatcher=request.getRequestDispatcher("task-list.jsp");
		dispatcher.forward(request, response);
	}
	private void showNewForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		RequestDispatcher requestDispatcher=request.getRequestDispatcher("task-form.jsp");
		requestDispatcher.forward(request,response);
		
	}
	
	private void showEditForm(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException,SQLException {
		long id=Long.parseLong(request.getParameter("id"));
		Task rtask=taskDao.selectTask(id);
		RequestDispatcher dispatcher=request.getRequestDispatcher("task-form.jsp");
		request.setAttribute("task", rtask);
		dispatcher.forward(request, response);
	}
	
	private void updateTask(HttpServletRequest request,HttpServletResponse response)throws IOException,SQLException{
		Long id=Long.parseLong(request.getParameter("id"));
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String due_date=request.getParameter("due_date");
		String status=request.getParameter("status");
		String priority=request.getParameter("priority");
		
		Task task=new Task(id,title,description,LocalDate.parse(due_date), Priority.valueOf(priority),  Status.valueOf(status));
		taskDao.updateTask(task, id);
		response.sendRedirect("list");
		
	}
	
	private void insertTask(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
		String id=request.getParameter("id");
		String title=request.getParameter("title");
		String description=request.getParameter("description");
		String due_date=request.getParameter("due_date");
		String status=request.getParameter("status");
		String priority=request.getParameter("priority");
		Task task=new Task(Long.parseLong(id),title,description,LocalDate.parse(due_date), Priority.valueOf(priority),  Status.valueOf(status));
		taskDao.insertTask(task);
		response.sendRedirect("list");
	}
	private void deleteTask(HttpServletRequest request,HttpServletResponse response) throws SQLException,IOException {
		long id=Long.parseLong(request.getParameter("id"));
		taskDao.deleteTask(id);
		response.sendRedirect("list"); 
	}
		
	}

	


