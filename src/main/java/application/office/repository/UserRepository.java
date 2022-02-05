package application.office.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import application.office.model.User;



@Repository
public interface UserRepository extends JpaRepository<User, Long>{  //JpA pour accéder à CRUD database
	User findByEmail(String email);
}