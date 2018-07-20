package com.baidu.dsp.common.vo;

import java.util.HashMap;
import java.util.Map;

import com.baidu.dsp.common.constant.FrontEndInterfaceConstant;

import lombok.Data;

/**
 * 多层结构的 成功返回
 *
 * @author liaoqiqi
 * @version 2013-12-3
 */
@Data
public class JsonObject extends JsonObjectBase {

	/**
	 *
	 */
	private static final long serialVersionUID = -7115209443980058705L;

	private Map<String, Object> result = new HashMap<String, Object>();

	public JsonObject() {
		super();
		success = FrontEndInterfaceConstant.RETURN_OK;
	}

	public void addData(String key, Object value) {
		result.put(key, value);
	}

	public Map<String, Object> getResult() {
		return result;
	}

	public void setResult(Map<String, Object> result) {
		this.result = result;
	}

	public int hashCode() {
		int PRIME = 59;
		int result = 1;
		Object $result = getResult();
		result = result * PRIME + ($result == null ? 0 : $result.hashCode());
		return result;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof JsonObject))
			return false;
		JsonObject other = (JsonObject) o;
		if (!other.canEqual(this))
			return false;
		Object thisResult = getResult();
		Object otherResult = other.getResult();
		return thisResult == null ? otherResult == null : thisResult.equals(otherResult);
	}

	protected boolean canEqual(Object other) {
		return other instanceof JsonObject;
	}

	@Override
	public String toString() {
		return "JsonObject [result=" + result + "]";
	}

}
