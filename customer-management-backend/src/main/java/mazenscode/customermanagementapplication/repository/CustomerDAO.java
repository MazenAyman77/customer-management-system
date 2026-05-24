package mazenscode.customermanagementapplication.repository;

import mazenscode.customermanagementapplication.entity.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

// interface dealing with the database
public interface CustomerDAO extends JpaRepository<Customers, Integer> {

    // giving you ready crud implementation

}
