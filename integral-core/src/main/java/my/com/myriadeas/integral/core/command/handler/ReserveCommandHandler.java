package my.com.myriadeas.integral.core.command.handler;

import my.com.myriadeas.integral.circulation.command.CheckInEventCommand;
import my.com.myriadeas.integral.circulation.command.ReleaseEventCommand;

public interface ReserveCommandHandler {

	public void handle(CheckInEventCommand command);
	public void handle(ReleaseEventCommand command);
}
