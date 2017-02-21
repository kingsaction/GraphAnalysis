package com.uniplore.graph.dsm.db.service;

import com.uniplore.graph.dsm.db.entity.DbPO;
import java.util.List;

public interface IDbService {

  public String connectDataBase(DbPO dbPo)throws Exception;

  public List<String> showDataBase(DbPO dbPo)throws Exception;
}
