package com.jt.aop;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.jt.anno.Cache_ItemCat;
import com.jt.json.ObjectJsonUtil;
import com.jt.mapper.ItemCatMapper;
import com.jt.vo.EasyUITree;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

@Aspect
@Component
public class ItemCatAop {

//	@Autowired(required = false)
//	private ShardedJedis jedis;

//	@Autowired(required = false)
//	private Jedis jedis;
	
	@Autowired(required = false)
	private JedisCluster jedis;
	
	@Around("@annotation(cacheItemCat)")
	public Object findItemCatAll(ProceedingJoinPoint jp,Cache_ItemCat cacheItemCat) {

		//		Class<ItemCatController> clz = ItemCatController.class;
		//
		//		ItemCatController instance = clz.newInstance();
		//
		//		Method[] methods = clz.getMethods();
		//		for (Method method : methods) {
		//			System.out.println(methods);
		//		}
		//
		//		Method method = clz.getMethod("findItemCatAll", Long.class);
		//
		//		Annotation[] annotations = method.getAnnotations();
		//
		//		for (Annotation annotation : annotations) {
		//
		//			System.out.println(annotation);
		//		}

		
		String key = getKey(jp,cacheItemCat);
		
		String result = jedis.get(key);
		
		List<EasyUITree> easyUITree = new ArrayList<>();
		
		try {
			
			if(StringUtils.isEmpty(result)) {
				
				easyUITree	= (List<EasyUITree>) jp.proceed();
				
				if(cacheItemCat.sencods() == 0) {
					
					jedis.set(key, ObjectJsonUtil.toJSON(easyUITree));
				}else {
					jedis.setex(key, cacheItemCat.sencods(), ObjectJsonUtil.toJSON(easyUITree));
				}
			}else {
				
				 easyUITree = ObjectJsonUtil.toObject(result, easyUITree.getClass());
				
			}
			
		} catch (Throwable e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
			
		} 
		
		return easyUITree;
	}
//		
//		String key = getKey(jp,cacheItemCat);
//
//		String result = jedis.get(key);
//
//		List<EasyUITree> easyUITree = new ArrayList<>();
//
//		if(StringUtils.isEmpty(result)) {
//
//			try {
//
//				easyUITree = (List<EasyUITree>)jp.proceed();
//
//				
//				
//				if(cacheItemCat.sencods() == 0) {
//					
//					jedis.set(key, ObjectJsonUtil.toJSON(easyUITree));
//				}else {
//					jedis.setex(key, 7*24*3600, ObjectJsonUtil.toJSON(easyUITree));
//				}
//				System.out.println("从AOP的数据库取数据");
//				return easyUITree;
//
//			} catch (Throwable e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//				throw new RuntimeException();
//			}
//
//		}else {
//
//			easyUITree = ObjectJsonUtil.toObject(result, easyUITree.getClass());
//			System.out.println("从AOP的缓存库取数据");
//		}
//
//		return easyUITree;


	private String getKey(ProceedingJoinPoint jp, Cache_ItemCat cacheItemCat) {
		// TODO Auto-generated method stub
		
		String key = cacheItemCat.key();
		
		if(!StringUtils.isEmpty(key)) {
			
			return key;
		}else {
			
			Signature signature = jp.getSignature();
			
			String className = signature.getDeclaringTypeName();
			
			String methodName = signature.getName();
			
			Object[] args = jp.getArgs();
			
			key = className + methodName + args[0];
			
		}
		
		return key;
	}

	
}

















