package com.asmita.movieTicket.entity.impl;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import com.asmita.movieTicket.entity.Auditorium;
import com.asmita.movieTicket.entity.Theater;

@Entity
@Table(name="auditorium")
public class AuditoriumImpl implements Auditorium {
	
	@Id	
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "idaudi")
	int idAudi;
	
	@Column(name = "capacity")
	int capacity;
	
	@ManyToOne(fetch = FetchType.EAGER,targetEntity=TheaterImpl.class)
	@JoinColumn(name="theaterId")
	private Theater theater;
	
	@OneToMany(mappedBy = "audi", targetEntity=ShowInfoImpl.class, cascade=CascadeType.ALL)	
    private List<ShowInfoImpl> showInfoList;
	
	/*@Transient
	HashMap<String, List<ShowInfoImpl>> shows = new HashMap<String, List<ShowInfoImpl>>();*/
	
	
	
	@Override
	public int getAudiId(){
		return idAudi;
	}
	
	@Override
	public int getCapacity(){
		return capacity;
	}

	public Theater getTheater() {
		return theater;
	}
	
	/*This setter is only used to add new show in given AuditoriumId*/
	public void setAudiId(int idAudi){
		this.idAudi = idAudi;
	}

}
