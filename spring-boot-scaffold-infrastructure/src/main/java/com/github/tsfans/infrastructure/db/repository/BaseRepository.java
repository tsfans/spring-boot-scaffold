package com.github.tsfans.infrastructure.db.repository;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.tsfans.domain.BaseValueObject;
import com.github.tsfans.infrastructure.db.remote.DistributedIdentifierService;

public abstract class BaseRepository {
    
    @Autowired
    private DistributedIdentifierService identifierService;
   
    protected Long generateIdentifier() {
        return identifierService.fetchIdentifier();
    }

    protected boolean isInsert(BaseValueObject object) {
        return object != null && object.getVersion() == 0;
    }
    
}
