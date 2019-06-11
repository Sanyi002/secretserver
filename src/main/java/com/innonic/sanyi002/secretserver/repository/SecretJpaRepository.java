package com.innonic.sanyi002.secretserver.repository;

import com.innonic.sanyi002.secretserver.model.Secret;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface SecretJpaRepository extends CrudRepository<Secret, String> {
    Secret getSecretByHash(String hash);
}
