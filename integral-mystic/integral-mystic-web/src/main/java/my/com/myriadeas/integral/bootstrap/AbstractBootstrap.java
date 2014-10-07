package my.com.myriadeas.integral.bootstrap;

import java.io.IOException;

import javax.annotation.PostConstruct;

import my.com.myriadeas.integral.data.JpaUtils;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBootstrap implements BootstrapBean {

	/**
	 * Get resource stream from bootstrap path classpath:data/bootstrap
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public byte[] getResource(String path) throws IOException {
		return IOUtils.toByteArray(this.getClass().getClassLoader()
				.getResourceAsStream("data/bootstrap/" + path));
	}

	@Autowired
	private JpaUtils jpaUtilsImpl;

	@PostConstruct
	public void afterPropertiesSet() throws Exception {
		if (jpaUtilsImpl.isDatabaseEmpty()) {
			init();
		}
	}

}
