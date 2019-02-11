package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Receiver {

	public static Command cmd = new Command();

	
	public static void init(
			HttpServletRequest request, HttpServletResponse response){
		System.out.println("===== 2.리시버진입 =====");
		System.out.println("cmd::"+request.getParameter("cmd"));
		System.out.println("dir::"+request.getParameter("dir"));
		System.out.println("page::"+request.getParameter("page"));
	
		cmd = Commander.order(request,response);
		System.out.println("리시버 내부 cmd:::"+Receiver.cmd.getView());
	}
}
