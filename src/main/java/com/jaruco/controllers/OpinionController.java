package com.jaruco.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jaruco.entities.Opinion;
import com.jaruco.repositories.OpinionRepository;
import com.jaruco.services.PunctuationService;

@RestController
@RequestMapping("/v1/opinions")
public class OpinionController {

	@Autowired
	private OpinionRepository opinionRepository;
	@Autowired
	private PunctuationService punctuationService;

	@GetMapping(path="/", produces="application/json;charset=UTF-8")
	List<Opinion> findAll() {
		return opinionRepository.getAllValid();
	}

	@GetMapping(path="/getOpinion/{id}", produces="application/json;charset=UTF-8")
	Optional<Opinion> getOpinion(@PathVariable Long id) {
		return opinionRepository.findById(id);
	}
	
	@GetMapping(path="/getOpinionByPurchase/{idPurchase}", produces="application/json;charset=UTF-8")
	Opinion getByPurchase(@PathVariable Long idPurchase) throws Exception {
		return opinionRepository.findById(idPurchase).orElseThrow(() -> new Exception());
	}

	@GetMapping(path="/getOpinionByUser/{idUser}", produces="application/json;charset=UTF-8")
	List<Opinion> getByUser(@PathVariable Long idUser, @RequestBody String initialDate,
			@RequestBody String endDate) {
		return opinionRepository.searchByIdUser(idUser, initialDate, endDate);
	}

	@GetMapping(path="/getOpinionByStore/{idStore}", produces="application/json;charset=UTF-8")
	List<Opinion> getByStore(@PathVariable Long idStore, @RequestBody String initialDate,
			@RequestBody String endDate) {
		return opinionRepository.searchByIdStore(idStore, initialDate, endDate);
	}
	
	@GetMapping(path="/getPunctuation/{idStore}", produces="application/json;charset=UTF-8")
	Map<Long, Long> getPunctuationByStore(@PathVariable Long idStore) {
		return punctuationService.calculatePunctuation(idStore);
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("/createOpinion")
	Opinion newOpinion(@Valid @RequestBody Opinion newOpinion) {
		return opinionRepository.save(newOpinion);
	}

	@PutMapping("/updateOpinion/{id}")
	ResponseEntity<Opinion> updateOpinion(@RequestBody Opinion newOpinion, @PathVariable Long id) {

		Optional<Opinion> opinionOptional = opinionRepository.findById(id);

		if (!opinionOptional.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		newOpinion.setUserComment(newOpinion.getUserComment());
		opinionRepository.save(newOpinion);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping("/deleteOpinion/{id}")
	void deleteOpinion(@PathVariable Long id) {
		opinionRepository.logicalDeletion(id);
	}

}
