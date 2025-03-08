/*Esta clase implementa la interfaz ItemProcessor*/

package com.example.demospringbatch.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import com.example.demospringbatch.model.Persona;

/*a ItemProcessor se le indica cual será el objeto de entrada y salida, si pueden ser de diferente tipo*/

public class PersonaItemProcessor implements ItemProcessor<Persona,Persona>{

	/*agregamos la variable LOG,LoggerFactory permite crear mensajes para el seguimiento o registro de la ejecución de una aplicación*/
	
	private static final Logger LOG = LoggerFactory.getLogger(PersonaItemProcessor.class);
	
	/*Override indica al compilador que revise que lo que estamos sobreescribiendo representa a un mismo método con la misma firma en la superclase*/
	
	@Override
	public Persona process(Persona item) throws Exception {
		String primerNombre = item.getPrimerNombre().toUpperCase();
		String segundoNombre = item.getSegundoNombre().toUpperCase();
		String telefono = item.getTelefono();
		
		Persona persona = new Persona(primerNombre, segundoNombre, telefono);
		
		LOG.info("Convirtiendo ("+item+") a ("+persona+")");
		
		return persona;
	}

}
