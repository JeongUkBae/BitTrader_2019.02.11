package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import enums.Action;

public class Commander {
	public static Command order(HttpServletRequest request, 
			HttpServletResponse responset) {
		System.out.println("===== 3.커맨더 진입 =====");
		Command cmd = null;
		System.out.println("cmd::"+request.getParameter("cmd"));
		switch(Action.valueOf(request.getParameter("cmd").toUpperCase())) {
		case MOVE:
			System.out.println("===== 3.커맨더 진입 > 스위치 MOVE 진입 =====");
			cmd = new Command(request, responset);
			
			break;
		case REGISTER: case SIGNUP :
			cmd = new CreateCommand(request, responset);
			
			break;
		case ACCESS: case SIGNIN:
			cmd = new ExistCommand(request, responset);
			
			break;

		default :
			
			break;
		
		}
		System.out.println("커맨더내부 :"+Receiver.cmd.getView());
		return cmd;
	}
}
