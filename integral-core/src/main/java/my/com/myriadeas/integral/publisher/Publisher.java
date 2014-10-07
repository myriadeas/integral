package my.com.myriadeas.integral.publisher;


public interface Publisher {

	public void publish(String destination, Object eventCommand);
}
