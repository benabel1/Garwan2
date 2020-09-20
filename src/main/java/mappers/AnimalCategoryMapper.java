package mappers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import data.AnimalCategory;
import dto.AnimalCategoryDTO;

@Component
public class AnimalCategoryMapper {
	
	static Logger logger = LoggerFactory.getLogger(AnimalCategoryMapper.class);

	public static AnimalCategory code(AnimalCategoryDTO a) {
		
		if(a == null || a.getString() == null) {
			logger.info("AnimalCategory was NULL");
			return null;
		}
		
		return AnimalCategory.valueOf(a.getString());
	}
	
	public static AnimalCategoryDTO decode(AnimalCategory a) {
		return new AnimalCategoryDTO(a);
	}
}
