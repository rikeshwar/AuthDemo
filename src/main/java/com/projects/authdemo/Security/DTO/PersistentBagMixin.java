package com.projects.authdemo.Security.DTO;


import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonDeserialize(as = org.hibernate.collection.spi.PersistentBag.class)

public interface PersistentBagMixin {
}
