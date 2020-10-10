package cn.swift.infrastructure.db.remote;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;

import org.springframework.stereotype.Service;

import cn.swift.domain.IdentifierService;

@Service
public class DistributedIdentifierService implements IdentifierService {

    public Long fetchIdentifier() {
        // fetch id from some rpc service
        return LocalDateTime.now().getLong(ChronoField.MILLI_OF_DAY);
    }

    @Override
    public Long generateIdentifier() {
        return fetchIdentifier();
    }
    
}
