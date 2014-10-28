package my.com.myriadeas.integral.assetmanager.domain.model;


public class ItemRepositoryStub {
	
	private int count = 0;

	public long count() {
		return count;
	}

	public void save(Item item){
		
		count++;
	}

	
}
