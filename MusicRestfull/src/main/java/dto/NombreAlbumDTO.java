package dto;

import java.io.Serializable;

public class NombreAlbumDTO implements Serializable {
	
	private static final long serialVersionUID = 4627334864826669987L;
		
	private int album_id;
	private String nombre_album;
	private int artista_id;
	private int genero_id;
		
	public NombreAlbumDTO() {}

	public NombreAlbumDTO(int album_id, String nombre_album, int artista_id, int genero_id) 
	{

		this.album_id = album_id;
		this.nombre_album = nombre_album;
		this.artista_id = artista_id;
		this.genero_id = genero_id;
	}

	public int getAlbum_id() {
		return album_id;
	}

	public void setAlbum_id(int album_id) {
		this.album_id = album_id;
	}

	public String getNombre_album() {
		return nombre_album;
	}

	public void setNombre_album(String nombre_album) {
		this.nombre_album = nombre_album;
	}

	public int getArtista_id() {
		return artista_id;
	}

	public void setArtista_id(int artista_id) {
		this.artista_id = artista_id;
	}

	public int getGenero_id() {
		return genero_id;
	}

	public void setGenero_id(int genero_id) {
		this.genero_id = genero_id;
	}

	@Override
	public String toString() {
		return "AlbumDTO [album_id=" + album_id + ", nombre_album=" + nombre_album + ", artista_id=" + artista_id
				+ ", genero_id=" + genero_id + "]";
	}
		
}

