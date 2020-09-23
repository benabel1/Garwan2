package dto;

import java.math.BigDecimal;
import java.util.List;

public class ProductDTO {
	private long id;
	private String name;
	private BigDecimal price;
	
	private AnimalCategoryDTO animalCategoryDTO;
	
	private String decription;
	
	private List<String> gallery;

	public ProductDTO() {
		super();
	}

	public ProductDTO(long id, String name, BigDecimal price, AnimalCategoryDTO animalCategoryDTO, String decription, List<String> gallery) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.animalCategoryDTO = animalCategoryDTO;
		this.decription = decription;
		this.gallery = gallery;
	}

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public AnimalCategoryDTO getAnimalCategoryDTO() {
		return animalCategoryDTO;
	}

	public void setAnimalCategoryDTO(AnimalCategoryDTO animalCategoryDTO) {
		this.animalCategoryDTO = animalCategoryDTO;
	}

	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}

	public List<String> getGallery() {
		return gallery;
	}

	public void setGallery(List<String> gallery) {
		this.gallery = gallery;
	}

	
}
