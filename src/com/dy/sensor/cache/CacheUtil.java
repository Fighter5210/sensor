package com.dy.sensor.cache;

public class CacheUtil {
	private static Cache sysMenuCache;
	private String sysMenuCacheName;
	CacheFactory cacheFactory;

	public void init() {
		
			try {
				if (this.sysMenuCacheName != null)
				sysMenuCache = this.cacheFactory.buildCache(this.sysMenuCacheName);
			} catch (CacheException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public static Cache getSysMenuCache() {
		return sysMenuCache;
	}

	public static void setSysMenuCache(Cache sysMenuCache) {
		CacheUtil.sysMenuCache = sysMenuCache;
	}


	public String getSysMenuCacheName() {
		return sysMenuCacheName;
	}

	public void setSysMenuCacheName(String sysMenuCacheName) {
		this.sysMenuCacheName = sysMenuCacheName;
	}

	public CacheFactory getCacheFactory() {
		return cacheFactory;
	}

	public void setCacheFactory(CacheFactory cacheFactory) {
		this.cacheFactory = cacheFactory;
	}

}
