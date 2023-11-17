package dev.zig.mapper;

import dev.zig.model.dto.GroupDto;
import dev.zig.model.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {GroupMapper.class}, componentModel = "spring")
public interface GroupMapper {


    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDto toDto(Group group);

}
