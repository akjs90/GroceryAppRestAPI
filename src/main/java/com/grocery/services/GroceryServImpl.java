package com.grocery.services;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grocery.entity.Grocery;
import com.grocery.repository.GroceryRepository;

@Service
public class GroceryServImpl implements GroceryService {

	@Autowired
	GroceryRepository repository;

	@Override
	public Collection<Grocery> getGroceries() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Grocery findGrocery(BigInteger id) {
		// TODO Auto-generated method stub
		return repository.findOne(id);
	}

	@Override
	public boolean deleteItem(Grocery item) {
		// TODO Auto-generated method stub
		if (item.getId() == null)
			return false;
		Grocery deletedItem = repository.findOne(item.getId());
		if (deletedItem != null) {
			repository.delete(item.getId());
			return true;
		}
		return false;
	}

	@Override
	public Grocery updateItem(Grocery item) {
		// TODO Auto-generated method stub
		if (item.getId() == null)
			return null;
		Grocery storedItem = repository.findOne(item.getId());
		if (storedItem == null) {
			// Item not found
			return null;
		}

		return repository.save(item);
	}

	@Override
	public Grocery saveItem(Grocery item) {
		// TODO Auto-generated method stub
		Grocery storedItem = null;
		if (item.getId() != null)
			storedItem = repository.findOne(item.getId());
		if (storedItem != null) {
			// item with same id exists
			return null;
		}
		return repository.save(item);
	}

}
