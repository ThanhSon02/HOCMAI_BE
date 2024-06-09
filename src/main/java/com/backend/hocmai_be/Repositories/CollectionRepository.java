package com.backend.hocmai_be.Repositories;

import com.backend.hocmai_be.Model.Category;
import com.backend.hocmai_be.Model.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Integer> {
    Optional<Collection> findByCollectionName(String collectionName);
}

