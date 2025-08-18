package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.GenresDataService;
import com.gcu.data.entity.GenreEntity;
import com.gcu.model.GenreModel;

@Service
public class GenresBusinessService implements GenresBusinessServiceInterface {

	@Autowired
	GenresDataService service;
	
	@Override
	public List<GenreModel> getGenres() {
		List<GenreEntity> genresEntity = service.findAll();
		List<GenreModel> genreDomain = new ArrayList<GenreModel>();
		for (GenreEntity e : genresEntity)
			genreDomain.add(e.toModel());
		return genreDomain;
	}

	@Override
	public GenreModel getGenreById(int genre_id) {
		return service.findById(genre_id).toModel();
	}

	@Override
	public boolean addGenre(GenreModel genre) {	
		return service.create(new GenreEntity(genre.getGenre_id(), genre.getGenre_name()));
	}

	@Override
	public boolean updateGenre(GenreModel genre) {
		return service.update(new GenreEntity(genre.getGenre_id(), genre.getGenre_name()));
	}

	@Override
	public boolean deleteGenre(int id) {
		return service.deleteById(id);
	}

	@Override
	public boolean deleteGenre(GenreModel genre) {
		return deleteGenre(genre.getGenre_id());
	}

	@Override
	public List<GenreModel> getGenresInRange(int limit, int offset) {
		List<GenreEntity> genresEntities = service.findRange(limit, offset);
		List<GenreModel> genresDomain = new ArrayList<GenreModel>();
		for (GenreEntity e : genresEntities)
			genresDomain.add(e.toModel());
		return genresDomain;
	}

	@Override
	public int getTotalGenreCount() {
		return service.getEntityCount();
	}

}
