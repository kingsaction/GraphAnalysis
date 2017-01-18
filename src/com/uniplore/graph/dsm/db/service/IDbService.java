package com.uniplore.graph.dsm.db.service;

import com.uniplore.graph.dsm.db.entity.DbPO;

public interface IDbService {

	public String connectDataBase(DbPO dbPO)throws Exception;
}
