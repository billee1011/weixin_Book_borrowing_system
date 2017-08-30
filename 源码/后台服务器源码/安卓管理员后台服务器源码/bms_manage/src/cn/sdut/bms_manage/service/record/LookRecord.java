package cn.sdut.bms_manage.service.record;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdut.bms_manage.bean.Manager;
import cn.sdut.bms_manage.bean.Record;
import cn.sdut.bms_manage.dao.impl.DataBaseImpl;
@WebServlet("/LookRecord")
public class LookRecord extends HttpServlet {



	/**
	 * Constructor of the object.
	 */
	public LookRecord() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ResultSet b=null;
		response.setContentType("text/html");
		
		
		  String employnum=request.getParameter("employnum");
		  System.out.println(employnum);
		  String type=request.getParameter("type");
		  String loginid=request.getParameter("loginid");
		    if(employnum!=null&&type!=null&&loginid!=null){
			  DataBaseImpl<Manager> base = new DataBaseImpl<>();
			  OutputStream out = response.getOutputStream();
			   ObjectOutputStream opt=new ObjectOutputStream(out);
			   if(base.ishasOne("select * from staff where loginid=?",loginid)==false){
				   opt.writeObject(new Record("0","0","0"));
				   opt.writeObject(null);
					opt.flush();
					opt.close();
				    base.closeAll();
			   }else{
				   
				     if(type.equals("1")){
				    		b = base.selectOther("select date,userid,isbn from historyforborrowbook where manager=?",employnum);
				    		
				     }else{
				    		b = base.selectOther("select date,userid,ISBN from historyforreturnbook where manager=?",employnum); 
				     }
				     try {   
							while(b.next()){
								DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");//声明日期格式
								Calendar calendar = Calendar.getInstance();//获得当前时间
								Long time =Long.valueOf(b.getString(1));//获得毫秒
								calendar.setTimeInMillis(time);//设置要转化的毫秒
								//System.out.println(Calendar.getInstance().getTimeInMillis());//输出毫秒
								String now=formatter.format(calendar.getTime());//
								Record r=new Record(now, b.getString(2), b.getString(3));
								opt.writeObject(r);
								opt.flush();
							}
							opt.writeObject(null);
							opt.flush();
							opt.close();
							base.closeAll();
				     
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
			   }
			
			    
		  }else{
			  return;
		  }
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	  doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
