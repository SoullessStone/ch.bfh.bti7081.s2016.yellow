package ch.bfh.bti7081.s2016.yellow.SwissMD.model.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ch.bfh.bti7081.s2016.yellow.SwissMD.model.entity.AbstractDatabaseObject;

/**
 * Implementation of the {@code GenericDao} interface. Abstract, must be
 * extended.
 * 
 * @author K.Suter
 * 
 */
public abstract class GenericDaoImpl<T extends AbstractDatabaseObject, ID extends Serializable>
		implements GenericDao<T, ID> {

	protected Class<T> mEntityClass;

	protected EntityManager mEntityManager;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		ParameterizedType genericSuperclass = (ParameterizedType) getClass()
				.getGenericSuperclass();
		this.mEntityClass = (Class<T>) genericSuperclass
				.getActualTypeArguments()[0];
		this.mEntityManager = EntityManagerProvider.createEntityManager();
	}

	@Override
	public T create(T t) {
		startTransaction();
		mEntityManager.persist(t);
		commitTransaction();
		return t;
	}

	@Override
	public T update(T t) {
		startTransaction();
		mEntityManager.merge(t);
		commitTransaction();
		return t;
	}

	@Override
	public void delete(T t) {
		startTransaction();
		mEntityManager.remove(t);
		commitTransaction();
	}

	@Override
	public T read(ID id) {
		return mEntityManager.find(mEntityClass, id);
	}

	@Override
	public List<T> read(List<ID> ids) {
		if (ids == null || ids.isEmpty()) {
			return new ArrayList<>();
		}

		List<T> resultList = new ArrayList<T>(ids.size());
		for (ID id : ids) {
			resultList.add(read(id));
		}

		return resultList;
	}

	@Override
	public T readByField(String fieldName, Object value) {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(getEntityClass());
		Root<T> root = query.from(getEntityClass());
		query.select(root);
		Predicate predicate = cb.equal(root.get(fieldName), value);
		query.where(predicate);
		TypedQuery<T> typedQuery = getEntityManager().createQuery(query);
		T t = getSingleResultSafeTypedQuery(typedQuery);
		return t;
	}

	@Override
	public List<T> readAll() {
		CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
		CriteriaQuery<T> query = cb.createQuery(getEntityClass());
		query.from(getEntityClass());
		return getEntityManager().createQuery(query).getResultList();
	}

	@Override
	public <T2> T2 getSingleResultSafeTypedQuery(final TypedQuery<T2> query) {
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Object getSingleResultSafeQuery(final Query query) {
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	private EntityManager getEntityManager() {
		return mEntityManager;
	}

	private Class<T> getEntityClass() {
		return mEntityClass;
	}

	private void commitTransaction() {
		mEntityManager.getTransaction().commit();
	}

	private void startTransaction() {
		mEntityManager.getTransaction().begin();
	}
}