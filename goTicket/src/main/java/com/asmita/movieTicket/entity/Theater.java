package com.asmita.movieTicket.entity;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asmita.movieTicket.entity.impl.AuditoriumImpl;
import com.asmita.movieTicket.entity.impl.ShowInfoImpl;

@Service
public interface Theater {
	String getTheaterName();
	int getTheaterId();
	void SetTheaterId(int theaterid);
	List<ShowInfoImpl> getShowInfoList();
	List<AuditoriumImpl> getAudtList();
}
