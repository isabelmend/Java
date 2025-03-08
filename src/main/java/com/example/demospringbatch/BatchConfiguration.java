package com.example.demospringbatch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.example.demospringbatch.listener.JobListener;
import com.example.demospringbatch.model.Persona;
import com.example.demospringbatch.processor.PersonaItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Autowired
	public JobBuilderFactory jobBuilder;
	
	@Autowired
	public StepBuilderFactory stepBuilder;

	/*Un batch se compone de uno o varios steps y cada step se compone de un itemreader, itemwriter e itemprocessor (el processor es el único opcional)*/
	/*Primero definimos un reader, que obtiene la información en este caso de un archivo .csv, asigna cada columna a cada propiedad definida en Persona*/
	
	@Bean
	public FlatFileItemReader<Persona> reader(){
		return new FlatFileItemReaderBuilder<Persona>()
				.name("PersonaItemReader")
				.resource(new ClassPathResource("sample-data.csv"))
				.delimited()
				.names(new String[] {"primerNombre", "segundoNombre", "telefono"})
				.fieldSetMapper(new BeanWrapperFieldSetMapper<Persona>() {{
					setTargetType(Persona.class);
				}})
				.build();
	}
	
	/*PersonaItemProcessor se encarga de transformar a mayúsculas los datos de las personas*/
	
	@Bean
	public PersonaItemProcessor processor() {
		return new PersonaItemProcessor();
	}
	
	/*JdbcBatchItemWriter se encarga de escribir cada uno de los registros que vaya siendo leído por springbatch en nuestra base de datos*/
	
	@Bean
	public JdbcBatchItemWriter<Persona> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Persona>()
				/*BeanPropertyItemSqlParameterSourceProvider ya que las propiedades de nuestra clase persona son las que se van a guardar en nuestra base de datos*/
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO persona (primer_nombre, segundo_nombre, telefono) VALUES (:primerNombre, :segundoNombre, :telefono)")
				.dataSource(dataSource)
				.build();
	}
	
	/*creamos el método Job, usando listener para indicarle cúal es el listener que se va a ejecutar en caso de que el estado de nuestro Job cambie*/
	/*en la configuración del job hay que indicarle cuál es el paso a ejecutar y su flujo*/
	
	@Bean
	public Job importPersonaJob(JobListener listener, Step step1) {
		return jobBuilder.get("importPersonaJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(step1)
				.end()
				.build();
	}
	
	/*Definición del step, el cual tiene un reader, processor y writer*/
	@Bean
	public Step step1(JdbcBatchItemWriter<Persona> writer) {
		return stepBuilder.get("step1")
				.<Persona,Persona> chunk(10)
				.reader(reader())
				.processor(processor())
				.writer(writer)
				.build();
	}

}
