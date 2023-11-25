package com.developerex.server.common;

public interface Mapper<Entity, Dto> {

    Dto mapToDto(Entity entity);

    Entity mapToEntity(Dto dto);
}
