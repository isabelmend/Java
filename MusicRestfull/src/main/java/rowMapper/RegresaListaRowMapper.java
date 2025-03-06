package rowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import dto.NombreAlbumDTO;

public class RegresaListaRowMapper implements RowMapper <NombreAlbumDTO> {

	public NombreAlbumDTO mapRow(ResultSet resultset, int rowNum) throws SQLException {
		NombreAlbumDTO nombreAlbum = new NombreAlbumDTO();
		nombreAlbum.setAlbum_id(resultset.getInt("album_id"));
		nombreAlbum.setNombre_album(resultset.getString("nombre_album"));
		nombreAlbum.setArtista_id(resultset.getInt("artista_id"));
		nombreAlbum.setGenero_id(resultset.getInt("genero_id"));
		return nombreAlbum;
		}

}

