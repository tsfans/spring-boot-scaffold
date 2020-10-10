package cn.swift.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class BaseEntity extends BaseValueObject {

    private Long id;
    
}
