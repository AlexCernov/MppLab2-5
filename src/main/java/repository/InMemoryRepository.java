package repository;

import domain.BaseEntity;
import domain.validators.Validator;
import domain.validators.ValidatorException;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


public class InMemoryRepository<ID, T extends BaseEntity<ID>> implements Repository<ID, T> {

    private Map<ID, T> entities;
    private Validator<T> validator;


    public InMemoryRepository(Validator<T> validator) {
        this.validator = validator;
        entities = new HashMap<>();
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.get(id));
    }

    public Optional<T> delete(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("id must not be null");
        }
        return Optional.ofNullable(entities.remove(id));
    }


    @Override
    public Optional<T> update(T entity) throws ValidatorException {

        if (null == entity || null == entity.getId()) {
            throw new IllegalArgumentException("Null object or missing identifier.");
        }

        this.validateOrThrowException(entity);
        if (null != this.entities.get(entity.getId())) {
            entities.put(entity.getId(), entity);
            return Optional.empty();
        } else {
            return Optional.of(entity);
        }
    }


    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        Optional<T> opt = Optional.ofNullable(entity);
        opt.ifPresent(
                opts -> {
                    Optional<ID> optId = Optional.ofNullable(opts.getId());
                    optId.orElseThrow(() -> new IllegalArgumentException("Null object or missing identifier."));
                }
        );

//        if (null == entity || null == entity.getId()) {
//            throw new IllegalArgumentException("Null object or missing identifier.");
//        }

        this.validateOrThrowException(entity);
        if (null == this.entities.get(entity.getId())) {
            entities.put(entity.getId(), entity);
            return Optional.empty();
        } else {
            return Optional.of(entity);
        }
    }

    public Iterable<T> findAll() {
        Set<T> allEntities = entities.entrySet().stream().map(entry -> entry.getValue()).collect(Collectors.toSet());
        return allEntities;
    }

    private void validateOrThrowException(T entity) throws ValidatorException {
        try {
            validator.validate(entity);
        } catch (ValidatorException e) {
            throw new ValidatorException("Object couldn't pass the validation. Details: " + e.getMessage());
        }
    }


}
