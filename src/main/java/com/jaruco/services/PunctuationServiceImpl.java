package com.jaruco.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaruco.entities.Opinion;
import com.jaruco.repositories.OpinionRepository;

@Service
public class PunctuationServiceImpl implements PunctuationService {

	@Autowired
	private OpinionRepository opinionRepository;

	@Override
	public Map<Long, Long> calculatePunctuation(long idStore) {
		List<Opinion> opinions = opinionRepository.getAllValid();

		Map<Long, Long> punctuations = new HashMap<Long, Long>();

		for (Opinion op : opinions) {

			if (punctuations.containsKey(op.getIdStore())) {

				punctuations.replace(op.getIdStore(), (op.getPuntuacion() + punctuations.get(op.getIdStore())));
				
			} else {
				punctuations.put(op.getIdStore(), op.getPuntuacion());
			}
		}
		
		return punctuations;
	}

}