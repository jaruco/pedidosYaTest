package com.jaruco.services;

import java.util.Map;

public interface PunctuationService {

	Map<Long, Long> calculatePunctuation(long idStore);
}
