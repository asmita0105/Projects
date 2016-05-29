package com.asmita.movieTicket.repository.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.asmita.movieTicket.entity.Auditorium;
import com.asmita.movieTicket.entity.impl.AuditoriumImpl;
import com.asmita.movieTicket.entity.impl.ShowInfoImpl;
import com.asmita.movieTicket.repository.AuditoriumRepository;
import com.asmita.movieTicket.repository.ShowInforepository;


/*This class interacts with the data bases and retrieves and update data by using methods like getcapacity, getCurrentMovie.
 * update database by setting  booked ticket in database.*/
@Repository
public class AuditoriumRepositoryimpl implements AuditoriumRepository{
	@Autowired
    private SessionFactory sessionFactory;
	
	@Autowired
	ShowInforepository showinfo;

	@Override
	@SuppressWarnings("unchecked")
	public List<Auditorium> getAudiListTheater(int tId) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(AuditoriumImpl.class)
						.add(Restrictions.eq("theater.theaterId", tId));
		List<Auditorium> audis = crit.list();
		return audis;
	}
	
	@Override
	public List<ShowInfoImpl> getCurrentMovie(int audiId){
		 Session session = this.sessionFactory.getCurrentSession();
		 
		 Criteria crit = session.createCriteria(ShowInfoImpl.class)
					.add(Restrictions.eq("audi.idAudi",audiId));
		 @SuppressWarnings("unchecked")
		List<ShowInfoImpl> shows = crit.list();
		 return shows;
	 }
	
	@Override
	public int getAuditiroriumCapacity(int audiId){
		 Session session = this.sessionFactory.getCurrentSession();
		 
		 Criteria crit = session.createCriteria(AuditoriumImpl.class)
					.add(Restrictions.eq("idAudi",audiId));
		@SuppressWarnings("unchecked")
		List<AuditoriumImpl> audis = crit.list();
		 for(AuditoriumImpl audi:audis){
			 if(audi.getAudiId()==audiId){
				 return audi.getCapacity();
			 }
		 }
		return 0;
	 }

}
