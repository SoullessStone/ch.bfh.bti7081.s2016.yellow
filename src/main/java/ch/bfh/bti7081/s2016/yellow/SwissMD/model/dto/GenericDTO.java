package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dto;

/**
 * Base DTO for all DTOs in the application. Allows exact identification of a database object
 * via its unique database id.
 * 
 * @author K.Suter
 * 
 */
public class GenericDTO {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
