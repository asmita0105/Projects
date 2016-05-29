package com.asmita.movieTicket.repository;

import java.util.List;

import com.asmita.movieTicket.entity.Auditorium;
import com.asmita.movieTicket.entity.impl.ShowInfoImpl;

public interface AuditoriumRepository {
	public List<Auditorium> getAudiListTheater(int theaterId);
	public List<ShowInfoImpl> getCurrentMovie(int audiId);
	public int getAuditiroriumCapacity(int audiId);

}
