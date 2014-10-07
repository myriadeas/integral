package my.com.myriadeas.integral.dao;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;


@ContextConfiguration("classpath:application-context-integral-integration.xml")
public abstract class AbstractDataOnDemandTest extends AbstractTransactionalJUnit4SpringContextTests{

}
