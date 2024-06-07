package org.apache.camel.rest;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class EndpointRest extends RouteBuilder{

    CamelContext context = new DefaultCamelContext();
    @Override
    public void configure() throws Exception {
       /* rest("/cole")
        .get("pruebaRest")
        .produces("text/plain")
        .outType(String.class)
        .param()
        .name("name")
        .type(RestParamType.query)
        .endParam()
        .to("direct:pruebaRest");

        from("direct:pruebaRest")
        .setBody(simple("Hola esta es una prueba, ${header.name}"));  */

        rest("/say")
        .get("/hello").to("direct:hello")
        .get("/bye").consumes("application/json").to("direct:bye")
        .post("/bye").to("mock:update");

    from("direct:hello")
        .transform().constant("Hello World");

    from("direct:bye")
        .transform().constant("Bye World");


    }
    
}
