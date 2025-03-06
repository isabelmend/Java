package dto;

public class NombreAlbumRespuestaDTO {
	
	private NombreAlbumDTO [] nombreAlbumDTO;
	private ErrorDTO errorDTO;

	public NombreAlbumDTO[] getNombreAlbumDTO() {
	return nombreAlbumDTO;
	}
	public void setNombreAlbumDTO(NombreAlbumDTO[] nombreAlbumDTO) {
	this.nombreAlbumDTO = nombreAlbumDTO;
	}
	public ErrorDTO getErrorDTO() {
	return errorDTO;
	}
	public void setErrorDTO(ErrorDTO errorDTO) {
	this.errorDTO = errorDTO;
	}

}
