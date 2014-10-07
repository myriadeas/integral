package my.com.myriadeas.integral.data.populator;

import java.util.ArrayList;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.repositories.MaterialRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "materialDatabasePopulator")
public class MaterialDatabasePopulator extends AbstractDatabasePopulator implements MaterialDatabasePopulatorIntf {

	@Autowired
	private MaterialRepository materialRepository;

	public void init() {
		List<Material> materials = new ArrayList<Material>();
		materials.add(MAT1);
		materials.add(MAT2);
		materials.add(MAT3);
		materials.add(MAT4);
		materials.add(MAT5);
		materials.add(MAT6);
		materials.add(MAT7);
		materials.add(MAT8);
		materials.add(MAT9);
		materials.add(MAT10);
		materialRepository.save(materials);
	}

}
