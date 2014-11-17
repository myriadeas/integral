package my.com.myriadeas.integral.index.infrastructures.route;

import org.apache.camel.builder.RouteBuilder;

public class IndexRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		from("direct:index.index").beanRef("indexFacadeImpl", "index");
		from("direct:index.unindex").beanRef("indexFacadeImpl", "unindex");
	}

}
