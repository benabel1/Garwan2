package dto;

import data.AnimalCategory;

public class AnimalCategoryDTO {
	
	String name;
	
	public AnimalCategoryDTO() {
		
	}
	
	public AnimalCategoryDTO(AnimalCategory a) {
		if(a == null) {
			this.name = null;
		} else {
			this.name = a.name();
		}
	}

	public String getString() {
		return name;
	}

	

}
