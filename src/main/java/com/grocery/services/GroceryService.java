package com.grocery.services;

import java.math.BigInteger;
import java.util.Collection;

import com.grocery.entity.Grocery;

public interface GroceryService {
	Collection<Grocery> getGroceries();

	Grocery findGrocery(BigInteger id);

	boolean deleteItem(Grocery item);

	Grocery updateItem(Grocery item);
	Grocery saveItem(Grocery item);
}
