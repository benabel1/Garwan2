package dto;

public class ProductOnlyFewColumn {
	
	long id;
	String name;
	double price;
	AnimalCategoryDTO animal;

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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
