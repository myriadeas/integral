package my.com.myriadeas.integral.assetmanagement.domain.model;

import my.com.myriadeas.integral.assetmanagement.domain.model.Item;


public class ItemRepositoryStub {
	
	private int count = 0;

	public long count() {
		return count;
	}

	public void save(Item item){
		
		count++;
	}

	
}
