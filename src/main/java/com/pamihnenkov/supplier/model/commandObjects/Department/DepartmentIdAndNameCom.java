package com.pamihnenkov.supplier.model.commandObjects.Department;

import com.pamihnenkov.supplier.model.Department;
import com.pamihnenkov.supplier.security.ApplicationUser.ApplicationUser;

public class DepartmentIdAndNameCom {


        private final Long id;
        private final String name;
        private final Long organizationId;

        public DepartmentIdAndNameCom(Department department) {
            this.id = department.getId();
            this.name = department.getName();
            this.organizationId = department.getOrganization().getId();
        }

        public Long getId() {
            return id;
        }

    public String getName() {
        return name;
    }

    public Long getOrganizationId() {
        return organizationId;
    }
}