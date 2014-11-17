package my.com.myriadeas.integral.cataloguing2.interfaces.facade;

import java.io.UnsupportedEncodingException;

import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.CreateResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.DeleteResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.FinalizeResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.ReviseResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.UpdateResourceDescriptorRequest;
import my.com.myriadeas.integral.cataloguing2.interfaces.facade.request.VerifyRecordRequest;

import org.marc4j.marc.Record;

public interface ResourceDescriptorFacade {

	public Long createResourceDescriptor(
			CreateResourceDescriptorRequest createResourceDescriptorRequest);

	public void updateResourceDescriptor(
			UpdateResourceDescriptorRequest updateResourceDescriptorRequest)
			throws UnsupportedEncodingException;

	public void finalizeResourceDescriptor(
			FinalizeResourceDescriptorRequest finalizeResourceDescriptorRequest)
			throws UnsupportedEncodingException;

	public void finalizeResourceDescriptor(Long id, Record record)
			throws UnsupportedEncodingException;

	public void reviseResourceDescriptor(
			ReviseResourceDescriptorRequest reviseResourceDescriptorRequest)
			throws UnsupportedEncodingException;

	public void deleteResourceDescriptor(
			DeleteResourceDescriptorRequest deleteResourceDescriptorRequest);

	public String verifyRecord(
			VerifyRecordRequest verifyResourceDescriptorRequest);

	public String convertRecord(Record record);

}
