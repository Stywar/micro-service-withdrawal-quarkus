package org.aforo255.withdrawal.service;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;

import org.aforo255.withdrawal.entity.Transaction;
@ApplicationScoped
public class TransactionService implements ITransaction {

	@Transactional
	@Override
	public Transaction save(Transaction transaction) {
		// TODO Auto-generated method stub
		transaction.persist();
		return transaction;
	}

}
