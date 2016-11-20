package com.grocery.controller;

import java.math.BigInteger;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.grocery.entity.Grocery;
import com.grocery.services.GroceryServImpl;

@RestController

public class GroceryController {
	@Autowired
	GroceryServImpl service;

	@RequestMapping(value = "api/grocery", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Grocery>> getAllItems() {
		return new ResponseEntity<Collection<Grocery>>(service.getGroceries(),
				HttpStatus.OK);
	}

	@RequestMapping(value = "api/grocery/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grocery> getItem(@PathVariable("id") BigInteger id) {
		Grocery item = service.findGrocery(id);
		if (item != null)
			return new ResponseEntity<Grocery>(item, HttpStatus.FOUND);
		return new ResponseEntity<Grocery>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = "api/grocery", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grocery> saveItem(@RequestBody Grocery item) {
		System.out.println("Here in save controller");
		Grocery savedItem = service.saveItem(item);
		if (savedItem == null)
			return new ResponseEntity<Grocery>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Grocery>(savedItem, HttpStatus.CREATED);
	}

	@RequestMapping(value = "api/grocery", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grocery> updateItem(@RequestBody Grocery item) {
		Grocery updatedItem = service.updateItem(item);
		if (updatedItem == null)
			return new ResponseEntity<Grocery>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<Grocery>(updatedItem,HttpStatus.OK);

	}

	
	@RequestMapping(value = "api/grocery", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Grocery> deleteItem(@RequestBody Grocery item) {
		boolean deletedItem = service.deleteItem(item);
		
		if (deletedItem){
			System.out.println("item deleted ");
			return new ResponseEntity<Grocery>(HttpStatus.OK);
		}
		return new ResponseEntity<Grocery>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
