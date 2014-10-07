package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.ItemCategory;
import my.com.myriadeas.integral.data.jpa.domain.ItemStatus;
import my.com.myriadeas.integral.data.jpa.domain.Location;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.SMD;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ItemRepositoryImpl extends my.com.myriadeas.integral.data.jpa.repositories.ItemRepository{
	
	public Item findById(@Param("search") Long id);
	public Item findByItemIdentifier(@Param("search") String itemIdentifier);
	
	@Query("SELECT b FROM Item b WHERE b.material=(:material)")
	public List<Item> findByMaterial(@Param("material") Material material);
	
	@Query("SELECT b FROM Item b WHERE b.itemStatus IN (:itemStatusList)")
    public List<Item> findByItemStatusList(@Param("itemStatusList") List<ItemStatus> itemStatusList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.itemStatus = :itemStatus")
    public List<Item> findByItemStatus(@Param("itemStatus") ItemStatus itemStatus, Pageable pageable);
	
	
	@Query("SELECT b FROM Item b WHERE b.material=(:material) AND b.itemCategory=(:itemCategory)")
	public List<Item> findByMaterialAndItemCategory(@Param("material") Material material, @Param("itemCategory") ItemCategory itemCategory);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatron(@Param("patron") Patron patron, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemStatus IN (:itemStatusList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemStatus(@Param("patron") Patron patron, @Param("itemStatusList") List<ItemStatus> itemStatusList, Pageable pageable);
		
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemCategory IN (:itemCategoryList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemCategory(@Param("patron") Patron patron, @Param("itemCategoryList") List<ItemCategory> itemCategoryList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemStatus IN (:itemStatusList) AND b.itemCategory IN (:itemCategoryList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemStatusAndItemCategory(@Param("patron") Patron patron, @Param("itemStatusList") List<ItemStatus> itemStatusList, @Param("itemCategoryList") List<ItemCategory> itemCategoryList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.smd IN (:smdList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndSmd(@Param("patron") Patron patron, @Param("smdList") List<SMD> smdList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemStatus IN (:itemStatusList) AND b.smd IN (:smdList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemStatusAndSmd(@Param("patron") Patron patron, @Param("itemStatusList") List<ItemStatus> itemStatusList, @Param("smdList") List<SMD> smdList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.location IN (:locationList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndLocation(@Param("patron") Patron patron, @Param("locationList") List<Location> locationList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemStatus IN (:itemStatusList) AND b.location IN (:locationList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemStatusAndLocation(@Param("patron") Patron patron, @Param("itemStatusList") List<ItemStatus> itemStatusList, @Param("locationList") List<Location> locationList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemCategory IN (:itemCategoryList) AND b.smd IN (:smdList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemCategoryAndSmd(@Param("patron") Patron patron, @Param("itemCategoryList") List<ItemCategory> itemCategoryList, @Param("smdList") List<SMD> smdList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemStatus IN (:itemStatusList) AND b.itemCategory IN (:itemCategoryList) AND b.smd IN (:smdList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemStatusAndItemCategoryAndSmd(@Param("patron") Patron patron, @Param("itemStatusList") List<ItemStatus> itemStatusList, @Param("itemCategoryList") List<ItemCategory> itemCategoryList, @Param("smdList") List<SMD> smdList, Pageable pageable);
		
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemCategory IN (:itemCategoryList) AND b.smd IN (:smdList) AND b.location IN (:locationList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemCategoryAndSmdAndLocation(@Param("patron") Patron patron, @Param("itemCategoryList") List<ItemCategory> itemCategoryList, @Param("smdList") List<SMD> smdList, @Param("locationList") List<Location> locationList, Pageable pageable);
	
	@Query("SELECT b FROM Item b WHERE b.patron=(:patron) AND b.itemStatus IN (:itemStatusList) AND b.itemCategory IN (:itemCategoryList) AND b.smd IN (:smdList) AND b.location IN (:locationList) ORDER BY b.chargeDateTime DESC")
    public List<Item> findByPatronAndItemStatusAndItemCategoryAndSmdAndLocation(@Param("patron") Patron patron, @Param("itemStatusList") List<ItemStatus> itemStatusList, @Param("itemCategoryList") List<ItemCategory> itemCategoryList, @Param("smdList") List<SMD> smdList, @Param("locationList") List<Location> locationList, Pageable pageable);
		
	/*-
	@Query("SELECT d FROM Ctdocm d, Glpatritemelig e, Glloca l WHERE d.ct03docno=(:item) AND e.gl27cate=(:glcate) AND e.gl27icat=d.ct03icat AND e.gl27smd=d.ct03smd AND l.gl05loca=d.ct03loca AND e.gl27brnc=l.gl05brnc AND (e.gl27allowrsv='Y' OR e.gl27allowrsv='Y')")
    public List<Ctdocm> findForAllowReserveByGlpatritemeligGlloca(@Param("item") Ctdocm item, @Param("glcate") Glcate glcate);
	*/
}