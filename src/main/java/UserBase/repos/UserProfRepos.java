package UserBase.repos;

import UserBase.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserProfRepos extends JpaRepository<UserProfile, Long> {
    Optional<UserProfile> findById(Long id);

    void deleteById(Long id);

    Page<UserProfile> findAll(Pageable pageable);
}








