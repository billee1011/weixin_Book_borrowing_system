package weixinbusiness.weixinForLoginByweixin;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import state.StateForwx;
import weixinForUser.DataBaseForUser;
import weixinNeedDate.user.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	        String code=request.getParameter("code");
	        response.setContentType("text/html;charset=utf-8");
			PrintWriter rPrintWriter = response.getWriter();
			Properties file=(Properties) request.getServletContext().getAttribute("file");
	        System.out.println(code);
	        if(code!=null){
	        	 
	        String path="https://api.weixin.qq.com/sns/jscode2session?appid=wx0a07ec31225156b0&secret=ba69cb66ae3be4947aabb771dc9e9799&js_code="+code+"&grant_type=authorization_code";
	       //String path="https://api.weixin.qq.com/sns/jscode2session?appid=wx0cfe4437001003dc&secret=561955d6fe8e70e0bd3e8a66aa034018&js_code="+code+"&grant_type=authorization_code";
	        URL url=new URL(path);
	        HttpsURLConnection conn =(HttpsURLConnection)url.openConnection();
	        conn.setRequestMethod("GET");
	        conn.setConnectTimeout(5000);
	         int returncode =conn.getResponseCode();
	         if(returncode==200){
	        	 InputStream in=conn.getInputStream();
	        	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	     		int len = -1;
	     		byte[] buffer = new byte[1024]; //1kb
	     		while((len=in.read(buffer))!=-1){
	     			baos.write(buffer, 0, len);
	     		}
	     		in.close();
	     		String content = new String(baos.toByteArray(),"utf-8");
	     		String  userId=(String)JSONObject.parseObject(content).get("openid");
	     		System.out.println(userId);
	     		DataBaseForUser<User> dataBaseForUser= new DataBaseForUser<>(file);
	    		ResultSet resultSet=dataBaseForUser.selectOther("select * from user where weixin=?",userId);
	    		//
	    		//System.out.println(resultSet);
	    		try {
	    			if(resultSet.next()){
	    				//System.out.println(resultSet.getString(1));
					    if(!resultSet.getString(1).equals("-1")){
					    	rPrintWriter.append(StateForwx.getStateLoginByWenXin(1,resultSet.getString(1),null));
					    }else{
					    	rPrintWriter.append(StateForwx.getStateLoginByWenXin(2,null,userId));
					    }
					    dataBaseForUser.closeAll();
					}else{
						User u=new User("-1",null,null,null,userId);
						int n=dataBaseForUser.insertone(u);
						dataBaseForUser.closeAll();
						if(n!=0){
							rPrintWriter.append(StateForwx.getStateLoginByWenXin(2,null,userId));	
						}else{
							rPrintWriter.append(StateForwx.getStateLoginByWenXin(3,null,null));
						}
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	     		//System.out.println(content);
	         }else{
	        	 //服务器异常
	        	 rPrintWriter.append(StateForwx.getStateLoginByWenXin(3,null,null));
	         }
	        	 
	         }
	           
	           
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
