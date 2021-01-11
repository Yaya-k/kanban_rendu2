package org.rygn.kanban.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Developer {

	private @Id @GeneratedValue Long id;
	  
	private String firstname;
	
	private String lastname;
	
	private String email;
	
	private String password;
	
	private LocalDate startContract;

	@ToString.Exclude
	@EqualsAndHashCode.Exclude
	@ManyToMany(mappedBy="developers", fetch=FetchType.EAGER)
	@JsonIgnoreProperties("developers")
	private Set<Task> tasks;
	
	public Developer() {
		
		this.tasks = new HashSet<>();
	}
}
