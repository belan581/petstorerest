package com.upemor.petstorerest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.upemor.petstorerest.model.Orderpet;

@Repository
public interface OrderpetRepository extends JpaRepository<Orderpet, Integer> {

}
