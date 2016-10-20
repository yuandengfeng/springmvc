/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.util;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class MapSet {
    
    private HashMap<String,HashSet<String>> maps = new HashMap<String, HashSet<String>>();
	public void put(String key, String value) {
		
		if (!maps.containsKey(key)) {
			maps.put(key, new HashSet<String>());
		}
		maps.get(key).add(value);
	}
	public Set<String> get(String key){
		if(maps.containsKey(key)){
			return maps.get(key);
		}else{
			return new HashSet<String>();
		}
	}
	
	public Set<String> keySet(){
		return maps.keySet();
	}
	
   public void remove(Object key){
		   maps.remove(key);
   }
  
//     public static void main(String[] args) {
//    	 MapSet m = new MapSet();
//    	 m.put("liu","2" );
//    	 m.put("liu", "2");
//    	 m.put("liu", "1");
//    	 m.put("liu", "1");
//    	 System.out.println(m.get("liu").size());
//	}
}
