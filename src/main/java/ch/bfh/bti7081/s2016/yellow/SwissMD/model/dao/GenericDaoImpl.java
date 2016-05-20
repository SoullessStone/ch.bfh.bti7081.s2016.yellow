package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Implementation of the {@code GenericDao} interface.
 * 
 * @author K.Suter
 * 
 */
public class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID> {

	protected Class<T> entityClass;

	@PersistenceContext
	protected EntityManager entityManager;

	public GenericDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<T>) genericSuperclass.getActualTypeArguments()[0];
	}

	@Override
	public T persist(T t) {
		this.entityManager.persist(t);
		return t;
	}

	@Override
	public T findById(ID id) {
		return this.entityManager.find(entityClass, id);
	}

	@Override
	public T update(T t) {
		return this.entityManager.merge(t);
	}

	@Override
	public void remove(T t) {
		t = this.entityManager.merge(t);
		this.entityManager.remove(t);
	}

	@Override
	public List<T> findByIds(List<ID> ids) {
		if (ids == null || ids.isEmpty()) {
			return new ArrayList<>();
		}
		
		List<T> resultList = new ArrayList<T>(ids.size());
		for (ID id:ids) {
			resultList.add(findById(id));
		}
		
		return null;
	}

	@Override
	public T findByField(String fieldName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}
}