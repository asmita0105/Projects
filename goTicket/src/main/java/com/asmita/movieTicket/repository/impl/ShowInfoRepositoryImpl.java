package com.asmita.movieTicket.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.asmita.movieTicket.entity.ShowInfo;
import com.asmita.movieTicket.entity.impl.ShowInfoImpl;
import com.asmita.movieTicket.repository.ShowInforepository;

@Repository
public class ShowInfoRepositoryImpl implements ShowInforepository{
	@Autowired
	private SessionFactory sessionFactory;

	/* retrieving data from database*/
	@Override
	public ShowInfo getMovie(int showId) {
		Session session = this.sessionFactory.getCurrentSession();
		ShowInfo showInfo = (ShowInfo) session.get(ShowInfoImpl.class, showId);
		return showInfo;
	}
	
	
	/* inserting data into database*/
	@Override
	public int addShow(ShowInfo show){

		return (int) this.sessionFactory.getCurrentSession().save(show);

	}


	@SuppressWarnings("unchecked")
	@Override
	public List<ShowInfo> getShowInfo(String movie) {
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(ShowInfoImpl.class)
				.add(Restrictions.eq("movieName",movie));	
		List<ShowInfo>shows = crit.list();

		return shows;
	}

	@Override
	public ShowInfo getShowDetail(String movieName, int audiId,String time) {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria crit = session.createCriteria(ShowInfoImpl.class)
				.add(Restrictions.eq("audi.idAudi",audiId));
		@SuppressWarnings("unchecked")
		List<ShowInfoImpl> showInfo = crit.list();
		for(ShowInfoImpl show:showInfo){
			if(show.getMovieName().equalsIgnoreCase(movieName) && show.getTime().equals(time)){
				return  show;
			}

		}
		return null;
	}

	@Override
	public List<String> getMovieList(int Tid){
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(ShowInfoImpl.class)
				.add(Restrictions.eq("theaterId.theaterId",Tid));	

		@SuppressWarnings("unchecked")
		List<ShowInfo>shows = crit.list();
		List<String> movies = new ArrayList<String>();
		for(ShowInfo show:shows){
			movies.add(show.getMovieName());
		}

		return movies;

	}
	
	@Override
	public List<String> getMovieSList(){
		Criteria crit = this.sessionFactory.getCurrentSession().createCriteria(ShowInfoImpl.class);
	
		@SuppressWarnings("unchecked")
		List<ShowInfo>shows = crit.list();
		List<String> movies = new ArrayList<String>();
		for(ShowInfo show:shows){
			movies.add(show.getMovieName());
		}

		return movies;

	}



}
