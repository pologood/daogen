package com.dianping.daogen.import_organize;

import com.dianping.daogen.java.model.Dao;
import com.dianping.daogen.java.model.DaoMethod;
import com.dianping.daogen.java.model.Type;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author code4crafer@gmail.com
 */
public class ImportOrganizerTest {

    @Test
    public void testImportOrganize() throws Exception {
        Dao dao = new Dao();
        List<DaoMethod> daoMethods = new ArrayList<DaoMethod>();
        DaoMethod daoMethod = new DaoMethod();
        Type entity = new Type();
        entity.setTypeOriginName("com.dianping.daogen.Entity1");
        daoMethod.setEntity(entity);
        List<DaoMethod.Param> params = new ArrayList<DaoMethod.Param>();
        DaoMethod.Param param = new DaoMethod.Param();
        param.setType(new Type("java.util.Date"));
        params.add(param);
        daoMethod.setParams(params);
        daoMethods.add(daoMethod);
        dao.setMethods(daoMethods);
        Imports imports = new Imports();
        ImportOrganizer.organizeImports(dao, imports);
        assertThat(imports.getImports()).contains("com.dianping.daogen.Entity1");
        assertThat(imports.getImports()).contains("java.util.Date");
    }
}
