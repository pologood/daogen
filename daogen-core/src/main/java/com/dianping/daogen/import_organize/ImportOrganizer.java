package com.dianping.daogen.import_organize;

import com.dianping.daogen.model.java.Clazz;
import com.dianping.daogen.model.java.Type;
import com.dianping.daogen.utils.ObjectTraversal;
import com.dianping.daogen.utils.ObjectVisitor;

/**
 * @author code4crafer@gmail.com
 */
public abstract class ImportOrganizer {

    public static void organizeImports(Clazz clazz) {
        ImportOrganizerVisitor organizerVisitor = new ImportOrganizerVisitor(clazz.getImports());
        new ObjectTraversal(organizerVisitor).traverse("", clazz);
    }

    private static class ImportOrganizerVisitor implements ObjectVisitor {

        private Imports imports;

        private ImportOrganizerVisitor(Imports imports) {
            this.imports = imports;
        }

        @Override
        public void visit(String name, Object object) {
            if (object instanceof Type) {
                imports.merge(((Type) object).getTypeOriginName());
            }
        }
    }

}