package my.com.myriadeas.integral.cataloguing2.application.service;

import java.io.UnsupportedEncodingException;

import my.com.myriadeas.integral.cataloguing2.application.service.command.CreateResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.DeleteResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.FinalizeResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.ReviseResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.SendToDeleteResourceDescriptorCommand;
import my.com.myriadeas.integral.cataloguing2.application.service.command.UpdateResourceDescriptorCommand;

public interface ResourceDescriptorWriteService {

	public String createResourceDescriptor(
			CreateResourceDescriptorCommand createResourceDescriptorCommand);

	public void updateResourceDescriptor(
			UpdateResourceDescriptorCommand updateResourceDescriptorCommand)
			throws UnsupportedEncodingException;

	public void finalizeResourceDescriptor(
			FinalizeResourceDescriptorCommand finalizeResourceDescriptorCommand)
			throws UnsupportedEncodingException;

	public void reviseResourceDescriptor(
			ReviseResourceDescriptorCommand reviseResourceDescriptorCommand)
			throws UnsupportedEncodingException;

	public void deleteResourceDescriptor(
			DeleteResourceDescriptorCommand deleteResourceDescriptorCommand);

	public void sendToDeleteResourceDescriptor(
			SendToDeleteResourceDescriptorCommand sendToDeleteResourceDescriptorCommand);

}
