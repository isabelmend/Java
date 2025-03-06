package dao;

import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import dto.NombreAlbumDTO;

import org.springframework.jdbc.core.JdbcTemplate;
import exception.CorvException;
import rowMapper.RegresaListaRowMapper;

public class NombreAlbumDAO {
private JdbcTemplate jdbcTemplate;
private static final Logger LOGGER = LogManager.getLogger(NombreAlbumDAO.class);

public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
	this.jdbcTemplate = jdbcTemplate;
}

public List<NombreAlbumDTO> selectComentarios() throws Exception {
	String sql = "SELECT * FROM album_comentario_v2_12 LIMIT 10";
	try {
		int a=0;
		if(a<0)
		{
			throw new CorvException("Saldo insuficiente");
		}
		return this.jdbcTemplate.query(sql, new RegresaListaRowMapper());
	} catch (Exception e) {
	LOGGER.error("Falló al llenar la lista en selectAlbums en NombreAlbumDAO" + e.getMessage());
	e.printStackTrace();
	throw new CorvException("Falló al llenar la lista en selectAlbums en NombreAlbumDAO" + e.getMessage());
		}
	}

}

