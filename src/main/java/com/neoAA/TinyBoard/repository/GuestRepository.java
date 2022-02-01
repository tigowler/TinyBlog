package com.neoAA.TinyBoard.repository;

import com.neoAA.TinyBoard.model.GuestBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuestRepository extends JpaRepository<GuestBook, Long> {
}
