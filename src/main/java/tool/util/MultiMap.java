/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.util;

/**
 *
 * @author Administrator
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class MultiMap {

	private HashMap<String, HashMap<String,String>> maps = new HashMap<String,HashMap<String,String>>();
//����put���� 
	public void put(String key1, String key2,String value) {
		if (!maps.containsKey(key1)) {
			maps.put(key1,new HashMap<String,String>());
		}
		maps.get(key1).put(key2, value);
	}
	public HashMap<String,String> get(String key){
		if(maps.containsKey(key)){
			return maps.get(key);
		}else{
			return new HashMap<String,String>();
		}
	}
	
	public Set<String> keySet(){
		return maps.keySet();
	}
	
   public void remove(Object key){
		   maps.remove(key);
   }
     public static void main(String[] args) {
//    	 MultiMap m = new MultiMap();
//    	 m.put("liu", );
//    	 m.put("liu", 1);
//    	 m.put("liu", 1);
//    	 m.put("liu", 1);
//    	 System.out.println(m.get("liu"));
	}

//    void put(String key1, MultiMap m) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
}
