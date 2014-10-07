package my.com.myriadeas.integral.data.populator;

import my.com.myriadeas.integral.data.jpa.domain.Material;

public interface MaterialData {
String marc = "00058nam  2200037Ia 450024500200000000aTime Management";
	
	Material MAT1 = new Material.MaterialBuilder(marc).materialNo("0000000001").build();
	Material MAT2 = new Material.MaterialBuilder(marc).materialNo("0000000002").build();
	Material MAT3 = new Material.MaterialBuilder(marc).materialNo("0000000003").build();
	Material MAT4 = new Material.MaterialBuilder(marc).materialNo("0000000004").build();
	Material MAT5 = new Material.MaterialBuilder(marc).materialNo("0000000005").build();
	Material MAT6 = new Material.MaterialBuilder(marc).materialNo("0000000006").build();
	Material MAT7 = new Material.MaterialBuilder(marc).materialNo("0000000007").build();
	Material MAT8 = new Material.MaterialBuilder(marc).materialNo("0000000008").build();
	Material MAT9 = new Material.MaterialBuilder(marc).materialNo("0000000009").build();
	Material MAT10 = new Material.MaterialBuilder(marc).materialNo("0000000010").build();
}
