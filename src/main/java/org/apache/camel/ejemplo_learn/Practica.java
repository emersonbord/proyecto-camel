package org.apache.camel.ejemplo_learn;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class Practica {
    
    public static void main(String[] args) throws Exception {
        CamelContext contexto = new DefaultCamelContext();

        contexto.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("file:src/data?noop=true")
            .choice()
                .when(xpath("/person/city = 'London'"))
                    .log("UK message - Mensaje de Reino Unido")
                    .to("file:target/messages/uk")
                .otherwise()
                    .log("Other message - Otro mensaje")
                    .to("file:target/messages/others");
            }
            
        });
        contexto.close();
        while (true) {
            contexto.start();
            
        } 
        
    }
}
