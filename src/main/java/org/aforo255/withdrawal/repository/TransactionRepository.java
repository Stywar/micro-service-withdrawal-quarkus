package org.aforo255.withdrawal.repository;

import javax.enterprise.context.ApplicationScoped;

import org.aforo255.withdrawal.entity.Transaction;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

@ApplicationScoped
public class TransactionRepository  implements PanacheRepositoryBase<Transaction, Integer>{

}
