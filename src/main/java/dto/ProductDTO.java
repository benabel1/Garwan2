package dto;

import java.util.List;

public class ProductDTO {
	long id;
	String name;
	double price;
	
	AnimalCategoryDTO animalCategoryDTO;
	
	String decription;
	
	List<String> gallery;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public AnimalCategoryDTO getAnimalCategoryDTO() {
		return animalCategoryDTO;
	}

	public void setAnimalCategoryDTO(AnimalCategoryDTO animalCategoryDTO) {
		this.animalCategoryDTO = animalCategoryDTO;
	}
	
	public List<String> getGallery() {
		return gallery;
	}

	public void setGallery(List<String> gallery) {
		this.gallery = gallery;
	}
	
	

}
