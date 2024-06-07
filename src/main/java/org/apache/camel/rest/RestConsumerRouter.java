package org.apache.camel.rest;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.springframework.stereotype.Component;

@Component
public class RestConsumerRouter extends RouteBuilder{
CamelContext contexto = new DefaultCamelContext();
    @Override
    public void configure() throws Exception {
        from("timer://test-rest-api?period=10000")
        .log("Llamando Rest API...")
        .setHeader(Exchange.HTTP_METHOD, simple("GET"))
        .to("http://localhost:9090/api/auto")
        .process(new Processor() {

            @Override
            public void process(Exchange exchange) throws Exception {
                String output = exchange.getIn().getBody(String.class);
                System.out.println("Salida es: " + output);
            }
            
        });
        contexto.start();
    }
    
}