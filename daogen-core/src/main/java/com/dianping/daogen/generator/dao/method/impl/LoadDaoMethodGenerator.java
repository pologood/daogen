package com.dianping.daogen.generator.dao.method.impl;

import com.dianping.daogen.generator.GeneratorContext;
import com.dianping.daogen.db.model.Column;
import com.dianping.daogen.java.model.DaoMethod;
import com.dianping.daogen.java.model.Type;
import com.dianping.daogen.java.model.Field;
import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

/**
 * @author code4crafer@gmail.com
 */
public class LoadDaoMethodGenerator extends AbstractDaoMethodGenerator {

    @Override
    protected Type getReturnType(GeneratorContext generatorContext) {
        return generatorContext.getEntity();
    }

    @Override
    protected List<DaoMethod.Param> getMethodParams(GeneratorContext generatorContext) {
        DaoMethod.Param param = new DaoMethod.Param();
        Column primaryKey = getPrimaryColumn(generatorContext);
        Field primaryField = getPrimaryField(generatorContext);
        param.setName(StringUtils.uncapitalize(primaryField.getName()));
        param.setType(primaryField);
        param.setCondition(true);
        param.setColumn(primaryKey);
        return Collections.singletonList(param);
    }


    @Override
    protected String getMethodType(GeneratorContext generatorContext) {
        return DaoMethod.Type.LOAD;
    }

    @Override
    public String getMethodName() {
        return "loadById";
    }
}
