package my.com.myriadeas.integral.beanvalidation;

import org.springframework.beans.factory.FactoryBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class ObjectMapperFactoryBean implements FactoryBean<ObjectMapper> {

	private static ObjectMapper instance;

	@Override
	public ObjectMapper getObject() throws Exception {
		if (instance == null) {
			instance = new ObjectMapper();
			instance.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		}
		return instance;
	}

	@Override
	public Class<?> getObjectType() {
		return ObjectMapper.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return true;
	}

}
