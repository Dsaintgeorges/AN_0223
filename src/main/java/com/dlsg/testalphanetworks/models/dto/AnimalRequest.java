package com.dlsg.testalphanetworks.models.dto;

import com.dlsg.testalphanetworks.models.dao.AnimalType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AnimalRequest {
	private UUID userId;
	private String name;
	private AnimalType type;
}
