package com.prime.EmailSender.repo;

import com.prime.EmailSender.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Long> {

}
