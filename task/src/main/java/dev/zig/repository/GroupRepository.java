package dev.zig.repository;

import dev.zig.model.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GroupRepository extends JpaRepository<Group, Long> {


    Optional<Group> findByNumber(String number);
}
