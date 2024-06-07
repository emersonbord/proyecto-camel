package org.apache.camel.rest;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class RestProducerRoute extends RouteBuilder{

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet");
        rest("/borda")
        .get()
        .to("direct:blog-ejemplo");

        from("direct:blog-ejemplo")
        .transform().constant("Este es un ejemplo rest producer!!!");
        
    }

    
    
}
