package bcd.exercise.pwd;

public class App {
	
	public static void main(String[] args) throws Exception{
		
		String username = "sample2";
		String password = "passwd2";
		
		//Password.create(username, password);
	
		boolean isLogin = new ControllerLogin().verify(username, password);
		
		if (isLogin)
			System.out.println( "success" ); 
		else
			System.err.println( "fail" );
		
		
	}

}
