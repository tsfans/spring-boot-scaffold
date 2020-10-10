package cn.swift.infrastructure.db.repository;

import org.springframework.beans.factory.annotation.Autowired;

import cn.swift.domain.BaseValueObject;
import cn.swift.infrastructure.db.remote.DistributedIdentifierService;

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
