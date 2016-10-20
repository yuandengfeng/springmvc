/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tool.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Administrator
 */
public class MapInteger {
    
     private Map<String, Integer> map = new HashMap<String, Integer>();  
     public void put(String key) {
	   if (map.containsKey(key)) {  
              map.put(key, map.get(key) + 1);  
            } else {  
              map.put(key, 1);  
            }  
	}  
     public int get(String key){
		if(map.containsKey(key)){
			return map.get(key);
		}else{
			return 0;
		}
	}
    
}
