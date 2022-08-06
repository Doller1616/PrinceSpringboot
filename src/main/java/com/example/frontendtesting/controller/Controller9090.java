package com.example.frontendtesting.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.frontendtesting.model.Model9090;
import com.example.frontendtesting.repository.Repository9090;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/employee/")
public class Controller9090 {

@Autowired
private Repository9090 mongodb9090;

@Autowired
private MongoTemplate mongoTemplate;

@PostMapping("add")
public ResponseEntity<Model9090> addAllEmployees(@RequestBody Model9090 model9090){
	Model9090 modeldata = mongodb9090.save(model9090);
	return ResponseEntity.status(HttpStatus.OK).body(modeldata);
}


@GetMapping("all")
public ResponseEntity<Object> fetchAllEmployees(){
	List<Model9090> allEMployees = mongodb9090.findAll();
	return ResponseEntity.status(HttpStatus.OK).body(allEMployees);
}

@PostMapping("update")
public ResponseEntity<Object> updateEmployees(@RequestBody Model9090 model9090){
	
//	--------------------------------------
	
	Query query = new Query(); // apply Query
	Update update = new Update(); // to contain latest record
	
	query.addCriteria(Criteria.where("_id").is(model9090.getId()));
	
	update.set("handle", model9090.getHandle());
	update.set("firstname", model9090.getFirstname());
	update.set("lastname", model9090.getLastname());
	UpdateResult result = mongoTemplate.updateFirst(query, update, model9090.getClass());
	
//-------------------------------------------
	return ResponseEntity.status(HttpStatus.OK).body(result);
}

@GetMapping("delete")
public ResponseEntity<Object> deleteEmployees(@RequestParam("id") String id){
	
	Query query = new Query().addCriteria(Criteria.where("_id").is(id));
	
	DeleteResult result = mongoTemplate.remove(query, new Model9090().getClass());
	  
	return ResponseEntity.status(HttpStatus.OK).body(result);
}




	

}
