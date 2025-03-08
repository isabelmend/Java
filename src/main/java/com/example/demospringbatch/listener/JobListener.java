/*Listener nos permite conocer el estado de ejecución de nuestro job*/

package com.example.demospringbatch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.demospringbatch.model.Persona;

/*Se utiliza para indicar una clase como componente. Significa que Spring detectará automáticamente estas clases para la inyección de dependencia*/

@Component
public class JobListener extends JobExecutionListenerSupport {
	
	private static final Logger LOG = LoggerFactory.getLogger(JobListener.class);
	
	private JdbcTemplate jdbcTemplate;

/*@Autowired indica que el constructor debe inyectar las dependencias al crear la instancia (bean)*/
	
	@Autowired
	public JobListener(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOG.info("FINALIZO EL JOB!! Verifica los resultados:");
			
			jdbcTemplate
				.query("SELECT primer_nombre, segundo_nombre, telefono FROM persona", 
						(rs,row) -> new Persona(rs.getString(1), rs.getString(2), rs.getString(3)))
				.forEach(persona -> LOG.info("Registro < "+persona+" >"));
		}
	}

}
