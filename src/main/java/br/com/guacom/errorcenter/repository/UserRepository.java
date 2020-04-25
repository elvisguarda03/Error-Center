package br.com.guacom.errorcenter.repository;

import br.com.guacom.errorcenter.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
