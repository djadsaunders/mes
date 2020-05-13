package com.djad.mes.domain.resource;

import org.springframework.stereotype.Component;

@Component
public class ResourceFactory {

	public Resource createResource(String tag) {
		return this.createResource(tag, tag);
	}

	public Resource createResource(String tag, String name) {
		Resource resource = new Resource(tag);
		resource.setName(name == null ? tag : name);
		return resource;
	}
}