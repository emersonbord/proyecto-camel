package org.apache.camel.ejemplo_copia;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class CopiaArchivo {

    public static void main(String[] args) throws Exception {
        
        CamelContext contexto = new DefaultCamelContext();

        contexto.addRoutes(new RouteBuilder() {

            @Override
            public void configure() throws Exception { //<- es el enrutador
                // Aquí se guardan cualquier tipo de rutas

                //Endpoint from, to, desde  hacia, sirve para enviar y recibir intercambios de mensajes.
                from("file:carpeta_inicio") //nombre carpeta input_box - argumento ?noop=true
                //Camel incorpora el componente llamado "file" <- file se encargará de todas las operaciones de archivos
                //RESUMEN: Componente=file - endpoint=input_carpeta
                .to("file:carpeta_final");

                //Todo los que esté en la carptea input_box, o lo que llegue en ella se copiará en ella tan pronto sea posible, y los archi
                //archivos se mueven a la output_box

            }
        });
        //Iniciar el contexto en un loop infinito
        contexto.close();
        while (true) {
            contexto.start();
        }

        

    }
    
}
