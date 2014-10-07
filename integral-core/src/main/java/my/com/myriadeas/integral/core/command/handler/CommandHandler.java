package my.com.myriadeas.integral.core.command.handler;



public interface CommandHandler<C> {

	public void handle(C command);
	
}
