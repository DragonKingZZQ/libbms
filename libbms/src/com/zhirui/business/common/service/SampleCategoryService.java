package com.zhirui.business.common.service;

import java.util.List;

import com.zhirui.business.common.bean.Qualification;
import com.zhirui.business.common.bean.SampleCategory;

/**
 * @author zy
 */
public interface SampleCategoryService {

	public List<SampleCategory> getAll();

	SampleCategory getSampleCategory(int id);
}
