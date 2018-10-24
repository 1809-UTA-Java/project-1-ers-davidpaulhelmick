package com.revature.ers.repository;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import com.revature.ers.model.Users;
import com.revature.ers.model.Reimbursement;
import com.revature.ers.model.ReimbursementStatus;
import com.revature.ers.model.ReimbursementType;
import com.revature.ers.util.HibernateUtil;

public class ReimbursementDAO {
	public List<Reimbursement> getAllReimbursements() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Reimbursement").list();
	}
	
	public List<Reimbursement> getReimbusementsByStatus(String status) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Reimbursement e where e.status=:status").setString("status", status).list();
	}
	
	public List<Reimbursement> getReimbusementsByEmployee(Integer authorId) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Reimbursement e where e.author=:author").setInteger("author", authorId).list();
	}
	
	public void saveReimbursement(Reimbursement r) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.save(r);
		tx.commit();
	}
	
	public ReimbursementStatus status(Integer statusNum) {
		Session session = HibernateUtil.getSession();
		return (ReimbursementStatus) session.createQuery("from ReimbursementStatus rs where rs.rID=:num").setInteger("num", statusNum).list().get(0);
	}
	
	public ReimbursementType type(Integer typeNum) {
		Session session = HibernateUtil.getSession();
		return (ReimbursementType) session.createQuery("from ReimbursementType rt where rt.rID=:num").setInteger("num", typeNum).list().get(0);
	}
	
	public List<Reimbursement> getReimbursementsByAuthor(Users author) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Reimbursement e where e.author = :author_id").setParameter("author_id", author).list();
	}
	
	public List<Reimbursement> getPendingReimbursements() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Reimbursement e where e.status = :pending").setParameter("pending", status(0)).list();
	}
	
	public List<Reimbursement> getResolvedReimbursements() {;
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Reimbursement.class);
		Disjunction or = Restrictions.disjunction();
		or.add(Restrictions.eq("status", status(1)));
		or.add(Restrictions.eq("status", status(2)));
		criteria.add(or);
		
		return criteria.list();
	}
	
	public List<Reimbursement> getPendingReimbursementsByAuthor(Users author) {
		Session session = HibernateUtil.getSession();
		return session.createQuery("from Reimbursement where author = :author_id and status = :pending").setParameter("author_id", author).setParameter("pending", status(0)).list();
	}
	
	public List<Reimbursement> getResolvedReimbursementsByAuthor(Users author) {;
		Session session = HibernateUtil.getSession();
		Criteria criteria = session.createCriteria(Reimbursement.class);
		Disjunction or = Restrictions.disjunction();
		Criterion aut = Restrictions.eq("author", author);
		or.add(Restrictions.eq("status", status(1)));
		or.add(Restrictions.eq("status", status(2)));
		LogicalExpression andExp = Restrictions.and(aut, or);
		criteria.add(andExp);
		
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	public Reimbursement getRimbursementById(String reimId) {
		Session session = HibernateUtil.getSession();
		Reimbursement reim = null;
		List<Reimbursement> reimList = new ArrayList<Reimbursement>();
		
		reimList = (List<Reimbursement>) session.createQuery(
				"from Reimbursement where rID = :reimId")
				.setString("reimId", reimId).list();
		if (!reimList.isEmpty()) {
			reim = reimList.get(0);
		}
		
		return reim;
	}
	
	public void setReimbursement(Reimbursement reim) {
		Session session = HibernateUtil.getSession();
		Transaction tx = session.beginTransaction();
		session.update(reim);
		tx.commit();
	}
}