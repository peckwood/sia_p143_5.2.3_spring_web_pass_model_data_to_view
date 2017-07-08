package spittr.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import spittr.Spittle;
@Repository
public class MemorySpittleRepository implements SpittleRepository{
	List<Spittle> expectedSpittles = createSpittleList(20);
	
	@Override
	public List<Spittle> findSpittles(long max, int count) {
		if(count>20){
			count=20;
		}
		if(max<count){
			count = (int)max;
		}
		return expectedSpittles.subList(0, count-1);
	}
	
	private List<Spittle> createSpittleList(int count) {
		List<Spittle> spittles = new ArrayList<Spittle>();
		for (int i = 0; i < count; i++) {
			spittles.add(new Spittle("message", new Date(), 1.0, 2.0));
			
		}
		return spittles;
	}
	
}
