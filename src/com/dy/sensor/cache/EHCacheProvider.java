package com.dy.sensor.cache;

/*    */ 
/*    */ public class EHCacheProvider
/*    */   implements CacheProvider
/*    */ {
/*    */   public Cache buildCache(String regionName)
/*    */     throws CacheException
/*    */   {
/* 23 */     return new EHCache(regionName);
/*    */   }
/*    */ }
