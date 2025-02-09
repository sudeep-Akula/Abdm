package com.vignatrics.Abdm.repo;

import com.vignatrics.Abdm.entity.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotifyRepo extends JpaRepository<Notify,Long> {

}
