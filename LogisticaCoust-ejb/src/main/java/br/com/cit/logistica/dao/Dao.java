package br.com.cit.logistica.dao;

import java.util.Optional;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class Dao {
	protected <T> Optional<T> getRegistro(TypedQuery<T> typedQuery) {
		try {
			return Optional.of(typedQuery.getSingleResult());
		} catch(NoResultException e) {
			return Optional.empty();
		}
	}
}
