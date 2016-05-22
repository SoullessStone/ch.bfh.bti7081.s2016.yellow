package ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@MappedSuperclass
public abstract class AbstractDatabaseObject implements Serializable {
	@Transient private static final long serialVersionUID = 9081632481841531354L;

	@Id
	@Column(unique=true, name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}