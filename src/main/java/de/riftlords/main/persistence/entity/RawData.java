/**
 * 
 */
package de.riftlords.main.persistence.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author pasc2de
 *
 */
@Entity
@Table(name="raw_data")
public class RawData {
	
	@Id
	@SequenceGenerator(name="RD_SEQ", sequenceName="RD_SEQ", allocationSize=100)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	//the content of a text area
	@NotNull
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
}
