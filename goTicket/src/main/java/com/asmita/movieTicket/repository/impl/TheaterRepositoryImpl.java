package com.asmita.movieTicket.repository.impl;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.asmita.movieTicket.entity.impl.AuditoriumImpl;
import com.asmita.movieTicket.entity.impl.ShowInfoImpl;
import com.asmita.movieTicket.entity.impl.TheaterImpl;
import com.asmita.movieTicket.repository.TheaterRepository;

/*This class also interacts with database and proved data to the service layers*/
@Repository
public class TheaterRepositoryImpl implements TheaterRepository {
	
	@Autowired
    private SessionFactory sessionFactory;
	
	
	@Autowired
	ShowInfoRepositoryImpl show;
	
	@Override
	public int getAuditoriumId(String movie, String ShowTime,int tId) {
		

		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(ShowInfoImpl.class)
				.add(Restrictions.eq("movieName",movie))
				.add(Restrictions.eq("time", ShowTime));
		@SuppressWarnings("unchecked")
		List<ShowInfoImpl>shows = crit.list();		
		for(ShowInfoImpl show :shows){
			if(show.getTheaterId().getTheaterId()==(tId)){
				show.getAudiId().getAudiId();
				
				return show.getAudiId().getAudiId();
			}
		}
		return 0;
	}
	
	@Override
	public List<AuditoriumImpl> getAudilist(String tname){
		int tId = getTheaterId(tname);
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(AuditoriumImpl.class)
							.add(Restrictions.eq("theater.theaterId",tId));
		@SuppressWarnings("unchecked")
		List<AuditoriumImpl> audi =crit.list();
		return audi;
		
	}
	
	@Override
	public int getTheaterId(String tName){
		int tId =0;
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(TheaterImpl.class)
							.add(Restrictions.eq("theaterName",tName));
		 @SuppressWarnings("unchecked")
		List<TheaterImpl> theaters =crit.list();
		 for(TheaterImpl theater:theaters){
			 if(theater.getTheaterName().equalsIgnoreCase(tName)){
				 tId= theater.getTheaterId();
				 return tId;
			 }
			  
		 }
		 return tId;
	}
	
	@Override
	public String getTheaterName(int Tid){
		TheaterImpl Theater = (TheaterImpl)this.sessionFactory.getCurrentSession().get(TheaterImpl.class,Tid);
		return Theater.getTheaterName();
	}
	
	@Override
	public List<String> getTheaterList(){
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(TheaterImpl.class);
		@SuppressWarnings("unchecked")
		List<TheaterImpl>theaters = crit.list();
		List<String> theaterList = new ArrayList<String>();
		for(TheaterImpl theater:theaters){
			theaterList.add(theater.getTheaterName());
		}

		return theaterList;
	}
	
	
}
