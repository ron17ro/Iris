package com.helpdesk.service;

import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpdesk.model.Customer;
import com.helpdesk.model.TicketModel;
import com.helpdesk.model.User;

@Service
public class HibernateSearchService {


    @Autowired
    private EntityManager centityManager;


   @Autowired
    public HibernateSearchService(EntityManager entityManager) {
        super();
        this.centityManager = entityManager;
    }

    public void initializeHibernateSearch() {
    	
     try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
            fullTextEntityManager.createIndexer().startAndWait();
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
	private static final Logger LOGGER = Logger.getLogger(User.class.getName());
	
	@Transactional
    public List<User> fuzzySearchUsers(String searchTerm) throws Exception{

        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search.getFullTextEntityManager(centityManager);
       // centityManager.getTransaction().begin();

        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(User.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields( "first_name","last_name",
        		"username", "department.department_name", "roles.role_name","email")
                .matching(searchTerm).createQuery();
       // Query luceneQuery1 = qb.keyword().onField("first_name")
       //         .matching(searchTerm).createQuery();
        LOGGER.info("lucene query " + luceneQuery.toString());
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, User.class);

        // execute search
        LOGGER.info("jpaQuery query " + jpaQuery.toString());
  
       List<User> userList=null;

        try {        
         
           return jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }
       return null;
       
    }
	
	
	@Transactional
    public List<Customer> fuzzySearchCustomers(String searchTerm) throws Exception{

        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa
        		.Search.getFullTextEntityManager(centityManager);


        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
        		.forEntity(Customer.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1)
        		.withPrefixLength(1).onFields("customer_name","contact_person","email",
        		"phone", "address", "contract_number")
                .matching(searchTerm).createQuery();
    
        javax.persistence.Query jpaQuery = fullTextEntityManager
        		.createFullTextQuery(luceneQuery, Customer.class);


        try {              
           
           return jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }
       return null;
    }
	
	@Transactional
    public List<TicketModel> fuzzySearchTickets(String searchTerm) throws Exception{

        FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa
        		.Search.getFullTextEntityManager(centityManager);


        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder()
        		.forEntity(TicketModel.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1)
        		.withPrefixLength(1).onFields("priority.priority_name","customer.customer_name",
        		"owner.first_name", "owner.last_name", "subject", "description", "status.status_name")
                .matching(searchTerm).createQuery();
    
        javax.persistence.Query jpaQuery = fullTextEntityManager
        		.createFullTextQuery(luceneQuery, TicketModel.class);


        try {              
           
           return jpaQuery.getResultList();
        } catch (NoResultException nre) {
            ;// do nothing

        }
       return null;
    }
}
