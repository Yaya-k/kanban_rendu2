package org.rygn.kanban.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
public class Task {

	private @Id @GeneratedValue Long id;

	@NotNull(message = "Title cannot be null")
	@NotEmpty(message = "Title cannot be empty")
	private String title;

	@NotNull(message = "NbHoursForecast cannot be null")
	private Integer nbHoursForecast;

	@NotNull(message = "NbHoursReal cannot be null")
	private Integer nbHoursReal;
	
	private LocalDate created;
	
	@ManyToOne
	private TaskType type;
	
	@ManyToOne
	private TaskStatus status;

	@ManyToMany(fetch=FetchType.EAGER)
	@JsonIgnoreProperties({"password", "startContract", "tasks"})
	@EqualsAndHashCode.Exclude
	@NotEmpty(message = "Developers cannot be empty")
    private Set<Developer> developers;

	@OneToMany(mappedBy="task", cascade={CascadeType.ALL}, orphanRemoval=true)
	@ToString.Exclude
	@JsonIgnoreProperties("task")
	@EqualsAndHashCode.Exclude
	private Set<ChangeLog> changeLogs;
	
	public Task() {
		
		this.developers = new HashSet<>();
		
		this.changeLogs = new HashSet<>();
	}
	
	public void addDeveloper(Developer developer) {
		
		developer.getTasks().add(this);
		
		this.developers.add(developer);
	}
	
	public void addChangeLog(ChangeLog changeLog) {
		
		changeLog.setTask(this);
		
		this.changeLogs.add(changeLog);
	}

	public void clearChangeLogs() {
		
		for (ChangeLog changeLog :  this.changeLogs) {
			
			changeLog.setTask(null);
		}
		
		this.changeLogs.clear();
	}
}
