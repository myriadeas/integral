package my.com.myriadeas.integral.data.jpa.repositories.impl;

import java.util.Date;
import java.util.List;

import my.com.myriadeas.integral.data.jpa.domain.CirculationTransaction;
import my.com.myriadeas.integral.data.jpa.domain.Item;
import my.com.myriadeas.integral.data.jpa.domain.Officer;
import my.com.myriadeas.integral.data.jpa.domain.Patron;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CirculationTransactionRepositoryImpl extends my.com.myriadeas.integral.data.jpa.repositories.CirculationTransactionRepository{
	public CirculationTransaction findById(@Param("search") Long id);
	
	@Query("SELECT b FROM CirculationTransaction b WHERE b.patron=(:patron) AND b.item=(:item) AND b.flag=(:flag)")
	public List<CirculationTransaction> findByPatronAndItemAndFlag(@Param("patron") Patron patron, @Param("item") Item item, @Param("flag") String flag);
	
	@Query("SELECT b FROM CirculationTransaction b WHERE b.patron=(:patron) AND b.flag=(:flag)")
	public List<CirculationTransaction> findByPatronAndFlag(@Param("patron") Patron patron, @Param("flag") String flag);
	
	@Query("SELECT b FROM CirculationTransaction b WHERE b.patron=(:patron)")
	public List<CirculationTransaction> findByPatron(@Param("patron") Patron patron);
	
	@Query("SELECT b FROM CirculationTransaction b WHERE b.dischargeDateTime=(:dischargeDateTime) AND b.flag=(:flag) AND b.patron=(:patron) AND b.dischargeOfficer=(:dischargeOfficer)")
	public CirculationTransaction findByDischargeDateTimeAndFlagAndPatronAndDischargeOfficer(@Param("dischargeDateTime") Date dischargeDateTime, @Param("flag") String flag, @Param("patron") Patron patron, @Param("dischargeOfficer") Officer dischargeOfficer);
	
	@Query("SELECT b FROM CirculationTransaction b WHERE b.dischargeDateTime=(:dischargeDateTime) AND b.flag=(:flag) AND b.patron=(:patron)")
	public CirculationTransaction findByDischargeDateTimeAndFlagAndPatron(@Param("dischargeDateTime") Date dischargeDateTime, @Param("flag") String flag, @Param("patron") Patron patron);
	
	@Query("SELECT b FROM CirculationTransaction b WHERE b.item=(:item) AND b.flag=(:flag)")
	public List<CirculationTransaction> findByItemAndFlag(@Param("item") Item item, @Param("flag") String flag);
	
	@Query("SELECT b FROM CirculationTransaction b WHERE b.item=(:item)")
	public List<CirculationTransaction> findByItem(@Param("item") Item item);
	
}