package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Material;
import my.com.myriadeas.integral.data.jpa.domain.Patron;
import my.com.myriadeas.integral.data.jpa.domain.ReservationTransaction;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ReservationTransactionRepositoryImpl extends my.com.myriadeas.integral.data.jpa.repositories.ReservationTransactionRepository{
	public ReservationTransaction findById(@Param("search") Long id);
	
	@Query("SELECT DISTINCT b FROM ReservationTransaction b WHERE b.status=(:status) AND b.material=(:material) ORDER BY b.priorityWeight")
	public List<ReservationTransaction> findByStatusAndMaterialOrderByPriorityWeight(@Param("status") String status, @Param("material") Material material);
	
    @Query("SELECT b FROM ReservationTransaction b WHERE b.material=(:material) ORDER BY b.priorityWeight")
	public List<ReservationTransaction> findByMaterialOrderByPriorityWeight(@Param("material") Material material);
	
    @Query("SELECT b FROM ReservationTransaction b WHERE b.status=(:status) AND b.item=(:item)")
	public List<ReservationTransaction> findByStatusAndItem(@Param("status") String status, @Param("item") Item item);
	
    @Query("SELECT b FROM ReservationTransaction b WHERE b.item=(:item)")
	public List<ReservationTransaction> findByItem(@Param("item") Item item);
    
    @Query("SELECT b FROM ReservationTransaction b WHERE b.item=(:item) ORDER BY b.priorityWeight")
	public List<ReservationTransaction> findByItemOrderByPriorityWeight(@Param("item") Item item);
	
    @Query("SELECT b FROM ReservationTransaction b WHERE b.patron=(:patron)")
	public List<ReservationTransaction> findByPatron(@Param("patron") Patron patron);
    
    @Modifying(clearAutomatically=true) 
	@Transactional
	@Query("UPDATE ReservationTransaction b SET b.priorityWeight=(b.priorityWeight - 1) WHERE b.material=(:material) AND b.priorityWeight>(:priorityWeight)")
    public void decreasePriorityByOneForPriorityMoreThan(@Param("material") Material material, @Param("priorityWeight") int priorityWeight);
	    
    @Modifying(clearAutomatically=true) 
	@Transactional
	@Query("UPDATE ReservationTransaction b SET b.priorityWeight=(b.priorityWeight + 1) WHERE b.material=(:material) AND b.priorityWeight>=(:priorityWeight)")
    public void increasePriorityByOneForPriorityMoreThanEquals(@Param("material") Material material, @Param("priorityWeight") int priorityWeight);
	
    @Query("SELECT MAX(b.priorityWeight) FROM ReservationTransaction b WHERE b.material=(:material)")
    public Integer findMaxPriorityWeightByMaterial(@Param("material") Material material);

    
    
}