package com.ghost.Ghost.Respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ghost.Ghost.model.Register;

public interface RegisterRepository extends JpaRepository<Register,Long> {
	@Query(value="select * from public.register u where u.name=:name", nativeQuery=true)
	Register findByname(@Param("name") String name);
}
