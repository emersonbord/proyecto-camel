package org.apache.camel.ejemplo_producer_consumer_template;

import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;

public class ProducerConsumerTemplateEj {

    /**La ConsumerTemplate interfaz le permite recibir intercambios de mensajes desde 
     * puntos finales en una variedad de formas diferentes para facilitar el trabajo 
     * con instancias de Camel Endpoint desde código Java */

     /**Producer produce algo envía algo al punto final, y el consumer consume algo enviado por el productor */

    public static void main(String[] args) throws Exception {
        CamelContext contexto = new DefaultCamelContext();

        contexto.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception {
               //ahora definimos lo que el enrutador debe hacer
                from("direct:start") //<- componente directo, invocación directa y sincrónica de cualquier 
                                          //consumidor cuando un productor envía un intercambio de mensajes.
            //parte 2: agregar Processor - se utiliza para implementar consumidores de intercambios de mensajes o para implementar un Traductor de mensajes , y otros casos de uso.
                .process(new Processor() {

                    @SuppressWarnings("deprecation")
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        //Objeto Exchange podemos recibir cualquier mensaje enviado al mismo punto
                        //Obtenemos el punto de entrada y su cuerpo
                        String message = exchange.getIn().getBody(String.class);
                        //recibimos el mensaje
                        message = message + " por processor";
                        //Configuramos el objeto Exchange neuvamente
                        exchange.getOut().setBody(message);

                        // IMPRIMIR MENSAJE
                        //System.out.println("Prueba Processor");
                    }
                    
                })
            
                .to("seda:end"); //<- serie y sugerencia, componente SEDA proporciona una invocación asincrónica 
                                    //de cualquier consumidor cuando un productor envía un intercambio de mensajes.
            }
            
        });
        contexto.start();

        //Creamos la plantilla producer para enviar el mensaje
        ProducerTemplate produceTemplate = contexto.createProducerTemplate();
        //Enviar mensaje al primer endpoint, utilizando sendBody
        produceTemplate.sendBody("direct:start", "Hola, esta es una prueba");

        //Cuando el mensaje llegue al primer endpoint, enviará el mensaje a seda:end
        ConsumerTemplate consumerTemplate = contexto.createConsumerTemplate();
        //El resultado es una cadena deobjeto que puede ser almacenada en una variable
        String message = consumerTemplate.receiveBody("seda:end", String.class);
        System.out.println(message);
        contexto.close();
        
    }


   
    
}
