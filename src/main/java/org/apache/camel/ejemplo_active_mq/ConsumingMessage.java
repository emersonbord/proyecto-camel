package org.apache.camel.ejemplo_active_mq;
//RECIBIR CUALQUIER MENSAJE DE ACTIVEMQ

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ConsumingMessage {
 
    public static void main(String[] args) throws Exception {
        
        CamelContext context = new DefaultCamelContext();

        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("activemq:queue:cola_ejemplo") //Recibimos el mensaje desde activemq
                .log("log:recibiendo mensaje => ${body}");
            }
            
        });
        context.start();
        ConsumerTemplate consumerTempalte = context.createConsumerTemplate();
        String mensaje = consumerTempalte.receiveBody("seda:end", String.class);
        System.out.println(mensaje);
        context.close();
    }
    
}
