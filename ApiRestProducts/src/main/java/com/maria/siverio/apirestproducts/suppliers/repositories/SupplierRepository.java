package com.maria.siverio.apirestproducts.suppliers.repositories;

import com.maria.siverio.apirestproducts.suppliers.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
//    Supplier findSupplierByName(String name);
    Supplier getSupplierByName(String name);
}
