package com.preview.preview.core;

import java.util.Optional;

public interface Repository<T, S> {
    public Optional<T> findById(Long id);
    public Optional<T> findOne(S specification);
}

