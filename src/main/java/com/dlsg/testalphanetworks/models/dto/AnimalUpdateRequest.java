package com.dlsg.testalphanetworks.models.dto;

import com.dlsg.testalphanetworks.models.dao.AnimalType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnimalUpdateRequest {
	private String name;
	private AnimalType type;
}
