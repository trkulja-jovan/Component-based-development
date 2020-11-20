package beans;

import java.util.HashMap;

import javax.ejb.Remote;

@Remote
public interface ReviewNumberBeanRemote {
	public HashMap<Integer, Integer> getMapReviews();
	public void updateMap(Integer key, Integer value);
}
