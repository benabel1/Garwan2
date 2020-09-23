package dto;

import java.math.BigDecimal;

public class ProductOnlyFewColumn {
	
	private long id;
	private String name;
	private BigDecimal price;
	private AnimalCategoryDTO animal;

	public ProductOnlyFewColumn(ProductDTO decode) {
		setId(decode.getId());
		setName(decode.getName());
		setPrice(decode.getPrice());
		setAnimal(decode.getAnimalCategoryDTO());
	}

	public AnimalCategoryDTO getAnimal() {
		return animal;
	}

	public void setAnimal(AnimalCategoryDTO animal) {
		this.animal = animal;
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
	
}
