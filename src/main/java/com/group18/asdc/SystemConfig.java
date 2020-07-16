package com.group18.asdc;

import com.group18.asdc.dao.DaoAbstractFactory;
import com.group18.asdc.dao.DaoAbstractFactoryImpl;
import com.group18.asdc.entities.ModelAbstractFactory;
import com.group18.asdc.entities.ModelAbstractFactoryImpl;
import com.group18.asdc.service.ServiceAbstractFactory;
import com.group18.asdc.service.ServiceAbstractFactoryImpl;
import com.group18.asdc.util.UtilAbstractFactory;
import com.group18.asdc.util.UtilAbstractFactoryImpl;

public class SystemConfig {

	private static SystemConfig singleinstance = null;
	private ModelAbstractFactory modelAbstractFactory;
	private ServiceAbstractFactory serviceAbstractFactory;
	private DaoAbstractFactory daoAbstractFactory;
	private UtilAbstractFactory utilAbstractFactory;

	private SystemConfig() {

		this.modelAbstractFactory = new ModelAbstractFactoryImpl();
		this.daoAbstractFactory = new DaoAbstractFactoryImpl();
		this.serviceAbstractFactory = new ServiceAbstractFactoryImpl();
		this.utilAbstractFactory = new UtilAbstractFactoryImpl();
	}

	public static SystemConfig getSingletonInstance() {
		if (null == singleinstance) {
			singleinstance = new SystemConfig();
			return singleinstance;
		} else {
			return singleinstance;
		}
	}

	public ModelAbstractFactory getModelAbstractFactory() {
		return modelAbstractFactory;
	}

	public void setModelAbstractFactory(ModelAbstractFactory modelAbstractFactory) {
		this.modelAbstractFactory = modelAbstractFactory;
	}

	public ServiceAbstractFactory getServiceAbstractFactory() {
		return serviceAbstractFactory;
	}

	public void setServiceAbstractFactory(ServiceAbstractFactory serviceAbstractFactory) {
		this.serviceAbstractFactory = serviceAbstractFactory;
	}

	public DaoAbstractFactory getDaoAbstractFactory() {
		return daoAbstractFactory;
	}

	public void setDaoAbstractFactory(DaoAbstractFactory daoAbstractFactory) {
		this.daoAbstractFactory = daoAbstractFactory;
	}

	public UtilAbstractFactory getUtilAbstractFactory()
	{
		return this.utilAbstractFactory;
	}

	public void setUtilAbstractFactory(UtilAbstractFactory utilAbstractFactory)
	{
		this.utilAbstractFactory = utilAbstractFactory;
	}
}
