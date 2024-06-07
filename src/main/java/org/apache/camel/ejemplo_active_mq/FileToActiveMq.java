package org.apache.camel.ejemplo_active_mq;

import javax.jms.ConnectionFactory;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class FileToActiveMq {
    public static void main(String[] args) throws Exception {
        CamelContext context = new DefaultCamelContext();

        //Creamos y obtenemos una conexión para ActiveMq
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        context.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));
        context.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
                from("file:carpeta_inicio?noop=true")
                .to("activemq:queue:cola_ejemplo");
            }
            
        });
        context.close();
        while(true)
            context.start();
        
    }
}
