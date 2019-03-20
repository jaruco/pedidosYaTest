package com.jaruco.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jaruco.entities.Opinion;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {

	@Query(value = "SELECT * FROM opinions p WHERE p.valid=true", nativeQuery = true)
	List<Opinion> getAllValid();

	@Query(value = "UPDATE opinions p SET valid=false WHERE p.id = id", nativeQuery = true)
	void logicalDeletion(@Param("id") Long id);

	@Query(value = "SELECT * FROM opinions p WHERE p.userComment = userComment", nativeQuery = true)
	public Opinion searchByUserOpinion(@Param("userComment") String userComment);

	@Query(value = "SELECT * FROM opinions p WHERE p.idPurchase = idPurchase", nativeQuery = true)
	public Opinion searchByIdPurchase(@Param("idPurchase") Long idPurchase);

	@Query(value = "SELECT * FROM opinions p WHERE p.idUser="
			+ "(SELECT idUser FROM opinions x WHERE x.opinionDate "
			+ " BETWEEN initialDate AND endDate) AND p.valid=true", nativeQuery = true)
	List <Opinion> searchByIdUser(@Param("idUser") Long idUser, @Param("initialDate") String initialDate,
			@Param("endDate") String endDate);

	@Query(value = "SELECT * FROM opinions p WHERE p.idStore="
			+ "(SELECT idStore FROM opinions x WHERE x.opinionDate "
			+ " BETWEEN initialDate AND endDate) AND p.valid=true", nativeQuery = true)
	List<Opinion> searchByIdStore(@Param("idStore") Long idStore, @Param("initialDate") String initialDate,
			@Param("endDate") String endDate);
}