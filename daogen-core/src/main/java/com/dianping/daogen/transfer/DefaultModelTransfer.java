package com.dianping.daogen.transfer;

import com.dianping.daogen.schema.db.Column;
import com.dianping.daogen.schema.db.Table;
import com.dianping.daogen.schema.java.Field;
import com.dianping.daogen.schema.java.Model;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author code4crafer@gmail.com
 */
public class DefaultModelTransfer implements ModelTransfer {

    @Setter
    @Getter
    private boolean removePrefix = true;

    @Setter
    @Getter
    private FieldTransfer fieldTransfer;

    @Override
    public Model transfer(Table table) {
        String tableName = table.getName();
        if (removePrefix) {
            tableName = StringUtils.removePattern(table.getName(), "[A-Za-z]+_");
        }
        List<Field> fieldList = new ArrayList<Field>();
        for (Column column : table.getColumns()) {
            Field field = fieldTransfer.transfer(column);
            if (field != null) {
                fieldList.add(field);
            }
        }
        tableName = StringUtils.capitalize(tableName);
        return new Model(tableName, fieldList);
    }
}
