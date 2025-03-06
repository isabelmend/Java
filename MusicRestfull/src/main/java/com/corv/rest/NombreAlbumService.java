package com.corv.rest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.corv.musicrestfull.provider.ApplicationContextProvider;

import dao.NombreAlbumDAO;
import dto.ErrorDTO;
import dto.NombreAlbumDTO;
import dto.NombreAlbumRespuestaDTO;
import exception.CorvException;

@Path("/NombreAlbumService")
public class NombreAlbumService {
	
	private static final Logger LOGGER =LogManager.getLogger(NombreAlbumService.class);
	private NombreAlbumDAO nombrealbumDao;
	
	public NombreAlbumService () {
		try {
			nombrealbumDao =
		ApplicationContextProvider.getApplicationContext().getBean("albumdao", NombreAlbumDAO.class);
		} catch (Exception e) {
			LOGGER.error("Falló jalar el bean" + e.getMessage());
			e.printStackTrace();
		}
	}
	
	@GET
	@Path("/getNombreAlbum")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNombreAlbum() {
		LOGGER.info("Se va a ejecutar el método getNombreAlbum");
		NombreAlbumRespuestaDTO nombreAlbumRespuestaDto = new NombreAlbumRespuestaDTO();
		ErrorDTO errorDTO = new ErrorDTO();
		NombreAlbumDTO[] arrayDao = null;
		try {
			List<NombreAlbumDTO> listDao = nombrealbumDao.selectComentarios();
			arrayDao = listDao.toArray(new NombreAlbumDTO[listDao.size()]);
			nombreAlbumRespuestaDto.setNombreAlbumDTO(arrayDao);
		} catch (CorvException e) {
			LOGGER.error("Manejando un error de tipo corvelis ya manejado");
			errorDTO.setError(true);
			errorDTO.setMensaje(e.getMensaje());
		} catch (Exception e) {
			errorDTO.setError(true);
			errorDTO.setMensaje(e.getMessage());
			LOGGER.error("ERROR: " + e.getMessage());
			LOGGER.error("Fallo en getComentario de NombreAlbumService" + e.getMessage());
			e.printStackTrace();	
		}
		LOGGER.info("Se terminó de ejecutar getComentario");
		nombreAlbumRespuestaDto.setErrorDTO(errorDTO);
		return Response.ok(nombreAlbumRespuestaDto).build();
	}

}