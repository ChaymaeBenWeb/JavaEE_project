package application.office.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import application.office.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
