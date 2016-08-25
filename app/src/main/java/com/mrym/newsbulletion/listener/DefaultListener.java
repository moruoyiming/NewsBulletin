package com.mrym.newsbulletion.listener;

/**
 * 默认结果监听
 *
 * @author w
 */
public interface DefaultListener {
	/**
	 * 成功时调用
	 */
	public void onSuccess();

	/**
	 * 失败时调用
	 * @param errMsg 错误信息
	 */
	public void onError(String errMsg);
}
